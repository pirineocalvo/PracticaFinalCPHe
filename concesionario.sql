DROP DATABASE IF EXISTS `concesionario`;
CREATE DATABASE `concesionario` CHARACTER SET = 'latin1';
USE `concesionario`;

CREATE TABLE `users` (
    name VARCHAR(15) UNIQUE,
    password VARCHAR(100) NOT NULL,
    uuid BINARY(16) PRIMARY KEY DEFAULT (UUID_TO_BIN(UUID()))
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

CREATE TABLE `cars_owners`(
	uuid BINARY(16),
    plate VARCHAR(8),
    UNIQUE (uuid, plate),
    PRIMARY KEY(uuid, plate),
    FOREIGN KEY (uuid) REFERENCES users(uuid),
    FOREIGN KEY (plate) REFERENCES cars(plate)
)ENGINE=InnoDB;

SELECT name, password, BIN_TO_UUID(uuid) AS uuid FROM users;
-- SELECT * FROM users;
SELECT brand, plate, yearProduction, BIN_TO_UUID(uuid) as uuid FROM cars;
SELECT BIN_TO_UUID(uuid) as uuid, plate FROM cars_owners;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM cars_owners;
DROP TABLE cars_owners;
SELECT text_to_binary(uuid) FROM users;
SELECT name, password, BIN_TO_UUID(uuid) AS uuid FROM users WHERE uuid = UUID_TO_BIN('15894181-3d45-11f0-b4aa-862ccfb04448');
SELECT cars.brand, cars_owners.plate, cars.yearProduction, BIN_TO_UUID(cars_owners.uuid) as uuid FROM cars INNER JOIN cars_owners ON cars.plate = cars_owners.plate WHERE cars.uuid = UUID_TO_BIN('15894181-3d45-11f0-b4aa-862ccfb04448');
