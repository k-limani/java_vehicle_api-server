package model;

import java.io.Serializable;

public class Option implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Getter for Option
   *
   * @return serialversionuid
   */
  protected static long getSerialversionuid() {
    return serialVersionUID;
  }
  /**************************************************************************/
  //			data fields
  /**************************************************************************/

  String name = "";

  float price = 0;

  /**************************************************************************/
  ////////////////////////////// constructors //////////////////////////////
  /**************************************************************************/

  //	/** Constructor for Option
  //	 *
  //	 */
  //	protected Option() {
  //		super();
  //	}

  //	/** Constructor for Option
  //	 * @param name
  //	 * @param price
  //	 */
  //	protected Option(String name, float price) {
  //		super();
  //		this.name = name;
  //		this.price = price;
  //	}

  // improvisation -> two choices from the same parent category can't be added to choices arrlist in
  // auto
  String parentName = "";

  /**
   * Constructor for Option
   *
   * @param name
   * @param price
   */
  protected Option(String name, float price, String parentName) {
    super();
    this.name = name;
    this.price = price;
    this.parentName = parentName;
  }

  /**
   * Getter for Option
   *
   * @return name
   */
  protected String getName() {
    return name;
  }

  /**
   * Getter for Option
   *
   * @return parentCategory
   */
  protected String getParentName() {
    return parentName;
  }

  /**
   * Getter for Option
   *
   * @return price
   */
  protected float getPrice() {
    return price;
  }

  /**************************************************************************/
  //			methods
  /**************************************************************************/

  // method for printing both name and price
  protected void printOption() {
    System.out.println("   name=" + name + ", price=" + price + ", parentName=" + parentName);
  }

  /** Setter for Option Set name to @param name */
  protected void setName(String name) {
    this.name = name;
  }

  /** Setter for Option Set parentCategory to @param parentCategory */
  protected void setParentName(String parentOptionSet) {
    this.parentName = parentOptionSet;
  }

  /** Setter for Option Set price to @param price */
  protected void setPrice(float price) {
    this.price = price;
  }
}
