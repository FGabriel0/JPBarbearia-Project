CREATE TABLE  agendamento (
    ID SERIAL PRIMARY KEY,
    USUARIO_ID INTEGER REFERENCES USUARIO (ID),
    TELEFONE VARCHAR(20),
    DATA VARCHAR(255) NOT NULL,
    HORA VARCHAR(255) NOT NULL,
    SERVICE_ID  INTEGER REFERENCES SERVICE (ID),
    OBSERVACAO VARCHAR(255),
    STATUS VARCHAR(20),
    TOTAL NUMERIC(20,2) NOT NULL,
    DATA_AGENDAMENTO TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);