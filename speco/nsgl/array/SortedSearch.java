package nsgl.generic.array;

import nsgl.generic.array.sort.Merge;
import nsgl.integer.L2HOrder;
import nsgl.order.Order;

/**
 * <p>Searching algorithm for sorted arrays of objects</p>
 * 
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class SortedSearch {
	protected Order order;
	protected Object sorted;
	
    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     */
	public SortedSearch(Order order){ this.order = order; }
	
    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     */
	public SortedSearch(Array<?> sorted, Order order){ this( sorted.buffer, order ); }
	
    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     * @param order Order used for locating the object
     */
	public SortedSearch(Object sorted, Order order){
		this.order = order;
		this.sorted = sorted;
	}
	
	public void set( Object sorted ){ this.sorted = sorted; } 

	public void set( Array<?> sorted ){ this.sorted = sorted.buffer; } 

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(Object x) { return find( 0, java.lang.reflect.Array.getLength(sorted), x ); }

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(int start, int end, Object x) {
        int pos = findRight(start, end, x);
        if (pos > start && order.compare(x, java.lang.reflect.Array.get(sorted,pos - 1)) == 0) pos--;
        else pos = -1;
        return pos;
    }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(int start, int end, Object x){ return (find(start, end, x) != -1); }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(Object x) { return (find(x) != -1); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(Object x) { return findRight( 0, java.lang.reflect.Array.getLength(sorted), x ); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(int start, int end, Object x){ 
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (order.compare(x, java.lang.reflect.Array.get(sorted,a)) < 0)  return start;
            if (order.compare(x, java.lang.reflect.Array.get(sorted,b)) >= 0) return end;
            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (order.compare(x, java.lang.reflect.Array.get(sorted,m)) < 0) b = m;
                else a = m;
            }
            return b;
        }else return start;
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(Object x){
    	return findLeft( 0, java.lang.reflect.Array.getLength(sorted), x ); 
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(int start, int end, Object x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (order.compare(x, java.lang.reflect.Array.get(sorted,a)) <= 0)  return start-1;
            if (order.compare(x, java.lang.reflect.Array.get(sorted,b)) > 0) return b;
            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (order.compare(x, java.lang.reflect.Array.get(sorted,m)) <= 0) b = m;
                else a = m;
            }
            return a;
        }else return start;
    }
    
    public static void main( String[] args ) {
    	Order order = new L2HOrder();
    	Merge sort = new Merge(order);
    	int[] x = new int[] {3,1,5,9,7,13,11,15,4,2,8,6,0,12,10,16,14,18};
    	sort.apply(x, 0, x.length, order);
    	for( int i=0; i<x.length; i++)
    		System.out.println(x[i]);
    	SortedSearch search = new SortedSearch(x,order); 
    	for( int i=0; i<x.length; i++ ) {
    		System.out.println(x[i] + ":" + search.find(x[i]));
    	}
    } 
    
}