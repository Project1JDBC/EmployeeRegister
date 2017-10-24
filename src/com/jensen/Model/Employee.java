package com.jensen.Model;

import java.util.*;
import javax.persistence.*;
/**
 * 
 * This Class is the EntityType of a Employee
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
@Entity(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int id;

	@Column(name = "first_name")
	private String firstname;

	@Column(name = "last_name")
	private String lastname;

	@Column(name = "role")
	private Integer roleId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	private Role role;

	@Column(name = "location")
	private Integer locationId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "location", insertable = false, updatable = false)
	private Location location;

	@Column(name = "registration_date")
	private Date registrationDate;

	@ManyToMany
	@JoinTable(name="employee_has_skills",joinColumns ={
			@JoinColumn(name="employee")},inverseJoinColumns = {
					@JoinColumn(name="skill")})
	private Set<Skill> skills = new HashSet<Skill>(0);
	/**
	 * 
	 * @param id This is going to be this Object's id
	 * @param firstname This is going to be this Object's FirstName
	 * @param lastname This is going to be this Object's LastName
	 * @param location This is going to be this Object's Location
	 * @param role This is going to be this Object's Role
	 * @param registrationDate This is going to be this Object's Registration Date
	 */
	public Employee(int id, String firstname, String lastname, Integer location, Integer role, Date registrationDate) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setRoleId(role);
		this.setLocationId(location);
		this.registrationDate = registrationDate;
	}
	/**
	 * 
	 * @param firstname This is going to be this Object's FirstName
	 * @param lastname This is going to be this Object's LastName
	 * @param location location This is going to be this Object's Location
	 * @param role role This is going to be this Object's Role
	 */
	public Employee(String firstname, String lastname,Integer location, Integer role) {

		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setRoleId(role);
		this.setLocationId(location);
	}
	/**
	 * 
	 * @param id This is going to be this Object's id
	 * @param firstname This is going to be this Object's FirstName
	 */
	public Employee(int id, String firstname) {
		this.setId(id);
		this.setFirstname(firstname);
	}
	/**
	 * 
	 * @param id This is going to be this Object's id
	 */
	public Employee(int id) {
		this.setId(id);
	}
	/**
	 * Default Constructor of the Employee.class
	 */
	public Employee(){

	}
	/**
	 * 
	 * @return This Object's Id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * 
	 * @param id Sets this Object's Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return This Object's role
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 
	 * @param roleId Sets this Object's roleId
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 
	 * @return This Object's firstName
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * 
	 * @param firstname Sets this Object's firstName
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * 
	 * @return This Object's LastNaem
	 */
	public String getLastname() {
		return this.lastname;
	}
	/**
	 * 
	 * @param lastname Sets this Object's LastName
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * 
	 * @return This Object's role
	 */
	public Role getRole() {
		return this.role;
	}
	/**
	 * 
	 * @param role Sets this Object's role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * 
	 * @return This Object's location
	 */
	public Location getLocation() {
		return this.location;
	}
	/**
	 * 
	 * @param location Sets Object's locations
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * 
	 * @return This Object's set Of Skills
	 */
	public Set<Skill> getSkills() {
		return skills;
	}
	/**
	 * 
	 * @param skills Sets this Object's set of skills
	 */
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	/**
	 * 
	 * @return This Object's Registration Date
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	/**
	 * 
	 * @param registrationDate Sets this Object's Registration Date
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	/**
	 * 
	 * @return This Object's locationId
	 */
	public Integer getLocationId() {
		return locationId;
	}
	/**
	 * 
	 * @param locationId Sets this Object's locationId
	 */
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", roleId=" + roleId
				+ ", locationId=" + locationId + ", registrationDate=" + registrationDate + "]";
	}
}