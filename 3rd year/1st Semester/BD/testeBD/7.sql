-- Exercício 7)
-- a) Indique o nome dos médicos que deram consultas em cada uma das
-- especialidades existentes durante o ano de 2016.
SELECT Medicos.Nome FROM Medicos WHERE ((SELECT COUNT(Especialidades.id) FROM
        Medicos, Pacientes, Consultas, Especialidades WHERE Medicos.id =
        Consultas.id_medico AND Pacientes.id_paciente = Consultas.id_paciente
        AND Medicos.id_especialidade = Especialidades.id AND
        date_format(Consultas.Data_Hora, '%Y') = 2016) = (SELECT
        COUNT(Especialidades.id) FROM Especialidades);

-- b) Indique o nome e a idade dos médicos que nunca deram consultas a
-- pacientes residentes em 'Guimarães'.
SELECT Medicos.nome, idade(Medicos.Dta_nascimento) FROM Medicos, Consultas,
Pacientes, Cod_Postal WHERE (Medicos.id = Consultas.id_medico AND
    Consultas.id_paciente = Pacientes.id_paciente AND Paciente.id NOT IN
    (SELECT Pacientes.id FROM Pacientes, Cod_Postal WHERE Pacientes.Cod_Postal
        = Cod_Postal. codigo AND Cod_Postal.Localidade = 'Guimarães'));

-- c) Apresente o nome dos pacientes que foram consultados a todas as
-- especialidades.
SELECT Pacientes.Nome FROM Pacientes, Consultas, Medicos, Especialidades WHERE
(Pacientes.id_paciente = Consultas.id_pacientes AND Consultas.id_medico =
    Medicos.id AND Medicos.id_especialidade = Especialidades.id AND ((SELECT
            DISTINCT COUNT(Especialidades.id) FROM Pacientes, Consultas,
            Medicos, Especialidades WHERE Pacinetes.id_paciente =
            Consultas.id_paciente AND Consultas.id_medico = Medicos.id AND
            Medicos.id_especialidade = Especialidades.id) != (SELECT DISTINCT
            COUNT(Especialidades.id) FROM Especialidades)));

-- d) Indique o número de consultas realizadas pelo médico 'Manuel da Silva e
-- Silva' durante o ano de 2016.

SELECT COUNT(con.id) FROM Consultas con, Medicos med
WHERE con.id_medico = med.id AND date_format(con.Data_Hora, '%Y') = 2016
      AND med.nome = 'Manuel da Silva e Silva';
