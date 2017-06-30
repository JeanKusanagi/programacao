import static java.lang.System.*;

public class P92 {

  public static void main(String[] args) {
    //NumberArray na = new NumberArray();
	int[] na = new int[args.length];
	int j = 0;
	for (String i : args) {
		try{
			na[j] = Integer.parseInt(i);
			j++;
		} catch (NumberFormatException e) {
        err.println("Error: input text \"" + i +"\"cannot be converted to integer!");
        exit(2);
      }
	}
    insertionSort(na, 0, na.length);
    for (int i : na) {
    	System.out.print(i+ "  ");
    }
	//insertionSort(na.array(), 0, na.length());
	//na.print();
  }

  static void insertionSort(int[] a, int start, int end) {
    for (int i = start + 1; i < end ; i++) { // percorrer de start até end    	 
    	int temp = a[i];
    	int j;
    	for (j = i-1 ; j >= start && a[j] > temp; j--) {
    	//for (j = start; j <= i-1; j++) {
    		a[j+1] = a[j];
    	}
    	a[j+1] = temp;
    }
  }
}

