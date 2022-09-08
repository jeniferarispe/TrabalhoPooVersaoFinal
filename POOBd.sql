CREATE TABLE categoria(
id_categoria INTEGER PRIMARY KEY,
nome_categoria varchar(50) NOT NULL
);
CREATE TABLE produto(
id_produto INTEGER PRIMARY KEY,
nome_produto varchar(50) NOT NULL,
valor_produto NUMERIC(8,2) NOT NULL,
descricao_produto varchar(250) NOT NULL
);
CREATE SEQUENCE usersequence
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

CREATE SEQUENCE usersequence1
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;


ALTER TABLE categoria ALTER COLUMN id_categoria SET DEFAULT NEXTVAL('usersequence'::regclass);

ALTER TABLE categoria ADD UNIQUE (id_categoria);


ALTER TABLE produto ALTER COLUMN id_produto SET DEFAULT NEXTVAL('usersequence1'::regclass);

ALTER TABLE produto ADD UNIQUE (id_produto);


SELECT * FROM categoria;
SELECT * FROM produto;
