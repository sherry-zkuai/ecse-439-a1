/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;

// line 46 "../../../../../PizzaDeliverySystem.ump"
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String name;
  private double price;

  //Ingredient Associations
  private PizzaDeliveryManager pizzaDeliveryManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, double aPrice, PizzaDeliveryManager aPizzaDeliveryManager)
  {
    // line 52 "../../../../../PizzaDeliverySystem.ump"
    if(aName==null || aName.length()==0)
    		{
    			throw new RuntimeException("Ingredient name cannot be empty");
    		}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    price = aPrice;
    boolean didAddPizzaDeliveryManager = setPizzaDeliveryManager(aPizzaDeliveryManager);
    if (!didAddPizzaDeliveryManager)
    {
      throw new RuntimeException("Unable to create ingredient due to pizzaDeliveryManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 52 "../../../../../PizzaDeliverySystem.ump"
    if(aName==null || aName.length()==0)
    		{
    			throw new RuntimeException("Ingredient name cannot be empty");
    		}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public PizzaDeliveryManager getPizzaDeliveryManager()
  {
    return pizzaDeliveryManager;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPizzaDeliveryManager(PizzaDeliveryManager aPizzaDeliveryManager)
  {
    boolean wasSet = false;
    if (aPizzaDeliveryManager == null)
    {
      return wasSet;
    }

    PizzaDeliveryManager existingPizzaDeliveryManager = pizzaDeliveryManager;
    pizzaDeliveryManager = aPizzaDeliveryManager;
    if (existingPizzaDeliveryManager != null && !existingPizzaDeliveryManager.equals(aPizzaDeliveryManager))
    {
      existingPizzaDeliveryManager.removeIngredient(this);
    }
    pizzaDeliveryManager.addIngredient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    PizzaDeliveryManager placeholderPizzaDeliveryManager = pizzaDeliveryManager;
    this.pizzaDeliveryManager = null;
    if(placeholderPizzaDeliveryManager != null)
    {
      placeholderPizzaDeliveryManager.removeIngredient(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pizzaDeliveryManager = "+(getPizzaDeliveryManager()!=null?Integer.toHexString(System.identityHashCode(getPizzaDeliveryManager())):"null");
  }
}