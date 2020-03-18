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
package nsgl.generic.hashmap;

import java.util.Iterator;

import nsgl.generic.Sized;
import nsgl.generic.keymap.KeyMap;

/**
 * <p>Title: HashMap</p>
 *
 * <p>Description: A hashmap collection</p>
 *
 */
public class HashMap<K,V> implements KeyMap<K,V>, Sized{
	/**
	 * Inner hashmap
	 */
	java.util.HashMap<K, V> map = new java.util.HashMap<K, V>();
	
	@Override
	public Iterator<V> iterator(){ return map.values().iterator(); }

	/**
	 * Removes all entries in the HashMap
	 */
	public void clear() { map.clear(); }

	/**
	 * Obtains the number of entries in the HashMap
	 * @return Size of the HashMap
	 */
	public int size(){ return map.size(); }

	/**
	 * Sets an element with the given map into the HashMap
	 * @param key Key used for setting the object 
	 * @param value The element to be set
	 * @return <i>true</i> if the element was set, <i>false</i> otherwise
	 */
	public boolean set( K key, V value ){
		map.put(key, value);
		return true;
	}

	/**
	 * Removes the element with the given key
	 * @param key Key of the object to be removed
	 * @return <i>true</i> if the key was removed or was associated with a <i>null</i> value, <i>false</i> otherwise. 
	 */
	public boolean remove(K key){ return map.remove(key)!= null; }
	
	/**
	 * Obtains the object that has the given key
	 * @param key Key of the object
	 * @return Object with the given key 
	 */
	public V get(K key){ return map.get(key); }
	
	// KeyMap methods
	/**
	 * Determines if there is an object in the collection associated to the given key
	 * @param key Key that is being checked
	 * @return <i>true</i>if there is an object in the collection associated to the given key, <i>false</i> otherwise
	 */
	public boolean valid(K key){ return map.containsKey(key); }
	
	/**
	 * Collection of keys
	 * @return An Iterable collection of the keys stored by the HashMap
	 */
	public Iterable<K> keys(){ return map.keySet(); }
	
	/**
	 * Merges the given HashMap (shallow copy) with the HashMap, replace if already in the HashMap
	 * @param map MashMap to be merged
	 */
	public void merge( HashMap<K, V> map ){ for( K k : map.keys() ) this.set(k, map.get(k)); }

	/**
	 * Determines if the collection is empty or not
	 * @return <i>true</i> if the collection is empty <i>false</i> otherwise
	 */
	public boolean isEmpty(){ return size()==0; }

	@Override
	public Iterable<K> locations(){ return keys();	}
	
	@Override
	public KeyMap<K,V> instance(){ return new HashMap<K,V>(); }  
	
}