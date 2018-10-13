package ca.mcgill.ecse439.pds.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.CENTER;

import ca.mcgill.ecse439.pds.controller.PizzaDeliveryController;
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
	private String[] orderTableColumnNames = {"Customer", "Contact", "Address", "Bill", "Order", "Delivering" };
	private Object[][] orderTableData;
	
	private String error;
	
	// UI Elements
	private JLabel title;
	private JLabel errorMsg;
	
	private JTable orderTable;
	private DefaultTableModel tableModel;
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
		orderTableData = prepTableData();
		orderTable = new JTable(orderTableData, orderTableColumnNames);	
		orderScrollPane = new JScrollPane(orderTable);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		DefaultTableCellRenderer bgRenderer = new DefaultTableCellRenderer();
		bgRenderer.setHorizontalAlignment( JLabel.CENTER );
		bgRenderer.setBackground(Color.decode("#FF6961"));
		tableModel = new DefaultTableModel(orderTableData, orderTableColumnNames)
		{
			private static final long serialVersionUID = 5452268246136815997L;

			@Override
		    public boolean isCellEditable(int row, int column)
			{
		       return false;
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
		orderTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		orderTable.getColumnModel().getColumn(5).setCellRenderer( bgRenderer );
		orderScrollPane.setPreferredSize(TABLE_DIM);
		orderTable.addMouseListener(new java.awt.event.MouseAdapter()
		{
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt)
		    {
		        int row = orderTable.rowAtPoint(evt.getPoint());
		        int col = orderTable.columnAtPoint(evt.getPoint());
		        
		        if ((col == 5) && (row > -1) && (row < orders.size()))
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

	private Object[][] prepTableData()
	{		
		if (PizzaDeliveryManager.getInstance().hasOrders())
		{
			orders = PizzaDeliveryManager.getInstance().getOrders();
		}
		else
		{
			error = "There are no orders in the system";
			refreshData();
			return null;
		}
		
		PizzaDeliveryController cont = new PizzaDeliveryController();

		Object[][] tablePrep = new Object[orders.size()][6];
		
		for (int i = 0; i < orders.size(); i++)
		{	
			String contact;
			if (orders.get(i).getPhoneNumber() != null && !orders.get(i).getPhoneNumber().equals(""))
			{
				contact = orders.get(i).getPhoneNumber();
			}
			else if (orders.get(i).getEmail() != null && !orders.get(i).getEmail().equals(""))
			{
				contact = orders.get(i).getEmail();
			}
			else
			{
				error = "Could not find customer contact info. Internal error. Contact Admin.";
				refreshData();
				return null;
			}
									
			String items = "<html>";
			for (int k = 0; k < orders.get(i).getPizzas().size(); k++)
			{	
				Pizza p = orders.get(i).getPizza(k);
				
				int nbPizzas = orders.get(i).getNumberOfEachPizza(k);

				if (nbPizzas < 1)
				{
					continue;
				}
				
				items += Integer.toString(nbPizzas) + " x ";
				if (p instanceof MenuPizza)
				{
					items += (((MenuPizza) p).getName()+ ", ");
				}
				else
				{
					items += ("Custom Pizza, ");
				}
			}
			items += "</html>";
						
			Object[] temp = {new String(orders.get(i).getCustomerName()),
							 new String(contact),
							 new String("<html>" + orders.get(i).getAddress() + "</html>"),
							 new Double(cont.getOrderValue(orders.get(i))),
							 new String(items),
							 "Close Order"};
			
			tablePrep[i] = temp;
		}
		
		return tablePrep;
	}
	
	private void deleteButtonActionPerformed(int i) 
	{		
		PizzaDeliveryController cont = new PizzaDeliveryController();
		cont.removeOrder(orders.get(i));
		
		tableModel.removeRow(i);
		orderTable.repaint();

	}
}