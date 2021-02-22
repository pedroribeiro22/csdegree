-module(login_manager).

-export([
    loop/1,
    start/0,
    create_account/2,
    close_account/2,
    login/2,
    logout/1,
    online/0]).

start() -> register(?MODULE, spawn(fun() -> loop(#{}) end)).

% Abstract implementation of remote procedure call to the ?MODULE process that
% requires *Username* and *Password* authentication. If the specific operation
% that one wants to perform does not require one of the specified fields, some
% other value might be used. As you can see in the main loop I used "undefined"
% when the fields are not required.

remote_procedure_call(Procedure, Username, Password) ->
    ?MODULE ! {Procedure, Username, Password, self()},
    receive
        {Result, ?MODULE} ->
            Result
    end.

create_account(Username, Password) ->
    remote_procedure_call(create_account, Username, Password).

close_account(Username, Password) ->
    remote_procedure_call(close_account, Username, Password).

login(Username, Password) ->
    remote_procedure_call(login, Username, Password).

logout(Username) ->
    remote_procedure_call(logout, Username, undefined).

online() ->
    remote_procedure_call(online, undefined, undefined).

% Create Account operation handler: firstly we have to check wether there is
% already another account registered with the specified username. If there isn't
% the account shall be created.

create_account_helper(Accounts, Username, Password, From) ->
    case maps:find(Username, Accounts) of
        {ok, {_, _}} ->
            From ! {user_exists, ?MODULE},
            loop(Accounts);
        error ->
            UpdatedMap = maps:put(Username, {Password, 0}, Accounts),
            From ! {ok, ?MODULE},
            loop(UpdatedMap)
    end.

% Close Account operation handler: the correct password for the specified
% profile has to be provided in order to sucessfuly accomplish the operation.

close_account_helper(Accounts, Username, Password, From) ->
    case maps:find(Username, Accounts) of
        {ok, {Password, _}} ->
            UpdatedMap = maps:remove(Username, Accounts),
            From ! {ok, ?MODULE},
            loop(UpdatedMap);
        _ ->
            From ! {invalid, ?MODULE},
            loop(Accounts)
    end.

% Login Operation handler: a valid {Username, Password} combination has to be
% provided in order to sucessfuly complete the operation.

login_helper(Accounts, Username, Password, From) ->
    case maps:find(Username, Accounts) of
        {ok, {Password, _}} ->
            UpdatedMap = maps:update(Username, {Password, 1}, Accounts),
            From ! {ok, ?MODULE},
            loop(UpdatedMap);
        _ ->
            From ! {invalid, ?MODULE},
            loop(Accounts)
    end.

% Logout Operation handler: In order to perform the operation the specified
% user has to be logged in. If the user isn't logged in the operation is
% considered `invalid`.

logout_helper(Accounts, Username, From) ->
    case maps:find(Username, Accounts) of
        {ok, {Password, 1}} ->
            UpdatedMap = maps:update(Username, {Password, 0}, Accounts),
            From ! {ok, ?MODULE},
            loop(UpdatedMap);
        _ ->
            From ! {invalid, ?MODULE},
            loop(Accounts)
    end.

% Online users listing operation handler: this function filters the accounts map
% choosing only the ones that are logged in. After that we just return the keys
% (usernames) of those users.

online_helper(Accounts, From) ->
    Pred = fun(_, {_, LoggedIn}) -> LoggedIn == 1 end,
    FilteredMap = maps:filter(Pred, Accounts),
    Usernames = maps:keys(FilteredMap),
    From ! {Usernames, ?MODULE},
    loop(Accounts).


% Main Loop: Always listenning to messages of the described types.
% Any other type of message shall not be picked up from the mailbox.

loop(Accounts) ->

    receive

        {create_account, Username, Password, From} ->
            create_account_helper(Accounts, Username, Password, From);
        {close_account, Username, Password, From} ->
            close_account_helper(Accounts, Username, Password, From);
        {login, Username, Password, From} ->
            login_helper(Accounts, Username, Password, From);
        {logout, Username, undefined, From} ->
            logout_helper(Accounts, Username, From);
        {online, undefined, undefined, From} ->
            online_helper(Accounts, From)

    end.