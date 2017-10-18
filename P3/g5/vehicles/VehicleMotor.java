package g5.vehicles;

/**
 * MotorVehicle
 * 
 * @author Beatriz Borges, 79857 | Pedro Teixeira, 84715, MIECT
 */

public abstract class VehicleMotor extends Vehicle {

	// Instance Fields
	private Motor motor;

	// ----------------------------
	// Constructors
	/**
	 * 
	 * Constructor
	 * @param year
	 * @param color
	 * @param numWheels
	 * @param maxSpeed
	 * @param motor
	 */
	public VehicleMotor(int year, String color, int numWheels, int maxSpeed, Motor motor) {
		super(year, color, numWheels, maxSpeed);
		this.motor = motor;
	}

	// ----------------------------
	// Getters
	public double getCylinderCapacity() {
		return motor.getCylinderCapacity();
	}

	public double getPower() {
		return motor.getPower();
	}

	public double getConsumption() {
		return motor.getConsumption();
	}

	public FuelType getFuelType() {
		return motor.getFuelType();
	}

	// ----------------------------
	// Setters
	public boolean setFuelType(FuelType type) {
		return motor.setFuelType(type);
	}

	// ----------------------------
	// Methods
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("\n\tMotor:");
		builder.append(motor);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((motor == null) ? 0 : motor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VehicleMotor other = (VehicleMotor) obj;
		if (motor == null) {
			if (other.motor != null) {
				return false;
			}
		} else if (!motor.equals(other.motor)) {
			return false;
		}
		return true;
	}
}
