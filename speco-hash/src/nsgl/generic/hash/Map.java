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
package nsgl.generic.hash;

import nsgl.compare.Comparator;
import nsgl.generic.collection.Growable;
import nsgl.generic.collection.Shrinkable;
import nsgl.generic.hashmap.HashMap;

/**
 * <p>Title: Map</p>
 *
 * <p>Description: A map of objects that use the hash code of the objects as key</p>
 *
 */
public class Map<T> extends HashMap<Integer,T> implements Growable<T>, Shrinkable<T>{
	protected Comparator c=null;
	protected Hashing<T> h=null;
	
	public Map(){ this( new ShallowHashing<T>() ); }
	
	public Map( Hashing<T> h ){ this.h = h; }
	
	public Map( Comparator c ){
		this();
		this.c=c; 
	}

	public Map( Hashing<T> h, Comparator c ){
		this(h);
		this.c=c; 
	}
	
	/**
	 * Inserts a data element in the map
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(T data){ return set(h.hashCode(data), data); }
	
	/**
	 * Removes a data element from the structure
	 * @param data Data element to be removed
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	public boolean del(T data){
		Integer loc = find(data);
		if( loc != null ) return remove(loc);
		return false;
	}
	
	/**
	 * Determines if the given object belongs to the structure
	 * @param data Data object to be located
	 * @return <i>true</i>If the object belongs to the structure, <i>false</i> otherwise
	 */
	public boolean contains( T data ){
		Integer k = find(data);
		return( k!=null );
	}
	
	public Integer find(T data) {
		if(c!=null) { for(Integer k:keys()) if(c.eq(get(k),data)) return k; }
		else{ for(Integer k:keys()) if( get(k)==data ) return k; }
		return null;
	}
}