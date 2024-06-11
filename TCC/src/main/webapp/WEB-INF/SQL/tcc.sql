CREATE DATABASE tcc
GO
USE tcc

CREATE TABLE  cliente (
cpf				VARCHAR(11)		NOT NULL,
nome			VARCHAR(100)	NOT NULL,
email			VARCHAR(100)	NOT NULL,
telefone		VARCHAR(9)		NOT NULL,
PRIMARY KEY (cpf)
)

CREATE TABLE servico (
placa				VARCHAR(7)		NOT NULL,
servico				VARCHAR(100)	NOT NULL,
dt_entrada			DATE			NOT NULL,
dt_retirada			DATE			NOT NULL,
cliente				VARCHAR(11)		NOT NULL,
status				VARCHAR(13)		NOT NULL,
PRIMARY KEY (placa),
FOREIGN KEY (cliente) REFERENCES cliente (cpf)
)

SELECT * FROM cliente
SELECT * FROM servico

INSERT INTO cliente VALUES 
('50147178029', 'José Santos',		'jose.santos@gmail.com',		'911111111'),
('20278501044', 'Paula Oliveira',	'paula.oliveira@outlook.com',	'922222222'),
('15232649000', 'Helen Barros',		'helen.barros@outlook.com',		'933333333'),
('54339759082', 'Mario Campos',		'mario.campos@gmail.com',		'944444444')

INSERT INTO servico VALUES
('AAA0000',		'Alinhamento',			'03/05/2024', '08/05/2024', '15232649000'),
('BBB1111',		'Balanceamento',		'04/05/2024', '07/05/2024', '20278501044'),
('CCC2222',		'Sistema de freio',		'07/06/2024', '11/06/2024', '54339759082'),
('DDD3333',		'Revisao Completa',		'15/06/2024', '25/06/2024', '50147178029')

SELECT c.cpf, c.nome, c.email, c.telefone, s.placa, s.servico, s.dt_entrada, s.dt_retirada 
FROM servico s, cliente c 
WHERE c.cpf = s.cliente 
AND s.placa = 'AAA0000'

SELECT c.cpf, c.nome, c.email, c.telefone, s.placa, s.servico, convert(varchar(10),dt_entrada,103) AS dt_entrada, s.dt_retirada, s.status 
FROM servico s, cliente c 
WHERE c.cpf = s.cliente 

SELECT convert(varchar(10),dt_entrada,103) AS dt_entrada, servico
FROM servico  
WHERE dt_entrada =	@dt_entrada	
ORDER BY dt_entrada