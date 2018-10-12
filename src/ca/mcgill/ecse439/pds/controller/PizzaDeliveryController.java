package ca.mcgill.ecse439.pds.controller;

import ca.mcgill.ecse439.pds.persistence.PersistenceXStream;

import java.util.ArrayList;

import ca.mcgill.ecse439.pds.model.CustomPizza;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Order;
import ca.mcgill.ecse439.pds.model.Order.OrderStatus;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class PizzaDeliveryController {
	
	public PizzaDeliveryController () 
	{
	}
	
	// Modify Order
	public void createOrder(String name, String phoneNumber, String email, String address, Pizza[] pizzas)
			throws InvalidInputException {
		new Order(name, phoneNumber, email, address, PizzaDeliveryManager.getInstance(), pizzas);
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeOrder(Order aOrder){
		aOrder.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateOrderStatus(Order aOrder){
		if(aOrder.getStatus()==OrderStatus.InProcess){
			aOrder.setStatus(OrderStatus.Delivered);
			this.removeOrder(aOrder);	// When the status changes to "delivered", the order will be removed
		}
		aOrder.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateOrder(Order aOrder, String aCustomerName, String aPhoneNumber, String aEmail, String aAddress,
			OrderStatus aStatus, Pizza...newPizzas) throws InvalidInputException{
		if((aPhoneNumber==null || aPhoneNumber.length()==0) && (aEmail==null || aEmail.length()==0)){
			throw new InvalidInputException("At least one of customer's phone number and email should be provided.");
		}
		aOrder.setAddress(aAddress);
		aOrder.setCustomerName(aCustomerName);
		aOrder.setEmail(aEmail);
		aOrder.setPhoneNumber(aPhoneNumber);
		aOrder.setPizzas(newPizzas);
		aOrder.setStatus(aStatus);
		aOrder.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	// Modify Ingredient
	public void createIngredient (String aName, Double aPrice) throws InvalidInputException{
		new Ingredient(aName, aPrice, PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeIngredient(Ingredient aIngredient){
		aIngredient.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateIngredient (Ingredient aIngredient, String aName, Double aPrice) throws InvalidInputException{
		aIngredient.setName(aName);
		aIngredient.setPrice(aPrice);
		aIngredient.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	// Modify MenuPizza
	public void createMenuPizza(String aName, Double aPrice, int aCalorieCount, Ingredient...ingredients) throws InvalidInputException{
		new MenuPizza(aPrice, PizzaDeliveryManager.getInstance(), aName, aCalorieCount, ingredients);
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeMenuPizza(MenuPizza aMenuPizza){
		aMenuPizza.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateMenuPizza(MenuPizza aMenuPizza, String aName, Double aPrice, int aCalorieCount, Ingredient...ingredients) throws InvalidInputException{
		aMenuPizza.setCalorieCount(aCalorieCount);
		aMenuPizza.setIngredients(ingredients);
		aMenuPizza.setName(aName);
		aMenuPizza.setPrice(aPrice);
		aMenuPizza.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
}
