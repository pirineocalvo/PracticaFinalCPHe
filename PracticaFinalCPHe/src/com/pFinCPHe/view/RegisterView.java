package com.pFinCPHe.view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import com.pFinCPHe.controller.IMainController;
import com.pFinCPHe.model.entities.User;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.util.UUID;

public class RegisterView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private JPanel registerPanel;
	private static JTextField userField;
	private static JTextField passwordField;
	private static JButton submitButton;
	private static JButton cancelButton;
	private static JTextField passwordConfirmField;
	private JLabel compulsaryLabel1;
	private JLabel compulsaryLabel2;
	private JLabel compulsaryLabel3;

	public RegisterView() {
		setBackground(new Color(14, 77, 100));
		registerPanel = new JPanel();
		registerPanel.setBackground(new Color(14, 77, 100));
		registerPanel.setLayout(null);
		setLayout(null);
		
		JLabel welcomeMessage = new JLabel("Bienvenido, crea tu cuenta");
		welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessage.setForeground(new Color(163, 217, 165));
		welcomeMessage.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		welcomeMessage.setBounds(315, 31, 501, 54);
		add(welcomeMessage);
		
		JLabel userNameLabel = new JLabel("Nombre de usuario");
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userNameLabel.setForeground(new Color(163, 217, 165));
		userNameLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		userNameLabel.setBounds(213, 165, 263, 54);
		add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setForeground(new Color(163, 217, 165));
		passwordLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		passwordLabel.setBounds(213, 245, 263, 54);
		add(passwordLabel);
		
		userField = new JTextField("");
		userField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		userField.setBounds(447, 175, 369, 39);
		add(userField);
		userField.setColumns(10);
		
		passwordField = new JTextField("");
		passwordField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		passwordField.setColumns(10);
		passwordField.setBounds(447, 255, 369, 39);
		add(passwordField);
		
		cancelButton = new JButton("CANCELAR");
		cancelButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		cancelButton.setBounds(587, 462, 199, 39);
		add(cancelButton);
		cancelButton.addActionListener(e -> actionPerformed(e));
		
		submitButton = new JButton("CONFIRMAR");
		submitButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		submitButton.setBounds(326, 462, 199, 39);
		add(submitButton);
		submitButton.addActionListener(e -> actionPerformed(e));
		
		JLabel confirmPasswordLabel = new JLabel("Confirmar contraseña");
		confirmPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		confirmPasswordLabel.setForeground(new Color(163, 217, 165));
		confirmPasswordLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		confirmPasswordLabel.setBounds(213, 331, 263, 54);
		add(confirmPasswordLabel);
		
		passwordConfirmField = new JTextField("");
		passwordConfirmField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		passwordConfirmField.setColumns(10);
		passwordConfirmField.setBounds(447, 335, 369, 39);
		add(passwordConfirmField);
		
		compulsaryLabel1 = new JLabel("*");
		compulsaryLabel1.setForeground(new Color(255, 0, 0));
		compulsaryLabel1.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
		compulsaryLabel1.setBounds(826, 176, 46, 14);
		add(compulsaryLabel1);
		
		compulsaryLabel2 = new JLabel("*");
		compulsaryLabel2.setForeground(Color.RED);
		compulsaryLabel2.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
		compulsaryLabel2.setBounds(826, 255, 46, 14);
		add(compulsaryLabel2);
		
		compulsaryLabel3 = new JLabel("*");
		compulsaryLabel3.setForeground(Color.RED);
		compulsaryLabel3.setFont(new Font("Microsoft Tai Le", Font.BOLD, 17));
		compulsaryLabel3.setBounds(826, 331, 46, 14);
		add(compulsaryLabel3);
		
		JLabel userInformation = new JLabel("<html>"
												+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REQUESITOS"
												+ "<ul>"
													+ "<li>Nombre de usuario: Máximo 15 caracteres</li>"
													+ "<li>Contraseña: Máximo 100 caracteres</li>"
												+ "</ul>"
											+ "</html>");
		userInformation.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		userInformation.setBounds(854, 175, 284, 112);
		add(userInformation);
		
	}
	
	public static void actionPerformed(ActionEvent e){
		if (e.getSource() == submitButton) {
			String username = userField.getText();
			String password = passwordField.getText();
			String passwordConfirm = passwordConfirmField.getText();

			if (username.isBlank() || password.isBlank()) {
				JOptionPane.showMessageDialog(null,
						"Username o Password están vacíos",
						"Registro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!password.equals(passwordConfirm)) {
				JOptionPane.showMessageDialog(null,
						"Las contraseñas no coinciden",
						"Registro",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			User user = new User(username, password,  UUID.randomUUID());

			boolean result = mainController.register(user);

			if (result) {
				mainController.setCurrentUser(user);
				JOptionPane.showMessageDialog(null, "¡Registro correcto!",
						"Registro",
						JOptionPane.PLAIN_MESSAGE);
				mainController.showUserView();
				userField.setText("");
				passwordField.setText("");
				passwordConfirmField.setText("");
			} else {
				JOptionPane.showMessageDialog(null,
						"Username o Password inválido",
						"Registro",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == cancelButton) {
			userField.setText("");
			passwordField.setText("");
			passwordConfirmField.setText("");
			mainController.showMainView();
		} else {
			JOptionPane.showMessageDialog(null,
					"Accionador no controlado",
					"Registro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public void setMainController(IMainController mainController) {
		RegisterView.mainController=mainController;
	}
}
