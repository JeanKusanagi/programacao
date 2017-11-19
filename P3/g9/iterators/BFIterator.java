package g9.iterators;

/**
 * BFIterator
 * 
 * @author Pedro Teixeira, 84715, MIECT
 */

public interface BFIterator<T> {
	boolean hasPrevious();
	T previous();
	boolean hasNext();
	T next();
}