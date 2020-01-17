/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package nsgl.real.array;

import nsgl.generic.Cleanable;
import nsgl.generic.array.ArrayInterface;
import nsgl.generic.collection.Growable;
import nsgl.util.Fibonacci;

/**
 * <p>Title: Array</p>
 *
 * <p>Description: A dynamic array of objects (parameterized).</p>
 *
 */
public class DynArray extends Array implements Growable<Double>, Cleanable{
	/**
	 * Current size of the array
	 */
	protected int size=0;

	protected Fibonacci sizeManager;
	
	protected static final int DEFAULT_BUFFER_SIZE = 89;
	
	/**
	 * Default constructor
	 */
	public DynArray(){ this( new double[DEFAULT_BUFFER_SIZE], 0 ); }
	
	/**
	 * Creates an Array with the given buffer
	 * @param buffer Array of values used for initializing the Array 
	 */
	public DynArray( double[] buffer ){ this( buffer, buffer.length ); }
	
	/**
	 * Creates an Array using the first <i>s</i> values of <i>buffer</i>
	 * @param buffer buffer with the initial elements of the Array
	 * @param s Number of elements taken from the given buffer (the first <i>s</i> elements are taken)
	 */
	public DynArray( double[] buffer, int s ) {
		sizeManager = new Fibonacci(buffer.length);
		sizeManager.prev();
		this.buffer = buffer;
		this.size = s;
	}
	
	@Override
	public ArrayInterface<Double> instance(int s){ return new DynArray( new double[s], s); }

	@Override
	public Object clone(){ return new DynArray( buffer.clone(), size ); }
	
	/**
	 * Reset the array to initial values (including the buffer size)
	 */
	public void clear(){
		sizeManager.clear();
		resize();
		size = 0;
	}

	/**
	 * Obtains the size of the array
	 * @return Size of the array
	 */
	@Override
	public int size(){ return size; }
	
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
	public boolean remove( int index ){
		if(0<=index && index<size){
			leftShift( index );
			return true;
		}	
		return false;
	}	
	
	// Array specific methods 
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
	public boolean add( double data ){
		if( size==sizeManager.n_2() ) grow();
		buffer[size] = data;
		size++;
		return true;
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
	public boolean add( int index, double data ){
		if( index < 0 ) return false;
		rightShift(index);
		buffer[index] = data;
		return true;
	}
	
	/**
	 * Increases the size of the buffer according to the associated Fibonacci numbers (new buffer size will be <i>b+c</i>)
	 */
	protected void grow(){
		sizeManager.next();
		resize();        
	}

	/**
	 * Decreases the size of the buffer according to the associated Fibonacci numbers (new buffer size will be <i>b</i>)
	 */
	protected void shrink(){ if(sizeManager.n()!=sizeManager.prev()) resize(); }
	
	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize( int n ){
		int x = sizeManager.n();
		if( x!=sizeManager.find_fib(n) ) resize();
		this.size = n; 
	}
	
	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( index < size ) System.arraycopy(buffer, index+1, buffer, index, size-index);
		if( size < sizeManager.n() ) shrink();
	}
	
	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		int n = buffer.length; 
		if( n == size ) grow();
		System.arraycopy(buffer, index, buffer, index+1, size-index-1);
		size++;
	}
	
	/**
	 * Resizes the inner buffer according to the associated Fibonacci numbers (new buffer size must be <i>c</i>)
	 */
	protected void resize() {
		int n = sizeManager.n_2();
		double[] nbuffer = new double[n];
		System.arraycopy(buffer, 0, nbuffer, 0, size);
		buffer = nbuffer;
	}

	@Override
	public boolean add(Double data){ return add((double)data); }	
}