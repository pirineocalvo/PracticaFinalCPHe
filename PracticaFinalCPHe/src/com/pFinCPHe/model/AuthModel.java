package com.pFinCPHe.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pFinCPHe.model.entities.User;

public class AuthModel implements IAuthModel {

	private Connection connection;
	
	public AuthModel() throws ClassNotFoundException, SQLException, IOException {
		
		this.connection = DatabaseConnection.getConnection();
	}
	
	public boolean register(User user) {
		try {
			String query = "INSERT INTO users (name, password) value (?, ?)";
			PreparedStatement ps1 = connection.prepareStatement(query);
			
			ps1.setString(1, user.getName());
			ps1.setString(2, user.getPassword());

			ps1.executeUpdate();
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}


}
