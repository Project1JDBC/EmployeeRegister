package com.jensen.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity(name="roles")
public class Role {
	
	@Id
	@Column(name="role_id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Role(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
}
