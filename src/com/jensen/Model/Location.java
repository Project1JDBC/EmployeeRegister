package com.jensen.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * 
 * This Class is the EntityType of a Location
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
@Entity(name="locations")
public class Location {
	@Id
	@Column(name="location_id")
	private int id;
	
	@Column(name="location")
	private String location;
	
	@OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
	private Set<Employee> employees = new HashSet<Employee>();
	/**
	 * Default Constructor for Location.class
	 */
	public Location() {}
	/**
	 * This Method is being used to retrieve the id of the Location Object
	 * 
	 * @return The Unique ID of the Location Object
	 */
	public int getId() {
		return id;
	}
	/**
	 * This Method is being used to set the ID of a Location Object
	 * 
	 * @param id Used for setting this Object's Unique ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * This Method is being used to retrieve the Location of a Location Object
	 * 
	 * @return A String of the Location Value
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * This Method is being used to set the Location of a Location Object
	 * 
	 * @param location Used for settting this Object's Unique ID
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * This Method is being used to get a Set of this Object's listed Employees
	 * 
	 * @return A Set of Employee Objects
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}
	/**
	 * This Method is being used to set the Set of Employee Objects
	 * 
	 * @param employees Used to set this Location Object's Set of Employees
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}