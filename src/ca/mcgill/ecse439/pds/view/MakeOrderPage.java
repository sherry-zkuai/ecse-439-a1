package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import java.awt.*;

import ca.mcgill.ecse439.pds.controller.PizzaDeliveryController;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class MakeOrderPage extends JFrame {
	private static final long serialVersionUID = 2517121556423951520L;

	private PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();

	private static String TITLE = "Pizza Order Form";
	private static String DESC = "Are you ready for Mamma's Pizza?";
	
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
		getContentPane().setBackground(Color.DARK_GRAY);

		// Error Message
		errorMsg = new JLabel();
		errorMsg.setForeground(Color.MAGENTA);
		
		// Title
		title = new JLabel(TITLE);
		title.setForeground(Color.RED);
		title.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 25));
		
		// Description
		desc = new JLabel(DESC);
		desc.setForeground(Color.RED);
		desc.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 14));
		
		// Name
		nameLabel = new JLabel("Name:");
		nameLabel.setForeground(Color.WHITE);
		nameField = new JTextField();
		
		// Phone
		phoneLabel = new JLabel("Phone Number:");
		phoneLabel.setForeground(Color.WHITE);
		phoneField = new JTextField();
		
		// Email
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(Color.WHITE);
		emailField = new JTextField();
		
		// Address
		addressLabel = new JLabel("Address:");
		addressLabel.setForeground(Color.WHITE);
		addressField = new JTextArea();
		addressField.setLineWrap(true);
		addressField.setWrapStyleWord(true);
		
		
		
		
		// TODO Pizza stuff here.
		
		
		
		
		
		
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
				layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
						.addComponent(errorMsg))
					.addGroup(layout.createSequentialGroup()
							.addGap(180)
							.addComponent(title))
					.addGroup(layout.createSequentialGroup()
							.addGap(20)
							.addComponent(desc))
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
									.addComponent(addressField)))
					.addComponent(submitButton));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { nameLabel, phoneLabel, emailLabel, addressLabel });
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { nameField, phoneField, emailField, addressField });

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(errorMsg)
				.addGap(20)
				.addComponent(title)
				.addComponent(desc)
				.addGap(30)
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
						.addComponent(addressField))
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
