/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;

import java.util.*;

// line 3 "../../../../../PizzaDeliverySystem.ump"
public class PizzaDeliveryManager {

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	private static PizzaDeliveryManager theInstance = null;

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// PizzaDeliveryManager Associations
	private List<Pizza> pizzas;
	private List<Ingredient> ingredients;
	private List<Order> orders;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	private PizzaDeliveryManager() {
		pizzas = new ArrayList<Pizza>();
		ingredients = new ArrayList<Ingredient>();
		orders = new ArrayList<Order>();
	}

	public static PizzaDeliveryManager getInstance() {
		if (theInstance == null) {
			theInstance = new PizzaDeliveryManager();
		}
		return theInstance;
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetMany */
	public Pizza getPizza(int index) {
		Pizza aPizza = pizzas.get(index);
		return aPizza;
	}

	public List<Pizza> getPizzas() {
		List<Pizza> newPizzas = Collections.unmodifiableList(pizzas);
		return newPizzas;
	}

	public int numberOfPizzas() {
		int number = pizzas.size();
		return number;
	}

	public boolean hasPizzas() {
		boolean has = pizzas.size() > 0;
		return has;
	}

	public int indexOfPizza(Pizza aPizza) {
		int index = pizzas.indexOf(aPizza);
		return index;
	}

	/* Code from template association_GetMany */
	public Ingredient getIngredient(int index) {
		Ingredient aIngredient = ingredients.get(index);
		return aIngredient;
	}

	public List<Ingredient> getIngredients() {
		List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
		return newIngredients;
	}

	public int numberOfIngredients() {
		int number = ingredients.size();
		return number;
	}

	public boolean hasIngredients() {
		boolean has = ingredients.size() > 0;
		return has;
	}

	public int indexOfIngredient(Ingredient aIngredient) {
		int index = ingredients.indexOf(aIngredient);
		return index;
	}

	/* Code from template association_GetMany */
	public Order getOrder(int index) {
		Order aOrder = orders.get(index);
		return aOrder;
	}

	public List<Order> getOrders() {
		List<Order> newOrders = Collections.unmodifiableList(orders);
		return newOrders;
	}

	public int numberOfOrders() {
		int number = orders.size();
		return number;
	}

	public boolean hasOrders() {
		boolean has = orders.size() > 0;
		return has;
	}

	public int indexOfOrder(Order aOrder) {
		int index = orders.indexOf(aOrder);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfPizzas() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Pizza addPizza(double aPrice, Ingredient... allIngredients) {
		return new Pizza(aPrice, this, allIngredients);
	}

	public boolean addPizza(Pizza aPizza) {
		boolean wasAdded = false;
		if (pizzas.contains(aPizza)) {
			return false;
		}
		PizzaDeliveryManager existingPizzaDeliveryManager = aPizza.getPizzaDeliveryManager();
		boolean isNewPizzaDeliveryManager = existingPizzaDeliveryManager != null
				&& !this.equals(existingPizzaDeliveryManager);
		if (isNewPizzaDeliveryManager) {
			aPizza.setPizzaDeliveryManager(this);
		} else {
			pizzas.add(aPizza);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removePizza(Pizza aPizza) {
		boolean wasRemoved = false;
		// Unable to remove aPizza, as it must always have a pizzaDeliveryManager
		if (!this.equals(aPizza.getPizzaDeliveryManager())) {
			pizzas.remove(aPizza);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addPizzaAt(Pizza aPizza, int index) {
		boolean wasAdded = false;
		if (addPizza(aPizza)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPizzas()) {
				index = numberOfPizzas() - 1;
			}
			pizzas.remove(aPizza);
			pizzas.add(index, aPizza);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMovePizzaAt(Pizza aPizza, int index) {
		boolean wasAdded = false;
		if (pizzas.contains(aPizza)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfPizzas()) {
				index = numberOfPizzas() - 1;
			}
			pizzas.remove(aPizza);
			pizzas.add(index, aPizza);
			wasAdded = true;
		} else {
			wasAdded = addPizzaAt(aPizza, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfIngredients() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Ingredient addIngredient(String aName, double aPrice) {
		return new Ingredient(aName, aPrice, this);
	}

	public boolean addIngredient(Ingredient aIngredient) {
		boolean wasAdded = false;
		if (ingredients.contains(aIngredient)) {
			return false;
		}
		PizzaDeliveryManager existingPizzaDeliveryManager = aIngredient.getPizzaDeliveryManager();
		boolean isNewPizzaDeliveryManager = existingPizzaDeliveryManager != null
				&& !this.equals(existingPizzaDeliveryManager);
		if (isNewPizzaDeliveryManager) {
			aIngredient.setPizzaDeliveryManager(this);
		} else {
			ingredients.add(aIngredient);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeIngredient(Ingredient aIngredient) {
		boolean wasRemoved = false;
		// Unable to remove aIngredient, as it must always have a pizzaDeliveryManager
		if (!this.equals(aIngredient.getPizzaDeliveryManager())) {
			ingredients.remove(aIngredient);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addIngredientAt(Ingredient aIngredient, int index) {
		boolean wasAdded = false;
		if (addIngredient(aIngredient)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfIngredients()) {
				index = numberOfIngredients() - 1;
			}
			ingredients.remove(aIngredient);
			ingredients.add(index, aIngredient);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index) {
		boolean wasAdded = false;
		if (ingredients.contains(aIngredient)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfIngredients()) {
				index = numberOfIngredients() - 1;
			}
			ingredients.remove(aIngredient);
			ingredients.add(index, aIngredient);
			wasAdded = true;
		} else {
			wasAdded = addIngredientAt(aIngredient, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfOrders() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Order addOrder(String aCustomerName, String aPhoneNumber, String aEmail, String aAddress,
			Pizza... allPizzas) {
		return new Order(aCustomerName, aPhoneNumber, aEmail, aAddress, this, allPizzas);
	}

	public boolean addOrder(Order aOrder) {
		boolean wasAdded = false;
		if (orders.contains(aOrder)) {
			return false;
		}
		PizzaDeliveryManager existingPizzaDeliveryManager = aOrder.getPizzaDeliveryManager();
		boolean isNewPizzaDeliveryManager = existingPizzaDeliveryManager != null
				&& !this.equals(existingPizzaDeliveryManager);
		if (isNewPizzaDeliveryManager) {
			aOrder.setPizzaDeliveryManager(this);
		} else {
			orders.add(aOrder);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeOrder(Order aOrder) {
		boolean wasRemoved = false;
		// Unable to remove aOrder, as it must always have a pizzaDeliveryManager
		if (!this.equals(aOrder.getPizzaDeliveryManager())) {
			orders.remove(aOrder);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addOrderAt(Order aOrder, int index) {
		boolean wasAdded = false;
		if (addOrder(aOrder)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfOrders()) {
				index = numberOfOrders() - 1;
			}
			orders.remove(aOrder);
			orders.add(index, aOrder);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveOrderAt(Order aOrder, int index) {
		boolean wasAdded = false;
		if (orders.contains(aOrder)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfOrders()) {
				index = numberOfOrders() - 1;
			}
			orders.remove(aOrder);
			orders.add(index, aOrder);
			wasAdded = true;
		} else {
			wasAdded = addOrderAt(aOrder, index);
		}
		return wasAdded;
	}

	public void delete() {
		while (pizzas.size() > 0) {
			Pizza aPizza = pizzas.get(pizzas.size() - 1);
			aPizza.delete();
			pizzas.remove(aPizza);
		}

		while (ingredients.size() > 0) {
			Ingredient aIngredient = ingredients.get(ingredients.size() - 1);
			aIngredient.delete();
			ingredients.remove(aIngredient);
		}

		while (orders.size() > 0) {
			Order aOrder = orders.get(orders.size() - 1);
			aOrder.delete();
			orders.remove(aOrder);
		}

	}

}