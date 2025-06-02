package com.pFinCPHe.model.entities;

import java.sql.Date;
import java.util.UUID;

public class Car {
	private String brand;
	private String plate;
	private Date yearProduction;
	private UUID uuid;
	
	public Car(String brand, String plate, Date yearProduction, UUID uuid) {
		super();
		this.brand = brand;
		this.plate = plate;
		this.yearProduction = yearProduction;
		this.uuid = uuid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Date getYearProduction() {
		return yearProduction;
	}

	public void setYearProduction(Date yearProduction) {
		this.yearProduction = yearProduction;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
