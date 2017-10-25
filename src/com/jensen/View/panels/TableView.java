package com.jensen.View.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * This Class serves as the TableView (or JPanel) of this Application's GUI. It contains the JTable the user can interact with. All Database Data will be displayed here.
 * 
 * @author Kami Hassanzadeh
 * @author Gustav Malm
 *
 */
public class TableView implements Panel {
	private JPanel panel;
	private static JTable table = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scroll = new JScrollPane(); 
	/**
	 * This Constructor of TableView.class initializes the Object's GUI.
	 */
	public TableView(){
		this.init();
	}
	/**
	 * This Method is being used to retrieve 
	 * 
	 * @return The DefaultTableModel from the TableView Object
	 */
	public DefaultTableModel getModel() {
		return model;
	}

	private void init() {
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panel.setPreferredSize(new Dimension(650, 200));
		
		table.setModel(model); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		scroll = new JScrollPane(table);
		
		this.panel.add(scroll);
		this.panel.setVisible(true);
	}
	/**
	 * This Method is being used to retrieve the JPanel of this TableView Object
	 */
	@Override
	public JPanel getPanel() {
		return this.panel;
	}
	/**
	 * This Method is being used by the Application whenever it's required to refresh the TableView
	 */
	@Override
	public void update() {}
}