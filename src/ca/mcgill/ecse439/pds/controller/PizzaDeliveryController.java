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

public class PizzaDeliveryController
{	
	final double BASE_PRICE=3.00;
	
	public PizzaDeliveryController () 
	{
	}
	
	// Order --------------------------------------------------------------------------------------------------------------------------------------
	public void createOrder(String name, String phoneNumber,
							String email, String address,
							Pizza[] pizzas, int[] number) throws InvalidInputException
	{
		try
		{
			Order order = PizzaDeliveryManager.getInstance().addOrder(name, phoneNumber, email, address, pizzas);
			for(int i:number)
			{
				order.addNumberOfEachPizza(i);
			}
		}
		catch (RuntimeException e)
		{
			throw new InvalidInputException(e.getMessage());
		}
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeOrder(Order aOrder){
		aOrder.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateOrderStatus(Order aOrder)
	{
		if (aOrder.getStatus() == OrderStatus.InProcess)
		{
			aOrder.setStatus(OrderStatus.Delivered);
			
			ArrayList<Pizza> ps=new ArrayList<>(aOrder.getPizzas());
			
			for(Pizza p:ps)
			{
				if (p instanceof CustomPizza)
				{
					this.removeCustomPizza((CustomPizza)p);			// Also delete the custom pizza created
				}
			}
		}
		this.removeOrder(aOrder);	// When the status changes to "delivered", the order will be removed
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateOrder(Order aOrder, String aCustomerName,
							String aPhoneNumber, String aEmail,
							String aAddress, OrderStatus aStatus, Pizza...newPizzas) throws InvalidInputException
	{
		if (!PizzaDeliveryManager.getInstance().getOrders().contains(aOrder))
		{
			throw new InvalidInputException("Order not found");
		}
		if ((aPhoneNumber == null || aPhoneNumber.length() == 0) && (aEmail == null || aEmail.length() == 0))
		{
			throw new InvalidInputException("At least one of customer's phone number and email should be provided.");
		}
		
		aOrder.setAddress(aAddress);
		aOrder.setCustomerName(aCustomerName);
		aOrder.setEmail(aEmail);
		aOrder.setPhoneNumber(aPhoneNumber);
		aOrder.setPizzas(newPizzas);
		aOrder.setStatus(aStatus);
		if(aOrder.getStatus() == OrderStatus.Delivered)
		{
			for(Pizza p:aOrder.getPizzas())
			{
				if (p instanceof CustomPizza)
				{
					this.removeCustomPizza((CustomPizza)p);			// Also delete the custom pizza created
				}
			}
			this.removeOrder(aOrder);
			return;
		}
		
		aOrder.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public double getOrderValue(Order aOrder)
	{	
		double total = 0;
		ArrayList<Pizza> ps=new ArrayList<>(aOrder.getPizzas());
		for (int i = 0; i < ps.size(); i++)
		{
			total += aOrder.getPizza(i).getPrice() * aOrder.getNumberOfEachPizza(i);
		}
		
		return total;
	}
	
	// Ingredient -----------------------------------------------------------------------------------------------------------------------------------
	public void createIngredient (String aName, double aPrice) throws InvalidInputException
	{
		try
		{
			PizzaDeliveryManager.getInstance().addIngredient(aName, aPrice);
		}
		catch (RuntimeException e)
		{
			throw new InvalidInputException(e.getMessage());
		}
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeIngredient(Ingredient aIngredient)
	{
		aIngredient.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateIngredient (Ingredient aIngredient, String aName, double aPrice) throws InvalidInputException
	{
		if (!PizzaDeliveryManager.getInstance().getIngredients().contains(aIngredient))
		{
			throw new InvalidInputException("Ingredient not found.");
		}
		
		aIngredient.setName(aName);
		aIngredient.setPrice(aPrice);
		aIngredient.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	// MenuPizza ----------------------------------------------------------------------------------------------------------------------------------------
	public void createMenuPizza(String aName, double aPrice, int aCalorieCount, Ingredient...ingredients) throws InvalidInputException
	{
		try
		{
			new MenuPizza(aPrice, PizzaDeliveryManager.getInstance(), aName, aCalorieCount, ingredients);
		}
		catch(RuntimeException e)
		{
			throw new InvalidInputException(e.getMessage());
		}
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeMenuPizza(MenuPizza aMenuPizza)
	{
		aMenuPizza.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateMenuPizza(MenuPizza aMenuPizza, String aName, double aPrice,
								int aCalorieCount, Ingredient...ingredients) throws InvalidInputException
	{
		if (!PizzaDeliveryManager.getInstance().getPizzas().contains(aMenuPizza))
		{
			throw new InvalidInputException("Pizza not found");
		}
		
		aMenuPizza.setCalorieCount(aCalorieCount);
		aMenuPizza.setIngredients(ingredients);
		aMenuPizza.setName(aName);
		aMenuPizza.setPrice(aPrice);
		aMenuPizza.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	// CustomPizza -------------------------------------------------------------------------------------------------------------------------------------
	public void createCustomPizza(Ingredient... ingredients) throws InvalidInputException
	{
		double price = BASE_PRICE;
		for (Ingredient i:ingredients)
		{
			price+=i.getPrice();
		}
		
		try
		{
			new CustomPizza(price,PizzaDeliveryManager.getInstance(),ingredients);
		}
		catch(RuntimeException e)
		{
			throw new InvalidInputException(e.getMessage());
		}
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void removeCustomPizza(CustomPizza aCustomPizza)
	{
		aCustomPizza.delete();
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
	
	public void updateCustomPizza(CustomPizza aCustomPizza, Ingredient...ingredients) throws InvalidInputException
	{	
		if (!PizzaDeliveryManager.getInstance().getPizzas().contains(aCustomPizza))
		{
			throw new InvalidInputException("Pizza not found.");
		}
		
		double price = BASE_PRICE;
		for (Ingredient i:ingredients)
		{
			price += i.getPrice();
		}
		
		aCustomPizza.setIngredients(ingredients);
		aCustomPizza.setPrice(price);
		aCustomPizza.setPizzaDeliveryManager(PizzaDeliveryManager.getInstance());
		PersistenceXStream.saveToXMLwithXStream(PizzaDeliveryManager.getInstance());
	}
}
