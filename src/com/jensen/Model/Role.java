package com.jensen.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
/**
 * 
 * This Class is the EntityType of a Role
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
@Entity(name="roles")
public class Role {
	@Id
	@Column(name="role_id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private Set<Employee> employees = new HashSet<Employee>();
	/**
	 * Default Constructor of Role.class
	 */
	public Role() {}
	/**
	 * This Method is being used to retrieve the Unique ID of a Role Object
	 * 
	 * @return A Unique ID of a Role Object
	 */
	public int getId() {
		return id;
	}
	/**
	 * This Method is being used to set the Unique ID of a Role Object
	 * 
	 * @param id Used to set the Unique ID of this Role Object
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * This Method is being used to set a String Value of a Role Object
	 * 
	 * @return A String Value of this Role Object
	 */
	public String getRole() {
		return role;
	}
	/**
	 * This Method is being used to set a String Value of a Role Object
	 * 
	 * @param role Used to set this Object's Role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * This Method is being used to retrieve a Set of Employee Objects
	 * 
	 * @return A Set of Employee Objects
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}
	/**
	 * This Method is being used to set a Set of Employee Objects
	 * 
	 * @param employees Used to set a Set of Employee Objects
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}