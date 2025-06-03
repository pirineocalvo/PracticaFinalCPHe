package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import com.pFinCPHe.model.entities.Car;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CreateView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private static JTextField yearField;
	private static JTextField plateField;
	private static JTextField brandField;
	private static JButton confirmButton;
	private static JButton returnButton;
	
	public CreateView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JLabel createTitle = new JLabel("CREAR COCHE");
		createTitle.setHorizontalAlignment(SwingConstants.CENTER);
		createTitle.setForeground(new Color(163, 217, 165));
		createTitle.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		createTitle.setBounds(331, 35, 418, 49);
		add(createTitle);
		
		JLabel brandLabel = new JLabel("Marca: ");
		brandLabel.setHorizontalAlignment(SwingConstants.LEFT);
		brandLabel.setForeground(new Color(163, 217, 165));
		brandLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		brandLabel.setBounds(184, 160, 126, 49);
		add(brandLabel);
		
		JLabel plateLabel = new JLabel("Matrícula: ");
		plateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		plateLabel.setForeground(new Color(163, 217, 165));
		plateLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		plateLabel.setBounds(184, 271, 126, 49);
		add(plateLabel);
		
		JLabel yearLabel = new JLabel("Año de fabricación: ");
		yearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yearLabel.setForeground(new Color(163, 217, 165));
		yearLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		yearLabel.setBounds(184, 383, 220, 49);
		add(yearLabel);
		
		yearField = new JTextField();
		yearField.setBounds(401, 391, 452, 36);
		add(yearField);
		yearField.setColumns(10);
		
		plateField = new JTextField();
		plateField.setColumns(10);
		plateField.setBounds(401, 279, 452, 36);
		add(plateField);
		
		brandField = new JTextField();
		brandField.setColumns(10);
		brandField.setBounds(401, 168, 452, 36);
		add(brandField);
		
		confirmButton = new JButton("CREAR");
		confirmButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		confirmButton.setBounds(342, 513, 154, 36);
		add(confirmButton);
		confirmButton.addActionListener(e -> actionPerformed(e));
		
		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		returnButton.setBounds(576, 513, 154, 36);
		add(returnButton);
		returnButton.addActionListener(e -> actionPerformed(e));

	}
	
	public static void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirmButton) {
			String brand = brandField.getText();
			String plate = plateField.getText();
			Date yearProduction = Date.valueOf(yearField.getText()+"-01-01");
			UUID uuid= mainController.getCurrentUser().getUuid();
			
			if (brand.isBlank() || plate.isBlank() || yearField==null) {
				JOptionPane.showMessageDialog(null,
						"Completa todos los campos",
						"Crear coche",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Car car = new Car(brand, plate, yearProduction, uuid);

			boolean result = mainController.create(car);

			if (result) {
				JOptionPane.showMessageDialog(null, "¡Coche creado con éxito!",
						"Crear coche",
						JOptionPane.PLAIN_MESSAGE);
				mainController.showUserView();
				brandField.setText("");
			    plateField.setText("");
			    yearField.setText("");
			} else {
				JOptionPane.showMessageDialog(null,
						"Ha ocurrido un error",
						"Crear coche",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == returnButton) {
			brandField.setText("");
		    plateField.setText("");
		    yearField.setText("");
			mainController.showUserView();
		} else {
			JOptionPane.showMessageDialog(null,
					"Accionador no controlado",
					"Crear coche",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setMainController(IMainController mainController) {
		CreateView.mainController=mainController;
	}
}
