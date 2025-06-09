package com.pFinCPHe.model;

import java.util.UUID;

import com.pFinCPHe.model.entities.Car;
import com.pFinCPHe.model.entities.Outlay;

public interface ICarModel {
	boolean create(Car car);
	boolean edit(Car modifiedCar, UUID uuid);
	Car findCarByPlate(String plate, UUID uuid) throws Exception;
	boolean addNewOwner(Car modifiedCar);
	String showCarTable(UUID uuid);
	boolean delete(Car deletedCar, UUID uuid);
	boolean addOutlay(Outlay outlay, UUID uuid);
	String showOutlayTable(UUID uuid);
	String filterByYear(UUID uuid, String year);
	String filterByDate(UUID uuid, String dateFrom, String dateTo);
	String filterByKm(UUID uuid, String kmMin, String kmMax);
}
