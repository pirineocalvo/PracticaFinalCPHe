package com.pFinCPHe.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pFinCPHe.controller.IMainController;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private IMainController mainController;

	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 743);
		mainPanel = new JPanel();
		mainPanel.setForeground(new Color(255, 255, 255));
		mainPanel.setBackground(new Color(14, 77, 100));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(mainPanel);
		showFirstView();
		}
	
	public void showFirstView() {
		mainPanel.removeAll();
		mainPanel.setLayout(null);
		
		JLabel appTitle = new JLabel("CONCESIONARIO");
		appTitle.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 40));
		appTitle.setHorizontalAlignment(SwingConstants.CENTER);
		appTitle.setForeground(new Color(163, 217, 165));
		appTitle.setBounds(280, 67, 560, 49);
		mainPanel.add(appTitle);
		
		JButton registerButton = new JButton("REGISTRARSE");
		registerButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		registerButton.setBounds(390, 356, 347, 68);
		mainPanel.add(registerButton);
		
		JButton loginButton = new JButton("INICIAR SESIÓN");
		loginButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		loginButton.setBounds(390, 240, 347, 68);
		mainPanel.add(loginButton);
		
		JButton quitButton = new JButton("SALIR");
		quitButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 14));
		quitButton.setBounds(503, 493, 128, 39);
		mainPanel.add(quitButton);
		
		loginButton.addActionListener(e->{
			mainController.showLoginView();
		});
		
		registerButton.addActionListener(e->{
			mainController.showRegisterView();
		});
		
		quitButton.addActionListener(e -> {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(quitButton);
			
			JDialog dialog = new JDialog(frame, "Confirmar salida", true); // true = modal

			JLabel label = new JLabel("¿Estás seguro de que quieres salir?");
			JButton yesButton = new JButton("Sí");
			JButton noButton = new JButton("No");

			yesButton.addActionListener(ev -> {
				dialog.dispose();
			    System.exit(0); 
			});

			noButton.addActionListener(ev -> dialog.dispose());

			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			    
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(yesButton);
			buttonPanel.add(noButton);

			panel.add(Box.createVerticalStrut(20));
			panel.add(label);
			panel.add(Box.createVerticalStrut(10));
			panel.add(buttonPanel);
			panel.add(Box.createVerticalStrut(20));

			dialog.getContentPane().add(panel);
			dialog.pack();

			dialog.setLocation(560, 370);

			dialog.setVisible(true);
		});

		mainPanel.revalidate();
		mainPanel.repaint();
		this.setVisible(true);
	}
	
	public void setContentPanel(JPanel panel) {
		mainPanel.removeAll();
		mainPanel.setLayout(null);
		panel.setBounds(0, 0, getWidth(), getHeight());
		mainPanel.add(panel);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	public void setMainController(IMainController mainController) {
		this.mainController=mainController;
	}
}
