package server;

import adapter.AutoStockable;
import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.ProxyAutomobile;
import java.util.Iterator;
import model.AutoStock;
import model.Automobile;

public class BuildCarModelOptions extends ProxyAutomobile {

  /**************************************************************************/
  // 		DATA FIELDS
  /**************************************************************************/
  private static final long serialVersionUID = 1L;

  private static final int WAITING = 0;
  private static final int REQUEST_BUILD_AUTO = 1;
  private static final int REQUEST_CONFIGURE_AUTO = 2;
  private static boolean WAITING_FOR_CONFIGURED_AUTO;
  private int state = WAITING;

  boolean DEBUG = true;

  AutoStockable as1;

  /**************************************************************************/
  // 		CONSTRUCTORS
  /**************************************************************************/
  public BuildCarModelOptions() {
    if (as1 == null) as1 = new AutoStock();
  }

  // check for defective autos
  public boolean checkDefectiveAutos() {
    boolean popped = false;
    Iterator<String> it1 = as1.getInventoryLhm().keySet().iterator();
    while (it1.hasNext()) {
      Automobile aa = as1.getInventoryLhm().get(it1.next());
      if (aa.getMake() == null
          || aa.getMake() == ""
          || aa.getModel() == null
          || aa.getModel() == ""
          || aa.getYear() == null
          || aa.getYear() == ""
          || aa.getBasePrice() <= 0) {
        if (DEBUG) System.out.println("--> Removing defective auto " + aa.getKey() + ".\n");
        as1.deleteAutoFromStock(aa.getKey());
        popped = true;
      }
    }
    return popped;
  }

  // check if object is automobile
  public boolean isAutomobile(Object obj) {
    boolean isAutomobile = false;
    try {
      @SuppressWarnings("unused")
      Automobile a1 = (Automobile) obj;
      isAutomobile = true;
    } catch (ClassCastException e) {
      return isAutomobile;
    }
    return isAutomobile;
  }

  /**************************************************************************/
  // 		INSTANCE METHODS
  /**************************************************************************/
  public Object processRequest(Object obj) {
    Object toClient = null;

    if (state == REQUEST_BUILD_AUTO) {
      // code to buildauto
      CreateAuto c1 = new BuildAuto();
      c1.BuildAuto(obj); // build auto prome String, Properties, or file.txt
      boolean removeAuto = checkDefectiveAutos(); // removes defective autos
      String msg = super.getAllModels() + "Removed defective auto from lot.";
      if (!removeAuto)
        msg =
        super.getAllModels()
        + "Automobile object successfully added to database.\n"
        + "Press ENTER to return to main menu.";
      toClient = msg;

    } else if (state == REQUEST_CONFIGURE_AUTO) {
      // code to configureauto
      String key = obj.toString(); // client requests auto by name
      Automobile autoFromStock = new Automobile();
      autoFromStock = as1.getInventoryLhm().get(key); // get requested auto from inventory
      toClient = autoFromStock; // send auto to client
      WAITING_FOR_CONFIGURED_AUTO = true; // enables server to receive modified auto back

    } else if (WAITING_FOR_CONFIGURED_AUTO) {
      Automobile modifiedAuto;
      if (isAutomobile(obj)) { // check if client returns auto object
        modifiedAuto = (Automobile) obj;
        // as1.getInventoryLhm().replace(modifiedAuto.getKey(), modifiedAuto); //below
        // method simpler
        as1.addAutoToStock(modifiedAuto);
        toClient =
            "Configured automobile successfully added to inventory.\n"
                + "Press ENTER to go to main menu.";
        WAITING_FOR_CONFIGURED_AUTO = false; //
      } else {
        toClient = "Unable to load Automobile object.\n";
      }
    }
    as1.printStock(); // debug
    this.state = WAITING;
    return toClient;
  }

  // menu helper method
  public String setRequest(int i) {
    String output = null;
    if (i == 1) {
      this.state = REQUEST_BUILD_AUTO;
      output = "Upload a file to create an Automobile";
    } else if (i == 2) {
      this.state = REQUEST_CONFIGURE_AUTO;
      output =
          super.getAllModels()
          + "Type car name from ALL AUTOS list above,"
          + "\nfor example \"BMW i7 2018\"\n";
    } else {
      output = "Invalid request.\nPress ENTER to return to main menu.";
    }
    return output;
  }
}
