package com.jensen.Controller.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.jensen.Model.Employee;
import com.jensen.Model.Location;
import com.jensen.Model.Role;
import com.jensen.Model.Skill;

public class ConnectionManager {

	private Connection connection;
	private static final String URL = "jdbc:mysql://localhost:3306/company", USER = "root", PW = "";
	private Session session;
	public ConnectionManager() {
		init();
	}

	public void init() {
		this.session = new Configuration().configure()
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Skill.class)
				.addAnnotatedClass(Role.class)
				.addAnnotatedClass(Location.class)
				.buildSessionFactory().openSession();
		try {
			connection = DriverManager.getConnection(URL, USER, PW);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public Session getSession(){
		return this.session;
	}

	public Connection getConnection() {
		return this.connection;
	}

}
