package nsgl.generic.array.sort;

import nsgl.generic.array.Sort;
import nsgl.generic.order.Order;

/**
 * <p>MergeSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Merge<T> extends Sort<T> {

    /**
     * InsertionSort for sorting an array of less than 8 elements
     */
    protected Insertion<T> insertion;

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     */
    public Merge(Order<T> order) { super(order); }

    /**
     * Crates a sorting algorithm with the given order
     * @param order Order used for sorting the objects
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorted
     */
    public Merge(Order<T> order, int start, int end){ super(order, start, end ); }

    /**
     * Sorts a portion of the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorte
     * @return <i>true</i> If the sorting process was done without fails, <i>false</i> otherwise
     */
	protected void apply(T[] a, int start, int end, Order<T> order) {
		insertion = new Insertion<T>(order);
		int i=start;
		while( i<end-1 && order.compare(a[i], a[i+1]) <= 0 ){ i++; }
        if( i<end-1 ){
        	int n = end - start;
        	@SuppressWarnings("unchecked")
        	T[] ca = (T[])new Object[n];
        	System.arraycopy(a, start, ca, 0, n);
        	this.rec_apply( ca );
        	System.arraycopy(ca,0,a,start,n);
        }
    }

    /**
     * Recursive merge sort method
     * @param a Array to be sorted
     */
    public void rec_apply(T[] a) {
        int n = a.length;
        if (n > 7) {
            int nizq = n / 2;
            int nder = n - nizq;
            @SuppressWarnings("unchecked")
			T[] aIzq = (T[])new Object[nizq];
            @SuppressWarnings("unchecked")
			T[] aDer = (T[])new Object[nder];

            System.arraycopy(a, 0, aIzq, 0, nizq );
            System.arraycopy(a, nizq, aDer, 0, nder );
            this.rec_apply(aIzq);
            this.rec_apply(aDer);
            int k = 0;
            int izq = 0;
            int der = 0;
            while (izq < nizq && der < nder) {
                if(order.compare(aIzq[izq], aDer[der]) < 0) {
                    a[k] = aIzq[izq];
                    izq++;
                } else {
                    a[k] = aDer[der];
                    der++;
                }
                k++;
            } while (izq < nizq) {
                a[k] = aIzq[izq];
                izq++;
                k++;
            } while (der < nder) {
                a[k] = aDer[der];
                der++;
                k++;
            }
        } else {
            insertion.apply(a);
        }
    }
}