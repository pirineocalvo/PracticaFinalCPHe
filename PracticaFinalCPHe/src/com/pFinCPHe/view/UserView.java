package com.pFinCPHe.view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.pFinCPHe.controller.IMainController;

import javax.swing.JButton;

public class UserView extends JPanel {

	private static final long serialVersionUID = 1L;
	private IMainController mainController;

	public UserView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JLabel menuLabel = new JLabel("MENÚ");
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		menuLabel.setForeground(new Color(163, 217, 165));
		menuLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		menuLabel.setBounds(279, 30, 560, 49);
		add(menuLabel);
		
		JButton createButton = new JButton("CREAR COCHE");
		createButton.setBounds(300, 108, 523, 42);
		add(createButton);
		createButton.addActionListener(e -> mainController.showCreateView());
		
		JButton editButton = new JButton("EDITAR COCHES");
		editButton.setBounds(300, 187, 523, 42);
		add(editButton);
		editButton.addActionListener(e -> mainController.showEditView());
		
		JButton deleteButton = new JButton("BORRAR COCHES");
		deleteButton.setBounds(300, 265, 523, 42);
		add(deleteButton);
		deleteButton.addActionListener(e -> mainController.showDeleteView());
		
		JButton showCarsButton = new JButton("TUS COCHES");
		showCarsButton.setBounds(300, 343, 523, 42);
		add(showCarsButton);
		showCarsButton.addActionListener(e -> mainController.showCarsView());
		
		JButton addOutlayButton = new JButton("AÑADIR GASTOS");
		addOutlayButton.setBounds(300, 459, 523, 42);
		add(addOutlayButton);
		addOutlayButton.addActionListener(e -> mainController.showAddOutlayView());
		
		JButton showOutlayButton = new JButton("TUS GASTOS");
		showOutlayButton.setBounds(300, 536, 523, 42);
		add(showOutlayButton);
		showOutlayButton.addActionListener(e -> mainController.showOutlayView());
		
		JButton returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(502, 658, 118, 23);
		add(returnButton);
		returnButton.addActionListener(e -> mainController.showMainView());

	}
	
	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
