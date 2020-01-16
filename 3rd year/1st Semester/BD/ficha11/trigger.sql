drop trigger alinea_8;

delimiter |
create trigger alinea_8 after insert on Cromo
    for each row
    begin
        if New.Adquirido = 'S' then
            insert into `Caderneta`.`audCromos`(`data_registo`, `cromo`) values (sysdate(), NEW.Nr);
        end if;
    end;
|
