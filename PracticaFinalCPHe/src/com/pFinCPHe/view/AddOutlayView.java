package com.pFinCPHe.view;

import javax.swing.*;
import com.pFinCPHe.controller.IMainController;
import com.pFinCPHe.model.entities.Car;
import com.pFinCPHe.model.entities.Outlay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.UUID;

public class AddOutlayView extends JPanel {

	private static final long serialVersionUID = 1L;

	private IMainController mainController;

	private JTextField kilometerField;
	private JTextField dateField;
	private JTextField costField;
	private JTextField descriptionField;
	private JTextField plateField;

	private JCheckBox gasCheckBox;
	private JCheckBox itvCheckBox;
	private JCheckBox oilCheckBox;
	private JCheckBox otherCheckBox;

	private JButton addButton;
	private JButton returnButton;
	private JButton searchButton;

	public AddOutlayView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);

		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		returnButton.setBounds(635, 661, 129, 36);
		add(returnButton);
		returnButton.addActionListener(e -> actionPerformed(e));

		JLabel titleLabel = new JLabel("AÑADIR GASTOS");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(163, 217, 165));
		titleLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		titleLabel.setBounds(277, 24, 560, 49);
		add(titleLabel);

		gasCheckBox = new JCheckBox("Gasolina");
		gasCheckBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		gasCheckBox.setBounds(248, 127, 118, 36);
		add(gasCheckBox);

		itvCheckBox = new JCheckBox("ITV");
		itvCheckBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		itvCheckBox.setBounds(405, 127, 118, 36);
		add(itvCheckBox);

		oilCheckBox = new JCheckBox("Aceite");
		oilCheckBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		oilCheckBox.setBounds(577, 127, 118, 36);
		add(oilCheckBox);

		otherCheckBox = new JCheckBox("Otros");
		otherCheckBox.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		otherCheckBox.setBounds(752, 127, 118, 36);
		add(otherCheckBox);

		JLabel lblTipoDeGasto = new JLabel("Tipo de gasto");
		lblTipoDeGasto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeGasto.setForeground(new Color(163, 217, 165));
		lblTipoDeGasto.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		lblTipoDeGasto.setBounds(405, 82, 316, 49);
		add(lblTipoDeGasto);

		JLabel kilometersLabel = new JLabel("Kilometraje: ");
		kilometersLabel.setHorizontalAlignment(SwingConstants.LEFT);
		kilometersLabel.setForeground(new Color(163, 217, 165));
		kilometersLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		kilometersLabel.setBounds(248, 203, 158, 49);
		add(kilometersLabel);

		kilometerField = new JTextField();
		kilometerField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		kilometerField.setBounds(405, 212, 452, 36);
		add(kilometerField);

		JLabel dateLabel = new JLabel("Fecha: ");
		dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateLabel.setForeground(new Color(163, 217, 165));
		dateLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		dateLabel.setBounds(248, 278, 158, 49);
		add(dateLabel);

		dateField = new JTextField();
		dateField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		dateField.setBounds(405, 287, 452, 36);
		add(dateField);

		JLabel costLabel = new JLabel("Importe:");
		costLabel.setHorizontalAlignment(SwingConstants.LEFT);
		costLabel.setForeground(new Color(163, 217, 165));
		costLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		costLabel.setBounds(248, 354, 158, 49);
		add(costLabel);

		costField = new JTextField();
		costField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		costField.setBounds(405, 363, 452, 36);
		add(costField);

		JLabel descriptionLabel = new JLabel("Descripción:");
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setForeground(new Color(163, 217, 165));
		descriptionLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		descriptionLabel.setBounds(248, 454, 158, 49);
		add(descriptionLabel);

		descriptionField = new JTextField();
		descriptionField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		descriptionField.setBounds(405, 438, 452, 86);
		add(descriptionField);

		JLabel plateLabel = new JLabel("Matrícula:");
		plateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		plateLabel.setForeground(new Color(163, 217, 165));
		plateLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		plateLabel.setBounds(248, 555, 158, 49);
		add(plateLabel);

		plateField = new JTextField();
		plateField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		plateField.setBounds(405, 564, 452, 36);
		add(plateField);

		searchButton = new JButton("BUSCAR");
		searchButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		searchButton.setBounds(892, 570, 89, 23);
		add(searchButton);
		searchButton.addActionListener(e -> actionPerformed(e));

		addButton = new JButton("AÑADIR");
		addButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		addButton.setBounds(432, 661, 129, 36);
		add(addButton);
		
		JLabel formatDateLabel = new JLabel("AAAA-MM-DD");
		formatDateLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		formatDateLabel.setBounds(892, 297, 129, 14);
		add(formatDateLabel);
		addButton.addActionListener(e -> actionPerformed(e));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			Outlay.Type type = getSelectedOutlayType();
			if (type == null) {
				JOptionPane.showMessageDialog(this, "Selecciona un solo tipo de gasto.", 
						"Añadir gastos", 
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			String kmText = kilometerField.getText();
			String dateText = dateField.getText();
			String costText = costField.getText();
			String description = descriptionField.getText();
			String plate = plateField.getText();
		
			UUID uuid= mainController.getCurrentUser().getUuid();

			if (kmText.isEmpty() || dateText.isEmpty() || costText.isEmpty() ||description.isEmpty() || plate.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Completa todos los campos.", 
						"Añadir gastos", 
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			

			try {
				double kilometers = Double.parseDouble(kmText);
				Date dateData = Date.valueOf(dateText);
				double finalCost = Double.parseDouble(costText);

				Outlay outlay = new Outlay(type, kilometers, dateData, finalCost, description, plate);
				boolean result = mainController.addOutlay(outlay, uuid);
				

				if (result) {
					JOptionPane.showMessageDialog(this, "¡Gastos añadidos con éxito!", 
							"Añadir gastos", 
							JOptionPane.PLAIN_MESSAGE);
					clearFields();
					mainController.showUserView();
				} else {
					JOptionPane.showMessageDialog(this, "Ha ocurrido un error", 
							"Añadir gastos", 
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Introduce valores numéricos válidos en kilometraje e importe.", 
						"Añadir gastos", 
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.", 
						"Añadir gastos", 
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error inesperado", 
						"Añadir gastos", 
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == returnButton) {
			clearFields();
			mainController.showUserView();
		} else if (e.getSource() == searchButton) {
			String plate = plateField.getText();
			UUID uuid= mainController.getCurrentUser().getUuid();
			try {
				Car foundCar = mainController.findCarByPlate(plate, uuid);
				if (foundCar == null) {
					JOptionPane.showMessageDialog(this, "Coche no encontrado", 
							"Añadir gastos", 
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Coche encontrado", 
							"Añadir gastos", 
							JOptionPane.PLAIN_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al buscar el coche", 
						"Añadir gastos", 
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void clearFields() {
		kilometerField.setText("");
		dateField.setText("");
		costField.setText("");
		descriptionField.setText("");
		plateField.setText("");
		gasCheckBox.setSelected(false);
		itvCheckBox.setSelected(false);
		oilCheckBox.setSelected(false);
		otherCheckBox.setSelected(false);
	}

	public Outlay.Type getSelectedOutlayType() {
	    int count = 0;
	    Outlay.Type selected = null;

	    if (gasCheckBox.isSelected()) {
	        count++;
	        selected = Outlay.Type.echar_gasolina;
	    }
	    if (itvCheckBox.isSelected()) {
	        count++;
	        selected = Outlay.Type.ITV;
	    }
	    if (oilCheckBox.isSelected()) {
	        count++;
	        selected = Outlay.Type.cambio_aceite;
	    }
	    if (otherCheckBox.isSelected()) {
	        count++;
	        selected = Outlay.Type.otros;
	    }

	    if (count == 1) {
	        return selected;
	    } else {
	        return null;
	    }
	}


	public void setMainController(IMainController mainController) {
		this.mainController = mainController;
	}
}
