/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;
import java.util.*;

// line 47 "../../../../../PizzaDeliverySystem.ump"
public class Ingredient
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Ingredient> ingredientsByName = new HashMap<String, Ingredient>();

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
    // line 53 "../../../../../PizzaDeliverySystem.ump"
    if(aName==null || aName.length()==0)
    		{
    			throw new RuntimeException("Ingredient name cannot be empty");
    		}
    // END OF UMPLE BEFORE INJECTION
    price = aPrice;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
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
    // line 53 "../../../../../PizzaDeliverySystem.ump"
    if(aName==null || aName.length()==0)
    		{
    			throw new RuntimeException("Ingredient name cannot be empty");
    		}
    // END OF UMPLE BEFORE INJECTION
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      ingredientsByName.remove(anOldName);
    }
    ingredientsByName.put(aName, this);
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
  /* Code from template attribute_GetUnique */
  public static Ingredient getWithName(String aName)
  {
    return ingredientsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setPizzaDeliveryManager(PizzaDeliveryManager aPizzaDeliveryManager)
  {
    boolean wasSet = false;
    //Must provide pizzaDeliveryManager to ingredient
    if (aPizzaDeliveryManager == null)
    {
      return wasSet;
    }

    if (pizzaDeliveryManager != null && pizzaDeliveryManager.numberOfIngredients() <= PizzaDeliveryManager.minimumNumberOfIngredients())
    {
      return wasSet;
    }

    PizzaDeliveryManager existingPizzaDeliveryManager = pizzaDeliveryManager;
    pizzaDeliveryManager = aPizzaDeliveryManager;
    if (existingPizzaDeliveryManager != null && !existingPizzaDeliveryManager.equals(aPizzaDeliveryManager))
    {
      boolean didRemove = existingPizzaDeliveryManager.removeIngredient(this);
      if (!didRemove)
      {
        pizzaDeliveryManager = existingPizzaDeliveryManager;
        return wasSet;
      }
    }
    pizzaDeliveryManager.addIngredient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ingredientsByName.remove(getName());
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