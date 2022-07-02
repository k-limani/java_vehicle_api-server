package driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.Automobile;
import util.FileIO;

public class Testdriver {

  public static StringBuffer loadTextFile(String fname) {
    StringBuffer sbuff = new StringBuffer();
    try {
      BufferedReader buff = new BufferedReader(new FileReader(fname));
      boolean eof = false;
      int counter = 0;
      while (!eof) {
        String line = buff.readLine();
        if (line == null) eof = true;
        else {
          if (counter == 0) sbuff.append(line);
          else sbuff.append("\n" + line);
        }
        counter++;
      }
      buff.close();
    } catch (FileNotFoundException e) {
      System.err.println("Error in file directory ... ");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Error reading file from directory ... ");
      System.exit(1);
    }

    return sbuff;
  }

  public static void main(String[] args) {

    FileIO test = new FileIO();
    StringBuffer sbuff = new StringBuffer();
    sbuff = loadTextFile("w.txt");
    String filecont = sbuff.toString();
    Automobile auto = new Automobile();
    auto = test.readString(filecont);
    auto.printAutomobile();
  }
}
