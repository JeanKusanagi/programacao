package g5;

import g5.vehicles.LicensePlate;

/**
 * Ex2_VehiclesTest
 * 
 * @author Beatriz Borges, 79857 | Pedro Teixeira, 84715, MIECT
 */
public class Ex2_VehiclesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(LicensePlate.isValid("GZ-AG-21"));
		System.out.println(LicensePlate.isValid("GZ-42-BB"));
		System.out.println(LicensePlate.isValid("21-AG-9G"));
		System.out.println(LicensePlate.isValid("21-AG-GG"));
		System.out.println(LicensePlate.isValid("21-AG-9G2"));
		System.out.println(LicensePlate.isValid("21AG-9G2"));
		System.out.println(LicensePlate.isValid("Not Registered"));
		System.out.println(LicensePlate.isValid("Not registered"));
		System.out.println(LicensePlate.isValid("Not Registereds"));
	}

}
