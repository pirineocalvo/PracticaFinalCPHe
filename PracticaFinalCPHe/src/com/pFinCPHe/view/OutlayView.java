package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class OutlayView extends JPanel {

	private static final long serialVersionUID = 1L;
	private IMainController mainController;
	
	public OutlayView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JButton returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(517, 630, 118, 23);
		add(returnButton);
		
		JLabel lblTusGastos = new JLabel("TUS GASTOS");
		lblTusGastos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTusGastos.setForeground(new Color(163, 217, 165));
		lblTusGastos.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblTusGastos.setBounds(288, 31, 560, 49);
		add(lblTusGastos);
		returnButton.addActionListener(e -> mainController.showUserView());

	}

	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
