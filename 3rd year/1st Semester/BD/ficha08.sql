use  Clinica;

-- retorna número de médicos que existem na base de dados
-- create procedure medicos_counter(out numero_medicos int)
-- begin
--    declare medicos_count int default 0;
--    set medicos_count = (select count(id_medico) from MEDICO);
-- end;

-- a)
DELIMITER //
create procedure update_speciality_price(in year int, in percentage double)
begin
    declare average int default 0;
    declare done int default false;
    declare espec varchar(250);

    select distinct designacao, (sum(CONSULTA.preco) / count(CONSULTA.preco)) from ESPECIALIDADE
    join MEDICO on MEDICO.especialidade = ESPECIALIDADE.id_especialidade
    join CONSULTA on CONSULTA.id_medico = MEDICO.id_medico
    group by designacao;

    declare continue handler for not found set done = true;
        open curl;
            read_loop: LOOP
            fetch curl into average, espec;
            if done then
                leave read_loop;
            end if //
                update ESPECIALIDADE set preco = average;
            where espec = Clinica.Especialidade.id_especialidade;
            end loop //
    close curl;
end //
DELIMITER ;

drop procedure update_speciality_price;

call update_speciality_price(0, 0);

-- b)
