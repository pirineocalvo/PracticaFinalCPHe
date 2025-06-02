package com.pFinCPHe;

import com.pFinCPHe.controller.MainController;

import java.io.IOException;
import java.sql.SQLException;

import com.pFinCPHe.controller.IMainController;
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

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		RegisterView registerView = new RegisterView();
		LoginView loginView = new LoginView();
		MainView mainView = new MainView();
		UserView userView = new UserView();
		CreateView createView = new CreateView();
		EditView editView = new EditView();
		DeleteView deleteView = new DeleteView();
		CarsView carsView = new CarsView();
		AddOutlayView addOutlayView = new AddOutlayView();
		OutlayView outlayView = new OutlayView();
		IMainController mainController = new MainController(mainView, registerView, loginView, userView, createView, editView, deleteView, carsView, addOutlayView, outlayView);
		
		mainController.showMainView();
	}
}
