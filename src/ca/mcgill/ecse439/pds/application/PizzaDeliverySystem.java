package ca.mcgill.ecse439.pds.application;

import ca.mcgill.ecse439.pds.persistence.PersistenceXStream;
import ca.mcgill.ecse439.pds.persistence.PizzaDeliveryPersistence;

import java.util.LinkedList;

import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;
import ca.mcgill.ecse439.pds.view.HomePage;

public class PizzaDeliverySystem {

	public static LinkedList<Ingredient> pantry = new LinkedList<Ingredient>();
	public static MenuPizza[] menu = null; // TODO
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// load model
		PizzaDeliveryPersistence.loadPizzaDeliveryModel();

//		// Add Ingredients and Menu Items
//		pantry.add(new Ingredient("Eggplant", 999.0, PizzaDeliveryManager.getInstance()));
//		pantry.add(new Ingredient("Spaghet", 222.0, PizzaDeliveryManager.getInstance()));
//		pantry.add(new Ingredient("peps", 0.111, PizzaDeliveryManager.getInstance()));
//		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
		
		// start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HomePage().setVisible(true);
			}
		});

	}
}
