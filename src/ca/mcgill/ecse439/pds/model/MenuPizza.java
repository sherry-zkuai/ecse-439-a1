/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;
import java.util.*;

// line 26 "../../../../../PizzaDeliverySystem.ump"
public class MenuPizza extends Pizza
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, MenuPizza> menupizzasByName = new HashMap<String, MenuPizza>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuPizza Attributes
  private String name;
  private int calorieCount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuPizza(double aPrice, PizzaDeliveryManager aPizzaDeliveryManager, String aName, int aCalorieCount, Ingredient... allIngredients)
  {
    super(aPrice, aPizzaDeliveryManager, allIngredients);
    // line 34 "../../../../../PizzaDeliverySystem.ump"
    if(aName==null || aName.length()==0)
    		{
    			throw new RuntimeException("Pizza name cannot be empty");
    		}
    // END OF UMPLE BEFORE INJECTION
    calorieCount = aCalorieCount;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 34 "../../../../../PizzaDeliverySystem.ump"
    if(aName==null || aName.length()==0)
    		{
    			throw new RuntimeException("Pizza name cannot be empty");
    		}
    // END OF UMPLE BEFORE INJECTION
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      menupizzasByName.remove(anOldName);
    }
    menupizzasByName.put(aName, this);
    return wasSet;
  }

  public boolean setCalorieCount(int aCalorieCount)
  {
    boolean wasSet = false;
    calorieCount = aCalorieCount;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static MenuPizza getWithName(String aName)
  {
    return menupizzasByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getCalorieCount()
  {
    return calorieCount;
  }

  public void delete()
  {
    menupizzasByName.remove(getName());
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "calorieCount" + ":" + getCalorieCount()+ "]";
  }
}