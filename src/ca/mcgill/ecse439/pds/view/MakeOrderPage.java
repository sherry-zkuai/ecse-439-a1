package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.CENTER;

import ca.mcgill.ecse439.pds.controller.PizzaDeliveryController;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class MakeOrderPage extends JFrame
{
	private static final long serialVersionUID = 2517121556423951520L;

	private static String TITLE = "Pizza Order Form";
	private static String DESC = "Are you ready for Mamma's Pizza?";
	private static Dimension SHORT_DIM =  new Dimension(200, 24); 
	private static Dimension EXT_DIM =  new Dimension(200, 96); 
	private static Dimension MENU_DIM =  new Dimension(600, 100); 
	
	private List<MenuPizza> menuPizzas = new LinkedList<MenuPizza>();
	private String[] menuTableColumnNames = {"Name", "Price", "Ingredients", "Calories", "Order" };
	private Object[][] menuTableData = prepTableData();
	
	private String error;
	private String name = "";
	private String phone = "";
	private String email = "";
	private String address = "";
	
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
		errorMsg.setForeground(Color.RED);
		
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
			private static final long serialVersionUID = -6119566140421846301L;

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
		List<MenuPizza> tempPizza = new ArrayList<MenuPizza>();
		List<Integer> tempNum = new ArrayList<Integer>();
				
		for (int i = 0; i < menuTable.getRowCount(); i++)
		{
			int rowOrder = 0;
			
			try
			{
				rowOrder = (int) menuTable.getModel().getValueAt(i, 4);
				System.out.println(rowOrder);
			}
			catch (Exception e)
			{
				error = "Please input a whole number into the order cell.";
				e.printStackTrace();
				refreshData();
				return;
			}
					
			tempPizza.add(menuPizzas.get(i));
			tempNum.add(new Integer(rowOrder));
		}
				
		MenuPizza[] order = new MenuPizza[tempPizza.size()];
		int[] numbers = new int[tempNum.size()];
		
		if (tempPizza.size() != tempNum.size())
		{
			error = "Internal Error. Contact admin";
			return;
		}
		
		for (int k = 0; k < tempPizza.size(); k++)	
		{
			order[k] = tempPizza.get(k);
			numbers[k] = tempNum.get(k);
		}
								
		try
		{
			cont.createOrder(name, phone, email, address, order, numbers);
		} 
		catch (Exception e)
		{
			error = e.getMessage();
		}

		error = "Your order has been received!";
		refreshData();
	}

	private Object[][] prepTableData()
	{		
		int nbPizzas = 0;
				
		if (PizzaDeliveryManager.getInstance().hasPizzas())
		{
			ArrayList<Pizza> ps=new ArrayList<>(PizzaDeliveryManager.getInstance().getPizzas());
			for(Pizza p:ps)
			{
				if(p instanceof MenuPizza)
				{
					menuPizzas.add((MenuPizza)p);
					nbPizzas++;
				}
			}
		}
		else
		{
			error = "Could not find pizza objects. Contact admin.";
			return null;
		}
		
		Object[][] tablePrep = new Object[nbPizzas][5];
		
		for (int i = 0; i < nbPizzas; i++)
		{
			String ingredients = "<html>";
			for (Ingredient ing:menuPizzas.get(i).getIngredients())
			{
				ingredients += (ing.getName() + ", ");
			}
			ingredients += "</html>";
			
			Object[] temp = {new String(menuPizzas.get(i).getName()),
							 new Double(menuPizzas.get(i).getPrice()),
							 new String(ingredients),
							 new Integer(menuPizzas.get(i).getCalorieCount()),
							 new Integer(0)};
			
			tablePrep[i] = temp;
		}
		
		return tablePrep;
	}
}