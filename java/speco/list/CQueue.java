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

/**
 * <p>Title: CQueue</p>
 * <p>Description: A circular queue.</p>
 * @param <T> Type of elements stored by the circular queue
 */
public class CQueue<T> implements Iterable<T>{
	/**
	 * The cursor of the circular queue (current position)
	 */
	protected Node<T> start = null;

	/**
	 * Size of the circular queue
	 */
	protected int size=0;
	
	/**
	 * Constructor: Creates a empty circular queue
	 */
	public CQueue() {}
	
	/**
	 * Advances in the circular one position.
	 * <p>C = a -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * <p>C.next() =&gt;  C = b -&gt; c -&gt; d -&gt; a -&gt; b and returns b</p>
	 * @return The object at the circular queue cursor position after advancing one position. Returns null if the queue is empty
	 */
	public T next() {
		start = start.next;
		return start.data;
	}

	/**
	 * Advances in the circular queue n positions.
	 * <p>C = a -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * <p>C.advance( 2 ) =&gt;  C = c -&gt; d -&gt; a -&gt; b -&gt; c and returns c</p>
	 * @param n The number of positions to advance
	 * @return The object at the circular queue cursor position after advancing n positions. Returns null if the queue is empty
	 */
	public T advance(int n) {
		for (int i = 0; i < n; i++) start = start.next;
		return start.data;
	}

	/**
	 * Backs in the circular one position.
	 * <p>C = a -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * <p>C.prev( ) =&gt;  C = d -&gt; a -&gt; b -&gt; c -&gt; d and returns d</p>
	 * @return The object at the circular queue cursor position after backing one position. Returns null if the queue is empty
	 */
	public T prev() {
		start = start.prev;
		return start.data;
	}

	/**
	 * Determines if the collection is empty or not
	 * @return <i>true</i> if the collection is empty <i>false</i> otherwise
	 */
	public boolean isEmpty() { return size()==0; };     
	
	/**
	 * Adds a new object to the circular queue. The object is added after the current object in the queue. The cursor is not moved.
	 * <p>C = a -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * <p>C.add( x ) =&gt;  C = a -&gt; x -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * @param data The object to be added
	 * @return <i>true</i> If the element could be added to the circular queue, <i>false</i> otherwise 
	 */
	public boolean add(T data) {
		if (isEmpty()) {
			start = new Node<T>(data);
			start.prev = start;
			start.next = start;
		} else {
			Node<T> aux = new Node<T>(data);
			aux.next = start.next;
			aux.next.prev = aux;
			start.next = aux;
			aux.prev = start;
		}
		size++;
		return true;
	}

	/**
	 * Deletes the current object in the circular queue.
	 * The new current object is the next to the old current object
	 * <p>C = a -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * <p>C.del() =&gt;  C = b -&gt; c -&gt; d -&gt; b</p>
	 * @return <i>true</i> If the circular queue is not empty and the first element could be removed, <i>false</i> otherwise 
	 */
	public boolean del() {
		if (!isEmpty()) {
			if (start.next == start) start = null;
			else {
				start.prev.next = start.next;
				start.next.prev = start.prev;
				start = start.next;
			}
			size--;
			return true;
		}
		return false;
	}

	/**
	 * Gets the current object in the circular queue. If the circular queue is empty return null
	 * <p>C = a -&gt; b -&gt; c -&gt; d -&gt; a</p>
	 * <p>C.get() = a</p>
	 * @return The cursor of the circular queue
	 */
	public T get(){ return start.data; }

	@Override
	public Iterator<T> iterator(){ return new ListIterator<T>(start); }

	public int size() { return size; }
}