package com.jensen.Controller.managers;

import javax.swing.JOptionPane;
import com.jensen.Model.Employee;
/**
 * 
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class EmployeeManager{
	private QueryManager queryManager;
	/**
	 * 
	 * @param queryManager This Constructor takes in a Object of the QueryManager Class 
	 * as a Parameter.
	 * 
	 */
	public EmployeeManager(QueryManager queryManager) {
		this.queryManager = queryManager;
	}
	/**
	 * This Method is creating a new Employee-Object with the data received from the Parameters.
	 * 
	 * @param fname This parameter value is going to set this Object's FirstName
	 * @param lname This parameter value is going to set this Object's LastName
	 * @param role This parameter value is going to set this Object's Role
	 * @param location This parameter value is going to set this Object's Location
	 * @param skill This parameter value is going to set this Object's Skill
	 */
	public void addEmployee(String fname, String lname, String role, String location, String skill) {
		if (fname.matches(".*\\d+.*") || lname.matches(".*\\d+.*")) {
			JOptionPane.showMessageDialog(null, "Number is not allowed, try again.");
		} 
		else {
			Employee employee = new Employee( fname, lname,
					Integer.parseInt(location),Integer.parseInt(role));
			this.queryManager.insertInto(employee,skill);
			this.queryManager.showAllEmployee();
		}
	}
	/**
	 * This Method is removing a Employee-Object with the help of the parameter id.
	 * 
	 * @param id This parameter is used to find and remove a specific Employee by ID 
	 * from list of Employees and the Database by passing the value through to the Query Manager.
	 */
	public void deleteEmployee(String id) {
		if (id.isEmpty() && id.contains(null)) {

		} else {
			this.queryManager.deleteEmployee(id);
			this.queryManager.showAllEmployee();
		}
	}
	/**
	 * This Method is being used whenever a user wants to update a certain Employee-Object
	 * 
	 * @param id This parameter is used to find and update a specific Employee by ID from list of 
	 * Employees and the Database by passing the value through to the Query Manager
	 */
	public void updateEmployee(String id, String fn) {
		Employee employee = new Employee(Integer.parseInt(id), fn);
		this.queryManager.updateEmployee(employee);
		this.queryManager.getAllRowsOnlyName();
	}
}