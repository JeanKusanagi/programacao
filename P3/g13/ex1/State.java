package g13.ex1;

/**
 * Estado
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */
public class State extends Region {

	private static final long serialVersionUID = 7281206338083805025L;

	// Instance Fields (some are inherited)
	private Locality capital;


	// CTORs
	/**
	 * 
	 * Constructor
	 * @param nome
	 * @param population
	 * @param capital
	 * @throws IllegalArgumentException if the capital is not a city
	 */
	public State(String nome, int population, Locality capital) {
		super(nome, population);
		if (capital.getType() != LocalityType.City) throw new IllegalArgumentException("Capital must be a city");
		this.capital = capital;
	}

	// Getter
	/**
	 * @return the capital
	 */
	public Locality getCapital() {
		return capital;
	}

	// Other methods
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Estado ");
		builder.append(super.toString());
		builder.append(" (Capital: ");
		builder.append(capital);
		builder.append(")");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
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
		State other = (State) obj;
		if (capital == null) {
			if (other.capital != null) {
				return false;
			}
		} else if (!capital.equals(other.capital)) {
			return false;
		}
		return true;
	}

}
