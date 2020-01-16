package nsgl.generic.array.sort;

import nsgl.generic.array.Sort;
import nsgl.order.Order;

/**
 * <p>BubbleSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Bubble<T> extends Sort<T> {

    /**
     * Creates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Bubble(Order order) { super(order); }

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public Bubble(Order order, int start, int end){ super(order, start, end ); }

    /**
     * Sorts a portion of the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    @Override
	protected void apply(T[] a, int start, int end, Order order) {
		for(int i = start; i < end - 1; i++)
			for(int j = i + 1; j < end; j++)
				if(order.compare(a[j], a[i])<0) {
					T x = a[i];
					a[i] = a[j];
					a[j] = x;
				}
	}
}