package g01;

import g01.people.Data;
import g01.people.Pessoa;

/**
 * Test
 * 
 * @author Pedro Teixeira 84715
 */

public class Ex3_PeopleTest {
	public static void main (String[] args) {
		Data d = new Data(11, 4, 1974);
		Pessoa p = new Pessoa ("António Nunes", 98012244, d);
		System.out.print(d + "\n" + p);

	}
}
