package com.jensen.Model;

import java.util.*;
import javax.persistence.*;
/**
 * 
 * This Class makes sure that application runs
 * 
 * @author Kami  Hassanzadeh
 * @author Gustav Malm
 *
 */
@Entity(name="skills")
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="skill_id")
	private int id;
	
	@Column(name="skill")
	private String skill;
	
	@ManyToMany
	private Set<Employee> employee = new HashSet<Employee>(0);

	public Skill(int id){
		this.id = id;
	}
	public Skill(){
	}
	
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
	public Set<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}


}
