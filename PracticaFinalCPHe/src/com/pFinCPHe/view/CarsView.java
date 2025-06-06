package com.pFinCPHe.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

import com.pFinCPHe.controller.IMainController;

public class CarsView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private static JButton returnButton;
	private JLabel carsLabel;

	public CarsView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(531, 646, 118, 23);
		add(returnButton);
		returnButton.addActionListener(e -> {
			if (mainController != null) {
				mainController.showUserView();
			}
		});
		
		carsLabel = new JLabel();
		carsLabel.setVerticalAlignment(SwingConstants.TOP);
		carsLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		carsLabel.setOpaque(true);
		carsLabel.setBackground(Color.WHITE); 
		carsLabel.setBounds(236, 124, 658, 460);
		add(carsLabel);
		
		JLabel lblTusCoches = new JLabel("TUS COCHES");
		lblTusCoches.setHorizontalAlignment(SwingConstants.CENTER);
		lblTusCoches.setForeground(new Color(163, 217, 165));
		lblTusCoches.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblTusCoches.setBounds(293, 22, 560, 49);
		add(lblTusCoches);
	}

	public void setMainController(IMainController mainController) {
		CarsView.mainController = mainController;
	}

	public void refreshCarList() {
		if (mainController == null || mainController.getCurrentUser() == null) {
			carsLabel.setText("<html><i>No hay usuario activo.</i></html>");
			return;
		}
		UUID uuid = mainController.getCurrentUser().getUuid();
		String labelContent = mainController.showCarTable(uuid);
		carsLabel.setText("<html>" + labelContent.replace("\n", "<br>") + "</html>");
	}
}
