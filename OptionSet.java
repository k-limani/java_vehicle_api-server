package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {

  /**************************************************************************/
  //		data fields
  /**************************************************************************/

  private static final long serialVersionUID = 1L;

  private String name;
  // list of options
  private ArrayList<Option> options;
  // customer choice
  private Option choice;

  /**************************************************************************/
  ////////////////////////////// constructors //////////////////////////////
  /**************************************************************************/

  /** Constructor for OptionSet */
  protected OptionSet() {
    super();
    options = new ArrayList<>();
  }

  /**
   * "Copy Constructor" for OptionSet
   *
   * @param OptionSet OBJECT
   */
  @SuppressWarnings("unchecked")
  public OptionSet(OptionSet optionSetObject) {
    this.options = (ArrayList<Option>) optionSetObject.options.clone();
    this.name = optionSetObject.name;
  }

  /**
   * Constructor for OptionSet
   *
   * @param optionSetName
   */
  protected OptionSet(String optionSetName) {
    super();
    this.name = optionSetName;
    // you can probably initialize this in the data field decrlarations
    this.options = new ArrayList<>();
  }

  /**************************************************************************/
  //			getters/setters
  /**************************************************************************/

  /**************************************************************************/
  //			methods
  /**************************************************************************/

  // method1A => for adding an option to options
  protected void addOption(String optionName, float optionPrice) { // overloaded A
    options.add(new Option(optionName, optionPrice, this.name)); // array list method
  }
  // method1B => for adding an option to options
  protected void addOption(
      String optionName, float optionPrice, String parentName) { // pverloaded B
    options.add(new Option(optionName, optionPrice, this.name)); // array list method
  }

  /**
   * Getter for OptionSet
   *
   * @return choice
   */
  protected Option getChoice() {
    if (choice != null) return choice;
    return null;
  }
  /** Get Option by index getOption( option name/index ) */
  protected Option getOption(int index) { // overloaded A
    //		System.out.println(" optionSet.get(index).getName()=" + optionSet.get(index).getName());
    // //debug
    return options.get(index); // array list method
  }

  /** Get Option by optionName getOption( option name/index ) */
  protected Option getOption(String optionName) { // overloaded B
    if (optionName != null)
      for (Option o : options) {
        if (optionName.equals(o.getName())) {
          //					o.printOption(); //debug
          return o;
        }
      }
    return null;
  }
  /**
   * Getter for OptionSet
   *
   * @return optionSetName
   */
  protected String getOptionSetName() {
    return name;
  }

  /**************************************************************************/
  //			additional getters/setters
  /**************************************************************************/

  /**
   * Getter for OptionSet
   *
   * @return optionSets
   */
  protected ArrayList<Option> getOptionSets() {
    return options;
  }

  /**************************************************************************/
  //			getters/setters for options
  /**************************************************************************/

  // method2 => for printing OptionSet + all Options in it
  protected void printOptionSet() {
    System.out.println("\n optionSetName=" + name);
    //		for (int i = 0; i < options.size(); i++) {
    //			options.get(i).printOption();
    for (Option o : options) { // for each loop, same effect as above
      if (o != null) o.printOption();
    }
  }

  /** Setter for OptionSet Set choice to @param choice */
  protected void setChoice(Option choice) {
    this.choice = choice;
  }

  /** Set choice equal to an existing choice */
  protected void setChoice(String choiceName) {
    choice = getOption(choiceName);
  }

  /** Set Option by index setOption( option name/index, option name or price or both ) */
  protected void setOption(int index, String newoptionName, float newPrice) { // overloaded D
    Option o = getOption(index);
    if (o != null) {
      o.setPrice(newPrice);
      o.setName(newoptionName);
      o.printOption(); // debug
    }
  }

  /** Set Option price by optionName setOption( option name/index, option name or price or both ) */
  protected void setOption(String optionName, float newPrice) { // overloaded A
    Option o = getOption(optionName);
    if (o != null) {
      o.setPrice(newPrice);
      o.printOption(); // debug
    }
  }

  /**
   * Set Option optionName by optionName setOption( option name/index, option name or price or both
   * )
   */
  protected void setOption(String optionName, String newoptionName) { // overloaded B
    Option o = getOption(optionName);
    if (o != null) {
      o.setName(newoptionName);
      o.printOption(); // debug
    }
  }

  /** Set Option by optionName setOption( option name/index, option name or price or both ) */
  protected void setOption(
      String optionName, String newoptionName, float newPrice) { // overloaded C
    Option o = getOption(optionName);
    if (o != null) {
      o.setPrice(newPrice);
      o.setName(newoptionName);
      o.printOption(); // debug
    }
  }
  /** Setter for OptionSet Set optionSetName to @param optionSetName */
  protected void setOptionSetName(String optionSetName) {
    this.name = optionSetName;
    for (Option o : options) // when optionSetName is updated, so is the parentName of all options
      if (o != null) o.setParentName(optionSetName);
  }

  /** Setter for OptionSet Set optionSets to @param optionSets */
  protected void setOptionSets(ArrayList<Option> optionSets) {
    this.options = optionSets;
  }
}
