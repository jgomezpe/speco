package nsgl.generic.array;

import nsgl.generic.Cleanable;
import nsgl.generic.collection.Growable;

public abstract class Dynamic<T> extends Array<T> implements Growable<T>, Cleanable{
	public static final int DEFAULTSIZE = 89;

	public Dynamic() { super(0); }

	public Dynamic(Object buffer) {
		super(buffer);
	}

	public Dynamic(Object buffer, int s) {
		super(buffer);
		this.size = s;
	}

	@Override
	public void clear(){ size = 0; }

	
	/**
	 * Removes the element at the given position
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean removed = v.remove(3); // removed=true and v = [5, 1, 2, 9, 0, 9, 5] 
	 * System.out.println( ":" + removed); // Prints true
	 * removed = v.remove(0); // removed=true and v = [1, 2, 9, 0, 9, 5]
	 * System.out.println( ":" + removed);  // Prints true
	 * removed = v.del(8); // removed=false and v = [1, 2, 9, 0, 9, 5]
	 * System.out.println( ":" + removed);  // Prints false
	 * }
	 * </pre>
	 * @param index The position of the object to be deleted
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean remove( int index ){
		if(0<=index && index<size() && ready4Remove()){
			leftShift( index );
			return true;
		}	
		return false;
	}	
	
	/**
	 * Inserts a data element at the end of the array
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9 ]  
	 * boolean added = v.add(0); // added=true and v = [5, 1, 2, 8, 9, 0] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(9); // added=true and v = [5, 1, 2, 8, 9, 0, 9] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(5); // added=true and v = [5, 1, 2, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + added); // Prints true
	 * }
	 * </pre>
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add( T data ){
		init( data.getClass() );
		if( ready4Add() ) {
			set( size(), data );
			size++;
			return true;
		}else return false;
	}

	/**
	 * Adds an element at the given position. Elements at positions <i>index+1...size()-1</i> are moved one position ahead. 
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean added = v.add(2, 8); // added=true and v = [5, 1, 8, 2, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(0, 4); // added=true and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(10, 7); // added=true and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5, 7] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(-1, 6); // added=false and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5, 7] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(15, 6); // added=false and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5, 7, null, null, null, null, 6] 
	 * System.out.println( ":" + added); // Prints true
	 * }
	 * </pre>
	 * @param index Position to be occupied by the new element
	 * @param data Element that will be added into the Vector
	 * @return <i>true</i> If the element could be added at the given position, <i>false</i> otherwise
	 */
	public boolean add( int index, T data ){
		init( data.getClass() );
		if( index < 0 || index > size() || !ready4Add() ) return false;
		rightShift(index);
		set( index, data );
		return true;
	}
	
	/**
	 * Increases the size of the buffer according to the associated Fibonacci numbers (new buffer size will be <i>b+c</i>)
	 */
	protected abstract boolean ready4Add();

	/**
	 * Decreases the size of the buffer according to the associated Fibonacci numbers (new buffer size will be <i>b</i>)
	 */
	protected boolean ready4Remove() { return size()>0; }
	
	protected int initSize() { return DEFAULTSIZE; }
	
	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		System.arraycopy(buffer, index+1, buffer, index, size-index);
	}
	
	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		System.arraycopy(buffer, index, buffer, index+1, size-index);
		size++;
	}	
}
