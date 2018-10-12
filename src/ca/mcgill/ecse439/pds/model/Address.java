/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse439.pds.model;

// line 20 "../../../../../PizzaDeliverySystem.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String firstLine;
  private String secondLine;
  private String city;
  private String postalCode;

  //Address Associations
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aFirstLine, String aSecondLine, String aCity, String aPostalCode, Order aOrder)
  {
    firstLine = aFirstLine;
    secondLine = aSecondLine;
    city = aCity;
    postalCode = aPostalCode;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create address due to order");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFirstLine(String aFirstLine)
  {
    boolean wasSet = false;
    firstLine = aFirstLine;
    wasSet = true;
    return wasSet;
  }

  public boolean setSecondLine(String aSecondLine)
  {
    boolean wasSet = false;
    secondLine = aSecondLine;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public String getFirstLine()
  {
    return firstLine;
  }

  public String getSecondLine()
  {
    return secondLine;
  }

  public String getCity()
  {
    return city;
  }

  public String getPostalCode()
  {
    return postalCode;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setOrder(Order aNewOrder)
  {
    boolean wasSet = false;
    if (aNewOrder == null)
    {
      //Unable to setOrder to null, as address must always be associated to a order
      return wasSet;
    }
    
    Address existingAddress = aNewOrder.getAddress();
    if (existingAddress != null && !equals(existingAddress))
    {
      //Unable to setOrder, the current order already has a address, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Order anOldOrder = order;
    order = aNewOrder;
    order.setAddress(this);

    if (anOldOrder != null)
    {
      anOldOrder.setAddress(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order existingOrder = order;
    order = null;
    if (existingOrder != null)
    {
      existingOrder.setAddress(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "firstLine" + ":" + getFirstLine()+ "," +
            "secondLine" + ":" + getSecondLine()+ "," +
            "city" + ":" + getCity()+ "," +
            "postalCode" + ":" + getPostalCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}