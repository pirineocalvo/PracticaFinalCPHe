package com.pFinCPHe;

import com.pFinCPHe.controller.MainController;

import java.io.IOException;
import java.sql.SQLException;

import com.pFinCPHe.controller.IMainController;
import com.pFinCPHe.view.LoginView;
import com.pFinCPHe.view.MainView;
import com.pFinCPHe.view.RegisterView;
import com.pFinCPHe.view.UserView;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		RegisterView registerView = new RegisterView();
		LoginView loginView = new LoginView();
		MainView mainView = new MainView();
		UserView userView = new UserView();
		IMainController mainController = new MainController(mainView, registerView, loginView, userView);
		
		mainController.showMainView();
	}
}
