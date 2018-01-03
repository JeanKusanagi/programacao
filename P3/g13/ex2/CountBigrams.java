package g13.ex2;

import java.util.Map;
import java.util.TreeMap;

/**
 * CountBigrams
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */
public class CountBigrams {

	// Instance Fields
	private Map<String, Map<String, Integer>> bigrams;

	// CTORs
	public CountBigrams() {
		bigrams = new TreeMap<>();
	}

	/**
	 * @param w1
	 * @param w2
	 */
	public void addBigram(String w1, String w2) {
		if (bigrams.containsKey(w1)) {
			Map<String, Integer> bigram = bigrams.get(w1);
			if (bigram.containsKey(w2)) bigram.put(w2, bigram.get(w2) + 1);
			else bigram.put(w2, 1);
		} else
			bigrams.put(w1, createEntry(w2));
	}

	/**
	 * @param w2
	 * @return
	 */
	private static Map<String, Integer> createEntry(String w2) {
		Map<String, Integer> bigram = new TreeMap<>();
		bigram.put(w2, 1);
		return bigram;
	}

	@Override
	public String toString() {
		return bigrams.toString();
	}


}
