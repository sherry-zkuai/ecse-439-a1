/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;
import java.util.*;

// line 28 "../../../../../PizzaDeliverySystem.ump"
public class Pizza
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int SIZE = 12;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pizza Attributes
  private double price;

  //Pizza Associations
  private List<Ingredient> ingredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pizza(double aPrice, Ingredient... allIngredients)
  {
    price = aPrice;
    ingredients = new ArrayList<Ingredient>();
    boolean didAddIngredients = setIngredients(allIngredients);
    if (!didAddIngredients)
    {
      throw new RuntimeException("Unable to create Pizza, must have at least 1 ingredients");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public double getPrice()
  {
    return price;
  }
  /* Code from template association_GetMany */
  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfIngredientsValid()
  {
    boolean isValid = numberOfIngredients() >= minimumNumberOfIngredients();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfIngredients()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    ingredients.add(aIngredient);
    if (aIngredient.indexOfPizza(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aIngredient.addPizza(this);
      if (!wasAdded)
      {
        ingredients.remove(aIngredient);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    if (!ingredients.contains(aIngredient))
    {
      return wasRemoved;
    }

    if (numberOfIngredients() <= minimumNumberOfIngredients())
    {
      return wasRemoved;
    }

    int oldIndex = ingredients.indexOf(aIngredient);
    ingredients.remove(oldIndex);
    if (aIngredient.indexOfPizza(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aIngredient.removePizza(this);
      if (!wasRemoved)
      {
        ingredients.add(oldIndex,aIngredient);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setIngredients(Ingredient... newIngredients)
  {
    boolean wasSet = false;
    ArrayList<Ingredient> verifiedIngredients = new ArrayList<Ingredient>();
    for (Ingredient aIngredient : newIngredients)
    {
      if (verifiedIngredients.contains(aIngredient))
      {
        continue;
      }
      verifiedIngredients.add(aIngredient);
    }

    if (verifiedIngredients.size() != newIngredients.length || verifiedIngredients.size() < minimumNumberOfIngredients())
    {
      return wasSet;
    }

    ArrayList<Ingredient> oldIngredients = new ArrayList<Ingredient>(ingredients);
    ingredients.clear();
    for (Ingredient aNewIngredient : verifiedIngredients)
    {
      ingredients.add(aNewIngredient);
      if (oldIngredients.contains(aNewIngredient))
      {
        oldIngredients.remove(aNewIngredient);
      }
      else
      {
        aNewIngredient.addPizza(this);
      }
    }

    for (Ingredient anOldIngredient : oldIngredients)
    {
      anOldIngredient.removePizza(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Ingredient> copyOfIngredients = new ArrayList<Ingredient>(ingredients);
    ingredients.clear();
    for(Ingredient aIngredient : copyOfIngredients)
    {
      aIngredient.removePizza(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]";
  }
}