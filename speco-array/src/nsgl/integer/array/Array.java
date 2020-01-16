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
package nsgl.integer.array;

import java.util.Iterator;

import nsgl.generic.collection.Indexed;
import nsgl.integer.IntInterval;
import nsgl.generic.Sized;

/**
 * <p>Title: Array</p>
 *
 * <p>Description: A dynamic array of objects (parameterized).</p>
 *
 */
public class Array implements Indexed<Integer, Integer>, Sized, Cloneable{
	protected int[] buffer=null;

	/**
	 * Default constructor
	 */
	public Array(){ buffer = new int[0]; }
	
	/**
	 * Creates an Array with the given buffer
	 * @param buffer Array of values used for initializing the Array 
	 */
	public Array( int[] buffer ) { this.buffer = buffer; }
	
	/**
	 * Creates an Array using the first <i>s</i> values of <i>buffer</i>
	 * @param s Number of elements taken from the given buffer (the first <i>s</i> elements are taken)
	 */
	public Array( int s ){ this.buffer = new int[s]; }

	@Override
	public Object clone(){ return new Array( buffer.clone() ); }
	
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
	public Iterator<Integer> iterator(){ return iterator(0); }
	
	/**
	 * Obtains the size of the array
	 * @return Size of the array
	 */
	public int size(){ return buffer.length; }
	
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
	public boolean set( int index, int data ){
		buffer[index] = data;
		return index<size();
	}
	
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
	public int get(int index){ return buffer[index]; }
	
	/**
	 * Creates an iterator for the Array, starting at the given index.
	 * @param start Initial position for the iterator
	 * @return An iterator for the Array starting at the given position
	 */
	public Iterator<Integer> iterator( int start ) {
		return new Iterator<Integer>() {
			protected int pos=start;
			@Override
			public boolean hasNext(){ return pos<size(); }

			@Override
			public Integer next() { return get(pos++); }
		};
	}
	
	@Override
	public boolean insert(Integer index, Integer data) { return false; }

	@Override
	public boolean remove(Integer index) { return remove((int)index); }

	@Override
	public boolean set(Integer index, Integer data){ return set((int)index,(int)data); }

	@Override
	public boolean valid(Integer index) { return 0<=index && index<size(); }

	@Override
	public Integer get(Integer index) {	return get((int)index); }		
	
	/**
	 * Determines if the collection is empty or not
	 * @return <i>true</i> if the collection is empty <i>false</i> otherwise
	 */
	public boolean isEmpty(){ return size()==0; }	
	
	@Override
	public Iterable<Integer> locations() { return new IntInterval(size()); }      
}