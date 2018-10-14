package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.CENTER;

import ca.mcgill.ecse439.pds.controller.PizzaDeliveryController;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class ManageMenuPage extends JFrame
{
	private static final long serialVersionUID = -8440868920851450701L;

	private static String TITLE = "Manage Menu Items";
	private static Dimension TABLE_DIM = new Dimension(1000, 400);
	
	private List<MenuPizza> pizzas = new LinkedList<MenuPizza>();
	private String[] pizzaTableColumnNames = {"Name", "Price", "Ingredients", "Calories", "Update", "Delete" };
	private Object[][] pizzaTableData;
	
	private String error;
	
	// UI Elements
	private JLabel title;
	private JLabel errorMsg;
	
	private JTable pizzaTable;
	private DefaultTableModel tableModel;
	private JScrollPane pizzaScrollPane;
	
	public ManageMenuPage()
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
				
		// pizza Table
		pizzaTableData = prepTableData();
		pizzaTable = new JTable(pizzaTableData, pizzaTableColumnNames);	
		pizzaScrollPane = new JScrollPane(pizzaTable);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		DefaultTableCellRenderer bgRenderer = new DefaultTableCellRenderer();
		bgRenderer.setHorizontalAlignment( JLabel.CENTER );
		bgRenderer.setBackground(Color.decode("#FF6961"));
		tableModel = new DefaultTableModel(pizzaTableData, pizzaTableColumnNames)
		{
			private static final long serialVersionUID = 5452268246136815997L;

			@Override
		    public boolean isCellEditable(int row, int column)
			{
		       return false;
		    }
		};

		pizzaTable.setModel(tableModel);
		pizzaTable.setFillsViewportHeight(true);
		pizzaTable.setRowHeight(72);
		pizzaTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		pizzaTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		pizzaTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		pizzaTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		pizzaTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		pizzaTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		pizzaTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		pizzaTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		pizzaTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		pizzaTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		pizzaTable.getColumnModel().getColumn(4).setCellRenderer( bgRenderer );
		pizzaTable.getColumnModel().getColumn(5).setCellRenderer( bgRenderer );
		pizzaScrollPane.setPreferredSize(TABLE_DIM);
		pizzaTable.addMouseListener(new java.awt.event.MouseAdapter()
		{
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt)
		    {
		        int row = pizzaTable.rowAtPoint(evt.getPoint());
		        int col = pizzaTable.columnAtPoint(evt.getPoint());
		        
		        if ((col == 4) && (row > -1) && (row < pizzas.size()))
		        {
		            updateButtonActionPerformed(row);
		        }
		        else if ((col == 5) && (row > -1) && (row < pizzas.size()))
		        {
		            deleteButtonActionPerformed(row);
		        }
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
					.addComponent(pizzaScrollPane));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(errorMsg)
				.addGap(20)
				.addComponent(title)
				.addGap(30)
				.addComponent(pizzaScrollPane));

		pack();
		setLocationRelativeTo(null);
	}
	
	private void refreshData()
	{
		// Check for an Error
		errorMsg.setText(error);
		pack();
	}

	private Object[][] prepTableData()
	{		
		int nbPizzas = 0;
				
		if (PizzaDeliveryManager.getInstance().hasPizzas())
		{
			for(Pizza p:PizzaDeliveryManager.getInstance().getPizzas())
			{
				if(p instanceof MenuPizza)
				{
					pizzas.add((MenuPizza)p);
					nbPizzas++;
				}
			}
		}
		else
		{
			error = "There are currently no pizzas on the menu";
			return null;
		}
		
		Object[][] tablePrep = new Object[nbPizzas][6];
		
		for (int i = 0; i < nbPizzas; i++)
		{
			String ingredients = "<html>";
			for (Ingredient ing:pizzas.get(i).getIngredients())
			{
				ingredients += (ing.getName() + ", ");
			}
			ingredients += "</html>";
			
			Object[] temp = {new String(pizzas.get(i).getName()),
							 new Double(pizzas.get(i).getPrice()),
							 new String(ingredients),
							 new Integer(pizzas.get(i).getCalorieCount()),
							 new String("<html>Save<br>Changes</html>"),
							 new String("Delete")};
			
			tablePrep[i] = temp;
		}
		
		return tablePrep;
	}
	
	private void deleteButtonActionPerformed(int i) 
	{		
		PizzaDeliveryController cont = new PizzaDeliveryController();
		cont.removeMenuPizza(pizzas.get(i));
		
		tableModel.removeRow(i);
		pizzaTable.repaint();
	}
	
	private void updateButtonActionPerformed(int i) 
	{		
		PizzaDeliveryController cont = new PizzaDeliveryController();
		
		/**
		 * 
		 * TODO 
		 * 
		 */
		
		pizzaTable.repaint();
	}
}