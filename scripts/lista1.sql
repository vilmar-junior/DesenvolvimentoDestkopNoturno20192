CREATE SCHEMA LISTA1;

CREATE TABLE `lista1`.`empregado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `CPF` VARCHAR(11) NOT NULL,
  `SEXO` VARCHAR(1) NOT NULL,
  `IDADE` INT NOT NULL,
  `SALARIOBRUTO` DOUBLE NOT NULL,
  `DESCONTOIR` DOUBLE NOT NULL,
  `DESCONTOPREVIDENCIA` DOUBLE NOT NULL,
  `SALARIOBASE` DOUBLE NOT NULL,
  `SALARIO` DOUBLE NOT NULL,
  `COMISSAO` DOUBLE NOT NULL,
  `TIPO` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC));

CREATE TABLE `lista1`.`lotacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `SIGLA` VARCHAR(3) NULL,
  `ID_RESPONSAVEL` INT NOT NULL,
  `ID_LOTACAO_SUPERIOR` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `ID_idx` (`ID_RESPONSAVEL` ASC),
  CONSTRAINT `FK_RESPONSAVEL`
    FOREIGN KEY (`ID_RESPONSAVEL`)
    REFERENCES `lista1`.`empregado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
ALTER TABLE `lista1`.`lotacao` 
ADD INDEX `FK_LOTACAO_SUPERIOR_idx` (`ID_LOTACAO_SUPERIOR` ASC);
ALTER TABLE `lista1`.`lotacao` 
ADD CONSTRAINT `FK_LOTACAO_SUPERIOR`
  FOREIGN KEY (`ID_LOTACAO_SUPERIOR`)
  REFERENCES `lista1`.`lotacao` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `lista1`.`trabalha` (
  `ID_LOTACAO` INT NOT NULL,
  `ID_EMPREGADO` INT NOT NULL,
  INDEX `FK_EMPREGADO_idx` (`ID_EMPREGADO` ASC),
  CONSTRAINT `FK_LOTACAO`
    FOREIGN KEY (`ID_LOTACAO`)
    REFERENCES `lista1`.`lotacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_EMPREGADO`
    FOREIGN KEY (`ID_EMPREGADO`)
    REFERENCES `lista1`.`empregado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);