-- Projeção de todas as consultas
SELECT * FROM Clinica.CONSULTA;

use Clinica;

-- Exercício 2
-- a)
SELECT nome, data_inicio_servico FROM MEDICO WHERE YEAR(data_inicio_servico) <= 2009;

-- b)
SELECT nome, (SELECT designacao FROM ESPECIALIDADE WHERE id_especialidade = especialidade) FROM MEDICO;

SELECT nome FROM MEDICO
JOIN ESPECIALIDADE ON ESPECIALIDADE.id_especialidade = MEDICO.especialidade;

-- c)
SELECT  nome, morada FROM PACIENTE
JOIN CODIGO-POSTAL ON CODIGO-POSTAL.codigo_postal = PACIENTE.codigo_postal
WHERE CODIGO-POSTAL.localidade = 'BRAGA'; 

-- d)
SELECT nome FROM MEDICO
JOIN ESPECIALIDADE ON ESPECIALIDADE.designacao = MEDICO.especialidade
WHERE ESPECIALIDADE.designacao = 'Oftalmologia';

-- e)
SELECT nome, (2019- YEAR(data_nascimento) FROM MEDICO
JOIN ESPECIALIDADE ON ESPECIALIDADE.designacao = MEDICO.especialidade
WHERE ESPECIALIDADE.designacao = 'Clínica Geral' AND (2019 - YEAR(MEDICO.data_nascimento) > 40);

-- f) 
SELECT DISTINCT MEDICO.nome FROM MEDICO AS DOCTOR     -- DISTINCT - Elimina ocorrências repetidas
JOIN ESPECIALIDADE ON ESPECIALIDADE.designacao = DOCTOR.designacao
JOIN CONSULTA ON DOCTOR.id_medico = CONSULTA.id_medico
JOIN PACIENTE ON PACIENTE.id_paciente = CONSULTA.id_paciente
JOIN CODIGO-POSTAL ON CODIGO-POSTAL.codigo_postal = PACIENTE.codigo_postal
WHERE CODIGO-POSTAL.localidade = 'BRAGA' AND ESPECIALIDADE.designacao = 'Oftalmologia';

