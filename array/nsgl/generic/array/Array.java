package nsgl.generic.array;

import java.util.Iterator;

import nsgl.copy.Copier;
import nsgl.copy.Copyable;
import nsgl.generic.Sized;
import nsgl.generic.collection.Indexed;
import nsgl.integer.Interval;

public class Array<T> implements Indexed<Integer,T>, Sized, Copyable{
	protected int size;
	protected Object buffer;
	
	public Array( int size ) { 
		this.size = size; 
		this.buffer = null;
	}

	public Array( Object buffer ) {
		this.size = java.lang.reflect.Array.getLength(buffer);
		this.buffer = buffer;
	}

	protected int initSize() { return size; }	

	protected Object copy(int n) {
		if( buffer != null ) {
			Object nbuffer = java.lang.reflect.Array.newInstance(buffer.getClass().getComponentType(),n);
			System.arraycopy(buffer, 0, nbuffer, 0, Math.min(n, java.lang.reflect.Array.getLength(buffer)));
			return nbuffer;
		}else return null;
	}
	
	protected void init( Class<?> cl ) {
		if( buffer == null ) {
			if( cl.getName().equals("java.lang.Integer") ) buffer = new int[initSize()];
			else if( cl.getName().equals("java.lang.Double") ) buffer = new double[initSize()];
			else if( cl.getName().equals("java.lang.Long") ) buffer = new long[initSize()];
			else if( cl.getName().equals("java.lang.Character") ) buffer = new char[initSize()];
			else if( cl.getName().equals("java.lang.Byte") ) buffer = new byte[initSize()];
			else  buffer = new Object[initSize()];	
		}
	}
	
	public Array<T> instance(int n){
		if( buffer != null ) return new Array<T>(java.lang.reflect.Array.newInstance(buffer.getClass().getComponentType(),n));
		return new Array<T>(n);
	}
	
	public Array<T> instance(){ return instance(0); }

	@SuppressWarnings("unchecked")
	@Override
	public Object copy() {
		Array<T> a = (Array<T>)instance(size());
		for( int i=0; i<size(); i++ ) a.set( i, (T)Copier.apply(get(i)));
		return a;
	}

	/**
	 * Determines if the collection is empty or not
	 * @return <i>true</i> if the collection is empty <i>false</i> otherwise
	 */
	public boolean isEmpty(){ return size()==0; }
	
	@Override
	public int size() { return size; }

	
	@Override
	public boolean valid(Integer index) { return valid((int)index); }
	public boolean valid( int index ) { return 0<=index && index<size(); }
	
	@Override
	/**
	 * Gets the element that is located at the given position.
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * Integer k = v.obtain(3);  
	 * System.out.println( ":" + k); // Prints 8
	 * k = v.obtain(0);
	 * System.out.println( ":" + k);  // Prints 5
	 * k = v.obtain(-1); // Throws a NooSuchElementException 
	 * k = v.obtain(8); // Throws a NooSuchElementException 
	 * }
	 * </pre>
	 * @param index Position of the element to obtain
	 * @return The element that is located at the given position
	 * @throws NoSuchElementException If the index is a non valid position
	 */
	public T get(Integer index) { return get((int)index); };
	
	@SuppressWarnings("unchecked")
	public T get(int index) { return (T)java.lang.reflect.Array.get(buffer, index); };
	
	@Override
	/**
	 * Sets an element to a given position. The position must be a non negative and less than the Vector's size. 
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean set = v.set(2, 8); // set=true and v = [5, 1, 8, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + set); // Prints true
	 * set = v.set(6, 7); // set=true and v = [5, 1, 8, 8, 9, 0, 7, 5]
	 * System.out.println( ":" + set); // Prints true
	 * set = v.set(-1, 3); // set=false and v = [5, 1, 8, 8, 9, 0, 7, 5]
	 * System.out.println( ":" + set); // Prints false
	 * set = v.set(8, 4); // set=false and v = [5, 1, 8, 8, 9, 0, 7, 5]
	 * System.out.println( ":" + set); // Prints false
	 * }
	 * </pre>
	 * @param index Position where the element will be located
	 * @param data Element to set in the Vector
	 * @return <i>true</i> if the element could be set at the given position, <i>false</i> otherwise
	 */
	public boolean set( Integer index, T data ){
		init( data.getClass() );
		java.lang.reflect.Array.set(buffer, index, data);
		return index<size();
	}
	
	@Override
	public boolean remove(Integer index) { return remove((int)index); }
	public boolean remove( int index ) { return false; }

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
	public Iterator<T> iterator(){ return iterator(0); }
	
	/**
	 * Creates an iterator for the Array, starting at the given index.
	 * @param start Initial position for the iterator
	 * @return An iterator for the Array starting at the given position
	 */
	public Iterator<T> iterator( int start ) {
		return new Iterator<T>() {
			protected int pos=start;
			@Override
			public boolean hasNext(){ return pos<size(); }

			@Override
			public T next() { return get(pos++); }
		};
	}
	
	@Override
	public Iterable<Integer> locations() { return new Interval(size()); }
	
	public static void main( String[] args ) {
		Array<String> a = new Array<String>(100);
		a.set(0, "12");
		System.out.println(a.get(0));
		Array<Double> b = new Array<Double>(100);
		b.set(0, 12.0);
		System.out.println(b.get(0));
	}
}