package nsgl.generic.array;

import java.util.Iterator;

import nsgl.generic.Sized;
import nsgl.generic.collection.Indexed;
import nsgl.integer.IntInterval;

public interface ArrayInterface<T> extends Indexed<Integer,T>, Sized{
	/**
	 * Creates an iterator for the Array. The Array can be traversed using a for each approach.
	 * <pre>
	 *	{@code
	 * // Suppose that a is an Array<Integer> 
	 * // The next for each loop will print every element in a  
	 * for( Integer k : a )   
	 *   System.out.print( " " + k);
	 * }
	 * </pre>
	 * @return An iterator for the Array.
	 */
	@Override
	default Iterator<T> iterator(){ return iterator(0); }
	
	/**
	 * Creates an iterator for the Array, starting at the given index.
	 * @param start Initial position for the iterator
	 * @return An iterator for the Array starting at the given position
	 */
	default Iterator<T> iterator( int start ) {
		return new Iterator<T>() {
			protected int pos=start;
			@Override
			public boolean hasNext(){ return pos<size(); }

			@Override
			public T next() { return get(pos++); }
		};
	}
	
	/**
	 * Determines if the collection is empty or not
	 * @return <i>true</i> if the collection is empty <i>false</i> otherwise
	 */
	default boolean isEmpty(){ return size()==0; }

	@Override
	default boolean valid(Integer index) { return 0<=index && index<size(); }
	
	@Override
	default Iterable<Integer> locations() { return new IntInterval(size()); }
	
	@Override
	default boolean insert(Integer index, T data) { return false; }
}