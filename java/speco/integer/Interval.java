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
package speco.integer;

import java.util.Iterator;

import speco.set.Set;

/**
 * <p>Title: Interval</p>
 *
 * <p>Description: An [start,end) close-open interval of integer numbers.</p>
 *
 */
public class Interval implements Set<Integer,Integer>{
    /**
     * Left side of the interval
     */
    protected int start = 0;
    
    /** 
     * Right side (not included) of the interval
     */
    protected int end = 0;
    
    /**
     * Creates the [0,end) interval
     * @param end Right side of the interval (must be equal to or higher than 0)
     */
    public Interval( int end ) { this.end = end; }
    
    /**
     * Creates the [start,end) interval (start &lt;= end)
     * @param start Left side of the interval
     * @param end Right side of the interval
     */
    public Interval( int start, int end ) {
	this.start = start;
	this.end = end;	
    }
	
    /**
     * Gets the left side of the interval
     * @return Left side of the interval
     */
    public int inf() { return start; }
	
    /**
     * Gets the right side of the interval
     * @return Right side of the interval
     */
    public int sup(){ return end; }

    /**
     * Gets an iterable version of the set
     * @return An iterable version of the set
     */
    @Override
    public Iterator<Integer> iterator() {
	return new Iterator<Integer>() {
	    protected int pos = start;
	    @Override
	    public boolean hasNext(){ return pos < end; }
	    @Override
	    public Integer next() {	return pos++; }
	};
    }
	
    /**
     * Gets the number of elements in the set
     * @return Number of elements in the set
     */
    @Override
    public int size() { return end-start; }
	
    /**
     * Finds the associated index (key) of the given object.
     * @param data Object from which an associated index will be returned.
     * @return The associated index of the object, <i>null</i> otherwise.
     */
    @Override
    public  Integer index(Integer data) { return (start<=data && data<end)?data:null; }
	    
    /**
     * Gets the data element with the provided index 
     * @param index Index associated to the element to get
     * @return Data element that has associated the given index
     */
    @Override
    public Integer get(Integer index) { return index(index); }
	    
    /**
     * Adds a data element to the set
     * @param data Data element to be inserted
     * @return <i>true</i> if the element could be added, <i>false</i> otherwise
     */
    @Override
    public boolean add(Integer data) { return false; }

    /**
     * Removes the given data from the set 
     * @param data Object to be removed from the set
     * @return <i>true</i> if the element was in the set and it could be removed, <i>false</i> otherwise
     */
    public boolean remove(Integer data) { return false; }
    
    /**	
     * Reset the interval to initial values (does nothing)
     */
    @Override
    public void clear(){}

}