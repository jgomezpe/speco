package nsgl.integer.list;

import java.util.Iterator;

import nsgl.generic.collection.Growable;

/**
 * <p>Title: CQueue</p>
 * <p>Description: A circular queue.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class CQueue implements Growable<Integer>{
	/**
	 * The cursor of the circular queue (current position)
	 */
	private Node start = null;

	/**
	 * Constructor: Creates a empty circular queue
	 */
	public CQueue() { }

	/**
	 * Advances in the circular one position.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.next() =>  C = b -> c -> d -> a --> b and returns b</p>
	 * @return The object at the circular queue cursor position after advancing one position. Returns null if the queue is empty
	 */
	public int next() {
		start = start.next;
		return start.data;
	}

	/**
	 * Advances in the circular queue n positions.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.advance( 2 ) =>  C = c -> d -> a -> b --> c and returns c</p>
	 * @param n The number of positions to advance
	 * @return The object at the circular queue cursor position after advancing n positions. Returns null if the queue is empty
	 */
	public int advance(int n) {
		for (int i = 0; i < n; i++) start = start.next;
		return start.data;
	}

	/**
	 * Backs in the circular one position.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.prev( ) =>  C = d -> a -> b -> c --> d and returns d</p>
	 * @return The object at the circular queue cursor position after backing one position. Returns null if the queue is empty
	 */
	public int prev() {
		start = start.prev;
		return start.data;
	}

	/**
	 * Adds a new object to the circular queue. The object is added after the current object in the queue. The cursor is not moved.
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.add( x ) =>  C = a -> x -> b -> c -> d --> a</p>
	 * @param data The object to be added
	 */
	public boolean add(int data) {
		if (isEmpty()) {
			start = new Node(data);
			start.prev = start;
			start.next = start;
		} else {
			Node aux = new Node(data);
			aux.next = start.next;
			aux.next.prev = aux;
			start.next = aux;
			aux.prev = start;
		}
		return true;
	}

	/**
	 * Deletes the current object in the circular queue.
	 * The new current object is the next to the old current object
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.del() =>  C = b -> c -> d --> b</p>
	 */
	public boolean del() {
		if (!isEmpty()) {
			if (start.next == start) {
				start = null;
			} else {
				start.prev.next = start.next;
				start.next.prev = start.prev;
        //start = start.prev;
				start = start.next;
			}
			return true;
		}
		return false;
	}

	/**
	 * Gets the current object in the circular queue. If the circular queue is empty return null
	 * <p>C = a -> b -> c -> d --> a</p>
	 * <p>C.get() = a</p>
	 * @return The cursor of the circular queue
	 */
	public int get(){ return start.data; }
	
	@Override
	public Iterator<Integer> iterator(){ return new ListIterator(start); }	

	/**
	 * Determines if the circular queue is empty or not.
	 * @return True if the queue is empty, false in other case
	 */
	@Override
	public boolean isEmpty() { return (start == null); }


	@Override
	public boolean add(Integer data) { return add((int)data); }	
}