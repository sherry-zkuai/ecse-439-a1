package ca.mcgill.ecse439.pds.persistence;

import java.io.File;
import java.io.IOException;
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
		
		// If the model exists, load it; if not, create it
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			//----Start of adding basic menu items-------
			PizzaDeliveryManager pdm=PizzaDeliveryManager.getInstance();
			
			Ingredient flour=new Ingredient("flour",0.0,pdm);
			Ingredient yeast=new Ingredient("yeast",0.0,pdm);
			Ingredient sauce=new Ingredient("sauce",0,pdm);
			Ingredient cheese=new Ingredient("cheese",2,pdm);
			Ingredient tomato=new Ingredient("tomato",2.0,pdm);
			Ingredient pepperoni=new Ingredient("pepperoni",3,pdm);
			
			MenuPizza marinara=new MenuPizza(9.5,pdm,"marinara",1000,flour,yeast,cheese,sauce);
			MenuPizza pprn=new MenuPizza(11,pdm,"pepperoni",1200,flour,yeast,cheese,sauce,pepperoni);
			MenuPizza neapolitan=new MenuPizza(10,pdm,"neapolitan",1100,flour,yeast,sauce,cheese,tomato);

			PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
		}
	}

	public static void loadEventRegistrationModel() {
		//PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();
		PizzaDeliveryPersistence.initializeXStream();
//		PizzaDeliveryManager pdm = (PizzaDeliveryManager) PersistenceXStream.loadFromXMLwithXStream();
//		if (pdm2 != null) {
//			Iterator<Order> oIt = pdm2.getOrders().iterator();
//			while (oIt.hasNext())
//				pdm.addOrder(oIt.next());
//			Iterator<Pizza> pIt = pdm2.getPizzas().iterator();
//			while (pIt.hasNext())
//				pdm.addPizza(pIt.next());
//			Iterator<Ingredient> iIt = pdm2.getIngredients().iterator();
//			while (iIt.hasNext())
//				pdm.addIngredient(iIt.next());
//		}
	}

}
