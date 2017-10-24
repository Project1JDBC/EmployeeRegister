package com.jensen.Controller.managers;

import java.sql.*;
import java.util.*;

import javax.persistence.criteria.CriteriaQuery;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import com.jensen.Model.*;

public class QueryManager {

	private Connection connection;
	private Session session;
	private DefaultTableModel model;
	private List<Employee> employees;
	private List<Location> locations;
	private List<Role> roles;
	private List<Skill> skills;

	public QueryManager(Connection connection, DefaultTableModel model, Session session) {
		this.connection = connection;
		this.model = model;
		this.session = session;
	}

	/* Returns all Employees and places it into the JTableModel */
	public void showAllEmployee() {
		updateListOfEmployees();

		this.model.addColumn(TableColumnEnum.E_EMPLOYEEID);
		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);
		this.model.addColumn(TableColumnEnum.E_LASTNAME);
		this.model.addColumn(TableColumnEnum.E_ROLE);
		this.model.addColumn(TableColumnEnum.E_LOCATION);
		this.model.addColumn(TableColumnEnum.E_REGISTRATIONDATE);

		for (Employee employee : employees) {
			this.model.addRow(new Object[] { employee.getId(), employee.getFirstname(), employee.getLastname(),
					employee.getRole().getRole(), employee.getLocation().getLocation(),
					employee.getRegistrationDate() });
		}
	}

	/*
	 * Returns all Employees with only employee_id and first_name and places it into
	 * the JTableModel
	 */
	public void getAllRowsOnlyName() {
		updateListOfEmployees();

		this.model.addColumn(TableColumnEnum.E_EMPLOYEEID);
		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);

		for (Employee employee : employees) {
			this.model.addRow(new Object[] { employee.getId(), employee.getFirstname() });
		}
	}

	/* Inserts a new Employee into the Database */
	public void insertInto(Employee employee, Skill skill) {
		this.session.beginTransaction();

		Set<Skill> skills = new HashSet<Skill>();
		skills.add(skill);

		employee.setSkills(skills);
		this.session.save(employee);
		this.session.getTransaction().commit();

	}

	/* Removes a Employee from the Database */
	public void deleteEmployee(String id) {
		updateListOfEmployees();
		this.session.beginTransaction();

		Employee result = session.load(Employee.class, Integer.parseInt(id));

		this.session.delete(result);
		this.session.getTransaction().commit();

	}

	/* Updates a Employee from the Database */
	public void updateEmployee(Employee employee) {
		updateListOfEmployees();

		this.session.beginTransaction();

		Employee result = session.load(Employee.class, employee.getId());
		result.setFirstname(employee.getFirstname());
		this.session.update(result);

		this.session.getTransaction().commit();
	}

	/* Generates a ID */
	public int generateId() {
		String query = " SELECT employee_id FROM employees GROUP BY employee_id DESC";
		int generatedId = 0;
		Statement state;
		try {
			state = connection.createStatement();
			ResultSet result = state.executeQuery(query);
			result.first();
			generatedId = result.getInt("employee_id") + 1;
			System.out.println(generatedId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return generatedId;
	}

	public void updateListOfEmployees() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Employee> result = this.session.getCriteriaBuilder().createQuery(Employee.class);
		result.from(Employee.class);
		this.employees = this.session.createQuery(result).getResultList();
	}

	public void updateListOfLocations() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Location> result = this.session.getCriteriaBuilder().createQuery(Location.class);
		result.from(Location.class);
		this.locations = this.session.createQuery(result).getResultList();
	}

	public void updateListOfRoles() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Role> result = this.session.getCriteriaBuilder().createQuery(Role.class);
		result.from(Role.class);
		this.roles = this.session.createQuery(result).getResultList();
	}

	public void updateListOfSkills() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Skill> result = session.getCriteriaBuilder().createQuery(Skill.class);
		result.from(Skill.class);
		this.skills = this.session.createQuery(result).getResultList();
	}

	/*
	 * Returns a Employee with the same name as the Input parameter and places it
	 * into the JTableModel
	 */
	public void getEmployeeByName(String input) {
		updateListOfEmployees();
		this.model.addColumn(TableColumnEnum.E_EMPLOYEEID);
		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);
		this.model.addColumn(TableColumnEnum.E_LASTNAME);

		for (Employee employee : employees) {

			if (employee.getFirstname().contains(input) || employee.getLastname().contains(input)) {
				this.model.addRow(new Object[] { employee.getId(), employee.getFirstname(), employee.getLastname() });
			}
		}
	}

	/*
	 * Returns a Employee with the Location of the Input parameter and places it
	 * into the JTableModel
	 */
	public void getEmployeeByLocation(String input) {
		updateListOfEmployees();

		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);
		this.model.addColumn(TableColumnEnum.E_LASTNAME);
		this.model.addColumn(TableColumnEnum.E_LOCATION);

		for (Employee employee : employees) {
			if (input.equals(employee.getLocationId().toString())) {
				this.model.addRow(new Object[] { employee.getFirstname(), employee.getLastname(),
						employee.getLocation().getLocation() });
			}
		}
	}

	/* Returns all Locations and places it into the JTableModel */
	public void getAllLocation() {
		updateListOfLocations();

		this.model.addColumn(TableColumnEnum.L_LOCATIONID);
		this.model.addColumn(TableColumnEnum.L_LOCATION);

		for (Location location : locations) {
			this.model.addRow(new Object[] { location.getId(), location.getLocation() });
		}
	}

	/*
	 * Returns a Employee with the Role of the Input parameter and places it into
	 * the JTableModel
	 */
	public void getEmployeeByRole(String input) {
		updateListOfEmployees();

		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);
		this.model.addColumn(TableColumnEnum.E_LASTNAME);
		this.model.addColumn(TableColumnEnum.R_ROLE);

		for (Employee employee : employees) {
			if (input.equals(employee.getRoleId().toString())) {
				this.model.addRow(
						new Object[] { employee.getFirstname(), employee.getLastname(), employee.getRole().getRole() });

			}
		}
	}

	/* Returns all Roles and places it into the JTableModel */
	public void getAllRole() {
		updateListOfRoles();

		this.model.addColumn(TableColumnEnum.R_ROLEID);
		this.model.addColumn(TableColumnEnum.R_ROLE);

		for (Role role : roles) {
			this.model.addRow(new Object[] { role.getId(), role.getRole() });
		}
	}

	/*
	 * Returns a Employee with the Skill of the Input parameter and places it into
	 * the JTableModel
	 */
	public void getEmployeeBySkill(String input) {
		updateListOfEmployees();
		updateListOfSkills();

		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);
		this.model.addColumn(TableColumnEnum.E_LASTNAME);
		this.model.addColumn(TableColumnEnum.S_SKILL);

		Skill result = session.get(Skill.class, Integer.parseInt(input));
		for (int i = 0; i < this.employees.size(); i++) {
			if (this.employees.get(i).getSkills().iterator().hasNext()) {
				if (this.employees.get(i).getSkills().iterator().next().getSkill().contains(result.getSkill())) {
					this.model.addRow(new Object[] { this.employees.get(i).getFirstname(),
							this.employees.get(i).getLastname(), result.getSkill() });
				}
			}
		}
	}

	/* Returns all Skills and places it into the JTableModel */
	public void getAllSkills() {
		updateListOfSkills();

		this.model.addColumn(TableColumnEnum.S_SKILLID);
		this.model.addColumn(TableColumnEnum.S_SKILL);

		for (Skill skill : skills) {
			this.model.addRow(new Object[] { skill.getId(), skill.getSkill() });
		}
	}
}