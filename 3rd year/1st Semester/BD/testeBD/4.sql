-- Criar tabela Médicos
CREATE TABLE Medicos (
    id               INT(6) NOT NULL,
    Nome             VARCHAR(100) NOT NULL,
    Morada           VARCHAR(200),
    Cod_Postal       VARCHAR(8),
    Dta_nascimento   DATE,
    id_especialidade VARCHAR(3),
    Dta_ini_servico  DATE,
    Total_Consultas  INT,
    PRIMARY KEY (id),
    constraint FOREIGN KEY (Cod_Postal)       REFERENCES Cod_Postal(codigo),
    constraint FOREIGN KEY (id_especialidade) REFERENCES Especialidades(id)
);
-- chave primária: id
-- chaves estrangeiras: Cod_Postal e id_especialidade

-- Criar tabela Consultas
CREATE TABLE Consultas (
    id_medico   INT(6) NOT NULL,
    id_paciente INT(6) NOT NULL,
    Data_Hora   DATETIME NOT NULL,
    Preco       DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id_medico, id_paciente, Data_Hora),
    constraint FOREIGN KEY (id_medico) REFERENCES Medicos(id),
    constraint FOREIGN KEY (id_paciente) REFERENCES Pacientes(id_paciente)
);
-- chaves primárias: id_medico, id_paciente e Data_Hora
-- chave estrangeira: id_paciente
