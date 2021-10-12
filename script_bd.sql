CREATE SCHEMA IF NOT EXISTS `petshop` ;
USE `petshop` ;

CREATE TABLE IF NOT EXISTS `petshop`.`cliente` (
  `cpf` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;

CREATE INDEX `nome` ON `petshop`.`cliente` (`nome` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `petshop`.`pet` (
  `id_pet` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `especie` VARCHAR(45) NOT NULL,
  `raça` VARCHAR(45) NULL,
  `data_nascimento` DATETIME NULL,
  `cliente_cpf` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pet`),
  CONSTRAINT `fk_pet_cliente`
    FOREIGN KEY (`cliente_cpf`)
    REFERENCES `petshop`.`cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_pet_cliente_idx` ON `petshop`.`pet` (`cliente_cpf` ASC) VISIBLE;

CREATE INDEX `nome` ON `petshop`.`pet` (`nome` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `petshop`.`funcao_funcionario` (
  `id_funcao_funcionario` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_funcao_funcionario`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `petshop`.`funcionario` (
  `cpf` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `salario` FLOAT NULL,
  `funcao` INT NOT NULL,
  PRIMARY KEY (`cpf`),
  CONSTRAINT `fk_funcionario_funcao_funcionario1`
    FOREIGN KEY (`funcao`)
    REFERENCES `petshop`.`funcao_funcionario` (`id_funcao_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_funcionario_funcao_funcionario1_idx` ON `petshop`.`funcionario` (`funcao` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `petshop`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `preco_custo` FLOAT NULL,
  `preco_venda` FLOAT NULL,
  `qtde_estoque` INT NULL,
  PRIMARY KEY (`id_produto`))
ENGINE = InnoDB;

CREATE INDEX `nome` ON `petshop`.`produto` (`nome` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `petshop`.`forma_pagamento` (
  `id_forma_pagamento` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_forma_pagamento`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `petshop`.`venda` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `cliente_cpf` VARCHAR(45) NOT NULL,
  `funcionario_cpf` VARCHAR(45) NOT NULL,
  `valor_total` FLOAT NULL,
  `data` DATETIME NULL,
  `forma_pagamento` INT NOT NULL,
  PRIMARY KEY (`id_venda`),
  CONSTRAINT `fk_venda_cliente1`
    FOREIGN KEY (`cliente_cpf`)
    REFERENCES `petshop`.`cliente` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_funcionario1`
    FOREIGN KEY (`funcionario_cpf`)
    REFERENCES `petshop`.`funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_forma_pagamento1`
    FOREIGN KEY (`forma_pagamento`)
    REFERENCES `petshop`.`forma_pagamento` (`id_forma_pagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_venda_cliente1_idx` ON `petshop`.`venda` (`cliente_cpf` ASC) VISIBLE;

CREATE INDEX `fk_venda_funcionario1_idx` ON `petshop`.`venda` (`funcionario_cpf` ASC) VISIBLE;

CREATE INDEX `fk_venda_forma_pagamento1_idx` ON `petshop`.`venda` (`forma_pagamento` ASC) VISIBLE;

CREATE INDEX `data` ON `petshop`.`venda` (`data` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `petshop`.`venda_produto` (
  `id_venda` INT NOT NULL,
  `id_produto` INT NOT NULL,
  `qtde` INT NOT NULL,
  PRIMARY KEY (`id_venda`, `id_produto`),
  CONSTRAINT `fk_venda_has_produto_venda1`
    FOREIGN KEY (`id_venda`)
    REFERENCES `petshop`.`venda` (`id_venda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_has_produto_produto1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `petshop`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_venda_has_produto_produto1_idx` ON `petshop`.`venda_produto` (`id_produto` ASC) VISIBLE;

CREATE INDEX `fk_venda_has_produto_venda1_idx` ON `petshop`.`venda_produto` (`id_venda` ASC) VISIBLE;

CREATE TABLE IF NOT EXISTS `petshop`.`tipo_servico` (
  `id_tipo_servico` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  `preco` FLOAT NOT NULL,
  PRIMARY KEY (`id_tipo_servico`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `petshop`.`servico` (
  `id_servico` INT NOT NULL AUTO_INCREMENT,
  `pet_id_pet` INT NOT NULL,
  `funcionario_cpf` VARCHAR(45) NOT NULL,
  `data` DATETIME NULL,
  `forma_pagamento` INT NOT NULL,
  `tipo_servico` INT NOT NULL,
  PRIMARY KEY (`id_servico`),
  CONSTRAINT `fk_servico_pet1`
    FOREIGN KEY (`pet_id_pet`)
    REFERENCES `petshop`.`pet` (`id_pet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servico_funcionario1`
    FOREIGN KEY (`funcionario_cpf`)
    REFERENCES `petshop`.`funcionario` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servico_forma_pagamento1`
    FOREIGN KEY (`forma_pagamento`)
    REFERENCES `petshop`.`forma_pagamento` (`id_forma_pagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servico_tipo_servico1`
    FOREIGN KEY (`tipo_servico`)
    REFERENCES `petshop`.`tipo_servico` (`id_tipo_servico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_servico_pet1_idx` ON `petshop`.`servico` (`pet_id_pet` ASC) VISIBLE;

CREATE INDEX `fk_servico_funcionario1_idx` ON `petshop`.`servico` (`funcionario_cpf` ASC) VISIBLE;

CREATE INDEX `fk_servico_forma_pagamento1_idx` ON `petshop`.`servico` (`forma_pagamento` ASC) VISIBLE;

CREATE INDEX `fk_servico_tipo_servico1_idx` ON `petshop`.`servico` (`tipo_servico` ASC) VISIBLE;

CREATE INDEX `data` ON `petshop`.`servico` (`data` ASC) VISIBLE;