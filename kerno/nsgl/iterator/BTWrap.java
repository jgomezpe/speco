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
 * <p>Title: BTWrap</p>
 *
 * <p>Description: Utility class for converting a regular iterator as a backable and trackable iterator</p>
 *
 */
public abstract class BTWrap<T> implements BTIterator<T>{
	/**
	 * Wrapped iterator
	 */
	protected Iterator<T> iter;
	
	/**
	 * Default number of characters that is able to maintain the reader (last read characters)
	 */
	public static int MEMORY_SIZE = 100000;

	/**
	 * Last read characters
	 */
	protected Object[] data = null;
	/**
	 * Last iterator positions
	 */
	protected IteratorPosition[] extra = null;
	/**
	 * Number of characters that is able to maintain the reader (last read characters)
	 */
	protected int n;
	/**
	 * Absolute iterator position
	 */
	protected int offset=0;
	/**
	 * Initial position of the last stored positions
	 */
	protected int start = 0;
	/**
	 * Final position of the last stored positions
	 */
	protected int end = 0;
	/**
	 * Current cursor position
	 */
	protected int pos = 0;

	/**
	 * Creates a Backable and Trackeable Iterator for the given iterator
	 * @param iter Iterator that will be wrapped
	 */
	public BTWrap( Iterator<T> iter ) { this( iter, MEMORY_SIZE ); }
	
	/**
	 * Creates a Backable and Trackeable Iterator for the given iterator with the possiblity of going back at least <i>n</i> positions 
	 * @param iter Iterator that will be wrapped
	 * @param n Number of positions that the iterator can go back
	 */
	public BTWrap( Iterator<T> iter , int n ){
		this.n = n;
		this.iter = iter;
		data = new Object[n];
		extra = new IteratorPosition[n];
	}
	
	/**
	 * Position associated to the given iterated element
	 * @param data Element which tracked position will be determined 
	 * @return The tracked position of the element 
	 */
	protected abstract IteratorPosition pos(T data);
	
	/**
	 * Determines the current position of the iterator
	 * @return The current position of the iterator
	 */
	public IteratorPosition pos(){ return extra[pos]; }
	
	/**
	 * Determines if there are additional elements in the collection 
	 * @return <i>true</i> if there are more elements in the iteration process, <i>false</i> otherwise
	 */
	@Override
	public boolean hasNext() { return (pos!=end || iter.hasNext()); }

	/**
	 * Obtains the following element it the iteration process 
	 * @return The following element it the iteration process
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T next(){
		T c;
		if (pos == end) {
			c = iter.next();
			end = (end + 1 < n) ? end + 1 : 0;
			pos = end;
			data[pos] = c;
			extra[pos] = pos(c);
			if (end == start) start = (start + 1 < n) ? start + 1 : 0;
		}else{
			pos = (pos + 1 < n) ? pos + 1 : 0;
			c = (T)data[pos];
		}	
		return c;
	}

	/**
	 * Determines the maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 * @return Maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
	 */
	public int maxBack() {
		if (pos >= start)return pos - start;
		return n + pos - start;
	}

	/**
	 * Returns the last k read character to the stream, if possible
	 * @param k Number of characters to be returned to the stream
     * @return true if it was possible to return the last k read character, false otherwise
     */
	public boolean back(int k) {
		boolean flag = (0<k && k <= maxBack());
		if (flag) {
			offset -= k;
			pos -= k;
			if (pos < 0) {
				pos += n;
			}
		}
		return flag;
	}
		
	/**
	 * Marks the actual position as a mark for reseting the reader
	 */
	public void mark() { start = pos; }
}