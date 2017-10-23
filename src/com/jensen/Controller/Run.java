package com.jensen.Controller;

import java.util.logging.Level;

public class Run {

	public static void main(String[] args) {	
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		new Application();
	}
}
