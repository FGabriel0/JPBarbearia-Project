CREATE TABLE usuario(
    ID SERIAL NOT NULL,
    NOME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(125) NOT NULL,
  	SENHA VARCHAR(500) NOT NULL,
  	ROLE VARCHAR(500),
    DATA_CRIACAO TIMESTAMP NOT NULL DEFAULT CURRENT_DATE
);

ALTER TABLE usuario
   ADD PRIMARY KEY (id);