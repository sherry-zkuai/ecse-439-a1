package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.CENTER;

import ca.mcgill.ecse439.pds.view.MakeOrderPage;
import ca.mcgill.ecse439.pds.view.ManageMenuPage;
import ca.mcgill.ecse439.pds.view.ViewOrdersPage;

public class HomePage extends JFrame {
	private static final long serialVersionUID = -2543742458251795536L;

	// UI elements
	private JLabel title;
	private JLabel subTitle;
	private JButton makeOrderButton;
	private JButton viewOrdersButton;
	private JButton manageMenuButton;

	public HomePage() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Welcome to Mama Mia's!");
		getContentPane().setBackground(Color.decode("#228B22"));

		// Title
		title = new JLabel("Welcome to Mamma Mia's!");
		title.setForeground(Color.WHITE);
		title.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 40));

		subTitle = new JLabel("Your one stop shop for original pizza names and competitive prices!");
		subTitle.setForeground(Color.WHITE);
		subTitle.setFont(new java.awt.Font(null, java.awt.Font.PLAIN, 18));

		// Make Order Button
		makeOrderButton = new JButton();
		makeOrderButton.setForeground(Color.DARK_GRAY);
		makeOrderButton.setBackground(Color.WHITE);
		makeOrderButton.setText("Place Order");
		makeOrderButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				makeOrderButtonActionPerformed();
			}
		});
		
		// Manage Pizzas Button
		manageMenuButton = new JButton();
		manageMenuButton.setForeground(Color.DARK_GRAY);
		manageMenuButton.setBackground(Color.WHITE);
		manageMenuButton.setText("Manage Menu");
		manageMenuButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				manageMenuButtonActionPerformed();
			}
		});

		// View Orders Button (admin)
		viewOrdersButton = new JButton();
		viewOrdersButton.setForeground(Color.DARK_GRAY);
		viewOrdersButton.setBackground(Color.WHITE);
		viewOrdersButton.setText("Manage Orders");
		viewOrdersButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				viewOrdersButtonActionPerformed();
			}
		});

		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(CENTER)
				.addComponent(title)
				.addComponent(subTitle)
				.addGroup(layout.createSequentialGroup()
					.addComponent(makeOrderButton)
					.addGap(20)
					.addComponent(manageMenuButton)
					.addGap(20)
					.addComponent(viewOrdersButton)));

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { makeOrderButton, viewOrdersButton });

		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGap(30)
				.addComponent(title)
				.addComponent(subTitle)
				.addGap(30)
				.addGroup(layout.createParallelGroup()
					.addComponent(makeOrderButton)
					.addComponent(manageMenuButton)
					.addComponent(viewOrdersButton)));

		pack();
		setLocationRelativeTo(null);

	}

	// Re-route to Order Form
	private void makeOrderButtonActionPerformed() {
		new MakeOrderPage().setVisible(true);
		this.dispose();
	}
	
	// Re-route to Manage the Menu (requires login
	private void manageMenuButtonActionPerformed() {
		new ManageMenuPage().setVisible(true);
		this.dispose();
	}

	// Re-route to View Orders (requires login)
	private void viewOrdersButtonActionPerformed() {
		new ViewOrdersPage().setVisible(true);
		this.dispose();
	}
}
