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
	        String query1 = "INSERT INTO cars (brand, plate, yearProduction) VALUES (?, ?, ?)";
	        PreparedStatement ps1 = connection.prepareStatement(query1);
	        ps1.setString(1, car.getBrand());
	        ps1.setString(2, car.getPlate());
	        ps1.setDate(3, car.getYearProduction());
	        ps1.executeUpdate();

	        String query2 = "INSERT INTO cars_owners (uuid, plate) VALUES (UUID_TO_BIN(?), ?)";
	        PreparedStatement ps2 = connection.prepareStatement(query2);
	        ps2.setString(1, car.getUuid().toString());
	        ps2.setString(2, car.getPlate());
	        ps2.executeUpdate();
	    } catch (Exception ex) {
	        return false;
	    }
	    return true;
	}

	
	public Car findCarByPlate(String plate) throws Exception {
		String query = "SELECT brand, plate, yearProduction FROM cars WHERE plate LIKE ?";
		PreparedStatement ps3 = connection.prepareStatement(query);

		ps3.setString(1, plate);

		ResultSet rs = ps3.executeQuery();
		Car car = null;
		while (rs.next()) {
			String brand = rs.getString(1);
			String carPlate = rs.getString(2);
			Date yearProduction = rs.getDate(3);
			if (car == null)
				car = new Car(brand, carPlate, yearProduction, null);
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
			PreparedStatement ps4 = connection.prepareStatement(query);
			ps4.setString(1, modifiedCar.getBrand());
			ps4.setDate(2, modifiedCar.getYearProduction());
			ps4.setString(3, modifiedCar.getPlate());
			ps4.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean addNewOwner(Car modifiedCar) {
		try {
			String query = "INSERT INTO cars_owners (uuid, plate) value (UUID_TO_BIN(?), ?)";
			PreparedStatement ps5 = connection.prepareStatement(query);
			
			ps5.setString(1, modifiedCar.getUuid().toString());
			ps5.setString(2, modifiedCar.getPlate());

			ps5.executeUpdate();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public String showCarTable(UUID uuid) {
		String query = "SELECT cars.brand, cars.plate, cars.yearProduction, BIN_TO_UUID(cars_owners.uuid) as uuid FROM cars INNER JOIN cars_owners ON cars.plate = cars_owners.plate WHERE cars_owners.uuid = ?";
		
		return query;
	}
	
	
}
