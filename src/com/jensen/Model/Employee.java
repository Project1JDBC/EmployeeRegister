package com.jensen.Model;

import java.util.*;

import javax.persistence.*;

@Entity(name="employees")
public class Employee {
	
	@Id
	@Column(name="employee_id")
	private int id;
	
	@Column(name="first_name")
	private String firstname;

	@Column(name="last_name")
	private String lastname;
	
	@Column(name="role")
	private Integer roleId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "role", insertable = false, updatable = false)
	private Role role;
	
	@Column(name="location")
	private Integer locationId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "location", insertable = false, updatable = false)
	private Location location;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	@ManyToMany
	@JoinTable(name="employee_has_skills",joinColumns ={
			@JoinColumn(name="employee")},inverseJoinColumns = {
					@JoinColumn(name="skill")})
	private Set<Skill> skills = new HashSet<Skill>(0);
	

	public Employee(int id,String firstname, String lastname, int role, int location, Date registrationDate) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.locationId = location;
		this.roleId = role;
		this.registrationDate = registrationDate;
	}

	public Employee(int id,String firstname, String lastname, int role,int location) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.roleId = role;
		this.locationId = location;
	}

	public Employee(int id,String firstname) {
		this.setId(id);
		this.setFirstname(firstname);
	}
	public Employee(int id) {
		this.setId(id);
	}
	public Employee(){
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

}