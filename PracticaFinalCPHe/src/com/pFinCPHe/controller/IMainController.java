package com.pFinCPHe.controller;

import com.pFinCPHe.model.entities.User;

public interface IMainController {
	void showRegisterView();
	void showLoginView();
	void showMainView();
	boolean register(User user);
}
