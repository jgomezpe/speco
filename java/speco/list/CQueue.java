package speco.list;

import java.util.Iterator;

/**
 * <p>Title: CQueue</p>
 * <p>Description: A circular queue.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class CQueue<T> implements Iterable<T>{
	/**
	 * The cursor of the circular queue (current position)
	 */
	protected Node<T> start = null;

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
			if (start.next == start) {
				start = null;
			} else {
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

	/*
	@Override
	public Node<T> location(Object data){
	    if( isEmpty() ) return null;
	    Node<T> x = start;
	    while(x.next!=start && Comparator.NE(start.data,x.data)) x = x.next;
	    if( Comparator.NE(start.data,x.data) ) return null;
	    return x;
	}*/

	public int size() { return size; }
}