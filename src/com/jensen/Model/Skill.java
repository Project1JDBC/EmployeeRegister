package com.jensen.Model;

import java.util.*;
import javax.persistence.*;

@Entity(name="skills")
public class Skill {
	
	@Id
	@Column(name="skill_id")
	private int id;
	
	@Column(name="skill")
	private String skill;
	
	@ManyToMany
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Skill(int id){
		this.id = id;
		
	}
	public Skill(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}


}
