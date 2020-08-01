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
public class Bubble extends Sort {

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
	public void apply(Object a, int start, int end, Order order) {
		for(int i = start; i < end - 1; i++){
			Object x = java.lang.reflect.Array.get(a, i);
			for(int j = i + 1; j < end; j++) {
				Object y = java.lang.reflect.Array.get(a, j);
				if(order.compare(y, x)<0) {
					java.lang.reflect.Array.set(a, i, y);
					java.lang.reflect.Array.set(a, j, x);
					x = y;
				}
			}	
		}	
	}
}