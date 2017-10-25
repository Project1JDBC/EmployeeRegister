package com.jensen.View.panels;

import javax.swing.JPanel;
/**
 * 
 * This Class acts as a JPanel Interface. This sets the Methods that is required for every JPanel that implements this. Contains a getPanel Method as well as a update Method.
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public interface Panel {
	public JPanel getPanel();
	public void update();
}