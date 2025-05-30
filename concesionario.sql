DROP DATABASE IF EXISTS `concesionario`;
CREATE DATABASE `concesionario` CHARACTER SET = 'latin1';
USE `concesionario`;

CREATE TABLE `users` (
    name VARCHAR(15) UNIQUE,
    password VARCHAR(20) NOT NULL,
    uuid BINARY(16) PRIMARY KEY
) ENGINE=InnoDB;

CREATE TABLE `cars` (
    brand VARCHAR(20),
    plate VARCHAR(8) PRIMARY KEY,
    yearProduction DATE,
    uuid BINARY(16),
    FOREIGN KEY (uuid) REFERENCES `users`(uuid)
) ENGINE=InnoDB;

CREATE TABLE `outlays`(
	type ENUM('echar_gasolina', 'ITV', 'cambio_aceite', 'otros'),
    kilometers double,
    dateData date PRIMARY KEY,
    finalCost double,
    optionalDescription VARCHAR(300),
    plate varchar(8),
    foreign key (plate) references cars(plate)
)ENGINE=InnoDB;




