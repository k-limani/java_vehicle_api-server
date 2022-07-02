package model;

import adapter.AutoStockable;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class AutoStock implements AutoStockable { // , AutoServer {

  /**************************************************************************/
  // DATA FIELDS
  /**************************************************************************/
  public static LinkedHashMap<String, Automobile> inventoryLhm;

  boolean DEBUG = true;

  /**************************************************************************/
  // CONSTRUCTORS
  /**************************************************************************/
  /** Constructor for AutoInventory */
  public AutoStock() {
    super();
    setInventoryLhm();
  }

  /**************************************************************************/
  // METHODS
  /**************************************************************************/

  @Override
  public void addAutoToStock(Automobile a) { // add an auto to stock
    String key = a.getKey();
    inventoryLhm.put(key, a);
    if (DEBUG) System.out.println("Auto " + a.getKey() + " added to inventnoryLhm");
  }

  @Override
  public void deleteAutoFromStock(String key) { // delete an auto from stock
    inventoryLhm.remove(key);
  }

  @Override
  public void deleteEntireStock() { // delete entire stock
    inventoryLhm.clear();
  }

  /**************************************************************************/
  // GETTERS / SETTERS
  /**************************************************************************/
  /** Getter for @param setInventoryLhm LinkedHashMap<String, Automobile> */
  @Override
  public LinkedHashMap<String, Automobile> getInventoryLhm() {
    return inventoryLhm;
  }

  public Automobile getVehicle(String key) {
    return inventoryLhm.get(key);
  }
  // print all autos and their options
  @Override
  public void printStock() {
    Iterator<String> it1 = inventoryLhm.keySet().iterator(); // iterator loop through key set
    while (it1.hasNext()) {
      // it1.next() Returns the next element from this iterator; place next() somewhere to increment
      // the iterator
      Automobile aa = inventoryLhm.get(it1.next());
      aa.printAutomobile();
    }
  }

  // print all autos and their options
  @Override
  public void printStockNames() {
    Iterator<String> it1 = inventoryLhm.keySet().iterator(); // iterator loop through key set
    while (it1.hasNext()) {
      // it1.next() Returns the next element from this iterator; place next() somewhere to increment
      // the iterator
      Automobile aa = inventoryLhm.get(it1.next());
      aa.printAutoHeading();
    }
  }

  /** Setter for @param inventoryLhm LinkedHashMap<String, Automobile> */
  public void setInventoryLhm() {
    if (inventoryLhm == null) // if the static object has already been initialized skip this
      inventoryLhm = new LinkedHashMap<String, Automobile>();
  }

  @Override
  public void updateAutoFromStock(
      String originalKey, Automobile replacement) { // replace an auto with another
    inventoryLhm.replace(originalKey, replacement);
  }
}
