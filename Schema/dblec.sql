-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema clothes
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clothes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clothes` DEFAULT CHARACTER SET utf8 ;
USE `clothes` ;

-- -----------------------------------------------------
-- Table `clothes`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothes`.`categories` (
  `categoryID` INT NOT NULL,
  `categoryName` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`categoryID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothes`.`suppliers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothes`.`suppliers` (
  `supplierID` INT NOT NULL,
  `supplierName` VARCHAR(125) NOT NULL,
  `supplierLocation` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`supplierID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothes`.`productlines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothes`.`productlines` (
  `productLine` VARCHAR(25) NOT NULL,
  `description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`productLine`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clothes`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothes`.`products` (
  `productCode` VARCHAR(15) NOT NULL,
  `productName` VARCHAR(70) NOT NULL,
  `genderType` CHAR(1) NOT NULL,
  `materialType` VARCHAR(125) NOT NULL,
  `productSize` VARCHAR(15) NOT NULL,
  `productLine` VARCHAR(15) NOT NULL,
  `supplierID` INT NOT NULL,
  `categoryID` INT NOT NULL,
  PRIMARY KEY (`productCode`),
  INDEX `fk_products_category_idx` (`categoryID` ASC),
  INDEX `fk_products_suppliers1_idx` (`supplierID` ASC),
  INDEX `fk_products_productlines1_idx` (`productLine` ASC),
  CONSTRAINT `fk_products_category`
    FOREIGN KEY (`categoryID`)
    REFERENCES `clothes`.`categories` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_suppliers1`
    FOREIGN KEY (`supplierID`)
    REFERENCES `clothes`.`suppliers` (`supplierID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_productlines1`
    FOREIGN KEY (`productLine`)
    REFERENCES `clothes`.`productlines` (`productLine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
