package ca.mcgill.ecse439.pds.application;

import ca.mcgill.ecse439.pds.persistence.PizzaDeliveryPersistence;
import ca.mcgill.ecse439.pds.view.HomePage;

public class PizzaDeliverySystem
{	
	public static void main(String[] args)
	{
		PizzaDeliveryPersistence.loadPizzaDeliveryModel();
		
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new HomePage().setVisible(true);
			}
		});

	}
}
