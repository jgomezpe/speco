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
	
	@Override
	public boolean ready4Add() { return size()<maxSize; }

	@Override
	public int initSize() { return maxSize; }
}