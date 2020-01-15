package nsgl.integer;

public interface Order extends nsgl.generic.order.Order<Integer> {
    /**
     * Determines if the first Double is less than (in some order) the second Double (one<two)
     * @param one First Double
     * @param two Second Double
     * @return (one<two)
     */
    int compare(int one, int two);

    /**
     * Determines if the first Integer is less than (in some order) the second Integer (one<two)
     * @param one First Integer
     * @param two Second Integer
     * @return (one<two)
     */
    default int compare(Integer one, Integer two){ return compare((int)one, (int)two); }
}