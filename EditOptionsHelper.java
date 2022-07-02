package scale;

public class EditOptionsHelper implements Runnable {

  // car stats from driver
  String[] car1 = new String[4], car2 = new String[4];

  /**
   * EditOptionsHelper1 constructor
   *
   * @param String[4] car1
   * @param String[4] car2
   */
  public EditOptionsHelper(String[] car1, String[] car2) {
    for (int i = 0; i < 4; i++) {
      this.car1[i] = car1[i];
      this.car2[i] = car2[i];
    }
  }

  @Override
  public void run() {
    Thread th1 = new Thread(new EditOptions(car1)), th2 = new Thread(new EditOptions(car2));

    if (car1[0] != car2[0]) { // if modifying differenct cars, run threads simultaneously
      th1.start();
      th2.start();
    } else { // if modifying same car, synchronize
      synchronized (th1) {
        try {
          th1.start();
          th1.join(); // wait for th1 to finish
          th2.notify();
        } catch (Exception e) {
        }
      }
      synchronized (th2) {
        try {
          th2.start(); // continues here from th2.notify() above
        } catch (Exception e) {
        }
      }
    }
  }
}
