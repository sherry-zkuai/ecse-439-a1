/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;
import java.util.*;

// line 46 "../../../../../PizzaDeliverySystem.ump"
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
  private List<Pizza> pizzas;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, double aPrice)
  {
    price = aPrice;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    pizzas = new ArrayList<Pizza>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
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
  /* Code from template association_GetMany */
  public Pizza getPizza(int index)
  {
    Pizza aPizza = pizzas.get(index);
    return aPizza;
  }

  public List<Pizza> getPizzas()
  {
    List<Pizza> newPizzas = Collections.unmodifiableList(pizzas);
    return newPizzas;
  }

  public int numberOfPizzas()
  {
    int number = pizzas.size();
    return number;
  }

  public boolean hasPizzas()
  {
    boolean has = pizzas.size() > 0;
    return has;
  }

  public int indexOfPizza(Pizza aPizza)
  {
    int index = pizzas.indexOf(aPizza);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPizzas()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPizza(Pizza aPizza)
  {
    boolean wasAdded = false;
    if (pizzas.contains(aPizza)) { return false; }
    pizzas.add(aPizza);
    if (aPizza.indexOfIngredient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPizza.addIngredient(this);
      if (!wasAdded)
      {
        pizzas.remove(aPizza);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePizza(Pizza aPizza)
  {
    boolean wasRemoved = false;
    if (!pizzas.contains(aPizza))
    {
      return wasRemoved;
    }

    int oldIndex = pizzas.indexOf(aPizza);
    pizzas.remove(oldIndex);
    if (aPizza.indexOfIngredient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPizza.removeIngredient(this);
      if (!wasRemoved)
      {
        pizzas.add(oldIndex,aPizza);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPizzaAt(Pizza aPizza, int index)
  {  
    boolean wasAdded = false;
    if(addPizza(aPizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPizzas()) { index = numberOfPizzas() - 1; }
      pizzas.remove(aPizza);
      pizzas.add(index, aPizza);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePizzaAt(Pizza aPizza, int index)
  {
    boolean wasAdded = false;
    if(pizzas.contains(aPizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPizzas()) { index = numberOfPizzas() - 1; }
      pizzas.remove(aPizza);
      pizzas.add(index, aPizza);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPizzaAt(aPizza, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ingredientsByName.remove(getName());
    ArrayList<Pizza> copyOfPizzas = new ArrayList<Pizza>(pizzas);
    pizzas.clear();
    for(Pizza aPizza : copyOfPizzas)
    {
      if (aPizza.numberOfIngredients() <= Pizza.minimumNumberOfIngredients())
      {
        aPizza.delete();
      }
      else
      {
        aPizza.removeIngredient(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]";
  }
}