/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;
import java.util.*;

// line 3 "../../../../../PizzaDeliverySystem.ump"
public class Order
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { Pending, Cooking, Delivering, Done }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private String customerName;
  private Status status;

  //Order Associations
  private Address address;
  private List<Pizza> pizzas;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(String aCustomerName, Pizza... allPizzas)
  {
    customerName = aCustomerName;
    status = Status.Pending;
    pizzas = new ArrayList<Pizza>();
    boolean didAddPizzas = setPizzas(allPizzas);
    if (!didAddPizzas)
    {
      throw new RuntimeException("Unable to create Order, must have at least 1 pizzas");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCustomerName(String aCustomerName)
  {
    boolean wasSet = false;
    customerName = aCustomerName;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public Status getStatus()
  {
    return status;
  }
  /* Code from template association_GetOne */
  public Address getAddress()
  {
    return address;
  }

  public boolean hasAddress()
  {
    boolean has = address != null;
    return has;
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAddress(Address aNewAddress)
  {
    boolean wasSet = false;
    if (address != null && !address.equals(aNewAddress) && equals(address.getOrder()))
    {
      //Unable to setAddress, as existing address would become an orphan
      return wasSet;
    }

    address = aNewAddress;
    Order anOldOrder = aNewAddress != null ? aNewAddress.getOrder() : null;

    if (!this.equals(anOldOrder))
    {
      if (anOldOrder != null)
      {
        anOldOrder.address = null;
      }
      if (address != null)
      {
        address.setOrder(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPizzas()
  {
    return 1;
  }
  /* Code from template association_AddUnidirectionalMStar */
  public boolean addPizza(Pizza aPizza)
  {
    boolean wasAdded = false;
    if (pizzas.contains(aPizza)) { return false; }
    pizzas.add(aPizza);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePizza(Pizza aPizza)
  {
    boolean wasRemoved = false;
    if (!pizzas.contains(aPizza))
    {
      return wasRemoved;
    }

    if (numberOfPizzas() <= minimumNumberOfPizzas())
    {
      return wasRemoved;
    }

    pizzas.remove(aPizza);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMStar */
  public boolean setPizzas(Pizza... newPizzas)
  {
    boolean wasSet = false;
    ArrayList<Pizza> verifiedPizzas = new ArrayList<Pizza>();
    for (Pizza aPizza : newPizzas)
    {
      if (verifiedPizzas.contains(aPizza))
      {
        continue;
      }
      verifiedPizzas.add(aPizza);
    }

    if (verifiedPizzas.size() != newPizzas.length || verifiedPizzas.size() < minimumNumberOfPizzas())
    {
      return wasSet;
    }

    pizzas.clear();
    pizzas.addAll(verifiedPizzas);
    wasSet = true;
    return wasSet;
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
    Address existingAddress = address;
    address = null;
    if (existingAddress != null)
    {
      existingAddress.delete();
      existingAddress.setOrder(null);
    }
    pizzas.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "customerName" + ":" + getCustomerName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "address = "+(getAddress()!=null?Integer.toHexString(System.identityHashCode(getAddress())):"null");
  }
}