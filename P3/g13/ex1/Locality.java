package g13.ex1;

import java.io.Serializable;

/**
 * Locality
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public class Locality extends Region implements Serializable {

	private static final long serialVersionUID = 3600564147914726348L;

	// Global Variables
	static final Locality NO_CAPITAL = new Locality("", 0, LocalityType.Undefined); 

	// Instance Fields
	private LocalityType type;

	// CTORs
	/**
	 * 
	 * Constructor
	 * @param name
	 * @param populacao
	 * @param type
	 */
	public Locality(String name, int populacao, LocalityType type) {
		super(name, populacao);
		this.type = type;
	}

	// Getters
	/**
	 * @return the type
	 */
	public LocalityType getType() {
		return type;
	}

	// Other Methods
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(type);
		builder.append(" ");
		builder.append(super.toString());
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Locality other = (Locality) obj;
		if (type != other.type) {
			return false;
		}
		return true;
	} 

}
