package com.pFinCPHe.view;

import javax.swing.JPanel;

import com.pFinCPHe.controller.IMainController;
import com.pFinCPHe.model.entities.Car;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class DeleteView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private static JTextField plateField;
	private static JButton deleteButton;
	private static JButton searchButton;
	private static JButton returnButton;

	public DeleteView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);
		
		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		returnButton.setBounds(635, 476, 123, 36);
		add(returnButton);
		returnButton.addActionListener(e->actionPerformed(e));
		
		JLabel titleLabel = new JLabel("BORRAR COCHE");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(163, 217, 165));
		titleLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		titleLabel.setBounds(282, 25, 560, 49);
		add(titleLabel);
		
		JLabel deleteLabel = new JLabel("Coche a eliminar (matrícula): ");
		deleteLabel.setHorizontalAlignment(SwingConstants.LEFT);
		deleteLabel.setForeground(new Color(163, 217, 165));
		deleteLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 20));
		deleteLabel.setBounds(116, 303, 316, 49);
		add(deleteLabel);
		
		plateField = new JTextField();
		plateField.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		plateField.setColumns(10);
		plateField.setBounds(421, 311, 452, 36);
		add(plateField);
		
		searchButton = new JButton("BUSCAR");
		searchButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		searchButton.setBounds(922, 318, 89, 23);
		add(searchButton);
		searchButton.addActionListener(e->actionPerformed(e));
		
		deleteButton = new JButton("ELIMINAR");
		deleteButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		deleteButton.setBounds(421, 476, 123, 36);
		add(deleteButton);
		deleteButton.addActionListener(e->actionPerformed(e));
		
	}
	
	public static void actionPerformed(ActionEvent e){
		if (e.getSource() == deleteButton) {
			String plate = plateField.getText();
			UUID uuid= mainController.getCurrentUser().getUuid();

			if (plate.isBlank()) {
				JOptionPane.showMessageDialog(null,
						"Introduzca un coche",
						"Eliminar coche",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Car carForDelete = new Car(null, plate, null, uuid);

			boolean result = mainController.delete(carForDelete, uuid);

			if (result) {
				JOptionPane.showMessageDialog(null, "¡Coche eliminado con éxito!",
						"Eliminar coche",
						JOptionPane.PLAIN_MESSAGE);
				mainController.showUserView();
				plateField.setText("");
			} else {
				JOptionPane.showMessageDialog(null,
						"Ha ocurrido un error",
						"Eliminar coche",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == returnButton) {
			mainController.showUserView();
			plateField.setText("");
		} else if(e.getSource() == searchButton) {
			String plate = plateField.getText();
			UUID uuid= mainController.getCurrentUser().getUuid();
			Car foundCar=null;
			try {
				foundCar = mainController.findCarByPlate(plate, uuid);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			if(foundCar==null) {
				JOptionPane.showMessageDialog(null,
						"Coche no encontrado",
						"Eliminar coche",
						JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,
						"Coche encontrado",
						"Eliminar coche",
						JOptionPane.PLAIN_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null,
					"Accionador no controlado",
					"Eliminar coche",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setMainController(IMainController mainController) {
		DeleteView.mainController=mainController;
	}
}
