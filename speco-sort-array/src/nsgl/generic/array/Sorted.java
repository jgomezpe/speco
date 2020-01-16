package nsgl.generic.array;

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
public class Sorted<T> extends DynArray<T>{
	
    protected SortedSearch<T> search;
    protected Order order;
    
    public Sorted( T[] buffer, int n, Order order ){
        super( buffer, n );
        this.order = order;
        search = new SortedSearch<T>(buffer, order);
    }

    public Sorted( T[] buffer, Order order ){
        super( buffer );
        this.order = order;
        search = new SortedSearch<T>(buffer, order);
    }

    public Sorted( Order order ) {
        super();
        this.order = order;
        search = null;
    }
    
	protected void init( Class<?> cl ) {
		super.init(cl);
		search = new SortedSearch<T>(this, order);
	}
    
	/**
	 * Resizes the inner buffer according to the associated Fibonacci numbers (new buffer size must be <i>c</i>)
	 */
	protected void resize() {
		super.resize();
		if( search == null ) search = new SortedSearch<T>(this, order);
		else search.set(this);
	}
	
    public Integer find( T data ){
    	if( buffer==null ) init( data.getClass() );
    	return search.find(0,size(),data);
    }

    public boolean add( T data ){
    	if( buffer==null ) init( data.getClass() );
        int index = search.findRight(0,size(),data);
        if( index == this.size() ) return super.add(data);
        else return super.add(index, data);
    }
    
    public boolean set( int index, T data ) throws IndexOutOfBoundsException{
    	if( buffer==null ) init( data.getClass() );
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
    	if( buffer==null ) init( data.getClass() );
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
}