package g13.ex1;

import java.io.Serializable;

/**
 * Regiao
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public abstract class Region implements Serializable, Comparable<Region> {

	private static final long serialVersionUID = 7292011608428306881L;

	// Instance Fields
	private String name;
	private int population;

	// CTORs
	/**
	 * 
	 * Constructor
	 * @param name
	 * @param population
	 */
	public Region(String name, int population) {
		this.name = name;
		this.population = population;
	}

	// Getters
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	// Setters
	/**
	 * @param population the population to set
	 * @throws IllegalArgumentException if the given population is negative
	 */
	public void setPopulation(int population) {
		if (population < 0) throw new IllegalArgumentException("Population can't be negative");
		this.population = population;
	}

	// Other Methods
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(", população ");
		builder.append(population);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + population;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Region other = (Region) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (population != other.population) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Region o) {
		if (this.population < o.population) return -1;
		if (this.population > o.population) return 1;
		return 0;
	}

}
