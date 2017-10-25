package com.jensen.Model;

import java.util.*;
import javax.persistence.*;
/**
 * 
 * This Class is the EntityType of a Skill
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
	/**
	 * Default Constructor of Skill.class
	 */
	public Skill(){}
	/**
	 * This Constructor tales in a parameter value which will set this SKill Object's Unique ID
	 * 
	 * @param id Used to set this Skill Objects Unique ID
	 */
	public Skill(int id){
		this.id = id;
	}
	/**
	 * This Method is being used to retrieve the Unique ID of a Skill Object
	 * 
	 * @return A Unique ID of this Skill Object
	 */
	public int getId() {
		return id;
	}
	/**
	 * This Method is being used to set the Unique ID of a Skill Object
	 * 
	 * @param id Used to set the Unique Id of a Skill Object
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * This Method is being used to retrieve a String of a Skill Object
	 * 
	 * @return A String of a Skill Object
	 */
	public String getSkill() {
		return skill;
	}
	/**
	 * This Method is being used to set the String of a Skill Object
	 * 
	 * @param skill Used to set this Skill Object's Skill
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}
	/**
	 * This Method is being used to retrieve a Set of Employee Objects
	 * 
	 * @return A Set of Employee Objects
	 */
	public Set<Employee> getEmployee() {
		return employee;
	}
	/**
	 * This Method is being used to set a Set of Employee Objects
	 * 
	 * @param employee Used to set a Set of Employee Objects
	 */
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
}