/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;

import java.util.*;

// line 12 "../../../../../PizzaDeliverySystem.ump"
public class Pizza {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Pizza Attributes
	private double price;

	// Pizza Associations
	private List<Ingredient> ingredients;
	private PizzaDeliveryManager pizzaDeliveryManager;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Pizza(double aPrice, PizzaDeliveryManager aPizzaDeliveryManager, Ingredient... allIngredients) {
		// line 19 "../../../../../PizzaDeliverySystem.ump"
		if (allIngredients == null || allIngredients.length == 0) {
			throw new RuntimeException("Pizza ingredients cannot be empty");
		}
		// END OF UMPLE BEFORE INJECTION
		price = aPrice;
		ingredients = new ArrayList<Ingredient>();
		boolean didAddIngredients = setIngredients(allIngredients);
		if (!didAddIngredients) {
			throw new RuntimeException("Unable to create Pizza, must have at least 1 ingredients");
		}
		boolean didAddPizzaDeliveryManager = setPizzaDeliveryManager(aPizzaDeliveryManager);
		if (!didAddPizzaDeliveryManager) {
			throw new RuntimeException("Unable to create pizza due to pizzaDeliveryManager");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setPrice(double aPrice) {
		boolean wasSet = false;
		price = aPrice;
		wasSet = true;
		return wasSet;
	}

	public double getPrice() {
		return price;
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

	/* Code from template association_GetOne */
	public PizzaDeliveryManager getPizzaDeliveryManager() {
		return pizzaDeliveryManager;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfIngredients() {
		return 1;
	}

	/* Code from template association_AddUnidirectionalMStar */
	public boolean addIngredient(Ingredient aIngredient) {
		boolean wasAdded = false;
		if (ingredients.contains(aIngredient)) {
			return false;
		}
		ingredients.add(aIngredient);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeIngredient(Ingredient aIngredient) {
		boolean wasRemoved = false;
		if (!ingredients.contains(aIngredient)) {
			return wasRemoved;
		}

		if (numberOfIngredients() <= minimumNumberOfIngredients()) {
			return wasRemoved;
		}

		ingredients.remove(aIngredient);
		wasRemoved = true;
		return wasRemoved;
	}

	/* Code from template association_SetUnidirectionalMStar */
	public boolean setIngredients(Ingredient... newIngredients) {
		boolean wasSet = false;
		ArrayList<Ingredient> verifiedIngredients = new ArrayList<Ingredient>();
		for (Ingredient aIngredient : newIngredients) {
			if (verifiedIngredients.contains(aIngredient)) {
				continue;
			}
			verifiedIngredients.add(aIngredient);
		}

		if (verifiedIngredients.size() != newIngredients.length
				|| verifiedIngredients.size() < minimumNumberOfIngredients()) {
			return wasSet;
		}

		ingredients.clear();
		ingredients.addAll(verifiedIngredients);
		wasSet = true;
		return wasSet;
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

	/* Code from template association_SetOneToMany */
	public boolean setPizzaDeliveryManager(PizzaDeliveryManager aPizzaDeliveryManager) {
		boolean wasSet = false;
		if (aPizzaDeliveryManager == null) {
			return wasSet;
		}

		PizzaDeliveryManager existingPizzaDeliveryManager = pizzaDeliveryManager;
		pizzaDeliveryManager = aPizzaDeliveryManager;
		if (existingPizzaDeliveryManager != null && !existingPizzaDeliveryManager.equals(aPizzaDeliveryManager)) {
			existingPizzaDeliveryManager.removePizza(this);
		}
		pizzaDeliveryManager.addPizza(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		ingredients.clear();
		PizzaDeliveryManager placeholderPizzaDeliveryManager = pizzaDeliveryManager;
		this.pizzaDeliveryManager = null;
		if (placeholderPizzaDeliveryManager != null) {
			placeholderPizzaDeliveryManager.removePizza(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "price" + ":" + getPrice() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "pizzaDeliveryManager = "
				+ (getPizzaDeliveryManager() != null
						? Integer.toHexString(System.identityHashCode(getPizzaDeliveryManager()))
						: "null");
	}
}