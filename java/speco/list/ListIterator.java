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
import java.util.NoSuchElementException;

/**
 * <p>A List Iterator</p>
 * @param <T> Type of elements stored by the List
 */
class ListIterator<T> implements Iterator<T>{
	/**
	 * Iterator node
	 */
	protected Node<T> node;

	/**
	 * Creates an iterator starting at the given list node
	 * @param node List node for starting the iterator
	 */
	public ListIterator( Node<T> node ){
		this.node = new Node<T>();
		this.node.next = node;
	}

	/**
	 * Determines if there is a new element in the iterator
	 * @return <i>true</i> If there is a new element in the list, <i>false</i> otherwise
	 */
	@Override
	public boolean hasNext(){ return (node.next!=null); }

	/**
	 * Gets the next element in the list
	 * @return Next element in the list
	 */
	@Override
	public T next()  throws NoSuchElementException{
		try{
			node = node.next;
			return node.data;
		}catch( Exception e ){ throw new NoSuchElementException(); }
	}

	/**
	 * Removes the element in the current position of the iterator
	 */
	@Override
	public void remove() {
		if( node != null ){
			if( node.prev != null ) node.prev.next = node.next;

			if( node.next != null ) node.next.prev = node.prev;
		}    
	}    
}