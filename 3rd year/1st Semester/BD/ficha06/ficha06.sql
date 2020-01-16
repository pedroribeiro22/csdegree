use Clinica;

-- a)
select nome from MEDICO where YEAR(data_inicio_servico) <= 2009;

-- b)
select nome, designacao from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade;

-- c)
select nome, morada from PACIENTE
join CODIGO_POSTAL on CODIGO_POSTAL.codigo_postal = PACIENTE.codigo_postal
where CODIGO_POSTAL.localidade = 'BRAGA';

-- d)
select nome from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade
where ESPECIALIDADE.designacao = 'Oftalmologia';

-- e)
select nome, (2019 - year(data_nascimento)) from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade
where ESPECIALIDADE.designacao = 'Clinica Geral' and (2019 - year(data_nascimento) > 40);

-- f)
select distinct MEDICO.nome from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade
join CONSULTA on MEDICO.id_medico = CONSULTA.id_medico
join PACIENTE on PACIENTE.id_paciente = CONSULTA.id_paciente
join CODIGO_POSTAL on CODIGO_POSTAL.codigo_postal = PACIENTE.codigo_postal
where CODIGO_POSTAL.localidade = 'BRAGA' and ESPECIALIDADE.designacao = 'Oftalmologia';

-- g)
select distinct MEDICO.nome 'Médico', (2019 - year(data_inicio_servico)) 'Anos de Serviço' from MEDICO
join CONSULTA on CONSULTA.id_medico = MEDICO.id_medico
join PACIENTE on PACIENTE.id_paciente = CONSULTA.id_paciente
where (idade(PACIENTE.data_nascimento) < 20) and ((2019 - year(MEDICO.data_nascimento)) > 50) and (hour(data_hora) >= 12);

-- h)
select PACIENTE.nome, (2019 - year(PACIENTE.data_nascimento)) from PACIENTE
join CONSULTA on CONSULTA.id_paciente = PACIENTE.id_paciente
join MEDICO on  MEDICO.id_medico = CONSULTA.id_medico
join ESPECIALIDADE on (ESPECIALIDADE.id_especialidade = MEDICO.especialidade and ESPECIALIDADE.designacao != 'Oftalmologia')
where (2019 - year(PACIENTE.data_nascimento)) > 10;

-- i)
select distinct ESPECIALIDADE.designacao 'Especialidades' from ESPECIALIDADE
join MEDICO on MEDICO.especialidade = ESPECIALIDADE.id_especialidade
join CONSULTA on CONSULTA.id_medico = MEDICO.id_medico
where month(CONSULTA.data_hora) = 1 and year(CONSULTA.data_hora) = 2016;

-- j)
select distinct MEDICO.nome from MEDICO where (idade(MEDICO.data_nascimento) > 30 or idade(MEDICO.data_inicio_servico) < 5);

-- k)
-- ver com atenção
-- select distinct MEDICO.nome 'Nome', idade(MEDICO.data_nascimento) 'Idade' from MEDICO
-- join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade
-- join CONSULTA on CONSULTA.id_medico = MEDICO.id_medico
-- where ESPECIALIDADE.designacao = 'Clinica Geral' and not(year(CONSULTA.data_hora) = 2016) and not(month(CONSULTA.data_hora) = 1);

select MEDICO.id_medico, MEDICO.nome, year(MEDICO.data_nascimento) from MEDICO
join ESPECIALIDADE on MEDICO.especialidade = ESPECIALIDADE.id_especialidade
where ESPECIALIDADE.designacao = 'Clinica Geral'
and id_medico not in(
    select MEDICO.id_medico from MEDICO
    join CONSULTA on MEDICO.id_medico = CONSULTA.id_medico
    where year(CONSULTA.data_hora) = 2016 and month(CONSULTA.data_hora) = 1
    );

-- l)
select distinct PACIENTE.nome, MEDICO.nome, count(CONSULTA.id_medico) from PACIENTE
join CONSULTA on CONSULTA.id_paciente = PACIENTE.id_paciente
join MEDICO on MEDICO.id_medico = CONSULTA.id_medico
group by PACIENTE.nome, MEDICO.nome
having count(CONSULTA.id_medico) = (select distinct count(MEDICO.id_medico) from MEDICO);

-- m)
select distinct ESPECIALIDADE.designacao from ESPECIALIDADE
join MEDICO on MEDICO.especialidade = ESPECIALIDADE.id_especialidade
join CONSULTA on CONSULTA.id_medico = MEDICO.id_medico
where ((year(CONSULTA.data_hora) = 2016) and ((month(CONSULTA.data_hora) = 1) or (month(CONSULTA.data_hora) = 3)));

-- n)
