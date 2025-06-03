package com.pFinCPHe.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pFinCPHe.model.entities.Car;


public class CarModel implements ICarModel{
	private Connection connection;
	
	public CarModel() throws ClassNotFoundException, SQLException, IOException {
		this.connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(Car car) {
		try {
			String query = "INSERT INTO cars (brand, plate, yearProduction, uuid) value (?, ?, ?, UUID_TO_BIN(?))";
			PreparedStatement ps1 = connection.prepareStatement(query);
			
			ps1.setString(1, car.getBrand());
			ps1.setString(2, car.getPlate());
			ps1.setDate(3, car.getYearProduction());
			ps1.setString(4, car.getUuid().toString());

			ps1.executeUpdate();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	public Car findCarByPlate(String plate) throws Exception {
		String query = "SELECT brand, plate, yearProduction, BIN_TO_UUID(uuid) FROM cars WHERE plate LIKE ?";
		PreparedStatement ps2 = connection.prepareStatement(query);

		ps2.setString(1, plate);

		ResultSet rs = ps2.executeQuery();
		Car car = null;
		while (rs.next()) {
			String brand = rs.getString(1);
			String carPlate = rs.getString(2);
			Date yearProduction = rs.getDate(3);
			UUID uuid = UUID.fromString(rs.getString(4));
			if (car == null)
				car = new Car(brand, carPlate, yearProduction, uuid);
			else {
				throw new Exception("No puede haber dos coches con la matrícula: " + carPlate);
			}
		}
		return car;
	}

	@Override
	public boolean edit(Car modifiedCar) {
		String query = "UPDATE cars SET brand = ?, yearProduction = ? WHERE plate LIKE ?";
		try {
			PreparedStatement ps3 = connection.prepareStatement(query);
			ps3.setString(1, modifiedCar.getBrand());
			ps3.setDate(2, modifiedCar.getYearProduction());
			ps3.setString(3, modifiedCar.getPlate());
			ps3.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean addNewOwner(Car modifiedCar) {
		try {
			String query = "INSERT INTO cars_owners (uuid, plate) value (UUID_TO_BIN(?), ?)";
			PreparedStatement ps4 = connection.prepareStatement(query);
			
			ps4.setString(1, modifiedCar.getUuid().toString());
			ps4.setString(2, modifiedCar.getPlate());

			ps4.executeUpdate();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/*@Override
	public void showCarTable() {
		String query = "SELECT cars.brand, cars_owners.plate, cars.yearProduction, BIN_TO_UUID(cars_owners.uuid) as uuid FROM cars INNER JOIN cars.plate = cars_owners.plate WHERE cars.uuid = ?";
	}*/
	
	
}
