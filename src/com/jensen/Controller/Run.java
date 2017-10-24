package com.jensen.Controller;

import java.util.logging.Level;

/**
 * 
 * This Class makes sure that the  Application runs. See it as the Launcher.
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class Run {

	public static void main(String[] args) {	
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		new Application();
	}
}
