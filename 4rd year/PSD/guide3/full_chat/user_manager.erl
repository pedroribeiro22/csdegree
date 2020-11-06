-module(user_manager).
-export([up/0, loop/2]).

-import(login_manager, [start/0, login/2, logout/1, create_account/2, remove_account/2]).

up() ->
    LogInManagerPid = login_manager:start(),
    UserManagerPid = spawn(fun() -> loop(LogInManagerPid, []) end),
    register(?MODULE, UserManagerPid),
    UserManagerPid.

loop(LogInManagerPid, LoggedInUsers) ->

    receive
        {login, Username, Password, UserPid} ->
            LogInManagerPid ! {login, Username, Password, self()},
            receive
                {ok, _} ->
                    UpdateLoggedInUsers = [UserPid | LoggedInUsers],
                    io:format("Sucessful login!~n", []),
                    UserPid ! {ok, ?MODULE},
                    loop(LogInManagerPid, UpdateLoggedInUsers);
                _ ->
                    io:format("Unsuccessful login!~n", []),
                    UserPid ! {invalid, ?MODULE},
                    loop(LogInManagerPid, LoggedInUsers)
            end;
        {logout, Username, UserPid} ->
            LogInManagerPid ! {logout, Username, undefined, self()},
            receive
                {ok, _} ->
                    UpdatedLoggedInUsers = LoggedInUsers -- [UserPid],
                    io:format("Successful logout!~n", []),
                    UserPid ! {ok, ?MODULE},
                    loop(LogInManagerPid, UpdatedLoggedInUsers);
                _ ->
                    io:format("Unsuccessful logout!~n", []),
                    UserPid ! {invalid, ?MODULE},
                    loop(LogInManagerPid, LoggedInUsers)
            end;
        {register, Username, Password, UserPid} ->
            LogInManagerPid ! {create_account, Username, Password, self()},
            receive
                {ok, _} ->
                    io:format("Successful registry!~n", []),
                    UserPid ! {ok, ?MODULE},
                    loop(LogInManagerPid, LoggedInUsers);
                _ ->
                    io:format("Something went wrong! Probably the user already exists", []),
                    UserPid ! {invalid, ?MODULE},
                    loop(LogInManagerPid, LoggedInUsers)
            end;
        {delete, Username, Password, UserPid} ->
            LogInManagerPid ! {close_account, Username, Password, self()},
            receive
                {ok, _} ->
                    io:format("The account wras successfuly removed!~n", []),
                    UserPid ! {ok, ?MODULE},
                    loop(LogInManagerPid, LoggedInUsers);
                _ ->
                    io:format("The account credentials are wrong! The account was not removed!~n", []),
                    UserPid ! {invalid, ?MODULE},
                    loop(LogInManagerPid, LoggedInUsers)
            end
    end.        