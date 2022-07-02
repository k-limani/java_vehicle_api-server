package util;

import exception.AutoException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Automobile;

public class FileIO implements Serializable {

  private static final long serialVersionUID = 1L;

  /**************************************************************************/
  // data fields
  /**************************************************************************/

  private String filename = "";

  Properties props;
  Automobile auto;

  // variables used in methods 1 and 2
  int globalcount = 1; // total number of strings in method 2
  int opsetsize = 0, opsize = 0, index1 = 0, index2 = 0;
  boolean options = false; // will be switched to true in method2 once ontions are being read
  String tempOS = "";

  // debug
  boolean DEBUG1 = false;
  boolean DEBUG2 = false;

  /**************************************************************************/
  ////////////////////////////// constructors //////////////////////////////
  /**************************************************************************/
  /** Default constructor for FileIO */
  public FileIO() {
    super();
    setAuto();
  }

  /**
   * Constructor for FileIO
   *
   * @param props
   */
  public FileIO(Properties prt) {
    super();
    this.props = prt;
    setAuto();
  }

  /**
   * Constructor for FileIO
   *
   * @param fname
   */
  public FileIO(String fname) {
    super();
    this.filename = fname;
    setAuto();
  }

  /**************************************************************************/
  // getters/setters
  /**************************************************************************/

  // populates auto options & optionsets according to Properties props
  public Automobile adjustAutoProps() {
    if (props.equals(null)) return null;

    String CarMake =
        props.getProperty(
            "CarMake"); // this is how you read a property, analogous getting a value from
    // HashTable
    String CarModel = props.getProperty("CarModel");
    String CarYear = props.getProperty("CarYear");
    String CarPrice = props.getProperty("CarPrice");
    if (!CarMake.equals(null)
        && !CarModel.equals(null)
        && !CarYear.equals(null)
        && !CarPrice.equals(null)) {
      String OptionSet1 = props.getProperty("OptionSet1"); // get optionSet
      String OptionValue1a = props.getProperty("OptionValue1a"); // options
      String OptionPrice1a = props.getProperty("OptionPrice1a");
      String OptionValue1b = props.getProperty("OptionValue1b");
      String OptionPrice1b = props.getProperty("OptionPrice1b");
      String OptionSet2 = props.getProperty("OptionSet2"); // get optionSet
      String OptionValue2a = props.getProperty("OptionValue2a"); // options
      String OptionPrice2a = props.getProperty("OptionPrice2a");
      String OptionValue2b = props.getProperty("OptionValue2b");
      String OptionPrice2b = props.getProperty("OptionPrice2a");

      // populate data fields for auto object
      auto.setMakeModelYearPrice(CarMake, CarModel, CarYear, CarPrice);
      auto.addOptionSet(OptionSet1);
      auto.addOption(OptionSet1, OptionValue1a, OptionSet1, OptionPrice1a);
      auto.addOption(OptionSet1, OptionValue1b, OptionSet1, OptionPrice1b);
      auto.addOptionSet(OptionSet2);
      auto.addOption(OptionSet2, OptionValue2a, OptionSet2, OptionPrice2a);
      auto.addOption(OptionSet2, OptionValue2b, OptionSet2, OptionPrice2b);
      if (DEBUG2) auto.printAutomobile();

    } else {
      return null;
    }
    return auto;
  }

  // Method 5
  // checks if car name is empty, and throws exception
  public String checkCarName(String temp2) throws AutoException {
    if (temp2 == null) { // temp2.isEmpty()) {
      throw new AutoException(1, "No car name!");
    }
    return temp2;
  }

  /**************************************************************************/
  // methods
  /**************************************************************************/

  // Method 6
  // checks if base price is empty, and throws exception
  public String checkCarPrice(String temp2) throws AutoException {
    if (temp2 == null || temp2 == "") {
      throw new AutoException(2, "No base price!");
    }
    return temp2;
  }

  // Method 7
  // checks if number of option sets is empty, and throws exception
  public String checkNumberOfOptions(String temp2) throws AutoException {
    if (temp2 == "") { // make sure you put space instead of number of options to show fix3
      throw new AutoException(3, "No number of option sets!");
    }
    return temp2;
  }

  /**************************************************************************/
  // serializing methods
  /**************************************************************************/

  // Method 8
  // checks if number of option sets is empty, and throws exception
  public String checkOptionSetName(String temp2) throws AutoException {
    if (temp2 == null || temp2 == "") {
      throw new AutoException(4, "No option set name!");
    }
    return temp2;
  }

  // Method 4
  public void desirealize() { // deserializes an auto object
    ObjectInputStream in;
    try {
      in = new ObjectInputStream(new FileInputStream(filename));
      auto = (Automobile) in.readObject();
      // auto.printAutomotive();
      in.close();
    } catch (Exception e) {
      System.out.printf(" Error -- " + e.toString());
    }
  }

  /**************************************************************************/
  // exception methods
  /**************************************************************************/

  /** Getter for FileIO */
  public Automobile getAuto() {
    return auto;
  }

  // Method 1A
  // Reads data line by line from file param, and inputs into the tokenizer
  // method2
  public Automobile readFile() {
    int linecount = 0;
    try {
      FileReader file = new FileReader(filename);
      BufferedReader buff = new BufferedReader(file);
      String temp1;
      boolean eof = false;
      while (!eof) {
        String line = buff.readLine();
        linecount++;
        if (line == null) // stop reading file when all lines are read
          eof = true;
        else {
          temp1 = line;
          if (DEBUG1) System.out.println("\nlinecount=" + linecount + ", line: " + line); // debug1
          stokenizer(temp1, auto); // Method 2
        }
      }
      buff.close();
    } catch (Exception e) {
      System.out.println("Error - " + e.toString());
    }

    // prints all stats for automotive
    // caution: null pointer exception if auto object not finished ie. if not all
    // options are added
    if (DEBUG2 == true) auto.printAutomobile("printing from FileIO..."); // debug
    return auto;
  }

