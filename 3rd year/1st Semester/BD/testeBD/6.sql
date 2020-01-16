-- 6)
-- a) Quais são os pacientes ({id, Nome}) residentes em 'Braga' que foram
-- consultados na especialidade de 'Clínica Geral'?
SELECT Pacientes.id_paciente, Pacientes.Nome FROM Pacientes, Consultas,
Medicos, Especialidades, Cod_Postal WHERE ((Pacientes.Cod_Postal =
        Cod_Postal.codigo AND Cod_Postal.Localidade = 'Braga') AND
    (Pacientes.id_paciente = Consultas.id_paciente AND Consultas.id_medico =
        Medicos.id AND Medicos.id_especialidade = Especialidades.id AND
        Especialidade.Designacao = 'Clínica Geral'));

-- b) Quais são as especialidades ({id, Designacao}) com médicos com mais de 50
-- anos de idade e que deram consultas a pacientes residentes em 'Vila Verde'?
SELECT DISTINCT Especialidades.id, Especialidades.Designacao FROM Especialidades,
Medicos, Consultas, Pacientes, Cod_Postal WHERE ((Especialidades.id =
        Medicos.id_especialidade AND idade(Medicos.Dta_nascimento) > 50) AND
    (Especialidades.id = Medicos.id_especialidade AND Medicos.id =
        Consultas.id_medico AND Consultas.id_paciente = Pacientes.id_paciente
        AND Pacientes.Cod_Postal = Cod_Postal.codigo AND Cod_Postal.Localidade
        = 'Vila Verde'));

-- c) Quais são os nome dos médicos com mais de 15 anos de serviço?
SELECT Medicos.Nome, idade(Medicos.Dta_nascimento) FROM Medicos WHERE
idade(idade(Medicos.Dta_ini_servico) > 15);
