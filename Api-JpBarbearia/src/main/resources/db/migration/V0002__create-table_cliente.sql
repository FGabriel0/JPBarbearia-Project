CREATE TABLE cliente (
    ID SERIAL PRIMARY KEY NOT NULL,
    NOME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL,
    TELEFONE VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ROLE VARCHAR(255) NOT NULL,
    DATA_CRIACAO TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    DATA_ATUALIZACAO TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);