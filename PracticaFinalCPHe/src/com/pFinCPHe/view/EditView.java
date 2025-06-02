package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EditView extends JPanel {

	private static final long serialVersionUID = 1L;
	private IMainController mainController;

	public EditView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JButton returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(514, 659, 118, 23);
		add(returnButton);
		
		JLabel lblEditarCoche = new JLabel("EDITAR COCHE");
		lblEditarCoche.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarCoche.setForeground(new Color(163, 217, 165));
		lblEditarCoche.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblEditarCoche.setBounds(295, 23, 560, 49);
		add(lblEditarCoche);
		returnButton.addActionListener(e -> mainController.showUserView());

	}

	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
