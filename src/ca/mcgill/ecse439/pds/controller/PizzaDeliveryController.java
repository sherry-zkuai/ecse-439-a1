package ca.mcgill.ecse439.pds.controller;

import ca.mcgill.ecse439.pds.persistence.PersistenceXStream;

import java.util.ArrayList;

import ca.mcgill.ecse439.pds.model.CustomPizza;
import ca.mcgill.ecse439.pds.model.Ingredient;
import ca.mcgill.ecse439.pds.model.MenuPizza;
import ca.mcgill.ecse439.pds.model.Order;
import ca.mcgill.ecse439.pds.model.Pizza;
import ca.mcgill.ecse439.pds.model.PizzaDeliveryManager;

public class PizzaDeliveryController {

	public PizzaDeliveryController() {
	}

	public void createOrder(String name, String phoneNumber, String email, String address, Pizza[] pizzas)
			throws InvalidInputException {
		new Order(name, phoneNumber, email, address, PizzaDeliveryManager.getInstance(), pizzas);
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
}
