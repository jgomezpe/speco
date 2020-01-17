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

/**
 * <p>Title: PositionTrack</p>
 *
 * <p>Description: maintains the position in the collection that is being iterated</p>
 *
 */
public class IteratorPosition {
    protected int[] pos;
    
    public IteratorPosition( int src, int offset ){
    	pos = new int[] {src, offset};
    }
    
    public IteratorPosition( int src, int[] offset ){
    	pos = new int[offset.length+1];
    	pos[0] = src;
    	setOffset(offset);
    }
    
    public IteratorPosition(IteratorPosition src){
    	this.pos = src.pos.clone(); 
    }
   
    public void setSrc( int src ){ pos[0] = src; }
    
    public int src(){ return pos[0]; }

    public int[] offset(){
    	int[] offset = new int[pos.length-1];
    	System.arraycopy(pos, 1, offset, 0, offset.length);
    	return offset; 
    }
    
    public void setOffset( int[] offset ){
    	if( pos.length != offset.length+1 ) {
    		int src = pos[0];
    		pos = new int[offset.length+1];
    		pos[0] = src;
    	}
    	System.arraycopy(offset, 0, pos, 1, offset.length);
    }
    
	public int[] pos(){ return pos; }
}