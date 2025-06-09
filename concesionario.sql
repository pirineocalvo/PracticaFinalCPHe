DROP DATABASE IF EXISTS `PracticaFinalCPHe`;
CREATE DATABASE  `PracticaFinalCPHe` CHARACTER SET = 'latin1';
USE  `PracticaFinalCPHe`;

CREATE TABLE `users` (
    name VARCHAR(15) UNIQUE,
    password VARCHAR(100) NOT NULL,
    uuid BINARY(16) PRIMARY KEY
) ENGINE=InnoDB;

CREATE TABLE `cars` (
    brand VARCHAR(20),
    plate VARCHAR(8) PRIMARY KEY,
    yearProduction DATE
) ENGINE=InnoDB;

CREATE TABLE `outlays` (
	type ENUM('echar_gasolina', 'ITV', 'cambio_aceite', 'otros'),
	kilometers DOUBLE,
	dateData DATE PRIMARY KEY,
	finalCost DOUBLE,
	optionalDescription VARCHAR(300),
	plate VARCHAR(8),
	FOREIGN KEY (plate) REFERENCES cars(plate) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `cars_owners` (
	uuid BINARY(16),
	plate VARCHAR(8),
	PRIMARY KEY (uuid, plate),
	FOREIGN KEY (uuid) REFERENCES users(uuid),
	FOREIGN KEY (plate) REFERENCES cars(plate) ON DELETE CASCADE
) ENGINE=InnoDB;

SELECT name, password, BIN_TO_UUID(uuid) AS uuid FROM users;
SELECT * FROM cars;
SELECT name, password, BIN_TO_UUID(uuid) as uuid FROM users;
SELECT BIN_TO_UUID(uuid) as uuid, plate FROM cars_owners;
SELECT * FROM cars;
SELECT * FROM outlays;
SET SQL_SAFE_UPDATES = 0;
SELECT name, password, BIN_TO_UUID(uuid) AS uuid FROM users WHERE uuid = UUID_TO_BIN('15894181-3d45-11f0-b4aa-862ccfb04448');
SELECT cars.brand, cars_owners.plate, cars.yearProduction, BIN_TO_UUID(cars_owners.uuid) as uuid FROM cars INNER JOIN cars_owners ON cars.plate = cars_owners.plate WHERE cars.uuid = UUID_TO_BIN('15894181-3d45-11f0-b4aa-862ccfb04448');
SELECT * FROM cars_owners;
SELECT cars.brand, cars.plate, cars.yearProduction, BIN_TO_UUID(cars_owners.uuid) as uuid FROM cars INNER JOIN cars_owners ON cars.plate = cars_owners.plate WHERE cars_owners.uuid = UUID_TO_BIN('41bfd117-41f4-11f0-b4aa-862ccfb04448');