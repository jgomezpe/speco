package nsgl.generic.array;

public class DynamicWithMaxSize<T> extends Dynamic<T>{
	
	protected int maxSize;
	
	public DynamicWithMaxSize( int maxSize ) {
		this.maxSize = maxSize;
	}

	public DynamicWithMaxSize(Object buffer) {
		super(buffer);
		this.maxSize = size;
	}

	public DynamicWithMaxSize(Object buffer, int s) {
		super(buffer);
		this.maxSize = size;
		this.size = s;
	}
	
	public Array<T> instance(int n){
		if( buffer != null ) return new DynamicWithMaxSize<T>(java.lang.reflect.Array.newInstance(buffer.getClass().getComponentType(),n));
		return new DynamicWithMaxSize<T>(n);
	}
	
	
	@Override
	protected boolean ready4Add() { return size()<maxSize; }

	@Override
	protected int initSize() { return maxSize; }
	
	public int maxSize() { return maxSize; }
}