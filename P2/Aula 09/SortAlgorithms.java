import static java.lang.System.arraycopy;

/**
 * SortAlgorithms
 * 
 * @author Pedro Teixeira
 * Copyright 2017, MIECT - DETI UA
 */
public class SortAlgorithms {

	public static void main (String[] args) {

		int num = 10;
		if (args.length > 1) {
			try {
				num = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
			}
		}

		int[] array = new int [num];
		for (int i = 0; i < array.length; i++){
			array[i] = (int)(Math.random()*50);
		}

		// print original and sorted array for comparison
		System.out.println("Original array");
		print(array);
		
		System.out.println();
		int[] a = new int[array.length];
		arraycopy(array, 0, a, 0, array.length);
		System.out.println("Sequencial Sort");
		sequentialSort(a, 0, array.length);
		print(a);
		
		System.out.println();
		int[] b = new int[array.length];
		arraycopy(array, 0, b, 0, array.length);
		System.out.println("Selection Sort");
		selectionSort(b, 0, array.length);
		print(b);
		
		System.out.println();
		int[] c = new int[array.length];
		arraycopy(array, 0, c, 0, array.length);
		System.out.println("Bubble Sort");
		bubbleSort(c, 0, array.length);
		print(c);
		
		System.out.println();
		int[] d = new int[array.length];
		arraycopy(array, 0, d, 0, array.length);
		System.out.println("Insertion Sort");
		insertionSort(d, 0, array.length);
		print(d);
		
		System.out.println();
		int[] e = new int[array.length];
		arraycopy(array, 0, e, 0, array.length);
		System.out.println("Merge Sort");
		mergeSort(e, 0, array.length);
		print(e);
		
		System.out.println();
		int[] f = new int[array.length];
		arraycopy(f, 0, f, 0, array.length);
		//System.out.println("Quick Sort");
		//quickSort(f, 0, array.length);
		//print(f);
	}

	public static void print(int[] array){
		int i = 0;
		for (i = 0; i < array.length - 1; i++){
			System.out.print(array[i] + ", ");
		}
		System.out.print(array[i] + "\n");
	}

	/**
	 * Swaps two elements of an integer array.
	 * @param a the array
	 * @param i index of an element to swap
	 * @param j index of the other element to swap
	 * {@code i},{@code j} must be valid indexes within array {@code a}
	 */
	static void swap(int[] a, int i, int j) {
		assert a!=null;
		assert 0<=i && i<a.length;
		assert 0<=j && j<a.length;

		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;

	}

	
	/** Selection Sort
	 */
	static void selectionSort (int[] a, int start, int end) {
		for (int i = start; i < end - 1; i++) {
			int minIndex = searchMin (a, i+1 , end);
			if (a[i] > a[minIndex]) swap(a, i, minIndex);
		}
	}
	
	static int searchMin (int[] a, int start, int end) {
		int max      = a[start];
		int maxIndex = start;
		for (int i = start + 1; i < end ; i++) {
			if (a[i] < max) {
				max 	 = a[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	/**
	 * Sequential sort ("greedy" variation of selection sort).
	 * Increasing sorting of integer subarray a[start..end[
	 * @param a      the array to be (partially) sorted.
	 * @param start  index of the first element to sort.
	 * @param end    index of the first element not to be sorted (the last element to sort is {@code end-1}).
	 * Requires:   a!=null and 0 <= start <= end <= a.length
	 * Ensures:  The elements a[k] with start <= k < end are sorted.  the remaining elements are not changed.
	 */
	static void sequentialSort(int[] a, int start, int end) {
		assert a!=null;
		assert 0<=start && start<=end && end<=a.length;

		for (int i=start; i<end-1; i++) { // For each element (except the last):
			for (int j=i+1; j<end; j++) {   // Scan every following element
				if (a[j] < a[i]) {            // compare them
					swap(a, i, j);              // if necessary, swap them
				}
			}
		}
	}

	/**
	 * Bubble sort.
	 */
	static void bubbleSort(int[] a, int start, int end) {
		assert a!=null;
		assert 0<=start && start<=end && end<=a.length;

		int f = end;
		boolean existsSwap;
		do {
			existsSwap = false;
			for(int i=start; i<f-1; i++) {
				if (a[i] > a[i+1]) {
					swap(a, i, i+1);
					existsSwap = true;
				}
			}
			f--;
		} while (f>start+1 && existsSwap);
	}

	/** Insertion Sort, (variante com inserção sequencial). */
	
	/** Insertiont sort
	 */
	static void insertionSort(int[] a, int start, int end) {
		for (int i = start + 1; i < end; i++) {
			int v = a[i];
			int j;
			for (j = i - 1; (j >= start) && (a[j] > v); j--) {
				//System.out.println(j);
				a[j + 1] = a[j];	//push
			} 
			a[j+1] = v;
		}

	}

	/** Merge sort */
	
	/** Merge sort
	 */
	static void mergeSort(int[] a, int start, int end) {
		if (end - start > 1) {
			int middle = (end + start) / 2;
			mergeSort  (a, start, middle);
			mergeSort  (a, middle, end);
			mergeArrays(a, start, middle, end);
		}

	}

	// merge of arrays
	
	static void mergeArrays(int[] a, int start, int middle, int end) {
		int[] b = new int[end-start];

		int i1 = start;
		int i2 = middle;

		int j = 0;

		while(i1 < middle && i2 < end) {
			if (a[i1] < a[i2])
				b[j++] = a[i1++];
			else
				b[j++] = a[i2++];
		}

		while(i1 < middle)
			b[j++] = a[i1++];

		while(i2 < end)
			b[j++] = a[i2++];

		arraycopy(b, 0, a, start, end-start);
	}




}
