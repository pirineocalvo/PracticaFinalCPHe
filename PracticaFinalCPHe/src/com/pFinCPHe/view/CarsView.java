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
	private static IMainController mainController;
	private static JButton returnButton;

	public CarsView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(531, 646, 118, 23);
		add(returnButton);
		returnButton.addActionListener(e -> mainController.showUserView());
		
		
		JLabel carsLabel = new JLabel(/*writeLabel()*/);
		carsLabel.setVerticalAlignment(SwingConstants.TOP);
		carsLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		carsLabel.setOpaque(true);
		carsLabel.setBounds(236, 124, 658, 460);
		add(carsLabel);
		
		JLabel lblTusCoches = new JLabel("TUS COCHES");
		lblTusCoches.setHorizontalAlignment(SwingConstants.CENTER);
		lblTusCoches.setForeground(new Color(163, 217, 165));
		lblTusCoches.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblTusCoches.setBounds(293, 22, 560, 49);
		add(lblTusCoches);
	}
	
	/*public static String writeLabel() {
		UUID uuid= mainController.getCurrentUser().getUuid();
		mainController.showCarTable(uuid);
		return "";
	}*/
	
	public void setMainController(IMainController mainController) {
		CarsView.mainController=mainController;
	}
}
