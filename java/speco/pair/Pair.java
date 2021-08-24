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
package speco.pair;

/**
 * <p>A pair of two objects</p>
 * @param <A> First component type
 * @param <B> Second component type
 *
 */
public class Pair<A,B> implements Cloneable{
	/**
	 * First component of the pair
	 */
	protected A a;
    
	/**
	 * Second component of the pair
	 */
	protected B b;

	/**
	 * Creates a pair
	 * @param a First component of the pair
	 * @param b Second component of the pair
	 */
	public Pair(A a, B b){
		this.a = a;
		this.b = b;
	}

	/**
	 * Gets the first component of the pair
	 * @return First component of the pair
	 */
	public A a(){ return a; }
    
	/**
	 * Gets the second component of the pair
	 * @return Second component of the pair
	 */    
	public B b(){ return b; }

	/**
	 * Sets the first component of the pair
	 * @param a First component of the pair
	 */
	public void a( A a ){ this.a = a; }

	/**
	 * Sets the second component of the pair
	 * @param b Second component of the pair
	 */
	public void b( B b ){ this.b = b; }

	/**
	 * Creates a string version of the pair
	 * @return A string version of the pair
	 */
	public String toString(){ return a.toString()+","+b.toString(); }
}