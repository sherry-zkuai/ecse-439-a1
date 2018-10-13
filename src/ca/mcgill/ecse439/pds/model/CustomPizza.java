/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;
import java.util.*;

// line 41 "../../../../../PizzaDeliverySystem.ump"
public class CustomPizza extends Pizza
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final double BASE_PRICE = 3.0;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomPizza(double aPrice, PizzaDeliveryManager aPizzaDeliveryManager, Ingredient... allIngredients)
  {
    super(aPrice, aPizzaDeliveryManager, allIngredients);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]";
  }
}