--
-- AUTHOR: Jacob Charles
-- AUTHOR: Alexis Kennedy
-- VERSION: 0.5.0
--
DROP TABLE IF EXISTS `Invoice`;
DROP TABLE IF EXISTS `MainInvoice`;
DROP TABLE IF EXISTS `Products`;
DROP TABLE IF EXISTS `Addresses`;
DROP TABLE IF EXISTS `Company`;
DROP TABLE IF EXISTS `Emails`;
DROP TABLE IF EXISTS `Persons`;
DROP TABLE IF EXISTS `Address`;
DROP TABLE IF EXISTS `Street`;
DROP TABLE IF EXISTS `Zip`;
DROP TABLE IF EXISTS `City`;
DROP TABLE IF EXISTS `State`;
DROP TABLE IF EXISTS `Country`;

CREATE TABLE IF NOT EXISTS `Country` (
    `CountryID` INT(6) NOT NULL AUTO_INCREMENT,
    `Abrv` VARCHAR(6),
    `Name` VARCHAR(30),
    PRIMARY KEY (CountryID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `State` (
    `StateID` INT(6) NOT NULL AUTO_INCREMENT,
    `Abrv` VARCHAR(6),
    `Name` VARCHAR(30) NOT NULL,
    `CountryID` INT(6),
    PRIMARY KEY (StateID),
    FOREIGN KEY (CountryID) REFERENCES Country (CountryID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `City` (
    `CityID` INT(6) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `StateID` INT(6) NOT NULL,
    PRIMARY KEY (CityID),
    FOREIGN KEY (StateID) REFERENCES State (StateID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Zip` (
    `ZipID` INT(6) NOT NULL AUTO_INCREMENT,
    `ZipCode` VARCHAR(10),
    `CountryID` INT(6) NOT NULL,
    PRIMARY KEY (ZipID),
    FOREIGN KEY (CountryID) REFERENCES Country (CountryID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Street` (
    `StreetID` INT(6) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(50) NOT NULL,
    `CountryID` INT(6) NOT NULL,
    PRIMARY KEY (StreetID),
    FOREIGN KEY (CountryID) REFERENCES Country (CountryID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Address` (
    `AddressID` INT(6) NOT NULL AUTO_INCREMENT,
    `CountryID` INT(6) NOT NULL,
    `StateID` INT(6),
    `CityID` INT(6) NOT NULL,
    `ZipID` INT(6),
    `StreetID` INT(6) NOT NULL,
    PRIMARY KEY (AddressID),
    FOREIGN KEY (CountryID) REFERENCES Country (CountryID),
    FOREIGN KEY (StateID) REFERENCES State (StateID),
    FOREIGN KEY (CityID) REFERENCES City (CityID),
    FOREIGN KEY (ZipID) REFERENCES Zip (ZipID),
    FOREIGN KEY (StreetID) REFERENCES Street (StreetID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Persons` (
    `PersonsID` INT(6) NOT NULL AUTO_INCREMENT,
    `PersCode` VARCHAR(10),
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL,
    `AddressID` INT(6),
    PRIMARY KEY (PersonsID),
    FOREIGN KEY (AddressID) REFERENCES Address (AddressID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Emails` (
    `EmailID` INT(6) NOT NULL AUTO_INCREMENT,
    `PersonsID` INT(6),
    `Email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (EmailID),
    FOREIGN KEY (PersonsID) REFERENCES Persons (PersonsID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Company` (
    `CompanyID` INT(6) NOT NULL AUTO_INCREMENT,
    `PersonsID` INT(6),
    `AddressID` INT(6) NOT NULL,
    `Name` VARCHAR(60) NOT NULL,
    `TypeThing` VARCHAR(1) NOT NULL,
    `CompCode` VARCHAR(10),
    PRIMARY KEY (CompanyID),
    FOREIGN KEY (PersonsID) REFERENCES Persons (PersonsID),
    FOREIGN KEY (AddressID) REFERENCES Address (AddressID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Products` (
	`ProductID` INT(6) NOT NULL AUTO_INCREMENT,
	`TypeThing` VARCHAR(1),
	`ProdCode` VARCHAR(10),
	`UnitMoneh` DECIMAL(12,2),
	`PersonsID` INT(6),
	`HourMoneh` DECIMAL(12, 2),
	`Name` VARCHAR(60),
	`Fee` DECIMAL(12,2),
	`AnnualingCostings` DECIMAL(12,2),
	PRIMARY KEY (ProductID),
	FOREIGN KEY (PersonsID) REFERENCES Persons (PersonsID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `MainInvoice` (
    `MainInvoiceID` INT(6) NOT NULL AUTO_INCREMENT,
    `InvoiceCode` VARCHAR(20),
    `CompanyID` INT(6),
    `Subtotal` DECIMAL(12,2) DEFAULT 0.00,
    `SalesDudeID` INT(6),
    PRIMARY KEY (MainInvoiceID),
    FOREIGN KEY (CompanyID) REFERENCES Company (CompanyID),
    FOREIGN KEY (SalesDudeID) REFERENCES Persons (PersonsID)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `Invoice` (
    `InvoiceID` INT(6) NOT NULL AUTO_INCREMENT,
	`ProductID` INT(6) NOT NULL,
    `NumUnits` INT(6),
    `NumHours` DECIMAL(12,2),
    `StartDate` VARCHAR(11),
    `EndDate` VARCHAR(11),
    `MainInvoiceID` INT(6) NOT NULL,
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (ProductID) REFERENCES Products (ProductID),
    FOREIGN KEY (MainInvoiceID) REFERENCES MainInvoice (MainInvoiceID)
) ENGINE=InnoDB;