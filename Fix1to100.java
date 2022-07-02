package exception;

public class Fix1to100 { // helper class for fixing exceptions

  /** Constructor for Fix1to100 */
  public Fix1to100() {
    super();
  }

  // file not found
  public String fix0(String filename) {
    System.out.println(" --> calling fix0 method..."); // debug
    System.out.println("File \"" + filename + "\" not found!");
    return filename;
  }

  public String fix1() {
    System.out.println(" --> calling fix1 method..."); // debug
    return "No name";
  }

  public String fix2() {
    System.out.println(" --> calling fix2 method..."); // debug
    return "0.0";
  }

  public String fix3() {
    System.out.println(" --> calling fix3 method..."); // debug
    return "5";
  }

  public String fix4() {
    System.out.println(" --> calling fix4 method..."); // debug
    return null;
  }
}
