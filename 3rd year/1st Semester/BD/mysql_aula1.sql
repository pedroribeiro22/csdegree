-- Projeção de todas as consultas
select * from Clinica.CONSULTA;

use Clinica;

-- Exercício 2
-- a)
select nome, data_inicio_servico from MEDICO where YEAR(data_inicio_servico) <= 2009;

-- b)
select nome, (select designacao from ESPECIALIDADE where id_especialidade = especialidade) from MEDICO;

select nome from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade;

-- c)
select  nome, morada from PACIENTE
join CODIGO_POSTAL on CODIGO_POSTAL.codigo_postal = PACIENTE.codigo_postal
where CODIGO_POSTAL.localidade = 'BRAGA';

-- d)
select nome from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.designacao = MEDICO.especialidade
where ESPECIALIDADE.designacao = 'Oftalmologia';

-- e)
select nome, (2019- year(MEDICO.data_nascimento) from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.designacao = MEDICO.especialidade
where ESPECIALIDADE.designacao = 'Clínica Geral' and (2019 - year(MEDICO.data_nascimento) > 40);

-- f) 
select distinct MEDICO.nome from MEDICO as DOCTOR     -- DISTINCT - Elimina ocorrências repetidas
join ESPECIALIDADE on ESPECIALIDADE.designacao = DOCTOR.designacao
join CONSULTA on DOCTOR.id_medico = CONSULTA.id_medico
join PACIENTE on PACIENTE.id_paciente = CONSULTA.id_paciente
join CODIGO_POSTAL on CODIGO_POSTAL.codigo_postal = PACIENTE.codigo_postal
where CODIGO_POSTAL.localidade = 'BRAGA' and ESPECIALIDADE.designacao = 'Oftalmologia';

-- g)
select MEDICO.nome, (2019 - year(MEDICO.data_inicio_servico)) from MEDICO
join CONSULTA on MEDICO.id_medico = CONSULTA.id_medico
join PACIENTE on PACIENTE.id_paciente = CONSULTA.id_paciente
where hour(CONSULTA.data_hora) >= 12 && (2019 - year(PACIENTE.data_nascimento) < 20);

