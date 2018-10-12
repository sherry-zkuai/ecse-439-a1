/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;

import java.util.*;

// line 59 "../../../../../PizzaDeliverySystem.ump"
public class Order {

	// ------------------------
	// ENUMERATIONS
	// ------------------------

	public enum OrderStatus {
		InProcess, Delivered
	}

	// ------------------------
	// STATIC VARIABLES
	// ------------------------

	private static int nextOrderId = 1;

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Order Attributes
	private String customerName;
	private String phoneNumber;
	private String email;
	private String address;
	private OrderStatus status;

	// Autounique Attributes
	private int orderId;

	// Order Associations
	private List<Pizza> pizzas;
	private PizzaDeliveryManager pizzaDeliveryManager;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Order(String aCustomerName, String aPhoneNumber, String aEmail, String aAddress,
			PizzaDeliveryManager aPizzaDeliveryManager, Pizza... allPizzas) {
		// line 73 "../../../../../PizzaDeliverySystem.ump"
		if (aCustomerName == null || aCustomerName.length() == 0) {
			throw new RuntimeException("Customer name cannot be empty");
		}
		// END OF UMPLE BEFORE INJECTION
		// line 80 "../../../../../PizzaDeliverySystem.ump"
		if (aAddress == null || aAddress.length() == 0) {
			throw new RuntimeException("Customer address cannot be empty");
		}
		// END OF UMPLE BEFORE INJECTION
		// line 87 "../../../../../PizzaDeliverySystem.ump"
		if ((aPhoneNumber == null || aPhoneNumber.length() == 0) && (aEmail == null || aEmail.length() == 0)) {
			throw new RuntimeException("At least one of customer's phone number and email should be provided");
		}
		// END OF UMPLE BEFORE INJECTION
		customerName = aCustomerName;
		phoneNumber = aPhoneNumber;
		email = aEmail;
		address = aAddress;
		status = OrderStatus.InProcess;
		orderId = nextOrderId++;
		pizzas = new ArrayList<Pizza>();
		boolean didAddPizzas = setPizzas(allPizzas);
		if (!didAddPizzas) {
			throw new RuntimeException("Unable to create Order, must have at least 1 pizzas");
		}
		boolean didAddPizzaDeliveryManager = setPizzaDeliveryManager(aPizzaDeliveryManager);
		if (!didAddPizzaDeliveryManager) {
			throw new RuntimeException("Unable to create order due to pizzaDeliveryManager");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setCustomerName(String aCustomerName) {
		boolean wasSet = false;
		// line 73 "../../../../../PizzaDeliverySystem.ump"
		if (aCustomerName == null || aCustomerName.length() == 0) {
			throw new RuntimeException("Customer name cannot be empty");
		}
		// END OF UMPLE BEFORE INJECTION
		customerName = aCustomerName;
		wasSet = true;
		return wasSet;
	}

	public boolean setPhoneNumber(String aPhoneNumber) {
		boolean wasSet = false;
		phoneNumber = aPhoneNumber;
		wasSet = true;
		return wasSet;
	}

	public boolean setEmail(String aEmail) {
		boolean wasSet = false;
		email = aEmail;
		wasSet = true;
		return wasSet;
	}

	public boolean setAddress(String aAddress) {
		boolean wasSet = false;
		// line 80 "../../../../../PizzaDeliverySystem.ump"
		if (aAddress == null || aAddress.length() == 0) {
			throw new RuntimeException("Customer address cannot be empty");
		}
		// END OF UMPLE BEFORE INJECTION
		address = aAddress;
		wasSet = true;
		return wasSet;
	}

	public boolean setStatus(OrderStatus aStatus) {
		boolean wasSet = false;
		status = aStatus;
		wasSet = true;
		return wasSet;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public int getOrderId() {
		return orderId;
	}

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

	/* Code from template association_GetOne */
	public PizzaDeliveryManager getPizzaDeliveryManager() {
		return pizzaDeliveryManager;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfPizzas() {
		return 1;
	}

	/* Code from template association_AddUnidirectionalMStar */
	public boolean addPizza(Pizza aPizza) {
		boolean wasAdded = false;
		if (pizzas.contains(aPizza)) {
			return false;
		}
		pizzas.add(aPizza);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removePizza(Pizza aPizza) {
		boolean wasRemoved = false;
		if (!pizzas.contains(aPizza)) {
			return wasRemoved;
		}

		if (numberOfPizzas() <= minimumNumberOfPizzas()) {
			return wasRemoved;
		}

		pizzas.remove(aPizza);
		wasRemoved = true;
		return wasRemoved;
	}

	/* Code from template association_SetUnidirectionalMStar */
	public boolean setPizzas(Pizza... newPizzas) {
		boolean wasSet = false;
		ArrayList<Pizza> verifiedPizzas = new ArrayList<Pizza>();
		for (Pizza aPizza : newPizzas) {
			if (verifiedPizzas.contains(aPizza)) {
				continue;
			}
			verifiedPizzas.add(aPizza);
		}

		if (verifiedPizzas.size() != newPizzas.length || verifiedPizzas.size() < minimumNumberOfPizzas()) {
			return wasSet;
		}

		pizzas.clear();
		pizzas.addAll(verifiedPizzas);
		wasSet = true;
		return wasSet;
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

	/* Code from template association_SetOneToMany */
	public boolean setPizzaDeliveryManager(PizzaDeliveryManager aPizzaDeliveryManager) {
		boolean wasSet = false;
		if (aPizzaDeliveryManager == null) {
			return wasSet;
		}

		PizzaDeliveryManager existingPizzaDeliveryManager = pizzaDeliveryManager;
		pizzaDeliveryManager = aPizzaDeliveryManager;
		if (existingPizzaDeliveryManager != null && !existingPizzaDeliveryManager.equals(aPizzaDeliveryManager)) {
			existingPizzaDeliveryManager.removeOrder(this);
		}
		pizzaDeliveryManager.addOrder(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		pizzas.clear();
		PizzaDeliveryManager placeholderPizzaDeliveryManager = pizzaDeliveryManager;
		this.pizzaDeliveryManager = null;
		if (placeholderPizzaDeliveryManager != null) {
			placeholderPizzaDeliveryManager.removeOrder(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "orderId" + ":" + getOrderId() + "," + "customerName" + ":" + getCustomerName()
				+ "," + "phoneNumber" + ":" + getPhoneNumber() + "," + "email" + ":" + getEmail() + "," + "address"
				+ ":" + getAddress() + "]" + System.getProperties().getProperty("line.separator") + "  " + "status"
				+ "="
				+ (getStatus() != null
						? !getStatus().equals(this) ? getStatus().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "pizzaDeliveryManager = "
				+ (getPizzaDeliveryManager() != null
						? Integer.toHexString(System.identityHashCode(getPizzaDeliveryManager()))
						: "null");
	}
}