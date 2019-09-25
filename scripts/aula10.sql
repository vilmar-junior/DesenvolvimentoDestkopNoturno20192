CREATE SCHEMA `dbfoodtruck` ;

CREATE TABLE `dbfoodtruck`.`sobremesa` (
  `id` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `preco` DOUBLE NOT NULL,
  `light` INT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC));

  ALTER TABLE `dbfoodtruck`.`sobremesa` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

INSERT INTO SOBREMESA(ID, NOME, PRECO, LIGHT) VALUES (1, 'Sagu', 3.5, 0);
INSERT INTO SOBREMESA(ID, NOME, PRECO, LIGHT) VALUES (2, 'Pudim light', 4.45, 1);
INSERT INTO SOBREMESA(ID, NOME, PRECO, LIGHT) VALUES (3, 'Mousse', 8.9, 0);
INSERT INTO SOBREMESA(ID, NOME, PRECO, LIGHT) VALUES (4, 'Gelatina', 1.45, 1);


CREATE TABLE `dbfoodtruck`.`PRODUTO` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cor` VARCHAR(255) NOT NULL,
  `fabricante` VARCHAR(255) NOT NULL,
  `valor` DOUBLE NULL,
  `peso` DOUBLE NULL,
  `dataCadastro` DATE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (1, 'Mouse', 'Preto', 'Logitech', '150', '0.12', '2012-05-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (2, 'Teclado', 'Verde', 'Logitech', '190', '0.35', '2018-07-02');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (3, 'Monitor Wide', 'Preto', 'Samsung', '750', '4.6', '2018-03-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (4, 'Monitor CRT', 'Vermelho', 'LG', '200', '8.5', '2006-05-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (5, 'Notebook', 'Preto', 'Dell', '4500', '2.8', '2018-05-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (6, 'Notebook', 'Azul', 'Acer', '3250', '3.2', '2019-01-10');