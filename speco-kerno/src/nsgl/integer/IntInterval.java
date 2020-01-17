package nsgl.integer;

import java.util.Iterator;

import nsgl.generic.Collection;
import nsgl.generic.Sized;
import nsgl.iterator.Backable;

public class IntInterval implements Collection<Integer>, Sized{
	protected int start = 0;
	protected int end = 0;
	public IntInterval( int end ) { this.end = end;	}
	public IntInterval( int start, int end ) {
		this.start = start;
		this.end = end;	
	}
	
	public int inf() { return start; }
	
	public int sup(){ return end; }

	@Override
	public Iterator<Integer> iterator() {
		return new Backable<Integer>() {
			protected int pos = start;
			@Override
			public boolean hasNext(){ return pos < end; }

			@Override
			public Integer next() {	return pos++; }

			@Override
			public int maxBack() { return pos-start; }

			@Override
			public boolean back(int k) {
				if( k > maxBack() ) return false;
				pos -= k;
				return true;
			}
		};
	}
	
	@Override
	public int size() { return end-start; }
	
	/**
	 * Determines if the collection is empty or not
	 * @return <i>true</i> if the collection is empty <i>false</i> otherwise
	 */
	public boolean isEmpty(){ return size()==0; }      
}