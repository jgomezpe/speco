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
package nsgl.generic.array;

import nsgl.util.Fibonacci;

/**
 * <p>Title: Array</p>
 *
 * <p>Description: A dynamic array of objects (parameterized).</p>
 *
 */
public class Vector<T> extends Dynamic<T>{
	Fibonacci sizeManager;

	/**
	 * Default constructor
	 */
	public Vector(){
		sizeManager = new Fibonacci(Dynamic.DEFAULTSIZE);
		sizeManager.prev();
	}
	
	/**
	 * Default constructor
	 */
	public Vector(int n){
		sizeManager = new Fibonacci(n);
		sizeManager.prev();
	}
	
	public Vector(Object buffer){ this(buffer, java.lang.reflect.Array.getLength(buffer)); }

	public Vector(Object buffer, int s) {
		super(buffer);
		sizeManager = new Fibonacci(this.size);
		sizeManager.prev();
		this.size = s; 
		resize();
	}

	
	/**
	 * Reset the array to initial values (including the buffer size)
	 */
	@Override
	public void clear(){
		sizeManager.clear();
		resize();
		size = 0;
	}

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	protected void resize( int n ){
		int x = sizeManager.n();
		if( x!=sizeManager.find_fib(n) ) resize();
		size = n; 
	}
	
	/**
	 * Resizes the inner buffer according to the associated Fibonacci numbers (new buffer size must be <i>c</i>)
	 */
	protected void resize() { buffer = copy(sizeManager.n_2()); }

	@Override
	protected int initSize() { return sizeManager.n_2(); }
	
	/**
	 * Increases the size of the buffer according to the associated Fibonacci numbers (new buffer size will be <i>b+c</i>)
	 */
	@Override
	public boolean ready4Add() {
		if( size()==sizeManager.n_2() ){
			sizeManager.next();
			resize();        
		}
		return true;
	}
	
	/**
	 * Decreases the size of the buffer according to the associated Fibonacci numbers (new buffer size will be <i>b</i>)
	 */
	@Override
	public boolean ready4Remove() {
		if(  size() < sizeManager.n() && sizeManager.n()!=sizeManager.prev() ) resize();
		return true;
	}	
	
	public static void main( String[] args ) {
		Vector<Integer> A = new Vector<Integer>();
		for( int i=0; i<1000; i++ ) A.add(i);
		for( int i=0; i<100; i++ ) A.add(i*10, 100000);
		for( int i=0; i<A.size(); i++ ) System.out.println(A.get(i));
		for( int i=0; i<A.size(); i+=10 ) A.remove(i);
		for( int i=0; i<A.size(); i++ ) System.out.println(A.get(i));
	}		
}