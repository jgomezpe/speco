/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package speco.real;

/**
 * <p>Title: RealUtil</p>
 *
 * <p>Description: Set of constants and methods operating on the primitive <i>double</i> data type,
 * for example, for defining the precision in math operations.</p>
 *
 */
public class RealUtil {
	/**
	 * Casts an object to double if possible 
	 * @param x Object to cast
	 * @return The double value represented by the object
	 */
	public static double cast( Object x ){
		if( x instanceof Double ) return (Double)x;
		else return (Integer)x;
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
		for( int i=0; i<n; i++ ) sum +=  x[i];

		if( sum>0.0 ) for (int i = 0; i < n; i++) x[i] /= sum;
    }
    
	/**
	 * Computes the maximum of the array
	 * @param a Array to be analyzed
	 * @return Maximum value of the array
	 */
	public static double max(double[] a){
		double m = a[0];
		for( int i=1; i<a.length; i++ )
			if( m < a[i] ) m = a[i];
		return m;
	}
    
	/**
	 * Computes the minimum of the array
	 * @param a Array to be analyzed
	 * @return Minimum value of the array
	 */
	public static double min(double[] a){
		double m = a[0];
		for( int i=1; i<a.length; i++ )
			if( m > a[i] ) m = a[i];
		return m;
	}   

	/**
	 * Creates an array of size <i>n</i> filled with value <i>v</i>
	 * @param n Size of the array
	 * @param v Value used for filling the array
	 * @return An array of size <i>n</i> filled with value <i>v</i>
	 */	
	public static double[] create(int n, double v) {
		double[] x = new double[n];
		for( int i=0; i<n; i++ ) x[i] = v;
		return x;
	}
}