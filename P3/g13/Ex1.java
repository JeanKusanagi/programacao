package g13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import g13.ex1.Country;
import g13.ex1.Locality;
import g13.ex1.LocalityType;
import g13.ex1.Province;
import g13.ex1.State;

/**
 * Ex1
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */
public class Ex1 {

	public static void main(String[] args) {
		Locality cid1 = new Locality("Szohod", 31212,
				LocalityType.City);
		Locality cid2 = new Locality("Wadesdah", 23423,
				LocalityType.City);
		Locality cid3 = new Locality("BedRock", 23423,
				LocalityType.Town);
		State est1 = new State("North Borduria", 223133, cid1);
		State est2 = new State("South Borduria", 84321, cid2);
		Country p1 = new Country("Borduria", est1.getCapital());
		Country p2 = new Country("Khemed", cid2);
		Country p3 = new Country("Aurelia");
		Country p4 = new Country("Atlantis");
		try{
			Country p5 = new Country("Khemed", cid3);		// should give runtime exception
			System.out.println(p5);
		} catch (IllegalArgumentException e) {
			System.err.println("Error \"" + e.getLocalizedMessage() + "\" was caught and ignored\n");
		}

		p1.addRegion(est1);
		p1.addRegion(est2);
		p2.addRegion(new Province("Afrinia", 232475, "Aluko Pono"));
		p2.addRegion(new Province("Eriador", 100000, "Dumpgase Liru"));
		p2.addRegion(new Province("Laurania", 30000, "Mukabamba Dabba"));
		List<Country> org = new ArrayList<Country>();
		org.add(p1);
		org.add(p2);
		org.add(p3);
		org.add(p4);
		System.out.println("----Iterar sobre o conjunto");
		Iterator<Country> itr = org.iterator();
		while (itr.hasNext())
			System.out.println(itr.next());
		System.out.println("-------Iterar sobre o conjunto - For each (java 8)");
		for (Country pais: org)
			System.out.println(pais);

		// ToDo:
		Set<Country> paisesOrdenados = new TreeSet<>();
		paisesOrdenados.add(p1);
		paisesOrdenados.add(p2);
		paisesOrdenados.add(p3);
		paisesOrdenados.add(p4);
		System.out.println(paisesOrdenados);
		// adicionar, remover, ordenar, garantir elementos únicos
	}
}
