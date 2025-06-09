package com.pFinCPHe.view;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;
import com.pFinCPHe.controller.IMainController;

public class OutlayView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static IMainController mainController;
	private static JButton returnButton;
	private JLabel outlaysLabel;

	// Campos de filtro
	private JTextField yearField;
	private JTextField dateFromField;
	private JTextField dateToField;
	private JTextField kmMinField;
	private JTextField kmMaxField;
	private JButton filterButton;

	public OutlayView() {
		setBackground(new Color(14, 77, 100));
		setLayout(null);

		// Botón volver
		returnButton = new JButton("VOLVER");
		returnButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 11));
		returnButton.setBounds(531, 646, 118, 23);
		add(returnButton);
		returnButton.addActionListener(e -> {
			if (mainController != null) {
				mainController.showUserView();
			}
		});

		// Título
		JLabel titleLabel = new JLabel("TUS COCHES");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(163, 217, 165));
		titleLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		titleLabel.setBounds(293, 22, 560, 49);
		add(titleLabel);

		// Área para mostrar los datos
		outlaysLabel = new JLabel();
		outlaysLabel.setVerticalAlignment(SwingConstants.TOP);
		outlaysLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
		outlaysLabel.setOpaque(true);
		outlaysLabel.setBackground(Color.WHITE);
		outlaysLabel.setBounds(236, 124, 658, 460);
		add(outlaysLabel);

		// Filtros
		JLabel yearLabel = new JLabel("Año:");
		yearLabel.setForeground(Color.WHITE);
		yearLabel.setBounds(20, 100, 50, 20);
		add(yearLabel);
		yearField = new JTextField();
		yearField.setBounds(70, 100, 100, 20);
		add(yearField);

		JLabel dateFromLabel = new JLabel("Desde (YYYY-MM-DD):");
		dateFromLabel.setForeground(Color.WHITE);
		dateFromLabel.setBounds(20, 130, 150, 20);
		add(dateFromLabel);
		dateFromField = new JTextField();
		dateFromField.setBounds(170, 130, 100, 20);
		add(dateFromField);

		JLabel dateToLabel = new JLabel("Hasta (YYYY-MM-DD):");
		dateToLabel.setForeground(Color.WHITE);
		dateToLabel.setBounds(20, 160, 150, 20);
		add(dateToLabel);
		dateToField = new JTextField();
		dateToField.setBounds(170, 160, 100, 20);
		add(dateToField);

		JLabel kmMinLabel = new JLabel("Km mínimo:");
		kmMinLabel.setForeground(Color.WHITE);
		kmMinLabel.setBounds(20, 190, 100, 20);
		add(kmMinLabel);
		kmMinField = new JTextField();
		kmMinField.setBounds(120, 190, 100, 20);
		add(kmMinField);

		JLabel kmMaxLabel = new JLabel("Km máximo:");
		kmMaxLabel.setForeground(Color.WHITE);
		kmMaxLabel.setBounds(20, 220, 100, 20);
		add(kmMaxLabel);
		kmMaxField = new JTextField();
		kmMaxField.setBounds(120, 220, 100, 20);
		add(kmMaxField);

		// Botón para aplicar filtros
		filterButton = new JButton("Aplicar filtros");
		filterButton.setBounds(70, 260, 150, 25);
		add(filterButton);
		filterButton.addActionListener(e -> applyFilters());
	}

	public void setMainController(IMainController controller) {
		mainController = controller;
	}

	public void refreshOutlayList() {
		if (mainController == null || mainController.getCurrentUser() == null) {
			outlaysLabel.setText("<html><i>No hay usuario activo.</i></html>");
			return;
		}
		UUID uuid = mainController.getCurrentUser().getUuid();
		String labelContent = mainController.showOutlayTable(uuid);
		outlaysLabel.setText("<html>" + labelContent.replace("\n", "<br>") + "</html>");
	}

	public void showfilter(String contenidoHTML) {
		outlaysLabel.setText("<html>" + contenidoHTML.replace("\n", "<br>") + "</html>");
	}

	private void applyFilters() {
		String year = yearField.getText().trim();
		String dateFrom = dateFromField.getText().trim();
		String dateTo = dateToField.getText().trim();
		String kmMin = kmMinField.getText().trim();
		String kmMax = kmMaxField.getText().trim();

		int filledFields = 0;

		if (!year.isEmpty()) {
			filledFields++;
		}
		
		if (!dateFrom.isEmpty() || !dateTo.isEmpty()) {
			filledFields++;
		}
		
		if (!kmMin.isEmpty() || !kmMax.isEmpty()) {
			filledFields++;
		}

		if (filledFields > 1) {
			JOptionPane.showMessageDialog(this, "Solo puedes filtrar por un campo a la vez.", 
					"Tus gastos", 
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (mainController == null || mainController.getCurrentUser() == null) {
			JOptionPane.showMessageDialog(this, "No hay usuario activo.", 
					"Tus gastos", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (filledFields == 0) {
			refreshOutlayList();
			return;
		}

		// Aplicar filtro correspondiente
		if (!year.isEmpty()) {
			mainController.filterByYear(year);
		} else if (!dateFrom.isEmpty() || !dateTo.isEmpty()) {
			mainController.filterByDate(dateFrom, dateTo);
		} else if (!kmMin.isEmpty() || !kmMax.isEmpty()) {
			mainController.filterByKm(kmMin, kmMax);
		}
	}
}
