package nsgl.real.array;

public class Util {
    /**
     * Creates a double array of size <i>n</i> with the same value in each compoment
     * @param n Size of the array to be created
     * @param value Value that will be copied in each position of the array
     * @return double[]
     */
    public static double[] create(int n, double value) {
    double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = value;
        }
        return x;
    }

    /**
     * Reverses the given array
     * @param a Double array to be reversed
     */
    public static void invert(double[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            double tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }

    /**
     * Normalizes the array to the interval [0,1] using the sum of the values in the array as the maximum value
     * (precondition: Values in the array should be non negatives and at least one value should be different of zero
     * @param x Array to be normalized
     */
    public static void normalize( double[] x ){
    int n = x.length;
        double sum = 0.0;
        for( int i=0; i<n; i++ ){
            sum +=  x[i];
        }
        if( !nsgl.real.Util.isZero(sum) ){
            for (int i = 0; i < n; i++) {
                x[i] /= sum;
            }
        }
    }
    
    public static int[] create( int n, int value ){
    	int[] x = new int[n];
    	for( int i=0; i<n; i++ ) x[i] = value;
    	return x;
    }
    
    public static double max(double[] a){
        double m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m < a[i] ){
                m = a[i];
            }
        }
        return m;
    }
    
    public static double min(double[] a){
        double m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m > a[i] ){
                m = a[i];
            }
        }
        return m;
    }   
        
    /**
     * Casts an array of Doubles to an array of doubles
     * @param x Array of Doubles
     * @return Array of doubles
     */
    public static double[] cast( Double[] x ){
    	double[] y = new double[x.length];
    	for( int i=0; i<y.length; i++ )
    		y[i] = x[i];
    	return y;
    }

    /**
     * Casts an array of Doubles to an array of doubles
     * @param x Array of Doubles
     * @return Array of doubles
     */
    public static Double[] cast( double[] x ){
    	Double[] y = new Double[x.length];
    	for( int i=0; i<y.length; i++ )
    		y[i] = x[i];
    	return y;
    }
}
