package nsgl.integer;

import nsgl.integer.random.UniformInt;

/**
 * <p>Set of constants and methods operating on the primitive <i>int</i> data type,
 * for example, for accessing the bit representation of an int </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */


public class Int {

    /**
     * The minimum integer that can be represented
     */
    public static int MIN_INT;

    /**
     * The maximum integer that can be represented
     */
    public static int MAX_INT;

    /**
     * A integer with the lowest bit set in one and the remaining bits set in zero
     */
    public static int LOWEST_BIT = 1;

    /**
     * A integer with the highest bit set in one and the remaining bits set in zero
     */
    public static int HIGHEST_BIT = 1 << 31;

    /**
     * The number of bits used to represent an integer
     */
    public static int INTEGER_SIZE = 32;

    /**
     * Integer used to apply the modulus operation respect to the integer size
     * <p>  x & MOD_MASK = x mod INTEGER_SIZE </p>
     */
    public static int MOD_MASK = 31;

    /**
     * Integer used to apply the division operation respect to the integer size
     * <p>  x >>> DIV_MASK = x div INTEGER_SIZE </p>
     */
    public static int DIV_MASK = 5;

    /**
     * A integer with all the bits set in one
     */
    public static int ONE_BITS = -1;

    /**
     * Initializes the bit array static information (INTEGER_SIZE in bits, MOD_MASK, etc)
     */
    static {
        LOWEST_BIT = 1;
        ONE_BITS = -1;
        INTEGER_SIZE = 0;
        int val = 1;
        while (val != 0) {
            val <<= 1;
            INTEGER_SIZE++;
        }

        MOD_MASK = (INTEGER_SIZE - 1);

        MIN_INT = (1 << MOD_MASK);
        MAX_INT = MIN_INT - 1;

        HIGHEST_BIT = 1 << MOD_MASK;
        DIV_MASK = -1;
        val = INTEGER_SIZE;
        while (val > 0) {
            val >>>= 1;
            DIV_MASK++;
        }
    }

    /**
     * Transforms an unsigned binary number to reflected binary Gray code. For example the integer value
     * 2 is represented in binary code as: 0000.....0010
     * 2 is represented in gray code as: 0000.....0011 (3 in binary)
     * binarytoGray( 2 ) = 3
     * @param num Integer number to be converted to Gray code
     * @return Integer value that represents the value num (in gray binary format)
     */
    public static int binaryToGray(int num) {
        return (num >>> 1) ^ num;
    }

    /**
     * Determines the minimum number of bits required for representing n different values
     * @param n Number of different values
     * @return Number of bits required for representing such n different values
     */
    public static int getBitsNumber(int n) {
        return (int) (Math.log(n) / Math.log(2.0) + 0.9999);
    }

    /**
     * Transforms a reflected binary Gray code number to a unsigned binary number. For example,
     * 0000.....0011 in binary code represents 3, but in Gray code represents 2.
     * grayToBinary( 3 ) = 2
     * @param num Integer number in Gray code that will be converted to Binary code
     * @return Unsigned Integer value that is represented by the Gray value num
     */
    public static int grayToBinary(int num) {
        int k = (INTEGER_SIZE >>> 1);
        int temp = num;
        while (k > 0) {
            temp ^= (temp >>> k);
            k >>>= 1;
        }
        return temp;
    }
    
    /**
     * Reverses the given array
     * @param a Integer array to be reversed
     */
    public static void invert(int[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }
    
    public static int max(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m < a[i] ){
                m = a[i];
            }
        }
        return m;
    }
    
    public static int min(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m > a[i] ){
                m = a[i];
            }
        }
        return m;
    }   
    
    /**
     * Sorts (low to high) an array of integers using bubble sort
     * @param a Integer array to be sorted
     */
    public static void bubble(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
    }

    /**
     * Sorts (low to high) an array of integers using merge sort
     * @param a Integer array to be sorted
     */
    public static void merge(int[] a) {
        int n = a.length;
        if (n >= 4) {
            int nizq = n / 2;
            int nder = n - nizq;
            int[] aIzq = new int[nizq];
            int[] aDer = new int[nder];
            for (int i = 0; i < nizq; i++) {
                aIzq[i] = a[i];
            }
            for (int i = 0; i < nder; i++) {
                aDer[i] = a[nizq + i];
            }
            merge(aIzq);
            merge(aDer);
            int k = 0;
            int izq = 0;
            int der = 0;
            while (izq < nizq && der < nder) {
                if (aIzq[izq] < aDer[der]) {
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
            bubble(a);
        }
    }    

    public static int[] create( int n, int value ){
    	int[] x = new int[n];
    	for( int i=0; i<n; i++ ) x[i] = value;
    	return x;
    }
    
    public static int[] random( int n, int max ){
    	UniformInt g = new UniformInt(max);
    	return g.generate(n);
    }  
    
    public static Integer[] cast( int[] a ){
    	Integer[] v = new Integer[a.length];
    	for( int i=0; i<a.length; i++ ) v[i] = a[i];
    	return v;
    }
    
    /**
     * Casts an array of Doubles to an array of doubles
     * @param x Array of Doubles
     * @return Array of doubles
     */
    public static int[] cast( Integer[] x ){
    	int[] y = new int[x.length];
    	for( int i=0; i<y.length; i++ )
    		y[i] = x[i];
    	return y;
    }    
}
