package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CarsView extends JPanel {

	private static final long serialVersionUID = 1L;
	private IMainController mainController;

	public CarsView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JButton returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(531, 646, 118, 23);
		add(returnButton);
		
		JLabel lblTusCoches = new JLabel("TUS COCHES");
		lblTusCoches.setHorizontalAlignment(SwingConstants.CENTER);
		lblTusCoches.setForeground(new Color(163, 217, 165));
		lblTusCoches.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblTusCoches.setBounds(293, 22, 560, 49);
		add(lblTusCoches);
		returnButton.addActionListener(e -> mainController.showUserView());

	}

	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
