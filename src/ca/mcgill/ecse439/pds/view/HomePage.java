package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import java.awt.*;

import ca.mcgill.ecse439.pds.view.MakeOrderPage;
import ca.mcgill.ecse439.pds.view.ViewOrdersPage;

public class HomePage extends JFrame
{
	private static final long serialVersionUID = -2543742458251795536L;
	
	// UI elements
	private JLabel title;
	private JLabel subTitle;

	//new register buttons
	private JButton makeOrderButton;
	private JButton viewOrdersButton;

	public HomePage()
	{
		initComponents();
	}

	private void initComponents()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Welcome to Mama Mia's!");
		getContentPane().setBackground(Color.DARK_GRAY);

		// Title
		title = new JLabel("Welcome to Mama Mia's!");
		title.setForeground(Color.RED);
		title.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 25));
		
		subTitle = new JLabel("Your one stop shop for original pizza names and competitive prices!");
		subTitle.setForeground(Color.GREEN);
		subTitle.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 18));
		
		// Make Order Button
		makeOrderButton = new JButton();
		makeOrderButton.setForeground(Color.DARK_GRAY);
		makeOrderButton.setBackground(Color.WHITE);
		makeOrderButton.setText("Place Order");
		makeOrderButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event)
			{
				makeOrderButtonActionPerformed();
			}
		});

		// View Orders Button (admin)
		viewOrdersButton = new JButton();
		viewOrdersButton.setForeground(Color.DARK_GRAY);
		viewOrdersButton.setBackground(Color.WHITE);
		viewOrdersButton.setText("Register");
		viewOrdersButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event)
			{
				viewOrdersButtonActionPerformed();
			}
		});

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup().addGroup(layout.createSequentialGroup()
						.addGap(40)
						.addComponent(title))
				.addGroup(layout.createSequentialGroup()
						.addGap(25)
						.addComponent(subTitle))
				.addGroup(layout.createSequentialGroup()
						.addGap(20)
						.addComponent(makeOrderButton)
						.addGap(20)
						.addComponent(viewOrdersButton)
						.addGap(20)));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { makeOrderButton, viewOrdersButton });

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(30)
				.addComponent(title)
				.addComponent(subTitle)
				.addGap(30)
				.addGroup(layout.createParallelGroup()
						.addComponent(makeOrderButton)
						.addComponent(viewOrdersButton)));

		pack();
		setLocationRelativeTo(null);
			
	}
	
	// Re-route to Order Form
	private void makeOrderButtonActionPerformed()
	{
		new MakeOrderPage().setVisible(true);
		this.dispose();
	}
	
	// Re-route to View Orders (requires login)
	private void viewOrdersButtonActionPerformed()
	{
		new ViewOrdersPage().setVisible(true);
		this.dispose();
	}
}
