USE Caderneta;

DROP TABLE IF EXISTS ListaFaltas;

CREATE TABLE ListaFaltas (
    idCromo INT NOT NULL,
    descricao TEXT,
    PRIMARY KEY(idCromo)
    );