  // overloaded method A
  public Automobile readPropertiesFile() {
    props = new Properties(); // import java.util.Properties;
    try {
      FileInputStream in = new FileInputStream(filename);
      props.load(in); // This loads the entire file in memory.
      adjustAutoProps();
    } catch (IOException e) {
      System.err.println("Exception: " + e);
      return null;
    }
    return auto;
  }

  // overloaded method B
  public Automobile readPropertiesFile(Properties prt) {
    props = null;
    try {
      props = prt;
      adjustAutoProps();
    } catch (ClassCastException e) {
      System.err.println("Exception: " + e);
      return null;
    }
    return auto;
  }

  /**************************************************************************/
  // method for parsing properties file
  /**************************************************************************/

  // Method 1B
  // Reads data line by line from string param, and inputs into the tokenizer
  // method2
  public Automobile readString(String str) {
    int linecount = 0;
    String filecontent = str;
    try {
      //			FileReader file = new FileReader(filename);
      //			BufferedReader buff = new BufferedReader(file);
      Scanner sc = new Scanner(filecontent);
      String temp1;
      boolean eof = false;
      while (!eof) {
        String line = sc.nextLine();
        linecount++;
        if (line == null) // stop reading file when all lines are read
          eof = true;
        else {
          temp1 = line;
          if (DEBUG1) System.out.println("\nlinecount=" + linecount + ", line: " + line); // debug1
          stokenizer(temp1, auto); // Method 2
        }
      }
      sc.close();
    } catch (Exception e) {
      System.out.println("Error - " + e.toString());
    }

    // prints all stats for automotive
    // caution: null pointer exception if auto object not finished ie. if not all
    // options are added
    if (DEBUG2 == true) auto.printAutomobile("printing from FileIO..."); // debug
    return auto;
  }

  // Method3
  public void serialize() { // serializes an auto object
    filename = new String(auto.getMake() + auto.getModel() + auto.getYear() + ".dat");
    ObjectOutputStream out;
    try {
      out = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(auto);
      out.close();
    } catch (IOException e) {
      System.out.printf(" Error -- " + e.toString());
      e.printStackTrace();
    }
  }

  /** Setter for auto in FileIO */
  private void setAuto() {
    this.auto = new Automobile();
  }

  // Method 2
  // accepts lines from Method 1 and an auto object
  public void stokenizer(String myline, Automobile auto) {
    String strg = "", temp2 = "";

    int localcount = 1; // after each line that's inputed localcount is reset to 1

    StringTokenizer st1 = new StringTokenizer(myline, ";"); // second parameter sets the delimiter
    while (st1.hasMoreTokens()) {
      strg = st1.nextToken();

      // first line will update the auto object, giving it a name, baseprice, and # of
      // optionsets
      // Automotive ==> make, model, year, and baseprice
      if (globalcount <= 4 && options != true) { // globalcount <= # of words in 1st line
        switch (localcount) {
          case 1:
            try { // try/catch block #1, throws missing carname exception, fix returns "no name"
              temp2 = checkCarName(strg);
            } catch (AutoException ae) {
              temp2 = ae.fix(1, strg);
            }
            auto.setMake(temp2);
            break;
          case 2:
            try { // try/catch block #1, throws missing carname exception, fix returns "no name"
              temp2 = checkCarName(strg);
            } catch (AutoException ae) {
              temp2 = ae.fix(1, strg);
            }
            auto.setModel(temp2);
            break;
          case 3:
            try { // try/catch block #2, throws missing price exception, fix returns 0.0
              temp2 = checkCarPrice(strg);
            } catch (AutoException ae) {
              temp2 = ae.fix(2, strg);
            }
            auto.setBaseprice(temp2);
            break;

          case 4:
            try { // try/catch block #3, throws missing number of options exception, fix returns 5
              temp2 = checkNumberOfOptions(strg);
            } catch (AutoException ae) {
              temp2 = ae.fix(3, strg);
            }
            opsetsize = Integer.parseInt(temp2);
            auto.setYear(temp2);
            break;
        }
      }
      // OptionSet ==> optionset name, optionset size
      else if (options != true) { // option sets
        switch (localcount) { // updates optionset name
          case 1:
            try { // try/catch block #4 (and last), throws missing price exception, no fix
              temp2 = checkOptionSetName(strg);
            } catch (AutoException ae) {
              temp2 = ae.fix(4, strg); // default
            }
            auto.addOptionSet(temp2);
            tempOS = auto.getOptionSetName(temp2);
            break;

          case 2: // sets # of options for that optionset
            opsize = Integer.parseInt(strg);
            options = true;
            break;
        }
      }
      // Option ==> option name, option price
      else { // options
        switch (localcount) {
          case 1: // sets option name
            auto.addOption(index1, strg, tempOS);
            break;

          case 2: // sets option price
            auto.setOption(index1, index2, Float.parseFloat(strg));
            index2++; // moves on to next option
            break;
        }

        if (index2 == opsize) { // when finished with these options
          options = false;
          index1++; // keeps tracks of optionsets
          index2 = 0; // keeps track of options
        }
      }
      globalcount++;
      localcount++;
      continue;
    }
  }
}
