package com.pFinCPHe.model;

import com.pFinCPHe.model.entities.User;

public interface IAuthModel {
	boolean register(User user);
	boolean login(User user);
}
