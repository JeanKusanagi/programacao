package g13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Ex3
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public class Ex3 {

	public static void main (String[] args) {
		//		a) A empresa Brinca&Beira (BB) precisa de um registo com os nomes de todos os
		//		seus empregados.
		System.out.println("----------------------------------------------------------------");
		List<String> listEmployees = new LinkedList<>();

		listEmployees.add("João P.");
		listEmployees.add("Maria M.");
		listEmployees.add("João G.");
		listEmployees.add("João P.");

		System.out.println(listEmployees);

		listEmployees.remove("Maria M.");
		System.out.println(listEmployees);


		//		b) Em cada mês é selecionado aleatoriamente um funcionário para receber um
		//		brinquedo grátis. Deve ser possível guardar todos os pares funcionário-brinquedo.
		System.out.println("----------------------------------------------------------------");

		Map<String, String> listEmployeeToy = new HashMap<>();
		listEmployeeToy.put("João P.", "Jogo de Tabuleiro");
		listEmployeeToy.put("Maria M.", "Jogo de Cartas");
		listEmployeeToy.put("João G.", "Carro de brincar");
		listEmployeeToy.put("João P.", "Bonecas");

		System.out.println(listEmployeeToy);

		System.out.println(listEmployeeToy.remove("Maria M.."));		// null (no key)
		System.out.println(listEmployeeToy.remove("Maria M.", "Jogo de Tabuleiro"));		// false
		System.out.println(listEmployeeToy.remove("Maria M.", "Jogo de Cartas"));			// true
		System.out.println(listEmployeeToy);


		//		c) A empresa decidiu atribuir o primeiro nome de um empregado a cada produto.
		//		Prepare uma lista destes nomes sabendo que um nome só poderá ser usado uma
		//		vez.
		System.out.println("----------------------------------------------------------------");

		Set<String> listEmployees1 = new HashSet<>();
		listEmployees1.add("João P.");
		listEmployees1.add("Maria M.");
		listEmployees1.add("João G.");
		listEmployees1.add("João P.");

		System.out.println(listEmployees1);

		//		d) A BB decide, entretanto, que só quer usar os nomes mais populares para os seus
		//		brinquedos. Precisamos de uma estrutura com o número de funcionários que
		//		têm cada primeiro nome.
		System.out.println("----------------------------------------------------------------");

		Map<String, Integer> countFirstNames = new HashMap<>();
		countFirstNames.put("João", 2);
		countFirstNames.put("Maria", 1);

		System.out.println(countFirstNames);

		//		e) A empresa adquire ingressos para a próxima temporada da equipa local de
		//		futebol, para serem distribuídos rotativamente pelos funcionários. Crie a
		//		estrutura mais adequada – pode usar uma ordem qualquer.
		System.out.println("----------------------------------------------------------------");

		Queue<String> employees = new LinkedList<>();
		employees.add("João P.");
		employees.add("Maria M.");
		employees.add("João G.");
		employees.add("Manuel");

		System.out.println(employees);

		for (int i = 0; i < employees.size() * 2; i++) {
			String firstEmployee = employees.poll();
			System.out.println("Iteration " + i + ": Employee " + firstEmployee + " got his/her ticket");
			if (i == employees.size() + 1) System.err.println("Iteration " + i + ": The last printed employee should be the first one in the original queue");
			employees.add(firstEmployee);		// circular queue (last out will be added to the queue)
		}
	}
}

