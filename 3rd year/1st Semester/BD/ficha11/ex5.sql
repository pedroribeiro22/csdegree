use Caderneta;

create trigger alinea5 after update on Cromo for each row
    begin
        if NEW.Adquirido = 'N' then
            delete from ListaFaltas where Nr = NEW.Nr;
            update Cromo set NEW.Adquirido = 'S';
        end if;
    end