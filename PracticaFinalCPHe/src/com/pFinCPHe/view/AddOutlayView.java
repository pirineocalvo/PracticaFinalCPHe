package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AddOutlayView extends JPanel {

	private static final long serialVersionUID = 1L;
	private IMainController mainController;

	public AddOutlayView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JButton returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(525, 644, 118, 23);
		add(returnButton);
		
		JLabel lblAadirGastos = new JLabel("AÑADIR GASTOS");
		lblAadirGastos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAadirGastos.setForeground(new Color(163, 217, 165));
		lblAadirGastos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblAadirGastos.setBounds(276, 33, 560, 49);
		add(lblAadirGastos);
		returnButton.addActionListener(e -> mainController.showUserView());

	}

	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
