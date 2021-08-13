package speco.list;

import java.util.Iterator;

import kompari.Comparator;
import speco.set.MultiSet;

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
