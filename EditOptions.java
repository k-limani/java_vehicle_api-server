package scale;

import adapter.ProxyAutomobile;

public class EditOptions extends ProxyAutomobile implements Runnable {
	private static final long serialVersionUID = 1L;

	// String[4] with car stats [key, opsetName, opNane, newPrice]
	private String[] car;

	/**
	 * Constructor for EditOptions1
	 *
	 * @param String[4] cars
	 */
	public EditOptions(String[] car) {
		this.car = new String[4];
		for (int i = 0; i < 4; i++) {
			this.car[i] = car[i];
		}
	}

	// method for changing an Automobile object inside ProxyAuto LinkedHashMap
	public void change(String[] car) {
		updateOptionPrice(car[0], car[1], car[2], car[3]);
		printAuto(car[0]);
	}

	// changes a car object
	@Override
	public void run() {
		change(car);
	}
}
