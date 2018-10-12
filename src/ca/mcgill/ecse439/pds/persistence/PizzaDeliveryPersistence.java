package ca.mcgill.ecse439.pds.persistence;

import java.util.Iterator;

import ca.mcgill.ecse439.pds.model.CustomPizza;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Order;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class PizzaDeliveryPersistence {

    private static String filename = "data.xml";
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("custom_pizza", CustomPizza.class);
		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		PersistenceXStream.setAlias("menu_pizza", MenuPizza.class);
		PersistenceXStream.setAlias("order", Order.class);
		PersistenceXStream.setAlias("pizza", Pizza.class);						// TODO: Maybe unnecessary...
		PersistenceXStream.setAlias("manager", PizzaDeliveryManager.class);
	}

	public static void loadEventRegistrationModel() {
		PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();
		PizzaDeliveryPersistence.initializeXStream();
		PizzaDeliveryManager pdm2 = (PizzaDeliveryManager) PersistenceXStream.loadFromXMLwithXStream();
		if (pdm2 != null) {
			Iterator<Order> oIt = pdm2.getOrders().iterator();
			while (oIt.hasNext())
				pdm.addOrder(oIt.next());
			Iterator<Pizza> pIt = pdm2.getPizzas().iterator();
			while (pIt.hasNext())
				pdm.addPizza(pIt.next());
			Iterator<Ingredient> iIt = pdm2.getIngredients().iterator();
			while (iIt.hasNext())
				pdm.addIngredient(iIt.next());
		}
	}

}
