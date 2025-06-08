package com.pFinCPHe.controller;

import java.util.UUID;

import com.pFinCPHe.model.entities.Car;
import com.pFinCPHe.model.entities.Outlay;
import com.pFinCPHe.model.entities.User;

public interface IMainController {
	void showRegisterView();
	void showLoginView();
	void showMainView();
	boolean register(User user);
	boolean login(User user);
	void showUserView();
	void showCreateView();
	void showEditView();
	void showDeleteView();
	void showCarsView();
	void showAddOutlayView();
	void showOutlayView();
	boolean create(Car car);
	boolean edit(Car carToModify, UUID uuid);
	Car findCarByPlate(String plate, UUID uuid) throws Exception;
	boolean addNewOwner(Car modifiedCar);
	String showCarTable(UUID uuid);
	boolean delete(Car deletedCar, UUID uuid);
	boolean addOutlay(Outlay outlay, UUID uuid);
	String showOutlayTable(UUID uuid);
	
	void setCurrentUser(User user);
	User getCurrentUser();
}
