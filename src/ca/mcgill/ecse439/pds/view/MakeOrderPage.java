package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import static javax.swing.GroupLayout.Alignment.CENTER;

import ca.mcgill.ecse439.pds.controller.PizzaDeliveryController;
import ca.mcgill.ecse439.pds.model.Pizza;

public class MakeOrderPage extends JFrame {
	private static final long serialVersionUID = 2517121556423951520L;

	private static String TITLE = "Pizza Order Form";
	private static String DESC = "Are you ready for Mamma's Pizza?";
	private static Dimension SHORT_DIM =  new Dimension(200, 24); 
	private static Dimension EXT_DIM =  new Dimension(200, 96); 
	private static Dimension MENU_DIM =  new Dimension(600, 100); 
	
	// Test
	private String[] menuTableColumnNames = {"Name", "Price", "Ingredients",	"Calories", "Order" };
	private Object[][] menuTableData = {
		{"Cheese", new Double(8.00), "<html>White flour dough, Tomato Sauce, Cheese</html>", new Integer(500), new Integer(0)},
		{"Pepporoni", new Double(8.00), "<html>White flour dough, Tomato Sauce, Cheese, Pepperoni</html>", new Integer(500), new Integer(0)},
		{"Vegitarian", new Double(8.00), "<html>White flour dough, Tomato Sauce, Cheese, Mushrooms, Onions, Green Peppers, Tomatoes</html>", new Integer(500), new Integer(0)},
		{"All Dressed", new Double(8.00), "<html>White flour dough, Tomato Sauce, Cheese, Pepperoni, Mushrooms, Green Peppers</html>", new Integer(500), new Integer(0)},
	};
	
	private String error;
	private String name = "";
	private String phone = "";
	private String email = "";
	private String address = "";
	private Pizza[] order;
	
	// UI Elements
	private JLabel title;
	private JLabel desc;
	private JLabel errorMsg;
	
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
	
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextArea addressField;
	
	private JTable menuTable;
	private JScrollPane menuScrollPane;
	
	private JButton submitButton;

	
	public MakeOrderPage()
	{
		initComponents();
	}

	private void initComponents()
	{
		// Page Defaults
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("That's a spicy meat-a-ball!");
		getContentPane().setBackground(Color.decode("#228B22"));

		// Error Message
		errorMsg = new JLabel();
		errorMsg.setForeground(Color.MAGENTA);
		
		// Title
		title = new JLabel(TITLE);
		title.setForeground(Color.WHITE);
		title.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 40));
		
		// Description
		desc = new JLabel(DESC);
		desc.setForeground(Color.WHITE);
		desc.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 24));
		
		// Name
		nameLabel = new JLabel("Name:");
		nameLabel.setForeground(Color.WHITE);
		nameField = new JTextField();
		nameField.setPreferredSize(SHORT_DIM);;
		
		// Phone
		phoneLabel = new JLabel("Phone:");
		phoneLabel.setForeground(Color.WHITE);
		phoneField = new JTextField();
		phoneField.setPreferredSize(SHORT_DIM);;
		
		// Email
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(Color.WHITE);
		emailField = new JTextField();
		emailField.setPreferredSize(SHORT_DIM);
		
		// Address
		addressLabel = new JLabel("Address:");
		addressLabel.setForeground(Color.WHITE);
		addressField = new JTextArea();
		addressField.setPreferredSize(EXT_DIM);
		addressField.setLineWrap(true);
		addressField.setWrapStyleWord(true);
				
		// Menu Table
		menuTable = new JTable(menuTableData, menuTableColumnNames);	
		menuScrollPane = new JScrollPane(menuTable);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		DefaultTableCellRenderer bgRenderer = new DefaultTableCellRenderer();
		bgRenderer.setHorizontalAlignment( JLabel.CENTER );
		bgRenderer.setBackground(Color.decode("#C9CAC9"));
		DefaultTableModel tableModel = new DefaultTableModel(menuTableData, menuTableColumnNames)
		{
			@Override
		    public boolean isCellEditable(int row, int column)
			{
		       return (column == 4);
		    }
		};

		menuTable.setModel(tableModel);
		menuTable.setFillsViewportHeight(true);
		menuTable.setRowHeight(48);
		menuTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		menuTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		menuTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		menuTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		menuTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		menuTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		menuTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		menuTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		menuTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		menuTable.getColumnModel().getColumn(4).setCellRenderer( bgRenderer );
		menuScrollPane.setPreferredSize(MENU_DIM);
		
		// Submit Button
		submitButton = new JButton();
		submitButton.setText("Submit");
		submitButton.setForeground(Color.DARK_GRAY);
		submitButton.setBackground(Color.WHITE);
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed();
			}
		});
		
		// Creating the Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(CENTER)
					.addComponent(errorMsg)
					.addComponent(title)
					.addComponent(desc)
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(nameLabel)
									.addComponent(phoneLabel)
									.addComponent(emailLabel)
									.addComponent(addressLabel))
							.addGroup(layout.createParallelGroup()
									.addComponent(nameField)
									.addComponent(phoneField)
									.addComponent(emailField)
									.addComponent(addressField))
							.addGroup(layout.createParallelGroup()
									.addComponent(menuScrollPane)))
					.addComponent(submitButton));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { nameLabel, phoneLabel, emailLabel, addressLabel });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { nameField, phoneField, emailField, addressField });

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(errorMsg)
				.addGap(20)
				.addGroup(layout.createParallelGroup(CENTER))
					.addComponent(title)
				.addComponent(desc)
				.addGap(30)
				.addGroup(layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(nameField))
						.addGroup(layout.createParallelGroup()
								.addComponent(phoneLabel)
								.addComponent(phoneField))
						.addGroup(layout.createParallelGroup()
								.addComponent(emailLabel)
								.addComponent(emailField))
						.addGroup(layout.createParallelGroup()
								.addComponent(addressLabel)
								.addComponent(addressField)))
					.addComponent(menuScrollPane))
				.addGap(20)
				.addComponent(submitButton));

		pack();
		setLocationRelativeTo(null);
	}
	
	private void refreshData()
	{
		// Check for an Error
		errorMsg.setText(error);
		pack();
	}
	
	private void submitButtonActionPerformed() 
	{
		error = "";

		// Set Controller
		PizzaDeliveryController cont = new PizzaDeliveryController();

		// Gather Input
		name = nameField.getText();
		phone = phoneField.getText();
		email = emailField.getText();
		address = addressField.getText();
		
		try
		{
			cont.createOrder(name, phone, email, address, order);
		} 
		catch (Exception e)
		{
			error = e.getMessage();
		}

		refreshData();
	}
}
