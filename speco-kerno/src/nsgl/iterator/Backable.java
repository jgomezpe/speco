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
package nsgl.iterator;

import java.util.Iterator;

/**
 * <p>Title: BackableIterator</p>
 *
 * <p>Description: An iterator that can go back to previous obtained elements</p>
 *
 */
public interface Backable<T> extends Iterator<T>{
	/**
	 * Determines the maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 * @return Maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 */
	int maxBack() ;

	/**
	 * Returns the last k read character to the stream, if possible
	 * @param k Number of characters to be returned to the stream
     * @return true if it was possible to return the last k read character, false otherwise
     */
	boolean back(int k);
	
	/**
	 * Returns the last read character to the stream, if possible
	 * @return true if it was possible to return the last read character, false otherwise
	 */
	default boolean back(){ return back(1); }
	
	/**
	 * Resets the reader to the previous mark. Marks are dynamically adjusted in such a way that
	 * if the number of characters read, after calling the mark method, is greater than the size of the buffer
	 * then the mark is moved to such maximum number of characters. In this way at least the last n characters
	 * are always maintained by the reader.
	 */
	default void reset(){ back(maxBack()); }
}