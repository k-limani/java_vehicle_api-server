package adapter;

import model.AutoStock;

public interface CreateAuto {

  // these methods in ProxyAutomobile

  public abstract void BuildAuto(Object obj); // creates a new entry into the LinkedHashMap
  // Given​ a text​ file​ name​ a method​ called​ BuildAuto​ can​ ​be​ written​ ​to​ ​build​ an​
  // instance​ ​of Automobile.​
  // This​ method​ ​does​ ​ not​ ​have​ ​to​ return​​ the​ Auto​ instance.
  public abstract void BuildAuto(String filename); // creates a new entry into the LinkedHashMap
  // return inventory linked hash map
  public abstract AutoStock getStock();

  // prints auto stats and options
  public abstract void printAuto(String modelname);
}
