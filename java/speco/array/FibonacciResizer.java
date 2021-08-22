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
package speco.array;

/**
 * <p>Title: FibonacciResizer</p>
 *
 * <p>Description: An array resizer following Fibonacci numbers. Controls the underline buffer of an array.</p>
 *
 */
public class FibonacciResizer implements Resizer{
	/**
	 * Current numbers of Fibonacci
	 */
	protected int a=0, b=1, c=1;
	
	/**
	 * Current size of the Array
	 */
	protected int n;
	
	/**
	 * Minimum value for the Fibonacci numbers
	 */
	protected int MIN_A;

	/**
	 * Creates an Array resizer following Fibonacci numbers 
	 * @param min_size Minimum value for the Fibonacci numbers
	 */
	public FibonacciResizer(int min_size) {
		if( min_size < 0 ) min_size = 0;
		MIN_A = min_size;
		init();
		MIN_A = a;
	}
    
	/**
	 * Gets the number of Fibonacci for a minimum size Array
	 */
	protected void init() {
		n = 0;
		while(a<MIN_A) {
			a = b;
			b = c;
			c = a+b;
		}	
	}
    
	/**
	 * Increase the Fibonacci number one step
	 */
	protected void fibo_up() {
		a = b;
		b = c;
		c = a+b;	    	
	}
    
	/**
	 * Initializes an Array Size manager to the given size of the Array
	 * @param cl Class of the Array's elements
	 * @param n Size of the array
	 * @return Inner Array's buffer
	 */
	@Override
	public Object init(Class<?> cl, int n) {
		init();
		while(b<n) { fibo_up(); }
		this.n = n;
		return alloc(cl);
	}

	/**
	 * Adds a new component to the Array's buffer (resizes it if required)
	 * @param buffer Current Array's buffer
	 * @return A new buffer for the Array if required. The same buffer otherwise
	 */
	@Override
	public Object add(Object buffer) {
		n++;
		if(n==c){
			fibo_up();
			buffer = copy(buffer,n);
		}
		return buffer;
	}

	/**
	 * Removes a component from the Array's buffer (resizes it if required)
	 * @param buffer Current Array's buffer
	 * @return A new buffer for the Array if required. The same buffer otherwise
	 */
	@Override
	public Object del(Object buffer) {
		if(n<=a && a>MIN_A) {
			c=b;
			b=a;
			a=c-b;
			buffer = copy(buffer,n);
		}
		if( n>0 ) n--;
		return buffer;
	}

	/**
	 * Gets the size of the buffer of the Array
	 * @return Size of the buffer of the Array
	 */
	@Override
	public int buffer_size() { return c; }

	/**
	 * Gets the size of elements of the Array
	 * @return Size of the Array
	 */
	@Override
	public int size() { return n; }
}