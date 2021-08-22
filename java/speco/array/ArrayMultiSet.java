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

import kompari.Comparator;
import speco.set.MultiSet;

/**
 * <p>Title: ArrayMultiSet</p>
 *
 * <p>Description: A multi-set implementation using arrays.</p>
 * @param <T> Type of elements stored by the ArrayMultiSet
 *
 */
public class ArrayMultiSet<T> implements MultiSet<Integer, T> {
	/**
	 * Array maintaining the set elements 
	 */
	protected Array<T> array;
    
	/**
	 * Creates a set using an Array for maintaining the elements  
	 */
	public ArrayMultiSet() { this.array=new Array<T>(); }
 
	/**
	 * Creates a set using the Array for maintaining the elements
	 * @param array Array maintaining the elements
	 */
	public ArrayMultiSet( Array<T> array ) { this.array=array; }

	/**
	 * Gets the associated index (key) of the first appearance (in some order) of the given object.
	 * @param data Object from which an associated index will be returned.
	 * @return The associated index of the first appearance of the object, <i>null</i> otherwise.
	 */
	@Override
	public Integer first(T data) {
		int i=0;
		while( i<array.size() && Comparator.NE(data, array.get(i))) i++;
		if( i<array.size() ) return i;
		return null;
	}

	/**
	 * Gets the associated index (key) of the first appearance (in some order) of the given object.
	 * @param data Object from which an associated index will be returned.
	 * @return The associated index of the first appearance of the object, <i>null</i> otherwise.
	 */
	@Override
	public Integer last(T data) {
		int i=array.size()-1;
		while( i>=0 && Comparator.NE(data, array.get(i))) i--;
		if( i>=0 ) return i;
		return null;
	}
	
	/**
	 * Gets an iterable version of the collection of indices associated to a given object
	 * @param data Object from which associated indices will be returned.
	 * @return An iterable version of the collection of indices associated to a given object
	 */
	@Override
	public Iterator<Integer> get(T data) {
		return new Iterator<Integer>() {
			protected int pos=0;
			@Override
			public boolean hasNext() {
				while(pos<array.size() && Comparator.NE(data, array.get(pos))) pos++;
				return pos<array.size();
			}

			@Override
			public Integer next() {
				if(pos<array.size()) return pos++;
				return null;
			} 
		};
	}

	/**
	 * Adds a data element to the set
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	@Override
	public boolean add(T data) { return array.add(data); }
    
	/**
	 * Removes completely (all copies) the given data from the associated collection 
	 * @param data Object to be removed from the associated collection
	 * @return <i>true</i> if the element was in the multiset and could be (completely) removed, <i>false</i> otherwise
	 */
	@Override
	public boolean remove(T data) {
		int s = array.size();
		int pos = 0;
		while(pos<array.size()) {
			if( Comparator.EQ(data, array.get(pos)) ) array.remove(pos);
			else pos++;
		}
		return s>array.size();
	} 
    
	/**
	 * Removes the first appearance (inside the set)  the given data from the associated collection 
	 * @param data Object to be removed from the associated collection
	 * @return <i>true</i> if the element was in the multiset and could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean removeFirst(T data) {
		Integer pos = first(data);
		if(pos != null) {
			array.remove(pos);
			return true;
		}
		return false;
	}

	/**
	 * Removes the last appearance (inside the set)  the given data from the associated collection 
	 * @param data Object to be removed from the associated collection
	 * @return <i>true</i> if the element was in the multiset and could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean removeLast(T data) {
		Integer pos = last(data);
		if(pos != null) {
			array.remove(pos);
			return true;
		}
		return false;
	}

	/**
	 * Gets an iterable version of the set
	 * @return An iterable version of the set
	 */
	@Override
	public Iterator<T> iterator() { return array.iterator(); }
    
	/**
	 * Determines the number of objects stored by the array
	 * @return Number of objects stored by the array.
	 */
	@Override
	public int size() { return array.size(); }
     
	/**	
	 * Reset the array to initial values (including the buffer size)
	 */
	@Override
	public void clear(){ array.clear(); }
}