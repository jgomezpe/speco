package nsgl.integer.array.sort;

import nsgl.integer.Order;
import nsgl.integer.array.Sort;

/**
 * <p>InsertionSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Insertion extends Sort {
    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Insertion(Order order) { super(order); }

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public Insertion(Order order, int start, int end){ super(order, start, end ); }

  /**
   * Sorts a vector of objects using Insertion sort
   * @param a array to be sorted
   */
	public void apply(int[] a, int start, int end, Order order) {
		for (int i = start; i < end; i++) {
			int j = i - 1;
			int value = a[i];
			while(j >= start && order.compare(value, a[j])<0) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = value;
		}
	}
}
