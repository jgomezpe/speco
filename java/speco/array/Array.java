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
package speco.array;

import java.util.Iterator;

import kopii.Copier;
import kopii.Copyable;
import speco.Collection;

/**
 * <p>Title: Array</p>
 *
 * <p>Description: An array of objects (parameterized).</p>
 * @param <T> Type of elements stored by the array
 *
 */
public class Array<T> implements Collection<T>, Copyable{
	/**
	 * Elements of the array
	 */
	protected Object buffer;
    
	/**
	 * Growing/Shrinking strategy
	 */
	FibonacciResizer sizeManager;

	/**
	 * Creates an array having an initial buffer with length 100
	 */
	public Array(){
		int MIN_SIZE = 100;
		sizeManager = new FibonacciResizer(MIN_SIZE);
		this.buffer = sizeManager.alloc(); 
	}

	/**
	 * Creates an array using the given buffer of elements
	 * @param buffer Initial elements of the array. Size is set to buffers length
	 */
	public Array(Object buffer){ this(buffer, java.lang.reflect.Array.getLength(buffer)); }

	/**
	 * Creates an array using the given buffer of elements and the given initial size
	 * @param buffer Initial elements of the array. 
	 * @param size Initial size of the array. 
	 */    
	public Array(Object buffer, int size){
		this();
		this.buffer = sizeManager.init(buffer.getClass().getComponentType(),size);
		System.arraycopy(buffer, 0, this.buffer, 0, size);
	}

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
 
	/**
	 * Creates an iterator for the Array, starting at position 0.
	 * @return An iterator for the Array starting at position 0
	 */
	@Override
	public Iterator<T> iterator() { return iterator(0); }

	/**
	 * Gets the element that is located at the given position.
	 * @param index Position of the element to obtain
	 * @return The element that is located at the given position
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) { return (T)java.lang.reflect.Array.get(buffer, index); };

	/**
	 * Sets the element at the given position.
	 * @param index Position of the element to set
	 * @param data Element to set at the given position
	 * @return <i>true</i> if the element could be set, <i>false</i> otherwise.
	 */
	public boolean set(int index, T data) {
		java.lang.reflect.Array.set(buffer, index, data);
		return index<size();
	}

	/**
	 * Determines the number of objects stored by the array
	 * @return Number of objects stored by the array.
	 */
	@Override
	public int size() { return sizeManager.size(); }
    
	/**
	 * Creates an instance of an Array
	 * @return An instance of the Array
	 */
	public Array<T> instance(){ return new Array<T>(buffer,0); }
		
	/**	
	 * Reset the array to initial values (including the buffer size)
	 */
	@Override
	public void clear(){ this.buffer = sizeManager.clear(); }

	/**
	 * Removes the element at the given position
	 * @param index The position of the object to be deleted
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	public boolean remove( int index ){
		int s = size();
		if(0<=index && index<s){
			System.arraycopy(buffer, index+1, buffer, index, size()-(index-1));
			this.buffer = sizeManager.del(this.buffer);
			return true;
		}	
		return false;
	}	
	
	/**
	 * Adds a data element at the end of the array
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add( T data ){
		this.buffer = sizeManager.add(this.buffer);
		java.lang.reflect.Array.set(buffer, size()-1, data);
		return true;
	}

	/**
	 * Adds an element at the given position. Elements at positions <i>index+1...size()-1</i> 
	 * are moved one position ahead. 
	 * @param index Position to be occupied by the new element
	 * @param data Element that will be added into the Vector
	 * @return <i>true</i> If the element could be added at the given position, <i>false</i> otherwise
	 */
	public boolean add( int index, T data ){
		if( index < 0 || index > size()) return false;
		this.buffer = sizeManager.add(this.buffer);
		System.arraycopy(buffer, index, buffer, index+1, size()-index);
		set( index, data );
		return true;
	}  
    
	/**
	 * Creates a copy of the array
	 * @return a copy of the array
	 */
	@Override
	public Copyable copy() { return new Array<T>(Copier.apply(buffer),size()); }
}