-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `fog`;

-- -----------------------------------------------------
-- Table `fog`.`zipcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`zipcode`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `postalcode` INT         NOT NULL,
    `name`       VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`city`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(45) NOT NULL,
    `zipcode_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_City_zipcode1_idx` (`zipcode_id` ASC) VISIBLE,
    CONSTRAINT `fk_City_zipcode1`
        FOREIGN KEY (`zipcode_id`)
            REFERENCES `fog`.`zipcode` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`address`
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    `city`    VARCHAR(45) NOT NULL,
    `city_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_address_City1_idx` (`city_id` ASC) VISIBLE,
    CONSTRAINT `fk_address_City1`
        FOREIGN KEY (`city_id`)
            REFERENCES `fog`.`city` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`carport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`carport`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `length`      INT         NOT NULL,
    `width`       INT         NOT NULL,
    `angle`       INT         NULL DEFAULT NULL,
    `shed_length` INT         NULL DEFAULT NULL,
    `shed_width`  INT         NULL DEFAULT NULL,
    `name`        VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`material`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(45) NOT NULL,
    `cost`   DOUBLE      NOT NULL,
    `length` INT         NULL DEFAULT NULL,
    `height` INT         NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`link_tabel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`link_tabel`
(
    `carport_id`  INT NOT NULL,
    `material_id` INT NOT NULL,
    `quantity`    INT NOT NULL,
    INDEX `fk_link_tabel_carport1_idx` (`carport_id` ASC) VISIBLE,
    INDEX `fk_link_tabel_material1_idx` (`material_id` ASC) VISIBLE,
    CONSTRAINT `fk_link_tabel_carport1`
        FOREIGN KEY (`carport_id`)
            REFERENCES `fog`.`carport` (`id`),
    CONSTRAINT `fk_link_tabel_material1`
        FOREIGN KEY (`material_id`)
            REFERENCES `fog`.`material` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`user`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(45) NOT NULL,
    `email`      VARCHAR(45) NOT NULL,
    `password`   VARCHAR(45) NOT NULL,
    `role`       VARCHAR(45) NOT NULL DEFAULT 'customer',
    `telefon`    INT         NOT NULL,
    `address_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_user_address_idx` (`address_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_address`
        FOREIGN KEY (`address_id`)
            REFERENCES `fog`.`address` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`query`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`query`
(
    `id`         INT         NOT NULL,
    `querycol`   VARCHAR(45) NULL DEFAULT NULL,
    `price`      DOUBLE      NOT NULL,
    `carport_id` INT         NOT NULL,
    `user_id`    INT         NOT NULL,
    `message`    VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_query_carport1_idx` (`carport_id` ASC) VISIBLE,
    INDEX `fk_query_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_query_carport1`
        FOREIGN KEY (`carport_id`)
            REFERENCES `fog`.`carport` (`id`),
    CONSTRAINT `fk_query_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `fog`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fog`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`order`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `ordercol` VARCHAR(45) NULL DEFAULT NULL,
    `created`  TIMESTAMP   NULL DEFAULT NULL,
    `price`    DOUBLE      NOT NULL,
    `message`  VARCHAR(45) NULL DEFAULT NULL,
    `query_id` INT         NOT NULL,
    `user_id`  INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_query1_idx` (`query_id` ASC) VISIBLE,
    INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_query1`
        FOREIGN KEY (`query_id`)
            REFERENCES `fog`.`query` (`id`),
    CONSTRAINT `fk_order_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `fog`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;