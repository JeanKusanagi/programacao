package g11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import g11.prob1.ex1a.resources.figures.Circulo;
import g11.prob1.ex1a.resources.figures.Figura;
import g11.prob1.ex1a.resources.figures.Quadrado;
import g11.prob1.ex1a.resources.figures.Rectangulo;
import g11.prob1.ex1a.resources.people.Date;
import g11.prob1.ex1a.resources.people.Person;

/**
 * Ex1
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public class Prob1a {
	public static void main(String[] args) {
		ArrayList<Person> vp = new ArrayList<Person>();
		for (int i=0; i<10; i++)
			vp.add(new Person("Bebé no Vector "+i, 1000+i, Date.today()));

		Iterator<Person> vec = vp.iterator();
		printSet(vp.iterator());

		// -------------------------------------------
		LinkedList<Person> lp = new LinkedList<Person>();
		while ( vec.hasNext() ) lp.addLast( vec.next() );

		Iterator<Person> lista = lp.iterator();
		while ( lista.hasNext() )
			System.out.println( lista.next() );
		LinkedList<Figura> figList = new LinkedList<Figura>();
		figList.addLast(new Circulo (1,3, 1));
		figList.addLast(new Quadrado(3,4, 2));
		figList.addLast(new Rectangulo(1,2, 2,5));
		printSet(figList.iterator());
		System.out.println("Soma da Area de Lista de Figuras: " + sumArea(figList));

		// Partindo do principio que Quadrado extends Rectangulo:
		LinkedList< Rectangulo > quadList = new LinkedList<Rectangulo>();
		quadList.addLast(new Quadrado(3,4, 2));
		quadList.addLast(new Rectangulo(1,2, 2,5));
		System.out.println("Soma da Area de Lista de Quadrados: " + sumArea(quadList));

		//figList.print();
		//quadList.print();

	}


	private static <T extends Figura> double sumArea (LinkedList<T> list) {
		double sumArea = 0;
		if (list instanceof Iterable) {
			Iterator<T> i = list.iterator();
			while (i.hasNext()) {
				sumArea += i.next().getArea();
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				sumArea += list.get(i).getArea();
			}
		}

		return sumArea; 
	}

	@SuppressWarnings("unused")
	private static <T extends Figura> double sumArea (Iterator<T> t) {
		double sumArea = 0;
		while (t.hasNext()) { 
			sumArea += t.next().getArea();
		}
		return sumArea; 
	}

	private static <T> void printSet (Iterator<T> t) {
		while (t.hasNext()) {
			System.out.println(t.next());
		}
	}
}

