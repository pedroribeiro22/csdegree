-- atualizar a coluna 'Total_Consultas' da tabela Medicos
UPDATE Medicos SET Total_Colunas = (SELECT COUNT(*) FROM Consultas WHERE
    Consultas.id_medicos = Medicos.id);