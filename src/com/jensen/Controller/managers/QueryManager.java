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
 * This Class holds all the necessary methods and variables for the database connection. 
 * It also currently holds a few methods which updates the View with the correct data asked for.
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
	/**
	 * This is the Constructor for the Class QueryManager. Takes in a DefaultTableModel object
	 * as well as a Session Object through 2 (two) parameters.
	 * 
	 * @param session This parameter is used to set this Class's database connection. 
	 * Parameter value taken from Application.Class
	 * @param model This parameter is used to update this Class's DefaultTableModel reference. 
	 * 
	 */
	public QueryManager(DefaultTableModel model, Session session) {
		this.model = model;
		this.session = session;
	}
	/**
	 * Updates the list of Employees and then returns all Employees and places it into the 
	 * correct row in the DefaultTableModel. Uses EnumType for TableModel Column Names.
	 */
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
					employee.getLocation().getLocation(),employee.getRole().getRole(),
					employee.getRegistrationDate() });
		}
	}
	/**
	 * Updates the list of Employees and then returns all Employees sorted by Employee ID 
	 * and Employee Firstname and places it into the correct row in the DefaultTableModel. 
	 * Uses EnumType for TableModel Column Names.
	 */
	public void getAllRowsOnlyName() {
		updateListOfEmployees();

		this.model.addColumn(TableColumnEnum.E_EMPLOYEEID);
		this.model.addColumn(TableColumnEnum.E_FIRSTNAME);

		for (Employee employee : employees) {
			this.model.addRow(new Object[] { employee.getId(), employee.getFirstname() });
		}
	}
	/**
	 * This Method is being called whenever a user wants to insert a new Employee into the Database.
	 *  
	 * @param employee This parameter is used to act as the Employee object which is going 
	 * to be added into the database. 
	 * @param skillId This parameter is used to find the correct Skill object from the current 
	 * Session by calling session.get(Class.class, id) 
	 */
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
	/**
	 * This Method is being called whenever a user wants to remove a Employee from the Database.
	 *  
	 * @param id This parameter is used to load the Employee object from the current Session 
	 * which is then going to be removed from the database. 
	 */
	public void deleteEmployee(String id) {
		updateListOfEmployees();
		this.session.beginTransaction();

		Employee result = session.load(Employee.class, Integer.parseInt(id));

		this.session.delete(result);
		this.session.getTransaction().commit();

	}
	/**
	 * This Method is being called whenever a user wants to update a Employee from the Database.
	 *  
	 * @param employee This parameter is used to load the Employee object from the current Session 
	 * which is then going to be altered/updated to the database. 
	 */
	public void updateEmployee(Employee employee) {
		updateListOfEmployees();

		this.session.beginTransaction();

		Employee result = session.load(Employee.class, employee.getId());
		result.setFirstname(employee.getFirstname());
		this.session.update(result);

		this.session.getTransaction().commit();
	}
	/**
	 * This Method is being called whenever a user wants to update the current list of Employees
	 */
	public void updateListOfEmployees() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Employee> result = this.session.getCriteriaBuilder().createQuery(Employee.class);
		result.from(Employee.class);
		this.employees = this.session.createQuery(result).getResultList();
	}
	/**
	 * This Method is being called whenever a user wants to update the current list of Locations
	 */
	public void updateListOfLocations() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Location> result = this.session.getCriteriaBuilder().createQuery(Location.class);
		result.from(Location.class);
		this.locations = this.session.createQuery(result).getResultList();
	}
	/**
	 * This Method is being called whenever a user wants to update the current list of Roles
	 */
	public void updateListOfRoles() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Role> result = this.session.getCriteriaBuilder().createQuery(Role.class);
		result.from(Role.class);
		this.roles = this.session.createQuery(result).getResultList();
	}
	/**
	 * This Method is being called whenever a user wants to update the current list of Skills
	 */
	public void updateListOfSkills() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
		this.model.setColumnCount(0);
		CriteriaQuery<Skill> result = session.getCriteriaBuilder().createQuery(Skill.class);
		result.from(Skill.class);
		this.skills = this.session.createQuery(result).getResultList();
	}
	/**
	 * This Method is being called whenever a user wants to find all Employees who's First Name 
	 * or Last Name contains the Input Value. [Filtered by Employee ID, First Name and Last Name]
	 * 
	 * @param input This is the Input Value in which the Employee is being selected by 
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
	/**
	 * This Method is being called whenever a user wants to find all Employees who's the Location 
	 * is the same as the Input Value. [Filtered by First Name, Last Name and Location]
	 * 
	 * @param input This is the Input Value in which the Employee is being selected by 
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
	/**
	 * This Method is being called whenever a user wants to get all Locations 
	 * [Filtered by Location ID and Location]
	 */
	public void getAllLocation() {
		updateListOfLocations();

		this.model.addColumn(TableColumnEnum.L_LOCATIONID);
		this.model.addColumn(TableColumnEnum.L_LOCATION);

		for (Location location : locations) {
			this.model.addRow(new Object[] { location.getId(), location.getLocation() });
		}
	}
	/**
	 * This Method is being called whenever a user wants to find all Employees who's the Role 
	 * is the same as the Input Value. [Filtered by First Name, Last Name and Role]
	 * 
	 * @param input This is the Input Value in which the Employee is being selected by 
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
	/**
	 * This Method is being called whenever a user wants to get all Roles 
	 * [Filtered by Role ID and Role]
	 */
	public void getAllRole() {
		updateListOfRoles();

		this.model.addColumn(TableColumnEnum.R_ROLEID);
		this.model.addColumn(TableColumnEnum.R_ROLE);

		for (Role role : roles) {
			this.model.addRow(new Object[] { role.getId(), role.getRole() });
		}
	}
	/**
	 * This Method is being called whenever a user wants to find all Employees 
	 * who's the Skills are the same as the Input Value. 
	 * [Filtered by First Name, Last Name and Skill]
	 * 
	 * @param input This is the Input Value in which the Employee is being selected by 
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
	/**
	 * This Method is being called whenever a user wants to get all Skills 
	 * [Filtered by Skill ID and Skill]
	 */
	public void getAllSkills() {
		updateListOfSkills();

		this.model.addColumn(TableColumnEnum.S_SKILLID);
		this.model.addColumn(TableColumnEnum.S_SKILL);

		for (Skill skill : skills) {
			this.model.addRow(new Object[] { skill.getId(), skill.getSkill() });
		}
	}
}