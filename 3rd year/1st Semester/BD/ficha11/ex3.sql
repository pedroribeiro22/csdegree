set @@æutomcommit = 0;
drop procedure if exists adquireCromo;

create procedure adquireCromo (in n integer)
begin
    start transaction;
        delete from ListaFaltas where idCromo = n;
        update Cromo set Adquirido = 'S' where Nr = n;
        delimiter $$
        declare exit handler for sqlexception
            begin
                rollback;
            end;
    commit;
end

-- declare exit handler for sqlexception
--    begin
--        rollback;
--        exit procedure;
--    end


