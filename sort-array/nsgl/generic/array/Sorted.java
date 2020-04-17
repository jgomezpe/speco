package nsgl.generic.array;

import nsgl.integer.L2HOrder;
import nsgl.order.Order;

/**
 * <p>Title: SortedVector</p>
 *
 * <p>Description: Insert operation for sorted vectors</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Sorted<T> extends Vector<T>{
	
    protected SortedSearch search;
    protected Order order;
    
    public Sorted( Object buffer, int n, Order order ){
        super( buffer, n );
        this.order = order;
        search = new SortedSearch(order);
    }

    public Sorted( Object buffer, Order order ){
        super( buffer );
        this.order = order;
        search = new SortedSearch(order);
    }

    public Sorted( Order order ) {
        super();
        this.order = order;
        search = new SortedSearch(order);
    }
    
    @Override
    public Integer find( T data ){
    	init( data.getClass() );
    	search.set(buffer);
    	int index = search.find(0,size(),data);
    	if( index < 0 ) return null;
    	return index;
    }

    public boolean add( T data ){
    	init( data.getClass() );
    	search.set(buffer);
        int index = search.findRight(0,size(),data);
        if( index == this.size() ) return super.add(data);
        else return super.add(index, data);
    }
    
    public boolean set( int index, T data ) throws IndexOutOfBoundsException{
    	init( data.getClass() );
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || order.compare(get(index-1), data)<=0) &&
        					(index==size-1 || order.compare(data, get(index+1))<=0);
            if( flag ) set(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, T data ) throws IndexOutOfBoundsException{
    	init( data.getClass() );
        if( 0 <= index && index <= size ){
        	boolean flag = 	(index==0 || order.compare(get(index-1), data)<=0) &&
        					(index==size || order.compare(data, get(index))<=0);
            if( flag ) super.add(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }
    
    public boolean del( T data ) {
    	int index = find(data);
    	return remove(index);
    }
    
	public Array<T> instance(int s){
		Sorted<T> a = new Sorted<T>(order);
		a.size = size;
		return a; 
	}
	
	public static void main( String[] args ) {
		Sorted<Integer> s = new Sorted<Integer>(new L2HOrder());
		for( int i=0; i<100; i++) {
			int x = (int)(1000*Math.random());
			s.add(x);
			System.out.print(x+" ");
		}
		System.out.println(" **************");
		for( int k=0; k<s.size(); k++ ) System.out.print(s.get(k)+" ");
		System.out.println(" **************");
	}
}