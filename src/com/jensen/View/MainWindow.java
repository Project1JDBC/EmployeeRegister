package com.jensen.View;

import java.awt.GridLayout;

import javax.swing.*;

/* Class MainWindow */
public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Creates a simple Window which acts as a Main Frame for the Application. Takes a String as a Parameter */
	public MainWindow(String title) {
		this.setTitle(title);
		init();
	}
	
	private void init() {
		initDefaultGUI();
	}

	/* Currently Unused */
	private void initDefaultGUI() {
		this.setLayout(new GridLayout(2, 0));
	}
	
	public void addPanel(JPanel panel, JPanel panel1) {
		this.add(panel);
		this.add(panel1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setResizable(false); [Unused due to current design]	
		this.setVisible(true);
		this.pack();
		// this.setLocationRelativeTo(null); [Unused due to current design]
	}

	/* Updates the Frame - Currently Unused */
	public void update() {
		
	}
	/* Disposes the JFrame */
	public void close() {
		this.setVisible(false); 
		this.dispose();	
	}
	
}