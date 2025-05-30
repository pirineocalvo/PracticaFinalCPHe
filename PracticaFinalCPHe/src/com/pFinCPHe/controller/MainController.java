package com.pFinCPHe.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.pFinCPHe.model.AuthModel;
import com.pFinCPHe.model.IAuthModel;
import com.pFinCPHe.model.entities.User;
import com.pFinCPHe.view.LoginView;
import com.pFinCPHe.view.MainView;
import com.pFinCPHe.view.RegisterView;
import com.pFinCPHe.view.UserView;

public class MainController implements IMainController{
	private MainView mainView;
	private RegisterView registerView;
	private LoginView loginView;
	private StrongPasswordEncryptor passwordEncryptor;
	private IAuthModel authModel;
	private UserView userView;

	
	public MainController(MainView mainView, RegisterView registerView, LoginView loginView, UserView userView) throws ClassNotFoundException, SQLException, IOException {
		mainView.setMainController(this);
		loginView.setMainController(this);
		registerView.setMainController(this);
		userView.setMainController(this);
		this.mainView=mainView;
		this.loginView=loginView;
		this.registerView=registerView;
		this.userView=userView;
		this.passwordEncryptor = new StrongPasswordEncryptor();
		this.authModel = new AuthModel();
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
	
	public boolean register(User user) {
		String encrypted = this.passwordEncryptor.encryptPassword(user.getPassword());

		user.setPassword(encrypted);
		
		boolean result = this.authModel.register(user);
		
		return result;
	}
	
	public boolean login(User user) {
		boolean result = this.authModel.login(user);
		return result;
	}
	
}
