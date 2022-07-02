package adapter;

import java.util.LinkedHashMap;
import model.Automobile;

public interface AutoStockable {
  // these methods in AutoStock class

  public abstract void addAutoToStock(Automobile a); // adds auto to stock

  void deleteAutoFromStock(String key); // remove auto from stock

  public abstract void deleteEntireStock(); // remove all autos from stock

  public abstract LinkedHashMap<String, Automobile>
  getInventoryLhm(); // return static inventoryLhm from AutoStock

  public abstract void printStock(); // print the whole inventory with their options

  public abstract void printStockNames(); // print the whole inventory only names

  public abstract void updateAutoFromStock(
      String key, Automobile replacement); // replace auto with key with new auto
}
