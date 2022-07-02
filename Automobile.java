package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable {

  private static final long serialVersionUID = 1L;

  /**************************************************************************/
  // data fields
  /**************************************************************************/

  // private String name; //no need for this anymore
  private String make = "", model = "", year = "";

  private float baseprice = 0, totalprice = 0;

  // list of optionSets containing options
  private ArrayList<OptionSet> optionsets; // = new ArrayList<>();
  // customer choice
  private ArrayList<Option> choices; // = new ArrayList<>();

  boolean DEBUG = true;

  /**************************************************************************/
  ////////////////////////////// constructors //////////////////////////////
  /**************************************************************************/

  /** Constructor for Automobile */
  public Automobile() {
    super();
    setOptionSets();
    setChoices();
  }

  /**
   * Constructor for Automobile
   *
   * @param make
   * @param model
   * @param year
   * @param choices
   * @param baseprice
   */
  public Automobile(String model, String make, String year, float baseprice) {
    super();
    this.make = make;
    this.model = model;
    this.year = year;
    this.baseprice = baseprice;
    this.totalprice = baseprice;
    setOptionSets();
    setChoices();
  }

  /**************************************************************************/
  // getters/setters for Automobile
  /**************************************************************************/

  // method1B => for adding an option to OptionSet
  public void addOption(int index, String optionName) { // pverloaded A
    OptionSet os = getOptionSet(index);
    os.addOption(
        optionName,
        0,
        os.getOptionSetName()); // array list method //0 here only temporary until changed
  }

  // method1C => for adding an option to OptionSet
  public void addOption(int index, String optionName, String parentName) { // overloaded B
    OptionSet os = getOptionSet(index);
    os.addOption(
        optionName, 0, parentName); // array list method //0 here only temporary until changed
  }

  // method1D => for adding an option to OptionSet
  public void addOption(
      String optionSetName, String optionName, String parentName) { // overloaded C
    OptionSet os = getOptionSet(optionSetName);
    os.addOption(
        optionName, 0, optionSetName); // array list method //0 here only temporary until changed
    // here optionSetName is the parent name, so technically parentName isn't needed
  }

  // method1E => for adding an option to OptionSet (incldes price)
  public void addOption(
      String optionSetName, String optionName, String parentName, String price) { // overloaded D
    OptionSet os = getOptionSet(optionSetName);
    os.addOption(
        optionName, 0, optionSetName); // array list method //0 here only temporary until changed
    // here optionSetName is the parent name, so technically parentName isn't needed
    Option o = getOptionSet(optionSetName).getOption(optionName);
    o.setPrice(Float.parseFloat(price));
  }

  // method1E => adds an option to choices & set choice option in OptionSet (removes old choice if
  // it's from same category)
  public void addOptionChoice(String optionSetName, String optionName) {
    OptionSet os = getOptionSet(optionSetName);
    Option o = getOption(optionSetName, optionName);
    os.setChoice(o); // set choice Option in OptionSet object
    for (int i = 0; i < choices.size(); i++) // for each loop
      if (o.getParentName()
          == choices
          .get(i)
          .getParentName()) { // remove old choice if parent is the same as new choice
        if (DEBUG) {
          System.out.println(
              "--> Removing: "
                  + choices.get(i).getParentName()
                  + " >> "
                  + choices.get(i).getName());
          System.out.println("    Adding: " + o.getParentName() + " >> " + o.getName());
        }
        choices.remove(i);
      }
    choices.add(o);
    calculateTotalPrice(); // updates total price automatically
  }

  /**************************************************************************/
  // methods
  /**************************************************************************/

  // method1A => for adding an optionSet to auto
  public void addOptionSet(String optionSetName) {
    optionsets.add(new OptionSet(optionSetName)); // array list method
  }

  // method 2A => calculate totalprice
  public float calculateTotalPrice() {
    totalprice = baseprice;
    // for (int i = 0; i < choices.size(); i++) {
    // choices.get(i).printOptionSet();
    for (Option o : choices) { // for each loop, same effect as above
      if (o != null) totalprice += o.getPrice();
    }
    return totalprice;
  }

  // return auto heading as string
  public String getAutoHeading() {
    String heading = getKey() + ", $" + baseprice + "\n"; // key = make,model,year
    return heading;
  }

  /**
   * Getter for Automobile
   *
   * @return baseprice
   */
  public float getBasePrice() {
    return baseprice;
  }

  /**
   * Getter for Automobile
   *
   * @return choice
   */
  public ArrayList<Option> getChoice() {
    return choices;
  }

  // create a key for LinkedHashMap
  public String getKey() {
    String key = make + " " + model + " " + year;
    return key;
  }

  /**
   * Getter for Automobile
   *
   * @return make
   */
  public String getMake() {
    return make;
  }

  /**
   * Getter for Automobile
   *
   * @return model
   */
  public String getModel() {
    return model;
  }

  /** Get Option by index */
  public Option getOption(int optionsetIndex, int optionIndex) { // overloaded A
    Option temp = getOptionSet(optionsetIndex).getOption(optionIndex); // array list method
    // System.out.println(" optionsets.get(optionsetIndex).getOptionByIndex(optionIndex)=" + temp);
    // //debug
    return temp;
  }

  /** Get Option by optionSetName, optionName */
  public Option getOption(String optionSetName, String optionName) { // overloaded B
    OptionSet temp = getOptionSet(optionSetName);
    if (optionName != null) return temp.getOption(optionName); // debug
    return null;
  }

  /** Get choice for a given optionSet index */
  public Option getOptionChoice(int index) { // overloaded B
    Option temp = getOptionSet(index).getChoice();
    return temp;
  }
  /** Get choice for a given optionSet name */
  public Option getOptionChoice(String optionSetName) { // overloaded A
    Option temp = getOptionSet(optionSetName).getChoice();
    if (DEBUG && temp != null)
      System.out.println("OptionSetName=" + optionSetName + " ---> choice=" + temp.getName());
    return temp;
  }

  /** Get choice name for a given optionSet index */
  public String getOptionChoiceName(int index) { // overloaded B
    Option temp = getOptionSet(index).getChoice();
    return temp.getName();
  }

  /**************************************************************************/
  // additional getters/setters
  /**************************************************************************/

  /** Get choice name for a given optionSet name */
  public String getOptionChoiceName(String optionSetName) { // overloaded A
    Option temp = getOptionSet(optionSetName).getChoice();
    return temp.getName();
  }
  /** Get choice price for a given optionSet index */
  public float getOptionChoicePrice(int index) { // overloaded B
    Option temp = getOptionSet(index).getChoice();
    return temp.getPrice();
  }

  /** Get choice price for a given optionSet name */
  public float getOptionChoicePrice(String optionSetName) { // overloaded A
    Option temp = getOptionSet(optionSetName).getChoice();
    return temp.getPrice();
  }
  /** Get OptionSet by index */
  public OptionSet getOptionSet(int optionSetIndex) { // overloaded A
    OptionSet temp = optionsets.get(optionSetIndex);
    return temp;
  }

  /** Get OptionSet by name */
  public OptionSet getOptionSet(String optionSetName) { // overloaded B
    if (optionSetName != null)
      for (OptionSet os : optionsets)
        if (os.getOptionSetName().equals(optionSetName)) return os; // debug
    return null;
  }
  /** Get OptionSet name by name */
  public String getOptionSetName(String optionSetName) { // overloaded B
    if (optionSetName != null)
      for (OptionSet os : optionsets)
        if (os.getOptionSetName().equals(optionSetName)) return os.getOptionSetName(); // debug
    return null;
  }

  /**
   * Getter for Automobile
   *
   * @return optionSets
   */
  public ArrayList<OptionSet> getOptionSets() {
    return optionsets;
  }

  /**************************************************************************/
  // getters/setters for OptionSet
  /**************************************************************************/

  // modified
  /**
   * Getter for Automobile
   *
   * @return totalPrice
   */
  public float getTotalPrice() {
    return totalprice;
  }

  /**
   * Getter for Automobile
   *
   * @return year
   */
  public String getYear() {
    return year;
  }

  // method 3A
  public void printAutoHeading() { // overloaded A
    System.out.println(
        "\n-------------------------- " + getKey() + " -----------------------------");
    System.out.println(
        "make="
            + make
            + ", model="
            + model
            + ", year="
            + year
            + ", \nbaseprice="
            + baseprice
            + ", totalprice="
            + getTotalPrice());
  }

  // method 3A
  public void printAutoHeading(String msg) { // overloaded B
    System.out.println("\n------------------------ " + msg + " -------------------------");
    System.out.println(
        "make="
            + make
            + ", model="
            + model
            + ", year="
            + year
            + ", \nbaseprice="
            + baseprice
            + ", totalprice="
            + getTotalPrice());
  }
  ;

  // method 3 print auto data fields and all optionsets with their options
  public void printAutomobile() { // overloaded A
    printAutoHeading();
    // for (int i = 0; i < optionsets.size(); i++) {
    // optionsets.get(i).printOptionSet();
    for (OptionSet os : optionsets) { // for each loop, same effect as above
      if (os != null) os.printOptionSet();
    }
    printChoices();
    System.out.println(
        "------------------------------------------------------------------------------------\n");
  }
  ;

  /**************************************************************************/
  // getters/setters for Option
  /**************************************************************************/

  // method 3 print auto data fields and all optionsets with their options
  public void printAutomobile(String msg) { // overloaded B
    printAutoHeading(msg);
    // for (int i = 0; i < optionsets.size(); i++) {
    // optionsets.get(i).printOptionSet();
    for (OptionSet os : optionsets) { // for each loop, same effect as above
      if (os != null) os.printOptionSet();
    }
    printChoices();
    System.out.println(
        "------------------------------------------------------------------------------------\n");
  }

  // method 4 prints all options in choices
  public void printChoices() {
    boolean noChoices = true;
    calculateTotalPrice(); // calculate total price before printing choices
    System.out.println("\n ---> Customer added the following CHOICES:");
    for (Option o : choices) { // for each loop, same effect as above
      if (o != null) o.printOption();
      noChoices = false;
    }
    if (noChoices)
      System.out.println(
          " ---> (Nothing chosen for " + getMake() + " " + getModel() + " " + getYear() + ")");
  }

  /** Setter for Automobile Set baseprice to @param baseprice //overloaded A */
  public void setBaseprice(float baseprice) {
    this.baseprice = baseprice;
  }

  /** Setter for Automobile Set baseprice to @param baseprice //overloaded B */
  public void setBaseprice(String baseprice) {
    this.baseprice = Float.parseFloat(baseprice);
  }

  /** Setter for Automobile Set choice to @param choice */
  public void setChoices() {
    if (this.choices == null) this.choices = new ArrayList<>();
  }

  /** Setter for Automobile Set choice to @param choice */
  public void setChoices(ArrayList<Option> choice) {
    calculateTotalPrice();
    this.choices = choice;
  }

  /** Setter for Automobile Set make to @param make */
  public void setMake(String make) {
    this.make = make;
  }

  /**
   * Setter for Automobile
   *
   * @param make
   * @param model
   * @param year
   * @param price
   */
  public void setMakeModelYearPrice(String make, String model, String year, String baseprice) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.baseprice = Float.parseFloat(baseprice);
  }

  /** Setter for Automobile Set model to @param model */
  public void setModel(String model) {
    this.model = model;
  }
  /**
   * Set all Option vars by name setOption( optionSet name/index, option name/index, newname or
   * newprice or both )
   */
  public void setOption(int optionSetIndex, int optionIndex, float newPrice) { // overloaded D
    Option temp = getOptionSet(optionSetIndex).getOption(optionIndex);
    temp.setPrice(newPrice);
  }
  /**
   * Set all Option vars by name setOption( optionSet name/index, option name/index, newname or
   * newprice or both )
   */
  public void setOption(int optionSetIndex, int optionIndex, String newoptionName) { // overloaded C
    Option temp = getOptionSet(optionSetIndex).getOption(optionIndex);
    temp.setName(newoptionName);
  }
  /**
   * Set all Option vars by index setOption(optionSet name/index, option name/index, newname or
   * newprice or both)
   */
  public void setOption(
      int optionSetIndex, int optionIndex, String newoptionName, float newPrice) { // overloaded A
    Option temp = getOptionSet(optionSetIndex).getOption(optionIndex);
    temp.setPrice(newPrice);
    temp.setName(newoptionName);
  }
  /**
   * Set all Option vars by name setOption( optionSet name/index, option name/index, newname or
   * newprice or both )
   */
  public void setOption(String optionSetName, String optionName, float newPrice) { // overloaded E
    Option temp = getOptionSet(optionSetName).getOption(optionName);
    if (DEBUG) System.out.println("OPTIONS UPDATED:");
    temp.setPrice(newPrice);
    if (DEBUG)
      System.out.println(
          "  new_name="
              + temp.getName()
              + ", new_price="
              + temp.getPrice()
              + ", parent_name="
              + temp.getParentName()
              + "\n"); // debug
  }

  /**
   * Set all Option vars by name setOption( optionSet name/index, option name/index, newname or
   * newprice or both )
   */
  public void setOption(String optionSetName, String optionName, String price) { // overloaded F
    Option temp = getOptionSet(optionSetName).getOption(optionName);
    temp.setPrice(Float.parseFloat(price));
  }

  /**
   * Set all Option vars by name setOption( optionSet name/index, option name/index, newname or
   * newprice or both )
   */
  public void setOption(
      String optionSetName,
      String optionName,
      String newoptionName,
      float newPrice) { // overloaded B
    Option temp = getOptionSet(optionSetName).getOption(optionName);
    temp.setPrice(newPrice);
    temp.setName(newoptionName);
  }
  /** Set OptionSet name by index setOptionSet( optionSet name/index, newname ) */
  public void setOptionSet(int optionSetIndex, String optionSetName) { // overloaded A
    OptionSet temp = getOptionSet(optionSetIndex);
    // if (temp != null) //debug
    temp.setOptionSetName(optionSetName);
  }

  /** Set OptionSet name by name setOptionSet( optionSet name/index, newname ) */
  public void setOptionSet(String optionSetName, String newoptionSetName) { // overloaded B
    OptionSet temp = getOptionSet(optionSetName);
    // if (temp != null) //debug
    temp.setOptionSetName(newoptionSetName);
  }
  /** Setter for Automobile Set optionSets to @param optionSets */
  public void setOptionSets() {
    this.optionsets = new ArrayList<>();
  }

  /** Setter for Automobile Set optionSets to @param optionSets */
  public void setOptionSets(ArrayList<OptionSet> optionSets) {
    this.optionsets = optionSets;
  }

  /** Setter for Automobile Set totalPrice to @param totalPrice */
  public void setTotalPrice(float totalPrice) {
    this.totalprice = totalPrice;
  }

  /** Setter for Automobile Set year to @param year */
  public void setYear(String year) {
    this.year = year;
  }
}
