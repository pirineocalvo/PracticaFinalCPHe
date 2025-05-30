package com.pFinCPHe.model.entities;

import java.util.UUID;

public class User {
	private String name;
	private String password;
	private UUID uuid;
	
	public User(String name, String password, UUID uuid) {
		super();
		this.name = name;
		this.password = password;
		this.uuid = uuid;
	}
	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
