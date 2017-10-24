package com.jensen.View.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableView implements Panel {
	
	private JPanel panel;
	private static JTable table = new JTable();
	private DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scroll = new JScrollPane();

	public TableView(){
		init();
	}

	public DefaultTableModel getModel() {
		return model;
	}

	private void init() {
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panel.setPreferredSize(new Dimension(1200, 200));
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		scroll = new JScrollPane(table);
		
		this.panel.add(scroll);
		this.panel.setVisible(true);
	}

	@Override
	public JPanel getPanel() {
		
		return this.panel;
	}

	@Override
	public void update() {
		
	}

}