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
		StringBuilder result = new StringBuilder();
		
		String query = "SELECT cars.brand, cars.plate, cars.yearProduction FROM cars INNER JOIN cars_owners ON cars.plate = cars_owners.plate WHERE cars_owners.uuid = UUID_TO_BIN(?)";
		
		try {
			PreparedStatement ps6 = connection.prepareStatement(query);
			
			ps6.setString(1, uuid.toString());
			
		    ResultSet rs = ps6.executeQuery();
		    
		    result.append("<table border='1' cellspacing='0' cellpadding='4'>");
            result.append("<tr><th>Marca</th><th>Matrícula</th><th>Año de producción</th></tr>");
            
            boolean hasRows = false;
            
		    while (rs.next()) {
		    	hasRows=true;
		            String brand = rs.getString("brand");
		            String plate = rs.getString("plate");
		            Date year = rs.getDate("yearProduction");
		            
		            result.append("<tr>")
	                  .append("<td>").append(brand).append("</td>")
	                  .append("<td>").append(plate).append("</td>")
	                  .append("<td>").append(year.toString()).append("</td>")
	                  .append("</tr>");
		        }
		    
		    result.append("</table>");
		    
		    if (!hasRows) {
		    	return "<i>No tienes coches registrados</i>";
		    }

			return result.toString();
			
		} catch (SQLException e) {
			return "<b>Error al obtener los coches.</b>";
		}
	}

	public boolean delete(Car deletedCar) {
	    try {
	        String query1 = "DELETE FROM cars_owners WHERE plate = ?";
	        PreparedStatement ps1 = connection.prepareStatement(query1);
	        ps1.setString(1, deletedCar.getPlate());
	        ps1.executeUpdate();

	        String query2 = "DELETE FROM cars WHERE plate = ?";
	        PreparedStatement ps2 = connection.prepareStatement(query2);
	        ps2.setString(1, deletedCar.getPlate());
	        ps2.executeUpdate();

	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	
	
}
