package ca.mcgill.ecse439.pds.application;

import ca.mcgill.ecse439.pds.persistence.PizzaDeliveryPersistence;
import ca.mcgill.ecse439.pds.view.HomePage;

public class PizzaDeliverySystem {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// load model
		PizzaDeliveryPersistence.loadPizzaDeliveryModel();
		
		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HomePage().setVisible(true);
			}
		});

	}
}
