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

public class EditView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private static JTextField plateField;
	private static JTextField brandField;
	private static JTextField yearProductionField;
	private static JTextField ownersField;
	private static JButton confirmButton;
	private static JButton returnButton;
	private static JButton searchButton;
	private static JButton addOwnersButton;

	public EditView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		JLabel editTitle = new JLabel("EDITAR COCHE");
		editTitle.setHorizontalAlignment(SwingConstants.CENTER);
		editTitle.setForeground(new Color(163, 217, 165));
		editTitle.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		editTitle.setBounds(331, 35, 418, 49);
		add(editTitle);
		
		JLabel plateLabel = new JLabel("Coche a editar (matrícula): ");
		plateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		plateLabel.setForeground(new Color(163, 217, 165));
		plateLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		plateLabel.setBounds(184, 160, 267, 49);
		add(plateLabel);
		
		JLabel brandLabel = new JLabel("Marca: ");
		brandLabel.setHorizontalAlignment(SwingConstants.LEFT);
		brandLabel.setForeground(new Color(163, 217, 165));
		brandLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		brandLabel.setBounds(184, 274, 267, 49);
		add(brandLabel);
		
		JLabel yearProductionLabel = new JLabel("Año de producción: ");
		yearProductionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yearProductionLabel.setForeground(new Color(163, 217, 165));
		yearProductionLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		yearProductionLabel.setBounds(184, 388, 267, 49);
		add(yearProductionLabel);
		
		JLabel ownersLabel = new JLabel("Añadir propietarios: ");
		ownersLabel.setHorizontalAlignment(SwingConstants.LEFT);
		ownersLabel.setForeground(new Color(163, 217, 165));
		ownersLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		ownersLabel.setBounds(184, 500, 267, 49);
		add(ownersLabel);
		
		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		returnButton.setBounds(596, 612, 154, 36);
		add(returnButton);
		returnButton.addActionListener(e -> actionPerformed(e));
		
		confirmButton = new JButton("EDITAR");
		confirmButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 16));
		confirmButton.setBounds(354, 612, 154, 36);
		add(confirmButton);
		confirmButton.addActionListener(e -> actionPerformed(e));
		
		plateField = new JTextField();
		plateField.setColumns(10);
		plateField.setBounds(472, 168, 452, 36);
		add(plateField);
		
		brandField = new JTextField();
		brandField.setColumns(10);
		brandField.setBounds(472, 282, 452, 36);
		add(brandField);
		
		yearProductionField = new JTextField();
		yearProductionField.setColumns(10);
		yearProductionField.setBounds(472, 396, 452, 36);
		add(yearProductionField);
		
		ownersField = new JTextField();
		ownersField.setColumns(10);
		ownersField.setBounds(472, 508, 452, 36);
		add(ownersField);
		
		searchButton = new JButton("BUSCAR");
		searchButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		searchButton.setBounds(964, 175, 89, 23);
		add(searchButton);
		searchButton.addActionListener(e -> actionPerformed(e));
		
		addOwnersButton = new JButton("AÑADIR");
		addOwnersButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		addOwnersButton.setBounds(964, 515, 89, 23);
		add(addOwnersButton);
		addOwnersButton.addActionListener(e -> actionPerformed(e));

	}

	public static void actionPerformed(ActionEvent e){
		if (e.getSource() == confirmButton) {
			String brand = brandField.getText();
			String plate = plateField.getText();
			Date yearProduction = Date.valueOf(yearProductionField.getText()+"-01-01");
			UUID uuid= mainController.getCurrentUser().getUuid();
			
			
			if (brand.isBlank() || plate.isBlank() || yearProductionField==null) {
				JOptionPane.showMessageDialog(null,
						"Completa todos los campos",
						"Editar coche",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Car modifiedCar = new Car(brand, plate, yearProduction, uuid);

			boolean result = mainController.edit(modifiedCar);

			if (result) {
				JOptionPane.showMessageDialog(null, "¡Coche editado con éxito!",
						"Editar coche",
						JOptionPane.PLAIN_MESSAGE);
				mainController.showUserView();
				brandField.setText("");
				plateField.setText("");
				yearProductionField.setText("");
			} else {
				JOptionPane.showMessageDialog(null,
						"Ha ocurrido un error",
						"Editar coche",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource()==addOwnersButton) {
			String plate = plateField.getText();
			UUID newOwner= null;
			try {
				newOwner = UUID.fromString(ownersField.getText());
			}catch(IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, "Error en el UUID",
						"Editar coche",
						JOptionPane.PLAIN_MESSAGE);
				ownersField.setText("");
				return;
			}
			
			Car carWithNewOwner = new Car("", plate, null, newOwner);
			
			boolean hasOtherOwner = mainController.addNewOwner(carWithNewOwner);
			
			if(hasOtherOwner) {
				JOptionPane.showMessageDialog(null, "¡El coche tiene nuevo propietario!",
						"Editar coche",
						JOptionPane.PLAIN_MESSAGE);
				ownersField.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Error en el UUID",
						"Editar coche",
						JOptionPane.PLAIN_MESSAGE);
				ownersField.setText("");
			}
		}
		else if (e.getSource() == returnButton) {
			mainController.showUserView();
			brandField.setText("");
			plateField.setText("");
			yearProductionField.setText("");
		} else if(e.getSource() == searchButton) {
			String plate = plateField.getText();
			Car foundCar=null;
			try {
				foundCar = mainController.findCarByPlate(plate);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			if(foundCar==null) {
				JOptionPane.showMessageDialog(null,
						"Coche no encontrado",
						"Editar coche",
						JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,
						"Coche encontrado",
						"Editar coche",
						JOptionPane.PLAIN_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
					"Accionador no controlado",
					"Editar coche",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setMainController(IMainController mainController) {
		EditView.mainController=mainController;
	}
}
