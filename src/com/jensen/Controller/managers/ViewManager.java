package com.jensen.Controller.managers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.jensen.View.MainWindow;
import com.jensen.View.panels.MainPanel;
import com.jensen.View.panels.TableView;
/**
 * 
 * This Class makes sure that application runs
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class ViewManager {
	private MainWindow window;
	private MainPanel panel;
	private TableView tableView;
	ArrayList<JButton> buttonLeft;
	ArrayList<JButton> buttonRight;
	/**
	 * This is the Default Constructor of the Class ViewManager
	 */
	public ViewManager() {
		this.init();
	}
	/**
	 * This Method is being called the first time the ViewManager is being Instantiated 
	 */
	private void init() {
		this.window = new MainWindow("Employee Register");
		this.panel = new MainPanel();
		this.tableView = new TableView();
		this.setButtonLeft(this.getPanel().getButtonLeft());
		this.setButtonRight(this.getPanel().getButtonRight());
	}
	/**
	 * This Method is being called whenever the Application needs a DefaultTableModel
	 * 
	 * @return a DefaultTableModel
	 */
	public DefaultTableModel getTableModel(){
		return this.tableView.getModel();
	}
	/**
	 * This Method is being called whenever the Application needs to initialize the default GUI
	 */
	public void initDefaultGUI() {
		this.panel.addComponents();
		this.window.addPanel(panel.getPanel(),tableView.getPanel());
	}
	/**
	 * This Method is being called whenever the Application needs to return the MainWindow Object
	 * 
	 * @return A MainWindow Object 
	 */
	public MainWindow getWindow() {
		return this.window;
	}
	/**
	 * This Method is being called whenever the Application needs to return the MainPanel Object
	 * 
	 * @return A MainPanel Object 
	 */
	public MainPanel getPanel() {
		return this.panel;
	}
	/**
	 * This Method is being called whenever the Application needs to set the MainPanel Object
	 * 
	 * @param panel Set this Object's panel to this panel
	 */
	public void setPanel(MainPanel panel) {
		this.panel = panel;
	}
	/**
	 * This Method is being called whenever the Application needs to return the list of Buttons (Left Side in View) Object
	 * 
	 * @return A list of JButtons
	 */
	public ArrayList<JButton> getButtonLeft() {
		return buttonLeft;
	}
	/**
	 * This Method is being called whenever the Application needs to set the list of Buttons (Left Side in View) Object
	 * 
	 * @param arrayList Set this Object's list of JButtons to this Object
	 */
	public void setButtonLeft(ArrayList<JButton> arrayList) {
		this.buttonLeft = arrayList;
	}
	/**
	 * This Method is being called whenever the Application needs to return the list of Buttons (Right Side in View) Object
	 * 
	 * @return A list of JButtons
	 */
	public ArrayList<JButton> getButtonRight() {
		return this.buttonRight;
	}
	/**
	 * This Method is being called whenever the Application needs to set the list of Buttons (Right Side in View) Object
	 * 
	 * @param arrayList Set this Object's list of JButtons to this Object
	 */
	public void setButtonRight(ArrayList<JButton> arrayList) {
		this.buttonRight = arrayList;
	}
	/**
	 * This Method is being called whenever the Application needs to return the String Button Object
	 * 
	 * @return A String of JButtons
	 */
	public String[] getMenyButtonLeft() {
		return this.panel.getMenyButtonLeft();
	}
	/**
	 * This Method is being called whenever the Application needs to return the String Button Object
	 * 
	 * @return A String of JButtons
	 */
	public String[] getMenyButtonRight() {
		return this.panel.getMenyButtonRight();
	}
}
