package com.jensen.View.panels;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class MainPanel implements Panel {

	private JPanel panel;

	private ArrayList<JButton> buttonLeft, buttonRight;

	private String menyButtonLeft[] = { "Show All Employees", "Delete Employee", "Update Employee", "Add Employee",
			"Exit" };
	
	private String menyButtonRight[] = { "Show All Employees by locations", "Show All Employees by roles",
			"Show All Employees by skills", "Search for an employee" };

	private int bounds = 30;


	/* Main Constructor for MainPanel */
	public MainPanel() {
		init();
	}

	private void init() {
		this.panel = new JPanel();
		initDefaultGUI();
	}

	/* Creates JButtons and sets attributes */
	public void initDefaultGUI() {
		this.buttonRight = new ArrayList<JButton>();
		this.buttonLeft = new ArrayList<JButton>();

		for (int i = 0; i < this.menyButtonLeft.length; i++) {
			buttonLeft.add(new JButton(menyButtonLeft[i]));
			buttonLeft.get(i).setBounds(30, bounds, 150, 20);
			bounds = bounds + 30;
			if (buttonLeft.get(i).equals(4)) {
				buttonLeft.get(i).setBounds(30, bounds + 180, 80, 20);
			}
		}

		for (int i = 0; i < this.menyButtonRight.length; i++) {
			buttonRight.add(new JButton(menyButtonRight[i]));
			buttonRight.get(i).setBounds(200, bounds, 220, 20);
			bounds = bounds + 30;
		}
	}

	/* Places JButtons into a GridBagLayout to finally be added to the JPanel */
	public void addComponents() {
		
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.panel.setPreferredSize(new Dimension(1200, 400));
		
		for (int i = 0; i < buttonLeft.size(); i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.insets = new Insets(0, 15, 10, 0);
			this.panel.add(buttonLeft.get(i), gbc);
		}
		
		for (int i = 0; i < buttonRight.size(); i++) {
			gbc.gridx = 1;
			gbc.gridy = i;
			gbc.insets = new Insets(0, 30, 10, 15);
			this.panel.add(buttonRight.get(i), gbc);
		}
	}
	/* Updates the JPanel - Currently Unused */
	@Override
	public void update() {
	}

	/* Returns this ArrayList<Button> of buttonLeft */
	public ArrayList<JButton> getButtonLeft() {
		return buttonLeft;
	}

	/* Sets this ArrayList<Button> of buttonLeft */
	public void setButtonLeft(ArrayList<JButton> buttonLeft) {
		this.buttonLeft = buttonLeft;
	}

	/* Returns this ArrayList<Button> of buttonRight */
	public ArrayList<JButton> getButtonRight() {
		return buttonRight;
	}

	/* Sets this ArrayList<Button> of buttonRight */
	public void setButtonRight(ArrayList<JButton> buttonRight) {
		this.buttonRight = buttonRight;
	}

	/* Returns this JPanel */
	@Override
	public JPanel getPanel() {
		return this.panel;
	}
	
	/* Returns this array of menuButtonLeft */
	public String[] getMenyButtonLeft() {
		return menyButtonLeft;
	}

	/* Returns this array of menuButtonRight */
	public String[] getMenyButtonRight() {
		return menyButtonRight;
	}

}