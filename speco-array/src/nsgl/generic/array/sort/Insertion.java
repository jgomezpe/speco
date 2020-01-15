package nsgl.generic.array.sort;

import nsgl.generic.array.Sort;
import nsgl.generic.Order;

/**
 * <p>InsertionSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Insertion<T> extends Sort<T> {
    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Insertion(Order<T> order) { super(order); }

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public Insertion(Order<T> order, int start, int end){ super(order, start, end ); }

  /**
   * Sorts a vector of objects using Insertion sort
   * @param a array to be sorted
   */
	public void apply(T[] a, int start, int end, Order<T> order) {
		for (int i = start; i < end; i++) {
			int j = i - 1;
			T value = a[i];
			while(j >= start && order.compare(value, a[j])<0) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = value;
		}
	}
}
