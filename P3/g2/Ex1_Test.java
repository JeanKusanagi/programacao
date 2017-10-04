package g2;

/**
 * Ex1_Test
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

import g1.people.Data;
import g2.videoManagement.Age;
import g2.videoManagement.Client;
import g2.videoManagement.Employee;
import g2.videoManagement.Student;
import g2.videoManagement.Video;
import g2.videoManagement.VideoCatalog;

public class Ex1_Test {

	public static void main(String[] args) {
		VideoCatalog catalog = new VideoCatalog(5);

		// Testing Add Methods
		catalog.addClient(new Client("Pedro", 29482, new Data(11, 11, 2010), new Data(11, 02, 1990)));
		catalog.addClient(new Employee ("Joao", 2342, new Data(8, 05, 1999), new Data(11, 3, 1990), 43523, 1092));
		catalog.addClient(new Student ("Joao2", 324, new Data(23, 4, 2020), new Data(11, 02, 1997), 421, "EET"));

		catalog.addVideo(new Video ("Single Ladies", "Revenge", Age.M6));
		catalog.addVideo(new Video ("Let's Get Loud", "Family", Age.M16));
		catalog.addVideo(new Video ("That doesn't impress me much", "Love", Age.M6));
		catalog.addVideo(new Video ("Minions", "Comedy", Age.M12));

		System.out.println(catalog);
		System.out.println("-------------------------------------------------------------");

		//Testing loans
		System.out.println(catalog.addLoan(1, 3));			// true
		System.out.println(catalog.addLoan(2, 2));			// true
		System.out.println(catalog.addLoan(4, 3));			// false (client with ID #4 doesn't exist)
		System.out.println(catalog.removeLoan(1, 3, 5)); 	// true
		System.out.println(catalog.addLoan(2, 3));			// true
		System.out.println(catalog.removeLoan(2, 3, 2));	// true
		System.out.println(catalog.removeLoan(5, 2, 5));	// false (loan with client with ID #5 doesn't exist)
		System.out.println(catalog.addLoan(3, 4));			// true
		System.out.println(catalog.removeLoan(3, 4, 1));	// true	
		System.out.println("\n" + catalog);
		System.out.println("-------------------------------------------------------------");

		// Testing Remove methods
		System.out.println(catalog.removeClient(2));		// true (exists)
		System.out.println(catalog.removeClient(7));		// false (doesn't exist)
		System.out.println(catalog.removeVideo(1));			// true (exists)
		System.out.println(catalog.removeVideo(10));		// false (doesn't exist)
		System.out.println("\n" + catalog);
		System.out.println("-------------------------------------------------------------");

		// Testing getters
		System.out.println(catalog.getVideo("Loud"));
		System.out.println("\nVideos sort by Rating");
		printArray(catalog.getVideosByRating());
		System.out.println("\nList of Videos");
		printArray(catalog.getVideos());

		System.out.println(catalog.getVideoAvailability(1));// false (doesn't exist)
		System.out.println(catalog.getVideoAvailability(3));

		Integer[] videosClient1 = catalog.getLoansByClient(1);
		Integer[] clientsVideo3 = catalog.getLoansByVideo(3);

		System.out.println("Videos that were loaned by client 1: ");
		printArray(videosClient1);
		System.out.println("Clients that loaned video 3: ");
		printArray(clientsVideo3);

	}

	public static <E> void printArray(E[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}
