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
package nsgl.integer.list;
import java.util.Iterator;

import nsgl.generic.Sized;
import nsgl.generic.collection.Growable;
import nsgl.generic.collection.Shrinkable;

/**
 * <p>Title: List</p>
 *
 * <p>Description: A list of objects</p>
 *
 */
public class List implements Growable<Integer>, Shrinkable<Integer>, Sized {
    protected int size = 0;
    protected Node head = null;
    protected Node last = null;

    public List(){}

    /**
     * Removes all the objects in the data structure
     */
    public void clear(){
        head = null;
        last = null;
        size = 0;
    }

    /**
     * Obtains an iterator of the objects in the list
     * @return Iterator of the objects in the list
     */
    @Override
    public Iterator<Integer> iterator(){ return new ListIterator( head ); }


    /**
     * Inserts a data element in the structure
     * @param data Data element to be inserted
     * @return <i>true</i> if the element could be added, <i>false</i> otherwise
     */
    public boolean add(int data) {
        Node aux = new Node(data);
        if (head == null) {
            head = aux;
        } else {
            last.next = aux;
        }
        last = aux;
        size++;
        return true;
    }

    protected boolean del( Node node ){
        boolean flag = (node != null);
        if( flag ){
            size--;
            if( node.prev != null ){
                node.prev.next = node.next;
            }else{
                head = node.next;
            }
            if( node.next != null ){
                node.next.prev = node.prev;
            }else{
                last = node.prev;
            }
        }
        return flag;
    }

    /**
     * Removes the element indicated by the locator
     * @param locator The location information of the object to be deleted
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    public boolean remove( Node locator ){ return del( locator ); }

    /**
     * Removes a data element from the structure
     * @param data Data element to be removed
     * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
     */
    public boolean del(int data){
        Node aux = head;
        while( aux != null && data != aux.data ){ aux = aux.next; }
        return del( aux );
    }

	/**
	 * Locates the given object in the structure
	 * @param data Data object to be located
	 * @return A data object if the object belongs to the data structure, <i>false</i> otherwise
	 */
	public Node find( int data ){
		Node aux = head;
		while( aux != null && data != aux.data ){ aux = aux.next; }
		return aux;
	}

	public int get(){ return head.data; }
	
	@Override
	public boolean isEmpty() { return size()==0; }

    /**
     * Determines the number of objects stored by the data structure
     * @return Number of objects stored by the data structure
     */
	@Override
    public int size(){ return size; }


	@Override
	public boolean del(Integer data){ return del( (int)data ); }

	@Override
	public boolean add(Integer data) { return add((int)data); }
	
}