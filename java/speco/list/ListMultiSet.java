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
package speco.list;

import java.util.Iterator;

import kompari.Comparator;
import speco.set.MultiSet;

/**
 * <p>A multi-set implementation using lists.</p>
 * @param <T> Type of elements stored by the ListMultiSet
 *
 */
public class ListMultiSet<T> implements MultiSet<Node<T>,T> {
	/**
	 * List maintaining the set elements 
	 */
	protected List<T> list;
    
	/**
	 * Creates a set with a List for maintaining the elements
	 */
	public ListMultiSet() { this.list=new List<T>(); }

	/**
	 * Creates a set with the provided List for maintaining the elements
	 * @param list List maintaining the set elements
	 */
	public ListMultiSet( List<T> list ) { this.list=list; }

	/**
	 * Gets the associated index (key) of the first appearance (in some order) of the given object.
	 * @param data Object from which an associated index will be returned.
	 * @return The associated index of the first appearance of the object, <i>null</i> otherwise.
	 */
	@Override
		public Node<T> first(T data) {
		Node<T> aux = list.head;
		while( aux != null && Comparator.NE(data, aux.data) ){ aux = aux.next; }
		return aux;
	}

	/**
	 * Gets the associated index (key) of the first appearance (in some order) of the given object.
	 * @param data Object from which an associated index will be returned.
	 * @return The associated index of the first appearance of the object, <i>null</i> otherwise.
	 */
	@Override
	public Node<T> last(T data) {
		Node<T> aux = list.last;
		while( aux != null && Comparator.NE(data, aux.data) ){ aux = aux.prev; }
		return aux;
	}
	
	/**
	 * Gets an iterable version of the collection of indices associated to a given object
	 * @param data Object from which associated indices will be returned.
	 * @return An iterable version of the collection of indices associated to a given object
	 */
	@Override
	public Iterator<Node<T>> get(T data) {
		return new Iterator<Node<T>>() {
			protected Node<T> pos=list.head;
			@Override
			public boolean hasNext() {
				while(pos!=null && Comparator.NE(data, pos.data)) pos = pos.next;
				return pos!=null;
			}

			@Override
			public Node<T> next() {
				if(pos!=null) {
					Node<T> aux = pos;
					pos = pos.next;
					return aux;
				}
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
	public boolean add(T data) { return list.add(data); }
    
	/**
	 * Removes completely (all copies) the given data from the associated collection 
	 * @param data Object to be removed from the associated collection
	 * @return <i>true</i> if the element was in the multiset and could be (completely) removed, <i>false</i> otherwise
	 */
	@Override
	public boolean remove(T data) {
		int c=0;
		Node<T> pos = list.head;
		while(pos!=null) {
			if( Comparator.EQ(data, pos.data) ) {
				Node<T> aux = pos.next;
				list.remove(pos);
				pos = aux;
				c++;
				}else pos = pos.next;
		}
		return c>0;
	} 
    
	/**
	 * Removes the first appearance (inside the set)  the given data from the associated collection 
	 * @param data Object to be removed from the associated collection
	 * @return <i>true</i> if the element was in the multiset and could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean removeFirst(T data) {
		Node<T> pos = first(data);
		if(pos != null) return list.remove(pos);
		return false;
	}

	/**
	 * Removes the last appearance (inside the set)  the given data from the associated collection 
	 * @param data Object to be removed from the associated collection
	 * @return <i>true</i> if the element was in the multiset and could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean removeLast(T data) {
		Node<T> pos = last(data);
		if(pos!=null) return list.remove(pos);
		return false;
	}

	/**
	 * Gets an iterable version of the set
	 * @return An iterable version of the set
	 */
	@Override
	public Iterator<T> iterator() { return list.iterator(); }
    
	/**
	 * Determines the number of objects stored by the set
	 * @return Number of objects stored by the set.
	 */
	@Override
	public int size() { return list.size(); }
     
	/**	
	 * Reset the set to initial values
	 */
	@Override
	public void clear(){ list.clear(); }
}