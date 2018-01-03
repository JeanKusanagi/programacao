package g13.ex1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Country
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public class Country implements Serializable, Comparable<Country> {

	private static final long serialVersionUID = -1785927649634907802L;

	// Instance Fields
	private String name;
	private Locality capital;
	private Set<Region> regions;
	private int population;

	// CTORs
	/**
	 * 
	 * Constructor
	 * @param name
	 */
	public Country (String name) {
		this(name, Locality.NO_CAPITAL);		
	}

	/**
	 * 
	 * Constructor
	 * @param name
	 * @param capital
	 * @throws IllegalArgumentException
	 */
	public Country (String name, Locality capital) {
		if (capital != Locality.NO_CAPITAL && capital.getType() != LocalityType.City) 
			throw new IllegalArgumentException("Capital must be a city");		
		this.name = name;
		this.capital = capital;
		regions = new HashSet<>();
	}

	// Getters
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the capital
	 */
	public Locality getCapital() {
		return capital;
	}

	/**
	 * @return the regions
	 */
	public Set<Region> getRegions() {	
		return new HashSet<>(regions);				// returns a copy (information hiding)
	}

	// Setters
	/**
	 * @param region the region to set
	 */
	public boolean addRegion(Region region) {
		boolean added = regions.add(region);
		population += region.getPopulation();
		return added;
	}

	/**
	 * @param region the region to remove
	 */
	public boolean removeRegion(Region region) {
		boolean removed = regions.remove(region);
		population -= region.getPopulation();
		return removed;
	}

	// Other Methods
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pais: ");
		builder.append(name);
		builder.append(", População: ");
		builder.append(population);
		builder.append(" (Capital: ");
		if (capital != null) {
			builder.append(capital);
		}
		else {
			builder.append("*Indefinida*");
		}
		builder.append(")");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + population;
		result = prime * result + ((regions == null) ? 0 : regions.hashCode());
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
		Country other = (Country) obj;
		if (capital == null) {
			if (other.capital != null) {
				return false;
			}
		} else if (!capital.equals(other.capital)) {
			return false;
		}
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
		if (regions == null) {
			if (other.regions != null) {
				return false;
			}
		} else if (!regions.equals(other.regions)) {
			return false;
		}
		return true;
	}


	@Override
	public int compareTo(Country o) {
		if (this.population < o.population) return -1;
		if (this.population > o.population) return 1;
		return 0;
	}

}
