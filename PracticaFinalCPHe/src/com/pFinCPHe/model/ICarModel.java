package com.pFinCPHe.model;

import java.util.UUID;

import com.pFinCPHe.model.entities.Car;

public interface ICarModel {
	boolean create(Car car);
	boolean edit(Car modifiedCar);
	Car findCarByPlate(String plate) throws Exception;
	boolean addNewOwner(Car modifiedCar);
	String showCarTable(UUID uuid);
	boolean delete(Car deletedCar);
}
