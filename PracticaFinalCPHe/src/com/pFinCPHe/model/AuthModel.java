package com.pFinCPHe.model;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.pFinCPHe.model.entities.User;

public class AuthModel implements IAuthModel {

	private Connection connection;
	private StrongPasswordEncryptor passwordEncryptor;
	
	public AuthModel() throws ClassNotFoundException, SQLException, IOException {
		
		this.connection = DatabaseConnection.getConnection();
		this.passwordEncryptor= new StrongPasswordEncryptor();
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
	
	public boolean login(User user) {
		String query = "SELECT name, password, uuid FROM users WHERE name like ?";
		
		try {
			PreparedStatement ps2 = connection.prepareStatement(query);

			ps2.setString(1, user.getName());
			
			
			ResultSet rs = ps2.executeQuery();
			
			if (rs.next()) {
				String password = rs.getString(2);
				boolean result = passwordEncryptor.checkPassword(user.getPassword(), password);
				return result;
			} else {
	            return false;
	        }
		} catch (Exception e) {
            return false;
		}
	}
	
	public static UUID bytesToUUID(byte[] bytes) {
	    ByteBuffer bb = ByteBuffer.wrap(bytes);
	    long high = bb.getLong();
	    long low = bb.getLong();
	    return new UUID(high, low);
	}
}
