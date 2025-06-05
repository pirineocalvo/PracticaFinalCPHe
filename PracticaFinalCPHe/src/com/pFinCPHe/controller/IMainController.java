package com.pFinCPHe.controller;

import java.util.UUID;

import com.pFinCPHe.model.entities.Car;
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
	void setCurrentUser(User user);
	User getCurrentUser();
	boolean edit(Car car);
	Car findCarByPlate(String plate) throws Exception;
	boolean addNewOwner(Car modifiedCar);
	String showCarTable(UUID uuid);
}
