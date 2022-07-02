package printer;

// class for helping with console display
public class Printer {

  static int count1 = 0, count2, run = 0;

  /**************************************************************************/
  // DATA FIELDS
  /**************************************************************************/

  /**************************************************************************/
  // CONSTRUCTORS
  /**************************************************************************/

  /**************************************************************************/
  // INSTANCE METHODS
  /**************************************************************************/

  /**************************************************************************/
  // GETTERS / SETTERS
  /**************************************************************************/

  // method
  public static void printDivider1() { // overloaded
    String temp;
    count1++;
    temp = "%%%";
    System.out.println("\n");
    for (int i = 1; i <= 75; i++) {
      System.out.print(count1 + temp);
      if (i % 25 == 0) System.out.println();
    }
    System.out.println();
  }

  // method
  public static void printDivider1A(String pattern) { // overloaded
    String temp;
    count2++;
    temp = pattern + pattern + pattern;
    System.out.println("\n");
    for (int i = 1; i <= 75; i++) {
      System.out.print(count2 + temp);
      if (i % 25 == 0) System.out.println();
    }
    System.out.println();
  }

  // method
  public static void printDivider3A() {
    String temp = "[][]";
    System.out.println("\n");
    for (int i = 1; i <= 125; i++) {
      System.out.print(temp);
      if (i % 25 == 0) System.out.println();
    }
    System.out.println();
  }

  // method overloaded
  public static void printDivider3B() {
    String temp = "[][]";
    System.out.println("\n");
    for (int i = 1; i <= 50; i++) {
      System.out.print(temp);
      if (i % 25 == 0) System.out.println();
    }
    System.out.println();
  }
  // method overloaded
  public static void printDivider3B(String titleBtw2Bracket) {
    String temp = "[][]", str = "";
    if (titleBtw2Bracket != null) str = titleBtw2Bracket;
    System.out.println("\n" + str);
    for (int i = 1; i <= 50; i++) {
      System.out.print(temp);
      if (i % 25 == 0) System.out.println();
    }
    System.out.println();
  }

  public static void printDivider4(String methoD, String clasS, String packagE) {
    String temp = "%%%%";
    boolean stop = false;
    System.out.println("\n");
    for (int i = 1; i <= 50; i++) {
      System.out.print(temp);
      if (i % 25 == 0 && !stop) {
        System.out.println(
            "\n" + " METHOD=" + methoD + ", CLASS=" + clasS + ", PACKAGE=" + packagE);
        stop = true;
      }
    }
    System.out.println();
  }

  // method A --> 2 x "%"
  public static String returnDivider1(String titleBtw2Percent) {
    String temp = "%%";
    StringBuffer sbuff = new StringBuffer();
    boolean stop = false;

    sbuff.append("\n");
    for (int i = 1; i <= 50; i++) {
      sbuff.append(temp);
      if (i % 25 == 0 && !stop) {
        if (titleBtw2Percent != "") sbuff.append("\n" + "\t" + titleBtw2Percent + "\n");
        stop = true;
      }
    }
    sbuff.append("\n");
    return sbuff.toString();
  }

  // method B --> 4 x "%"
  public static String returnDivider2(String titleBtw42Percent) {
    String temp = "%%%%";
    StringBuffer sbuff = new StringBuffer();
    boolean stop = false;

    sbuff.append("\n");
    for (int i = 1; i <= 50; i++) {
      sbuff.append(temp);
      if (i % 25 == 0 && !stop) {
        if (titleBtw42Percent != "") sbuff.append("\n" + "\t" + titleBtw42Percent + "\n");
        stop = true;
      }
    }
    sbuff.append("\n");
    return sbuff.toString();
  }
}
