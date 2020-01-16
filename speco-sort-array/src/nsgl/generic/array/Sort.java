package nsgl.generic.array;

import nsgl.order.Order;

/**
 * <p>Abstract Sorting algorithm for Arrays of objects</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class Sort<T>{
    /**
     * Order used for sorting the objects
     */
    protected Order order = null;

    /**
     * start Initial position in the array to be sorted
     */
    protected int start=0;

    /**
     * end Final position in the array to be sorted
     */
    protected int end=-1;

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Sort(Order order) { this.order = order; }

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public Sort(Order order, int start, int end){ 
    	this(order); 
    	this.start = start;
    	this.end = end;
    }

    /**
     * Sets the sorting limits
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public void setLimits(int start, int end) {
    	this.start = start;
    	this.end = end;
    }

    /**
     * Sorts the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     * @param order Order used for sorting the objects
     */
    protected abstract void apply(T[] a, int start, int end, Order order);

    /**
     * Runs the sorting algorithm on the given array and the established order
     * @param input Array to be sorted
     * @return Sorted array
     */
	public T[] apply(T[] input) {
    	apply(input, start, (end==-1)?input.length:end, order);
    	return input;
    }
	
    /**
     * Runs the sorting algorithm on the given array and the established order
     * @param input Array to be sorted
     * @return Sorted array
     */
	public Array<T> apply(Array<T> input){
		apply(input.buffer);
		return input;
	}
}