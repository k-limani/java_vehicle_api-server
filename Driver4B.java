package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.EditAuto;
import adapter.UpdateAuto;
import printer.Printer;
import scale.EditOptionsHelper;

public class Driver4B {
  public static void main(String[] args) {

    /**************************************************************************/
    // Driver 4A
    /**************************************************************************/

    String filename = "wagonztw.txt", key = "Ford Focus Wagon ZTW 2012";
    String filename1 = "nissanaltima.txt", key1 = "Nissan Altima 2017";

    Printer.printDivider1(); // 1
    CreateAuto a11 = new BuildAuto(); // CreateAuto interface
    a11.BuildAuto(filename); // method 1
    a11.printAuto(key); // method 2

    Printer.printDivider1(); // 2
    UpdateAuto a22 = new BuildAuto(); // UpdateAuto interface
    a22.updateOptionSetName("Color", "Colour"); // method 1
    a22.updateOptionPrice("Colour", "Gold Clearcoat Metallic", "200"); // method
    // 2
    a22.setOptionChoice("Colour", "Gold Clearcoat Metallic"); // method 3
    a11.printAuto(key); // check above

    Printer.printDivider1(); // 3
    EditAuto a33 = new BuildAuto(); // EditAuto interface
    a33.updateOptionSetName(
        key, "Brakes/Traction Control", "Brakes & Traction Control"); // method 1
    a33.updateOptionPrice(key, "Brakes & Traction Control", "standard", "300"); // method 2
    a33.setOptionChoice(key, "Brakes & Traction Control", "standard"); // method
    // 3
    a11.printAuto(key); // check above

    Printer.printDivider1(); // 4
    CreateAuto b11 = new BuildAuto(); // CreateAuto interface
    b11.BuildAuto(filename1); // method 1
    b11.printAuto(key1); // method 2

    Printer.printDivider1(); // 5
    // UpdateAuto b2 = new BuildAuto(); //UpdateAuto interface
    a22.updateOptionSetName("Color", "Colour"); // method 1
    a22.updateOptionPrice("Colour", "Infra-Red Clearcoat", "210"); // method 2
    a22.setOptionChoice("Colour", "Infra-Red Clearcoat"); // method 3
    b11.printAuto(key1); // check above

    EditAuto b33 = new BuildAuto(); // EditAuto interface
    b33.updateOptionSetName(
        key1, "Brakes/Traction Control", "Brakes & Traction Control"); // method 1
    b33.updateOptionPrice(key1, "Brakes & Traction Control", "standard", "310"); // method 2
    b33.setOptionChoice(key1, "Brakes & Traction Control", "standard"); // method
    // 3
    b11.printAuto(key1); // check above

    Printer.printDivider3A();

    /**************************************************************************/
    // Driver 4B
    /**************************************************************************/

    // Ford Focus
    Printer.printDivider1(); // 1
    CreateAuto a1 = new BuildAuto(); // CreateAuto interface
    a1.BuildAuto(filename); // method 1
    a1.printAuto(key); // method 2

    // Nisan Altima
    Printer.printDivider1(); // 4
    CreateAuto b1 = new BuildAuto(); // CreateAuto interface
    b1.BuildAuto(filename1); // method 1
    b1.printAuto(key1); // method 2

    Printer.printDivider3A();

    // variables
    String optionSetName = "Color";
    String optionName = "Gold Clearcoat Metallic";

    String[] car0 = {key, optionSetName, optionName, "1111"}; // ford focus
    String[] car1 = {key1, optionSetName, optionName, "2222"}; // nissan altima
    String[] car2 = {key1, optionSetName, optionName, "3333"}; // nissan altima

    // updating car0 and car1, 2 different cars => these threads run
    // concurrently
    Thread t1 = new Thread(new EditOptionsHelper(car0, car1)); // different
    // cars
    t1.start();

    // updating car1 and car2, the same car => everything is synchronized
    Thread t2 = new Thread(new EditOptionsHelper(car1, car2)); // same car
    t2.start();

    String filename00 = "toyota-properties.txt", key00 = "Toyota Prius 1998";
    String filename01 = "nissanaltima.txt", key01 = "Nissan Altima 2017";
    String filename02 = "wagonztw.txt", key02 = "Ford Focus Wagon ZTW 2012";

    Printer.printDivider1();
    CreateAuto a110 = new BuildAuto(); // CreateAuto interface
    a110.BuildAuto(filename00); // method 1
    a110.printAuto(key00); // method 2

    /**************************************************************************/
    // Testion AutoStock class and AutoStockable interface
    /**************************************************************************/

    Printer.printDivider1();
    CreateAuto a30 = new BuildAuto();
    CreateAuto a31 = new BuildAuto();
    CreateAuto a32 = new BuildAuto();
    a30.BuildAuto(filename00);
    a31.BuildAuto(filename01);
    a32.BuildAuto(filename02);

    a30.getStock().printStock();
    a31.getStock().printStock();
    a32.getStock().printStock();
    Printer.printDivider1();

    // a30.getStock().deleteEntireStock();
    // a30.getStock().deleteAutoFromStock(key);

    // content of inventoryLhm = {Ford Wagon, Nissan Altime, Toyota Prius};
    // replace Toyota Prius with Ford Wagon ZTW, so in the end there will be 2 Ford Focus Wagons
    a31.getStock().updateAutoFromStock(key00, a30.getStock().getVehicle(key02));
    a31.getStock().printStock();
    Printer.printDivider1();
  }
}
