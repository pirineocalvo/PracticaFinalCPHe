package com.pFinCPHe.model.entities;

import java.sql.Date;

public class Outlay {
	public enum Type{echar_gasolina, ITV, cambio_aceite, otros};
	
	private Type type;
	private double kilometers;
	private Date dateData;
	private double finalCost;
	private String optionalDescription;
	private String plate;
	
	public Outlay(Type type, double kilometers, Date dateData, double finalCost, String optionalDescription, String plate) {
		super();
		this.type = type;
		this.kilometers = kilometers;
		this.dateData = dateData;
		this.finalCost = finalCost;
		this.optionalDescription = optionalDescription;
		this.plate = plate;
	}

	public double getKilometers() {
		return kilometers;
	}

	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}

	public Date getDateData() {
		return dateData;
	}

	public void setDateData(Date dateData) {
		this.dateData = dateData;
	}

	public double getFinalCost() {
		return finalCost;
	}

	public void setFinalCost(double finalCost) {
		this.finalCost = finalCost;
	}

	public String getOptionalDescription() {
		return optionalDescription;
	}

	public void setOptionalDescription(String optionalDescription) {
		this.optionalDescription = optionalDescription;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
