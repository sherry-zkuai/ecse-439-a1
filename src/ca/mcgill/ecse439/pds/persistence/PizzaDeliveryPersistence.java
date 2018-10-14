package ca.mcgill.ecse439.pds.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import ca.mcgill.ecse439.pds.model.CustomPizza;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Order;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class PizzaDeliveryPersistence {

    private static String filename = "output/data.xml";
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("custom_pizza", CustomPizza.class);
		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		PersistenceXStream.setAlias("menu_pizza", MenuPizza.class);
		PersistenceXStream.setAlias("order", Order.class);
		PersistenceXStream.setAlias("pizza", Pizza.class);
		PersistenceXStream.setAlias("manager", PizzaDeliveryManager.class);
		
		// If the model exists, load it; if not, create it
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
				//----Start of adding basic menu items-------
				PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();
				
				Ingredient flour = new Ingredient("Flour", 0.0, pdm);
				Ingredient yeast = new Ingredient("Yeast", 0.0, pdm);
				Ingredient sauce = new Ingredient("Sauce", 0, pdm);
				Ingredient cheese = new Ingredient("Cheese", 2, pdm);
				Ingredient tomato = new Ingredient("Tomato", 2.0, pdm);
				Ingredient pepperoni = new Ingredient("Pepperoni", 3, pdm);
				Ingredient basil = new Ingredient("Basil", 1.0, pdm);
				Ingredient bacon=new Ingredient("Bacon", 3, pdm);
				
				MenuPizza marinara = new MenuPizza(9.5, pdm, "Marinara", 1000, flour, yeast, cheese, sauce);
				MenuPizza pprn = new MenuPizza(11, pdm, "Pepperoni", 1200, flour, yeast, cheese, sauce, pepperoni);
				MenuPizza neapolitan = new MenuPizza(10, pdm, "Neapolitan", 1100, flour, yeast, sauce, cheese, tomato, basil);
				MenuPizza carbonara = new MenuPizza(10, pdm, "Carbonara", 1100, flour, yeast, sauce, cheese, tomato, bacon);

				PersistenceXStream.saveToXMLwithXStream(pdm);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void loadPizzaDeliveryModel() {
		PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();
		PizzaDeliveryPersistence.initializeXStream();
		PizzaDeliveryManager pdm2 = (PizzaDeliveryManager) PersistenceXStream.loadFromXMLwithXStream();

		if(pdm2!=null){
			ArrayList<Ingredient> is=new ArrayList<>(pdm2.getIngredients());
			ArrayList<Pizza> ps=new ArrayList<>(pdm2.getPizzas());
			ArrayList<Order> os=new ArrayList<>(pdm2.getOrders());
			for(int i=0;i<is.size();i++){
				pdm.addIngredient(is.get(i));
			}
			for(int i=0;i<ps.size();i++){
				pdm.addPizza(ps.get(i));
			}
			for(int i=0;i<os.size();i++){
				pdm.addOrder(os.get(i));
			}
		}
	}
}
