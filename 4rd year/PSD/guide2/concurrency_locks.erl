-module(concurrency_locks).

% released() ->
%     receive
%         {read, Pid} ->
%             Pid ! acquired,
%             reading([Pid]);
%         {write, Pid} ->
%             Pid ! acquired,
%             writing(Pid)
%     end.

% reading([Pid]) -> released();
% reading(Readers) ->
%     receive
%         {read, Pid} ->
%             Pid ! acquired,
%             reading([Pid | Readers]);
%         {release, Pid} ->
%             reading(Readers -- [Pid])
%     end.

% writing(Pid) ->
%     receive
%         {release, Pid} -> released()
%     end.
%
% EXPLANATION: This implementation is very writer starvation prone. As you can
% understand by analysing the code, when there are several readers with the
% acquired lock it gets very though for a writer to acquired the lock (since it
% will only be able to get it once there are no readers trying to acquire the
% lock). Although, we can fix the issue in a simple manner: limiting the amount
% of readers that are to acquire the lock once a writer tries to acquire. This
% is going to be the approach: once a writer tries to acquire the lock we will
% prohibit new readers from acquiring the lock until the writers desire to
% acquire the lock is met. The code will be as follows:

% Starts the process with no locks acquired.
start() ->
    spawn(fun released/0).

% Process acquiring the lock
acquire(Pid, Mode) when Mode == read; Mode == write ->
    Pid ! {Mode, self()},
    receive
        acquired -> true
    end.

% A process releasing the lock.
release(Pid) ->
    Pid ! {release, self()}.

released() ->
    receive
        {read, Pid} ->
            Pid ! acquired,
            reading([Pid]);
        {write, Pid} ->
            Pid ! acquired,
            writing(Pid)
    end.

reading([]) -> released();
reading(Readers) ->
    receive
        {read, Pid} ->
            Pid ! acquired,
            reading([Pid | Readers]);
        {release, Pid} ->
            reading(Readers -- [Pid]);
        {write, Pid} ->
            reading(Readers, Pid)
    end.

reading([], Writer) ->
    Writer ! acquired,
    writing(Writer);

reading(Readers, Writer) ->
    receive
        {release, Pid} -> reading(Readers -- [Pid], Writer)
    end.

writing(Pid) ->
    receive
        {release, Pid} -> released()
    end.

