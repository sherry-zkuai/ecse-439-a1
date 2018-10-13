package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.CENTER;

import ca.mcgill.ecse439.pds.controller.PizzaDeliveryController;
import ca.mcgill.ecse439.pds.model.CustomPizza;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Order;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class ViewOrdersPage extends JFrame
{
	private static final long serialVersionUID = -1280213708908191816L;
	
	private static String TITLE = "Order Management Table";
	private static Dimension TABLE_DIM = new Dimension(1000, 400);
	
	private List<Order> orders;
	private String[] orderTableColumnNames = {"Customer", "Contact", "Address", "Bill", "Order", "Delivering", "Close Order" };
//	private Object[][] orderTableData = prepTableData();
	private Object[][] orderTableData = {{"Hi", "Hi", "Hi", "Hi", "Hi", "Hi", "Hi"}};
	
	private String error;
	
	// UI Elements
	private JLabel title;
	private JLabel errorMsg;
	
	private JTable orderTable;
	private JScrollPane orderScrollPane;
	
	public ViewOrdersPage()
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
				
		// order Table
		orderTable = new JTable(orderTableData, orderTableColumnNames);	
		orderScrollPane = new JScrollPane(orderTable);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		DefaultTableCellRenderer bgRenderer = new DefaultTableCellRenderer();
		bgRenderer.setHorizontalAlignment( JLabel.CENTER );
		bgRenderer.setBackground(Color.decode("#C9CAC9"));
		DefaultTableModel tableModel = new DefaultTableModel(orderTableData, orderTableColumnNames)
		{
			@Override
		    public boolean isCellEditable(int row, int column)
			{
		       return (column == 4);
		    }
		};

		orderTable.setModel(tableModel);
		orderTable.setFillsViewportHeight(true);
		orderTable.setRowHeight(72);
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		orderTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		orderTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		orderTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(5).setCellRenderer( bgRenderer );
		orderTable.getColumnModel().getColumn(6).setCellRenderer( bgRenderer );
		orderScrollPane.setPreferredSize(TABLE_DIM);
		
		// Creating the Layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(CENTER)
					.addComponent(errorMsg)
					.addComponent(title)
					.addComponent(orderScrollPane));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(errorMsg)
				.addGap(20)
				.addComponent(title)
				.addGap(30)
				.addComponent(orderScrollPane));

		pack();
		setLocationRelativeTo(null);
	}
	
	private void refreshData()
	{
		// Check for an Error
		errorMsg.setText(error);
		pack();
	}

//	private Object[][] prepTableData()
//	{		
//		int nbPizzas = 0;
//
//		if (PizzaDeliveryManager.getInstance().hasPizzas())
//			System.out.println("Found one.");
//		
//		for(Pizza p:PizzaDeliveryManager.getInstance().getPizzas())
//		{
//			if(p instanceof orderPizza)
//			{
//				orderPizzas.add((orderPizza)p);
//				nbPizzas++;
//			}
//		}
//
//		Object[][] tablePrep = new Object[nbPizzas][5];
//		
//		for (int i = 0; i < nbPizzas; i++)
//		{
//			String ingredients = "<html>";
//			for (Ingredient ing:orderPizzas.get(i).getIngredients())
//			{
//				ingredients += (ing.getName() + ", ");
//			}
//			ingredients += "</html>";
//			
//			Object[] temp = {new String(orderPizzas.get(i).getName()),
//							 new Double(orderPizzas.get(i).getPrice()),
//							 new String(ingredients),
//							 new Integer(orderPizzas.get(i).getCalorieCount()),
//							 new Integer(0)};
//			
//			tablePrep[i] = temp;
//		}
//		
//		return tablePrep;
//	}
}
