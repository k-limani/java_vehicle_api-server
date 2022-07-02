package adapter;

import exception.AutoException;
import java.util.Iterator;
import java.util.Properties;
import model.AutoStock;
import model.Automobile;
import util.FileIO;

// acting as a delegate - code from model and util package will be integrated here.
public abstract class ProxyAutomobile extends AutoException {

  private static final long serialVersionUID = 1L;

  /**************************************************************************/
  // DATA FIELDS
  /**************************************************************************/
  private static Automobile auto;

  private static AutoStock autostock;

  // method implemented in AutoServer interface
  public void addAutoToStock(Automobile a) {
    autostock.addAutoToStock(a);
  }

  /**************************************************************************/
  // CONSTRUCTORS
  /**************************************************************************/

  // default constructor
  public void BuildAuto() {
    setAutoStock();
  }
  // overloaded constructor A
  public void BuildAuto(Object fname) {
    try { // try/catch block #1
      checkFilename(fname);
    } catch (AutoException ae) {
      ae.fix(0, fname.toString());
    }
  }

  // overloaded constructor B
  public void BuildAuto(String fname) {
    try { // try/catch block #1
      checkFilename(fname);
    } catch (AutoException ae) {
      ae.fix(0, fname.toString());
    }
  }
  // method for checking if filename is null
  public void checkFilename(Object filename) throws AutoException {
    // String filename = null;
    Properties prop = null;
    boolean isProp;

    try {
      prop = new Properties();
      prop = (Properties) filename;
      isProp = true;
    } catch (ClassCastException e) {
      System.err.println("Exception: " + e);
      isProp = false;
    }

    if (filename != null && !filename.toString().equals("")) {
      // if (filename.toString().matches("(?i).*prop.*")) {
      if (isProp) {
        FileIO f1b = new FileIO();
        auto = f1b.readPropertiesFile(prop);
        setAutoStock();
        autostock.addAutoToStock(auto);
      } else if (filename.toString().contains("(?i).*prop.*")) { // ?i case insensitive
        FileIO f1b = new FileIO(filename.toString());
        auto = f1b.readPropertiesFile();
        setAutoStock();
        autostock.addAutoToStock(auto);
      } else if (filename.toString().contains("(?i).*txt.*")) {
        FileIO f1a = new FileIO(filename.toString());
        auto = f1a.readFile();
        setAutoStock();
        autostock.addAutoToStock(auto);
      } else {
        FileIO f1a = new FileIO();
        auto = f1a.readString(filename.toString());
        setAutoStock();
        autostock.addAutoToStock(auto);
      }
    } else {
      throw new AutoException(0, "Missing file!");
    }
  }
  // new... in BuildCarModelOptions
  public String getAllModels() {
    StringBuffer sbuff = new StringBuffer();
    String autostats = "\n------------------ ALL AUTOS ------------------\n";
    sbuff.append(autostats);
    Iterator<String> it1 = autostock.getInventoryLhm().keySet().iterator();
    while (it1.hasNext()) {
      Automobile aa = autostock.getInventoryLhm().get(it1.next());
      sbuff.append(aa.getAutoHeading());
    }
    if (autostock.getInventoryLhm().isEmpty()) sbuff.append("Lot empty\n");
    sbuff.append("-----------------------------------------------\n");
    return sbuff.toString();
  }

  // getter for auto object
  public Automobile getAuto(String key) {
    Automobile a = autostock.getInventoryLhm().get(key);
    return a;
  }
  /*
   * (filename.toString().contains(".prop")) {
   * FileIO f1b = new FileIO(filename.toString());
   * auto = f1b.readPropertiesFile();
   * setAutoStock();
   * autostock.addAutoToStock(auto);
   */
  /**
   * Getter for AutoInventory ai1
   *
   * @return AutoInventory ai1
   */
  public AutoStock getStock() {
    return autostock;
  }
  // method implemented in EditAuto interface
  public void printAuto() {
    auto.printAutomobile();
  }

  // prints auto
  public void printAuto(String key) {
    Automobile a = autostock.getInventoryLhm().get(key);
    a.printAutomobile();
  }

  /** Setter for AutoInventory ai1 */
  protected void setAutoStock() {
    if (autostock == null) autostock = new AutoStock();
  }

  public void setOptionChoice(String optionSetName, String optionName) { // overloaded C
    auto.addOptionChoice(optionSetName, optionName);
  }

  public void setOptionChoice(String key, String optionSetName, String optionName) { // overloaded C
    Automobile a = autostock.getInventoryLhm().get(key);
    a.addOptionChoice(optionSetName, optionName);
  }

  public void updateOptionPrice(
      String OptSetname, String optionName, String newPrice) { // overloaded B
    auto.setOption(OptSetname, optionName, newPrice);
  }

  public void updateOptionPrice(
      String key, String OptSetname, String optionName, String newPrice) { // overloaded B
    Automobile a = autostock.getInventoryLhm().get(key);
    a.setOption(OptSetname, optionName, newPrice);
  }

  /**************************************************************************/
  // METHODS for altering auto last static Autobmobile object auto created
  /**************************************************************************/
  public void updateOptionSetName(String OptionSetname, String newName) { // overloaded A
    auto.setOptionSet(OptionSetname, newName);
  }

  /**************************************************************************/
  // METHODS for altering chosen Autobmobile object from l1 LinkedHashMap
  /**************************************************************************/
  public void updateOptionSetName(
      String key, String OptionSetname, String newName) { // overloaded A
    Automobile a = autostock.getVehicle(key);
    a.setOptionSet(OptionSetname, newName);
  }
}
