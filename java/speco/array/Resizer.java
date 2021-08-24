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
 * <p>An array resizer. Controls the underline buffer of an array.</p>
 *
 */
public interface Resizer {
	/**
	 * Restart the array size manager
	 * @return A new created buffer for the Array 
	 */
	default Object clear() { return init(0); }
    
	/**
	 * Gets the size of elements of the Array
	 * @return Size of the Array
	 */
	int size();
    
	/**
	 * Gets the size of the buffer of the Array
	 * @return Size of the buffer of the Array
	 */
	int buffer_size();
    
	/**
	 * Initializes an Array Size manager to the given size of the Array
	 * @param n Size of the array
	 * @return Inner Array's buffer
	 */
	default Object init(int n) { return init(Object.class,n); }
    
	/**
	 * Initializes an Array Size manager to the given size of the Array
	 * @param cl Class of the Array's elements
	 * @param n Size of the array
	 * @return Inner Array's buffer
	 */
	Object init(Class<?> cl, int n);
    
	/**
	 * Adds a new component to the Array's buffer (resizes it if required)
	 * @param buffer Current Array's buffer
	 * @return A new buffer for the Array if required. The same buffer otherwise
	 */
	Object add(Object buffer);
       
	/**
	 * Removes a component from the Array's buffer (resizes it if required)
	 * @param buffer Current Array's buffer
	 * @return A new buffer for the Array if required. The same buffer otherwise
	 */
	Object del(Object buffer);
    
	/**
	 * Creates a buffer of the current buffer size  
	 * @return A buffer of elements with the same size of the Array's buffer
	 */
	default Object alloc() { return alloc(Object.class); }

	/**
	 * Creates a buffer of the current buffer size  
	 * @param cl Class of the Array's elements
	 * @return A buffer of elements with the same size of the Array's buffer
	 */
	default Object alloc(Class<?> cl) {
		return java.lang.reflect.Array.newInstance(cl,buffer_size());
	}

	/**
	 * Creates a shallow copy of the first <i>n</i> elements of the Array's buffer 
	 * @param buffer Current Array's buffer
	 * @param n Number of elements to copy
	 * @return A buffer of the current Array's buffer with a shallow copy of the first <i>n</i> elements in the Array's buffer
	 */
	default Object copy(Object buffer, int n) {
		Object nbuffer = alloc(buffer.getClass().getComponentType());
		System.arraycopy(buffer, 0, nbuffer, 0, n);
		return nbuffer;
	}
}