package ca.mcgill.ecse439.pds.controller;

import ca.mcgill.ecse439.pds.persistence.PersistenceXStream;

import java.util.ArrayList;

import ca.mcgill.ecse439.pds.model.Address;
import ca.mcgill.ecse439.pds.model.CustomPizza;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Order;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class PizzaDeliveryController {
	
	public PizzaDeliveryController () 
	{
	}
	
	public void createOrder(String name, Address address, Pizza[] pizzas) throws InvalidInputException
	{
		if (name == null || name.trim().length() == 0)
			throw new InvalidInputException("You must enter your name");
		if (address == null)
			throw new InvalidInputException("You must enter your address");
		if (pizzas == null || pizzas.length < 1)
			throw new InvalidInputException("You must order at least one pizza");
		
		Order o = new Order(name, pizzas);
		o.setAddress(address);
		
		PizzaDeliveryManager pdm = PizzaDeliveryManager.getInstance();
		pdm.addOrder(o);
		PersistenceXStream.saveToXMLwithXStream(pdm);
	}
}
