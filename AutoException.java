package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoException extends Exception {

  private static final long serialVersionUID = 1L;
  private Fix1to100 h1;
  private int errorNum;
  private String errorMessage;

  /** Deffault constructor for AutoException */
  public AutoException() {
    super();
    // if these are uncommented, then it will call printmyproblem() twice,
    // once when the default constructor is called ie. catch (AutoException ae)
    // and another time when the other constructor is called ie. ae.fix(0, filename)
    //		errorLog();
    //		printmyproblem();
  }

  /**
   * Constructor for AutoException
   *
   * @param errorno
   */
  public AutoException(int errorno) {
    super();
    this.errorNum = errorno;
    errorLog();
    printmyproblem();
  }

  /**
   * Constructor for AutoException
   *
   * @param errorno
   * @param errormsg
   */
  public AutoException(int errorno, String errormsg) {
    super();
    this.errorNum = errorno;
    this.errorMessage = errormsg;
    errorLog();
    printmyproblem();
  }

  /**
   * Constructor for AutoException
   *
   * @param errormsg
   */
  public AutoException(String errormsg) {
    super();
    this.errorMessage = errormsg;
    errorLog();
    printmyproblem();
  }

  // write error logs to text file (from Bob's notes)
  public void errorLog() { // included in constructor
    try {
      DateFormat d1 = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
      Date date = new Date();
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("errlog.txt", true)));
      writer.println("[" + d1.format(date) + "] " + errorMessage);
      writer.close();
    } catch (IOException e) {
      System.out.println("IO Error, try restarting the process");
      System.exit(1);
    } finally {
    }
  }

  // fixes exception
  public String fix(int errorno, String errormsg) {
    h1 = new Fix1to100(); // the constructor for h1 will create an errorLog
    switch (errorno) { // added return to all the cases otherwise temp2 = ae.fix(1, strg) wont equal
      // a String
      case 0:
        return h1.fix0(errormsg);
      case 1:
        return h1.fix1();
      case 2:
        return h1.fix2();
      case 3:
        return h1.fix3();
      case 4:
        return h1.fix4();
    }
    return null;
  }

  /**
   * Getter for AutoException
   *
   * @return errormsg
   */
  public String getErrormsg() {
    return errorMessage;
  }

  /**
   * Getter for AutoException
   *
   * @return errorno
   */
  public int getErrorno() {
    return errorNum;
  }

  // print problem
  public void printmyproblem() {
    //		System.out.println("   printmyproblem() ->"); //debug
    System.out.println("Fix Problem [errorno: " + errorNum + ", errormsg: " + errorMessage + "]");
  }

  /** Setter for AutoException Set errormsg to @param errormsg */
  public void setErrormsg(String errormsg) {
    this.errorMessage = errormsg;
  }

  // How to define exception #'s:
  // 1 to 100 --> model package,
  // 101 to 200 --> fileio,
  // 101 - missing model name,
  // 102 - missing price, etc.

  /** Setter for AutoException Set errorno to @param errorno */
  public void setErrorno(int errorno) {
    this.errorNum = errorno;
  }
}
