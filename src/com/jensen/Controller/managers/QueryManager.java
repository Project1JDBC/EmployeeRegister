package com.jensen.Controller.managers;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import com.jensen.Model.Employee;
import com.jensen.Model.Location;
import com.jensen.Model.Role;
import com.jensen.Model.Skill;
import com.jensen.Model.TableColumnEnum;
/**
 * 
 * This Class makes sure that application runs
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class QueryManager {

	private Session session;
	private DefaultTableModel model;
	private List<Employee> employees = new LinkedList<Employee>();
	private List<Location> locations = new LinkedList<Location>();
	private List<Role> roles = new LinkedList<Role>();
	private List<Skill> skills = new LinkedList<Skill>();

	public QueryManager(DefaultTableModel model, Session session) {
		this.model = model;
		this.session = session;
	}

	/* Returns all Employees and places it into the JTableModel */
	public void showAllEmployee() {
		updateListOfEmployees();

		this.model.addColumn(TableColumnEnum.E_EMPLOYEEID);
		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);
		this.model.addColumn(TableColumnEnum.E_LASTNAME);
		this.model.addColumn(TableColumnEnum.E_LOCATION);
		this.model.addColumn(TableColumnEnum.E_ROLE);
		this.model.addColumn(TableColumnEnum.E_REGISTRATIONDATE);

		for (Employee employee : employees) {
			this.model.addRow(new Object[] { employee.getId(), employee.getFirstname(), employee.getLastname(),
				 employee.getLocation().getId(),employee.getRole().getId(),
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
	public void insertInto(Employee employee, String skillId) {
		this.session.beginTransaction();

		Set<Skill> skills = new HashSet<Skill>();
		Skill skill = session.get(Skill.class, Integer.parseInt(skillId));
		skills.add(skill);
		
		Role role = session.get(Role.class, employee.getRoleId());
		employee.setRole(role);
		Location location = session.get(Location.class, employee.getLocationId());
		employee.setLocation(location);
		Date date = new Date();
		employee.setRegistrationDate(date);
		
		employee.setSkills(skills);
		this.session.save(employee);
		this.session.getTransaction().commit();
		this.employees.add(employee);
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
			String fname =  employee.getFirstname();
			String lname = employee.getLastname();

			if (fname.compareToIgnoreCase(input) == 0 || lname.compareToIgnoreCase(input)== 0) {
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
