package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DeleteView extends JPanel {

	private static final long serialVersionUID = 1L;
	private IMainController mainController;

	public DeleteView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JButton returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(517, 648, 118, 23);
		add(returnButton);
		
		JLabel lblBorrarCoche = new JLabel("BORRAR COCHE");
		lblBorrarCoche.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrarCoche.setForeground(new Color(163, 217, 165));
		lblBorrarCoche.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		lblBorrarCoche.setBounds(282, 25, 560, 49);
		add(lblBorrarCoche);
		returnButton.addActionListener(e -> mainController.showUserView());

	}

	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
