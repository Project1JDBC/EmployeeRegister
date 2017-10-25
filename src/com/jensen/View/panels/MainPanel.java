package com.jensen.View.panels;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * This Class serves as the MainPanel (or JPanel) of this Application's GUI. It contains all the JButtons the user can interact with.
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class MainPanel implements Panel {
	private JPanel panel;
	private ArrayList<JButton> buttonLeft, buttonRight;
	private String menyButtonLeft[] = { "Show All Employees", "Delete Employee", "Update Employee", "Add Employee",
			"Exit" };
	private String menyButtonRight[] = { "Show All Employees by locations", "Show All Employees by roles",
			"Show All Employees by skills", "Search for an employee" };
	private int bounds = 30;
	/**
	 * This Constructor is used to initialize the MainPanel Object's GUI.
	 */
	public MainPanel() {
		this.init();
	}

	private void init() {
		this.panel = new JPanel();
		this.initDefaultGUI();
	}
	/**
	 * Creates JButtons and sets the appropriate attributes to them
	 */
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
	/**
	 * Places the Generated JButtons into a GridBagLayout to then finally be added to the JPanel
	 */
	public void addComponents() {
		
		this.panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		this.panel.setPreferredSize(new Dimension(650, 400));
		
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
	/**
	 * This Method is being used whenever the Application requires the MainTable to Refresh
	 */
	@Override
	public void update() {}
	/**
	 * This Method is being used to retrieve the ArrayList of JButton Objects of buttonLeft
	 * 
	 * @return This ArrayList of buttonLeft
	 */
	public ArrayList<JButton> getButtonLeft() {
		return buttonLeft;
	}
	/**
	 * This Method is being used to set the ArrayList of JButtons to this MainPanel Object
	 * 
	 * @param buttonLeft Used to set the ArrayList of JButtons to this MainPanel Object
	 */
	public void setButtonLeft(ArrayList<JButton> buttonLeft) {
		this.buttonLeft = buttonLeft;
	}
	/**
	 * This Method is being used to retrieve the ArrayList of JButton Objects of buttonRight
	 * 
	 * @return This ArrayList of buttonRight
	 */
	public ArrayList<JButton> getButtonRight() {
		return buttonRight;
	}
	/**
	 * This Method is being used to set the ArrayList of JButtons to this MainPanel Object
	 * 
	 * @param buttonRight Used to set the ArrayList of JButtons to this MainPanel Object
	 */
	public void setButtonRight(ArrayList<JButton> buttonRight) {
		this.buttonRight = buttonRight;
	}
	/**
	 * This Method is being used to retrieve a JPanel Object
	 */
	@Override
	public JPanel getPanel() {
		return this.panel;
	}
	/**
	 * This Method is being used to retrieve the Array of MenuButton [Left Side]
	 * 
	 * @return A Array of MenuButtons [Left Side]
	 */
	public String[] getMenyButtonLeft() {
		return menyButtonLeft;
	}
	/**
	 * This Method is being used to retrieve the Array of MenuButton [Right Side]
	 * 
	 * @return A Array of MenuButtons [Right Side]
	 */
	public String[] getMenyButtonRight() {
		return menyButtonRight;
	}
}