package com.jensen.Controller.managers;

import java.util.*;

import com.jensen.Model.Role;
/**
 * 
 * This Class manages all of the Role Objects
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class RoleManager {

	private List<Role> roles = new LinkedList<Role>();
	/**
	 * This serves as the Default Constructor of the Role Manager Class
	 */
	public RoleManager(){}
	/**
	 * This Method is being called whenever a user wants to return a List of Role Objects
	 * 
	 * @return a List of Role Objects
	 */
	public List<Role> getRoles(){
		return this.roles;
	}
	/**
	 * This Method is being called whenever a user wants to get a specific Role by ID
	 * 
	 * @param id This parameter is used to identify a Role from a List of Roles in order to either return that object or return null
	 * @return If Role is found it will return that Role object and return Null if it's NOT being found
	 */
	public Role getRoleById(int id){
		for(Role role :roles){
			if(id == role.getId()){
			return this.getRoles().get(id);
			}
		}
		return null;
	}
}