package com.pFinCPHe.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pFinCPHe.model.entities.Car;
import com.pFinCPHe.model.entities.User;


public class CarModel implements ICarModel{
	private Connection connection;
	
	public CarModel() throws ClassNotFoundException, SQLException, IOException {
		this.connection = DatabaseConnection.getConnection();
	}

	@Override
	public boolean create(Car car) {
		try {
			String query = "INSERT INTO cars (brand, plate, yearProduction, uuid) value (?, ?, ?, UUID_TO_BIN(?))";
			PreparedStatement ps2 = connection.prepareStatement(query);
			
			ps2.setString(1, car.getBrand());
			ps2.setString(2, car.getPlate());
			ps2.setDate(3, car.getYearProduction());
			ps2.setDate(4, car.getYearProduction());

			ps2.executeUpdate();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}
