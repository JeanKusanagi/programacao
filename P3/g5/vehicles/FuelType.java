package g5.vehicles;

/**
 * FuelType<p>
 * Possible types: Ethanol, Methanol, Gasoline, Diesel, Natural Gas, Hydrogen and Biodiesel.
 * 
 * @author Beatriz Borges, 79857 | Pedro Teixeira, 84715, MIECT
 */

public enum FuelType {
	Ethanol, Methanol, Gasoline, Diesel, NaturalGas { @Override
		public String toString() { 
		return "Natural Gas"; 
	} 
	}, Hydrogen, Biodiesel;

	public static boolean exists (FuelType fuelType) {
		for (FuelType me : FuelType.values()) {
			if (me.name().equals(fuelType))
				return true;
		}
		return false;
	}
}
