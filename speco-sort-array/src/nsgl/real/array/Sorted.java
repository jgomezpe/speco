package nsgl.real.array;

import nsgl.real.Order;

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
public class Sorted extends DynArray{
	
    protected SortedSearch search;
    protected Order order;
    
    public Sorted( double[] buffer, int n, Order order ){
        super( buffer, n );
        this.order = order;
        search = new SortedSearch(buffer, order);
    }

    public Sorted( double[] buffer, Order order ){
        super( buffer );
        this.order = order;
        search = new SortedSearch(buffer, order);
    }

    public Sorted( Order order ) {
        super();
        this.order = order;
        search = new SortedSearch(buffer,order);
    }
    
	/**
	 * Resizes the inner buffer according to the associated Fibonacci numbers (new buffer size must be <i>c</i>)
	 */
	protected void resize() {
		super.resize();
		if( search == null ) search = new SortedSearch(this, order);
		else search.set(this);
	}
	
    public Integer find( double data ){
    	return search.find(0,size(),data);
    }

    public boolean add( double data ){
        int index = search.findRight(0,size(),data);
        if( index == this.size() ) return super.add(data);
        else return super.add(index, data);
    }
    
    public boolean set( int index, double data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || order.compare(get(index-1), data)<=0) &&
        					(index==size-1 || order.compare(data, get(index+1))<=0);
            if( flag ) set(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, double data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index <= size ){
        	boolean flag = 	(index==0 || order.compare(get(index-1), data)<=0) &&
        					(index==size || order.compare(data, get(index))<=0);
            if( flag ) super.add(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }
    
    public boolean del( double data ) {
    	int index = find(data);
    	return remove(index);
    }
    
	public Array instance(int s){
		Sorted a = new Sorted(order);
		a.size = size;
		return a; 
	}
}