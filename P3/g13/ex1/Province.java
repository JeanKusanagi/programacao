package g13.ex1;

/**
 * Province
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public class Province extends Region {

	private static final long serialVersionUID = -2317045157958004652L;

	// Instance Fields (some are inherited)
	private String governor;

	// CTORs
	/**
	 * 
	 * Constructor
	 * @param name
	 * @param population
	 * @param governor
	 */
	public Province(String name, int population, String governor) {
		super(name, population);
		this.governor = governor;
	}

	// Getter
	/**
	 * @return the governor
	 */
	public String getGovernor() {
		return governor;
	}

	// Other methods
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Provincia ");
		builder.append(super.toString());
		builder.append(" (Governor: ");
		builder.append(governor);
		builder.append(")");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((governor == null) ? 0 : governor.hashCode());
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
		Province other = (Province) obj;
		if (governor == null) {
			if (other.governor != null) {
				return false;
			}
		} else if (!governor.equals(other.governor)) {
			return false;
		}
		return true;
	}
}
