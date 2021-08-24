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
package speco.set;

import speco.Collection;

/**
 * <p>A set allowing a single copy of an element. Provide access to elements using an index/key.</p>
 * @param <V> Type of elements stored by the Set
 * @param <K> Type of indices for accessing elements in the Set
 *
 */
public interface Set<K,V> extends Collection<V>{
	/**
	 * Determines if the given object belongs to the set.
	 * @param data Data object to analyze.
	 * @return <i>true</i>If the object belongs to the set, <i>false</i> otherwise.
	 */
	default boolean contains( V data ) { return index(data)!=null; } 
    
	/**
	 * Finds the associated index (key) of the given object.
	 * @param data Object from which an associated index will be returned.
	 * @return The associated index of the object, <i>null</i> otherwise.
	 */
	K index(V data);
    
	/**
	 * Gets the data element with the provided index 
	 * @param index Index associated to the element to get
	 * @return Data element that has associated the given index
	 */
	V get(K index);
    
	/**
	 * Adds a data element to the set
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	boolean add(V data);

	/**
	 * Removes the given data from the set 
	 * @param data Object to be removed from the set
	 * @return <i>true</i> if the element was in the set and it could be removed, <i>false</i> otherwise
	 */
	boolean remove(V data);   
}