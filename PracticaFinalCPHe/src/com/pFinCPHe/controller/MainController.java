package com.pFinCPHe.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.pFinCPHe.model.AuthModel;
import com.pFinCPHe.model.CarModel;
import com.pFinCPHe.model.IAuthModel;
import com.pFinCPHe.model.ICarModel;
import com.pFinCPHe.model.entities.Car;
import com.pFinCPHe.model.entities.Outlay;
import com.pFinCPHe.model.entities.User;
import com.pFinCPHe.view.AddOutlayView;
import com.pFinCPHe.view.CarsView;
import com.pFinCPHe.view.CreateView;
import com.pFinCPHe.view.DeleteView;
import com.pFinCPHe.view.EditView;
import com.pFinCPHe.view.LoginView;
import com.pFinCPHe.view.MainView;
import com.pFinCPHe.view.OutlayView;
import com.pFinCPHe.view.RegisterView;
import com.pFinCPHe.view.UserView;

public class MainController implements IMainController{
	private MainView mainView;
	private RegisterView registerView;
	private LoginView loginView;
	private StrongPasswordEncryptor passwordEncryptor;
	private IAuthModel authModel;
	private UserView userView;
	private CreateView createView;
	private EditView editView;
	private DeleteView deleteView;
	private CarsView carsView;
	private AddOutlayView addOutlayView;
	private OutlayView outlayView;
	private ICarModel carModel;
	
	private User currentUser;

	
	public MainController(MainView mainView, RegisterView registerView, LoginView loginView, 
								UserView userView, 
									CreateView createView, EditView editView, DeleteView deleteView, CarsView carsView, AddOutlayView addOutlayView, OutlayView outlayView
						) throws ClassNotFoundException, SQLException, IOException {
		mainView.setMainController(this);
		loginView.setMainController(this);
		registerView.setMainController(this);
		userView.setMainController(this);
		createView.setMainController(this);
		editView.setMainController(this);
		deleteView.setMainController(this);
		carsView.setMainController(this);
		addOutlayView.setMainController(this);
		outlayView.setMainController(this);
		this.mainView=mainView;
		this.loginView=loginView;
		this.registerView=registerView;
		this.userView=userView;
		this.createView=createView;
		this.editView=editView;
		this.deleteView=deleteView;
		this.carsView=carsView;
		this.addOutlayView=addOutlayView;
		this.outlayView=outlayView;
		this.passwordEncryptor = new StrongPasswordEncryptor();
		this.authModel = new AuthModel();
		this.carModel = new CarModel();
	}
	
	public void showMainView() {
		mainView.showFirstView();
	}
	
	public void showRegisterView() {
		mainView.setContentPanel(registerView);
	}
	
	public void showLoginView() {
		mainView.setContentPanel(loginView);
	}
	
	public void showUserView() {
		mainView.setContentPanel(userView);
	}
	
	public void showCreateView() {
		mainView.setContentPanel(createView);
	}
	
	public void showEditView() {
		mainView.setContentPanel(editView);
	}
	
	public void showDeleteView() {
		mainView.setContentPanel(deleteView);
	}
	
	public void showCarsView() {
		carsView.refreshCarList();
		mainView.setContentPanel(carsView);
	}
	
	public void showAddOutlayView() {
		mainView.setContentPanel(addOutlayView);
	}
	
	public void showOutlayView() {
		outlayView.refreshOutlayList();
		mainView.setContentPanel(outlayView);
	}
	
	public boolean register(User user) {
		String encrypted = this.passwordEncryptor.encryptPassword(user.getPassword());

		user.setPassword(encrypted);
		
		boolean result = this.authModel.register(user);
		
		return result;
	}
	
	public boolean login(User user) {
		boolean result = this.authModel.login(user);
		setCurrentUser(user);
		return result;
	}
	
	public boolean create(Car car) {
		boolean result = this.carModel.create(car);
		return result;
	}
	
	public boolean edit(Car modifiedCar, UUID uuid) {
		boolean result = this.carModel.edit(modifiedCar, uuid);
		return result;
	}
	
	public boolean delete(Car deletedCar, UUID uuid) {
		boolean result = this.carModel.delete(deletedCar, uuid);
		return result;
	}
	
	public Car findCarByPlate(String plate, UUID uuid) throws Exception {
		return this.carModel.findCarByPlate(plate, uuid);
	}
	
	public boolean addNewOwner(Car modifiedCar) {
		return this.carModel.addNewOwner(modifiedCar);
	}
	
	public String showCarTable(UUID uuid) {
		return this.carModel.showCarTable(uuid);
	}

	@Override
	public boolean addOutlay(Outlay outlay, UUID uuid) {
		return this.carModel.addOutlay(outlay, uuid);
	}
	
	public String showOutlayTable(UUID uuid) {
		return this.carModel.showOutlayTable(uuid);
	}
	
	public void filterByYear(String year) {
		if (currentUser == null) return;
		UUID uuid = currentUser.getUuid();
		String result = carModel.filterByYear(uuid, year);
		outlayView.showfilter(result);
	}

	public void filterByDate(String dateFrom, String dateTo) {
		if (currentUser == null) return;
		UUID uuid = currentUser.getUuid();
		String result = carModel.filterByDate(uuid, dateFrom, dateTo);
		outlayView.showfilter(result);
	}

	public void filterByKm(String kmMin, String kmMax) {
		if (currentUser == null) return;
		UUID uuid = currentUser.getUuid();
		String result = carModel.filterByKm(uuid, kmMin, kmMax);
		outlayView.showfilter(result);
	}

	
	public void setCurrentUser(User currentUser) {
	    this.currentUser = currentUser;
	}

	public User getCurrentUser() {
	    return currentUser;
	}
}
