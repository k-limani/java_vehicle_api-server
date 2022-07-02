package adapter;

public interface EditAuto {

  // prints the name, optionsets, options, and choices for a given auto
  public abstract void printAuto();

  // retrieves a1 auto object from LinkedHashMap l1 & updates OptionSetName and optionName
  public abstract void setOptionChoice(String key, String optionSetName, String optionName);

  // retrieves a1 auto object from LinkedHashMap l1 & updates Option price
  public abstract void updateOptionPrice(
      String key, String OptSetname, String optionName, String newPrice);

  // retrieves a1 auto object from LinkedHashMap l1 & updates OptionSetName
  public abstract void updateOptionSetName(String key, String OptionSetname, String newName);

  // page 517 textbook: All interface methods must be public abstract instance methods

}
