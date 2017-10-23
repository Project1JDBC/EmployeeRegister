package com.jensen.Controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.jensen.Controller.managers.*;

public class Application {
	private ConnectionManager connectionManager;
	private EmployeeManager employeeManager;
	private ViewManager viewManager;
	private QueryManager queryManager;


	public Application() {
		init();
	}
	/**
	 * Initializes all Managers
	 */
	public void init() {
		this.connectionManager = new ConnectionManager();
		this.viewManager = new ViewManager();
		this.queryManager = new QueryManager(this.connectionManager.getConnection(), this.viewManager.getTableModel(), this.connectionManager.getSession());
		this.employeeManager = new EmployeeManager(queryManager);
		addActionListeners();
		this.viewManager.initDefaultGUI();
	}

	/* The Dialog for creating a new Employee */
	private void showAddEmployeeDialog() {
		JTextField fnameField = new JTextField(5);
		JTextField lnameField = new JTextField(5);
		JTextField roleField = new JTextField(5);
		JTextField locationField = new JTextField(5);
		JTextField skillField = new JTextField(5);

		JPanel employeePanel = new JPanel(new BorderLayout(5, 5));
		JPanel credentials = new JPanel(new GridLayout(0, 1));
		credentials.add(new JLabel("Firstname:"));
		credentials.add(fnameField);
		credentials.add(new JLabel("Lastname:"));
		credentials.add(lnameField);
		credentials.add(new JLabel("Role:"));
		credentials.add(roleField);
		credentials.add(new JLabel("Location:"));
		credentials.add(locationField);
		credentials.add(new JLabel("Skill:"));
		credentials.add(skillField);

		employeePanel.add(credentials, BorderLayout.CENTER);

		int result = JOptionPane.showConfirmDialog(null, employeePanel, "Add New Employee",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			if(fnameField.getText().equals("") || lnameField.getText().equals("") || roleField.getText().equals("") || locationField.getText().equals("") || skillField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "All input must be filled!");
			}else{
				this.employeeManager.addEmployee(fnameField.getText(), lnameField.getText(), roleField.getText(), locationField.getText(), skillField.getText());
			}
		}
	}

	/* The Dialog for updating a Employee */
	private void showUpdateEmployeeDialog() {
		this.queryManager.getAllRowsOnlyName();
		JTextField idField = new JTextField(5);
		JTextField fnameField = new JTextField(5);

		JPanel employeePanel = new JPanel(new BorderLayout(5, 5));
		JPanel credentials = new JPanel(new GridLayout(0, 1));
		credentials.add(new JLabel("Employee ID:"));
		credentials.add(idField);
		credentials.add(new JLabel("Firstname:"));
		credentials.add(fnameField);

		employeePanel.add(credentials, BorderLayout.CENTER);

		int result = JOptionPane.showConfirmDialog(null, employeePanel, "Update Employee",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(fnameField.getText().equals("") || idField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "All input must be filled!");
			}else{	
				this.employeeManager.updateEmployee(idField.getText(), fnameField.getText());
			}
		}
	}

	/* The Dialog for removing a Employee */
	private void showDeleteEmployeeDialog() {
		this.queryManager.showAllEmployee();
		JTextField idField = new JTextField(5);

		JPanel employeePanel = new JPanel(new BorderLayout(5, 5));
		JPanel credentials = new JPanel(new GridLayout(0, 1));
		credentials.add(new JLabel("Employee ID:"));
		credentials.add(idField);

		employeePanel.add(credentials, BorderLayout.CENTER);

		int result = JOptionPane.showConfirmDialog(null, employeePanel, "Delete Employee",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(idField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "All input must be filled!");
			} else{
				this.employeeManager.deleteEmployee(idField.getText());
			}
		}
	}

	/* Adds ActionListeners to all the JButtons */
	public void addActionListeners() {
		ArrayList<JButton> buttonLeft = this.viewManager.getButtonLeft();
		ArrayList<JButton> buttonRight = this.viewManager.getButtonRight();

		for (int i = 0; i < buttonLeft.size(); i++) {
			buttonLeft.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (event.getSource() == buttonLeft.get(0)) {
						queryManager.showAllEmployee();
					} else if (event.getSource() == buttonLeft.get(1)) {
						showDeleteEmployeeDialog();
					} else if (event.getSource() == buttonLeft.get(2)) {
						showUpdateEmployeeDialog();
					} else if (event.getSource() == buttonLeft.get(3)) {
						showAddEmployeeDialog();
					} else if (event.getSource() == buttonLeft.get(4)) {
						viewManager.getWindow().close();
						System.exit(0);
					}
				}
			});
		}

		for (int i = 0; i < buttonRight.size(); i++) {
			buttonRight.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (event.getSource() == buttonRight.get(0)) {
						queryManager.getAllLocation();
						queryManager.getEmployeeByLocation(JOptionPane.showInputDialog("Select location:"));
					} else if (event.getSource() == buttonRight.get(1)) {
						queryManager.getAllRole();
						queryManager.getEmployeeByRole(JOptionPane.showInputDialog("Select Role:"));
					} else if (event.getSource() == buttonRight.get(2)) {
						queryManager.getAllSkills();
						queryManager.getEmployeeBySkill(JOptionPane.showInputDialog("Select Skills:"));
					} else if (event.getSource() == buttonRight.get(3)) {
						queryManager.showAllEmployee();
						queryManager.getEmployeeByName(JOptionPane.showInputDialog("Search"));
					}
				}
			});
		}
	}
}
