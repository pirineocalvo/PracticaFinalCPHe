package com.pFinCPHe.view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.pFinCPHe.controller.IMainController;
import javax.swing.JButton;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private IMainController mainController;
	private JTextField userNameField;
	private JTextField passwordField;

	public LoginView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JButton cancelButton = new JButton("CANCELAR");
		cancelButton.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 14));
		cancelButton.setBounds(577, 517, 199, 39);
		add(cancelButton);
		cancelButton.addActionListener(e -> mainController.showMainView());
		
		JLabel rewelcomeMessage = new JLabel("Bienvenido de nuevo, inicia sesión");
		rewelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		rewelcomeMessage.setForeground(new Color(163, 217, 165));
		rewelcomeMessage.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		rewelcomeMessage.setBounds(200, 51, 681, 54);
		add(rewelcomeMessage);
		
		JButton confirmButton = new JButton("CONFIRMAR");
		confirmButton.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 14));
		confirmButton.setBounds(308, 517, 199, 39);
		add(confirmButton);
		
		JLabel userNameLabel = new JLabel("Nombre de usuario");
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setForeground(new Color(163, 217, 165));
		userNameLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		userNameLabel.setBounds(223, 248, 263, 54);
		add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setForeground(new Color(163, 217, 165));
		passwordLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		passwordLabel.setBounds(223, 357, 263, 54);
		add(passwordLabel);
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		userNameField.setBounds(465, 258, 369, 39);
		add(userNameField);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(465, 367, 369, 39);
		add(passwordField);
	}
	
	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}

}
