package com.pFinCPHe.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pFinCPHe.model.entities.Car;
import com.pFinCPHe.model.entities.Outlay;


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

	
	public Car findCarByPlate(String plate, UUID uuid) throws Exception {
	    String query = "SELECT BIN_TO_UUID(uuid) as uuid, plate FROM cars_owners WHERE BIN_TO_UUID(uuid) LIKE ? AND plate LIKE ?";
	    PreparedStatement ps3 = connection.prepareStatement(query);

	    ps3.setString(1, uuid.toString());
	    ps3.setString(2, plate);

	    ResultSet rs = ps3.executeQuery();
	    Car car = null;
	    
	    while (rs.next()) {
	        UUID userUuid = UUID.fromString(rs.getString("uuid"));
	        String carPlate = rs.getString("plate");

	        car = new Car();
	        car.setUuid(userUuid);
	        car.setPlate(carPlate);
	    }
	    return car;
	}

	@Override
	public boolean edit(Car modifiedCar, UUID uuid) {
	    String query = "UPDATE cars " +
	                   "JOIN cars_owners ON cars.plate = cars_owners.plate " +
	                   "SET cars.brand = ?, cars.yearProduction = ? " +
	                   "WHERE cars_owners.plate = ? AND cars_owners.uuid = UUID_TO_BIN(?);";
	    try {
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, modifiedCar.getBrand());
	        ps.setDate(2, modifiedCar.getYearProduction());
	        ps.setString(3, modifiedCar.getPlate());
	        ps.setString(4, uuid.toString());

	        int rowsAffected = ps.executeUpdate();

	        return rowsAffected > 0;
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

	public boolean delete(Car deletedCar, UUID uuid) {
	    try {
	        String query1 = "DELETE FROM cars_owners WHERE plate = ? AND uuid = UUID_TO_BIN(?)";
	        PreparedStatement ps1 = connection.prepareStatement(query1);
	        ps1.setString(1, deletedCar.getPlate());
	        ps1.setString(1, deletedCar.getUuid().toString());
	        
	        int rowsAffected = ps1.executeUpdate();
	        
	        if(rowsAffected>0) {
	        	String query2 = "DELETE FROM cars WHERE plate = ?";
	 	        PreparedStatement ps2 = connection.prepareStatement(query2);
	 	        ps2.setString(1, deletedCar.getPlate());
	 	        
	 	        ps2.executeUpdate();
	 	        return true;
	        }
	        
	        return false;
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	public boolean addOutlay(Outlay outlay, UUID uuid) {
	    String checkPlateQuery = "SELECT 1 FROM cars_owners WHERE plate = ? AND uuid = UUID_TO_BIN(?)";
	    String insertQuery = "INSERT INTO outlays (type, kilometers, dateData, finalCost, optionalDescription, plate) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement checkPs = connection.prepareStatement(checkPlateQuery);
	        PreparedStatement insertPs = connection.prepareStatement(insertQuery);
	    
	        checkPs.setString(1, outlay.getPlate());
	        checkPs.setString(2, uuid.toString());
	        
	        ResultSet rs = checkPs.executeQuery();
	        if (rs.next()) {
	        	insertPs.setString(1, outlay.getType() != null ? outlay.getType().toString() : null);
	 	        insertPs.setDouble(2, outlay.getKilometers());
	 	        insertPs.setDate(3, outlay.getDateData());
	 	        insertPs.setDouble(4, outlay.getFinalCost());
	 	        insertPs.setString(5, outlay.getOptionalDescription());
	 	        insertPs.setString(6, outlay.getPlate());

	 	        insertPs.executeUpdate();
	 	        return true;
	        }

	       return false;

	    } catch (Exception ex) {
	        return false;
	    }
	}

	@Override
	public String showOutlayTable(UUID uuid) {
	    StringBuilder result = new StringBuilder();

	    String query = "SELECT outlays.type, outlays.kilometers, outlays.dateData, outlays.finalCost, outlays.optionalDescription, outlays.plate FROM outlays INNER JOIN cars_owners ON outlays.plate = cars_owners.plate WHERE cars_owners.uuid = UUID_TO_BIN(?)";

	    try {
	    	PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, uuid.toString());
	        ResultSet rs = ps.executeQuery();

	        result.append("<table border='1' cellspacing='0' cellpadding='4'>");
	        result.append("<tr><th>Tipo</th><th>Kilómetros</th><th>Fecha</th><th>Coste</th><th>Descripción</th><th>Matrícula</th></tr>");

	        boolean hasRows = false;

	        while (rs.next()) {
	            hasRows = true;
	            String type = rs.getString("type");
	            double kilometers = rs.getDouble("kilometers");
	            Date dateData = rs.getDate("dateData");
	            double finalCost = rs.getDouble("finalCost");
	            String description = rs.getString("optionalDescription");
	            String plate = rs.getString("plate");
	            
	            result.append("<tr>")
	                  .append("<td>").append(type).append("</td>")
	                  .append("<td>").append(kilometers).append("</td>")
	                  .append("<td>").append(dateData).append("</td>")
	                  .append("<td>").append(finalCost).append("</td>")
	                  .append("<td>").append(description).append("</td>")
	                  .append("<td>").append(plate).append("</td>")
	                  .append("</tr>");
	        }

	        result.append("</table>");

	        if (!hasRows) {
	            return "<i>No tienes gastos registrados</i>";
	        }

	        return result.toString();

	    } catch (SQLException e) {
	        return "<b>Error al obtener los gastos.</b>";
	    }
	}

	
	
}
