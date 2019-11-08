use Clinica;

-- Ficha de exercícios PL7

-- a)
select avg(idade(data_nascimento)) 'Idades' from MEDICO
where idade(data_inicio_servico) > 15;

-- b)
select avg(idade(data_inicio_servico)) 'Idades', designacao 'Especialidade' from MEDICO
join ESPECIALIDADE on ESPECIALIDADE.id_especialidade = MEDICO.especialidade
group by MEDICO.especialidade;

-- c) -- left join
select MEDICO.nome, count(MEDICO.id_medico) from MEDICO
left join CONSULTA on MEDICO.id_medico = CONSULTA.id_medico
group by MEDICO.nome;

-- d)
select avg(idade(data_nascimento)) 'Idade', localidade 'Localidade' from PACIENTE
join CODIGO_POSTAL on PACIENTE.codigo_postal = CODIGO_POSTAL.codigo_postal
group by CODIGO_POSTAL.localidade;

-- e)
select MEDICO.nome 'Nome', sum(CONSULTA.preco) 'Faturação' from MEDICO
left join CONSULTA on MEDICO.id_medico = CONSULTA.id_medico
where year(CONSULTA.data_hora) = 2016
group by MEDICO.nome;

-- f)
select designacao 'Especialidade', count(MEDICO.especialidade) "Nº Médicos" from ESPECIALIDADE
left join MEDICO on MEDICO.especialidade = ESPECIALIDADE.id_especialidade
group by ESPECIALIDADE.designacao;

-- g)
select ESPECIALIDADE.designacao, MEDICO.nome, max(CONSULTA.preco), min(CONSULTA.preco) from ESPECIALIDADE
join MEDICO on MEDICO.especialidade = ESPECIALIDADE.id_especialidade
join CONSULTA on CONSULTA.id_medico = MEDICO.id_medico
group by ESPECIALIDADE.designacao, MEDICO.nome;
