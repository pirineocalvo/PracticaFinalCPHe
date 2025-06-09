package com.pFinCPHe.view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import com.pFinCPHe.controller.IMainController;
import com.pFinCPHe.model.entities.User;

public class LoginView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private static JTextField userNameField;
	private static JPasswordField passwordField;  
	private static JButton confirmButton;
	private static JButton cancelButton;

	public LoginView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);

		cancelButton = new JButton("CANCELAR");
		cancelButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		cancelButton.setBounds(577, 517, 199, 39);
		add(cancelButton);
		cancelButton.addActionListener(e -> actionPerformed(e));

		JLabel rewelcomeMessage = new JLabel("Bienvenido de nuevo, inicia sesión");
		rewelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		rewelcomeMessage.setForeground(new Color(163, 217, 165));
		rewelcomeMessage.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		rewelcomeMessage.setBounds(200, 51, 681, 54);
		add(rewelcomeMessage);

		confirmButton = new JButton("CONFIRMAR");
		confirmButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		confirmButton.setBounds(308, 517, 199, 39);
		add(confirmButton);
		confirmButton.addActionListener(e -> actionPerformed(e));

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

		userNameField = new JTextField("");
		userNameField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		userNameField.setColumns(10);
		userNameField.setBounds(465, 258, 369, 39);
		add(userNameField);

		passwordField = new JPasswordField(""); 
		passwordField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		passwordField.setColumns(10);
		passwordField.setBounds(465, 367, 369, 39);
		add(passwordField);
	}

	public static void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			String username = userNameField.getText();
			String password = new String(passwordField.getPassword());

			if (username.isBlank() || password.isBlank()) {
				JOptionPane.showMessageDialog(null,
						"Username o Password están vacíos",
						"Inicio de sesión",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			User userToValidate = new User(username, password, null);

			boolean result = mainController.login(userToValidate);

			if (result) {
				mainController.setCurrentUser(userToValidate); 
				JOptionPane.showMessageDialog(null,
						"¡Inicio de sesión correcto!",
						"Inicio de sesión",
						JOptionPane.PLAIN_MESSAGE);
				passwordField.setText("");
				mainController.showUserView();
			} else {
				JOptionPane.showMessageDialog(null,
						"Username o Password inválido",
						"Inicio de sesión",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == cancelButton) {
			passwordField.setText("");
			mainController.showMainView();
		} else {
			JOptionPane.showMessageDialog(null,
					"Accionador no controlado",
					"Inicio de sesión",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setMainController(IMainController mainController) {
		LoginView.mainController = mainController;
	}
}
