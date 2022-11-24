-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema zipsee
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema zipsee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `zipsee` DEFAULT CHARACTER SET utf8mb3 ;
USE `zipsee` ;

-- -----------------------------------------------------
-- Table `zipsee`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`user` (
  `USER_ID` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `NICK_NAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `SEX` VARCHAR(45) NOT NULL,
  `REGISTER_DATE` DATETIME NOT NULL,
  `TOKEN` VARCHAR(1000) NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE INDEX `user_id_UNIQUE` (`USER_ID` ASC) VISIBLE,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`board` (
  `BOARD_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` VARCHAR(45) NOT NULL,
  `TITLE` VARCHAR(100) NOT NULL,
  `CONTENT` VARCHAR(10000) NOT NULL,
  `REGISTER_DATE` DATETIME NOT NULL,
  PRIMARY KEY (`BOARD_ID`),
  INDEX `board_user_userid_fk_idx` (`USER_ID` ASC) VISIBLE,
  CONSTRAINT `board_user_userid_fk`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `zipsee`.`user` (`USER_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`comment` (
  `COMMENT_ID` INT NOT NULL AUTO_INCREMENT,
  `BOARD_ID` INT NOT NULL,
  `CONTENT` VARCHAR(10000) NOT NULL,
  `REGISTER_DATE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`COMMENT_ID`),
  INDEX `board_comment_commentid_fk_idx` (`BOARD_ID` ASC) VISIBLE,
  CONSTRAINT `board_comment_commentid_fk`
    FOREIGN KEY (`BOARD_ID`)
    REFERENCES `zipsee`.`board` (`BOARD_ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`dong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`dong` (
  `DONG_CODE` VARCHAR(45) NOT NULL,
  `DONG_NAME` VARCHAR(45) NULL DEFAULT NULL,
  `GUGUN_NAME` VARCHAR(45) NULL DEFAULT NULL,
  `SIDO_NAME` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`DONG_CODE`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`houseinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`houseinfo` (
  `HOUSE_ID` INT NOT NULL AUTO_INCREMENT,
  `HOUSE_NAME` VARCHAR(45) NOT NULL,
  `HOUSE_TYPE` VARCHAR(45) NOT NULL,
  `DONG_CODE` VARCHAR(45) NOT NULL,
  `DONG_NAME` VARCHAR(45) NULL DEFAULT NULL,
  `BUILD_YEAR` INT NULL DEFAULT NULL,
  `JIBUN` VARCHAR(45) NULL DEFAULT NULL,
  `LAT` VARCHAR(45) NOT NULL,
  `LNG` VARCHAR(45) NOT NULL,
  `IMG` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`HOUSE_ID`),
  INDEX `house_dong_dongcode_fk_idx` (`DONG_CODE` ASC) VISIBLE,
  CONSTRAINT `house_dong_dongcode_fk`
    FOREIGN KEY (`DONG_CODE`)
    REFERENCES `zipsee`.`dong` (`DONG_CODE`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`housedeal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`housedeal` (
  `DEAL_ID` INT NOT NULL,
  `HOUSE_ID` INT NULL DEFAULT NULL,
  `AREA` INT NULL DEFAULT NULL,
  `FLOOR` INT NULL DEFAULT NULL,
  `DEAL_TYPE` VARCHAR(45) NOT NULL,
  `DEPOSIT` INT NULL,
  `PRICE` BIGINT NULL DEFAULT NULL,
  `LIKE` INT NULL DEFAULT 0,
  PRIMARY KEY (`DEAL_ID`),
  INDEX `deal_info_houseid_fk_idx` (`HOUSE_ID` ASC) VISIBLE,
  CONSTRAINT `deal_info_houseid_fk`
    FOREIGN KEY (`HOUSE_ID`)
    REFERENCES `zipsee`.`houseinfo` (`HOUSE_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`interest` (
  `INTEREST_ID` VARCHAR(45) NOT NULL,
  `INTEREST_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`INTEREST_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`user_deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`user_deal` (
  `USER_DEAL_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` VARCHAR(45) NOT NULL,
  `DEAL_ID` INT NOT NULL,
  PRIMARY KEY (`USER_DEAL_ID`),
  INDEX `user_deal_userid_fk_idx` (`USER_ID` ASC) VISIBLE,
  INDEX `user_deal_dealid_fk_idx` (`DEAL_ID` ASC) VISIBLE,
  CONSTRAINT `user_deal_dealid_fk`
    FOREIGN KEY (`DEAL_ID`)
    REFERENCES `zipsee`.`housedeal` (`DEAL_ID`),
  CONSTRAINT `user_deal_userid_fk`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `zipsee`.`user` (`USER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`user_dong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`user_dong` (
  `USER_DONG_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` VARCHAR(45) NOT NULL,
  `DONG_CODE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_DONG_ID`),
  INDEX `user_dong_userid_fk_idx` (`USER_ID` ASC) VISIBLE,
  INDEX `user_dong_dongcode_fk_idx` (`DONG_CODE` ASC) VISIBLE,
  CONSTRAINT `user_dong_dongcode_fk`
    FOREIGN KEY (`DONG_CODE`)
    REFERENCES `zipsee`.`dong` (`DONG_CODE`),
  CONSTRAINT `user_dong_userid_fk`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `zipsee`.`user` (`USER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `zipsee`.`user_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `zipsee`.`user_interest` (
  `USER_INTEREST_ID` INT NOT NULL AUTO_INCREMENT,
  `USER_ID` VARCHAR(45) NOT NULL,
  `INTEREST_ID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_INTEREST_ID`),
  INDEX `user_interest_userid_fk_idx` (`USER_ID` ASC) VISIBLE,
  INDEX `user_interest_interestid_fk_idx` (`INTEREST_ID` ASC) VISIBLE,
  CONSTRAINT `user_interest_interestid_fk`
    FOREIGN KEY (`INTEREST_ID`)
    REFERENCES `zipsee`.`interest` (`INTEREST_ID`),
  CONSTRAINT `user_interest_userid_fk`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `zipsee`.`user` (`USER_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
