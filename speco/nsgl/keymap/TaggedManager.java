package nsgl.generic;

import nsgl.cast.CastIterable;

public interface TaggedManager<T> {
	/**
	 * Sets the object that is being tagged. Removes all the non TaggedMethods associated to this object.
	 * @param object Object that is being tagged.
	 * @return A tagged version of the given object 
	 */
	default Tagged<T> wrap( T object ){ return new Tagged<T>(object); }
	
	/**
	 * Gets the object that is being tagged.
	 * @param obj object to be unwrapped (remove the associated tags)
	 * @return tagged object.
	 */
	default T unwrap( Tagged<T> obj ){ return obj.unwrap(); }
	
	/**
	 * Obtains the set of objects that are actually tagged.
	 * @param obj Set of TaggedObject 's.
	 * @return Actual objects (without tags).
	 */
	default T[] unwrap( @SuppressWarnings("unchecked") Tagged<T>... obj ){ 
		@SuppressWarnings("unchecked")
		T[] t_obj = (T[])new Object[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = obj[i].unwrap();
		return t_obj;
	}  
	
	/**
	 * Creates a TaggedObject array from an array of (possibly non tagged) objects.
	 * @param obj Array of (possibly non tagged) objects to be Tagged.
	 * @return A TaggedObject array from an array of (possibly non tagged) objects.
	 */
	default Tagged<T>[] wrap( @SuppressWarnings("unchecked") T... obj ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] t_obj = new Tagged[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = wrap(obj[i]);
		return t_obj;
	}
	
	default Iterable<T> unwrap( Iterable<Tagged<T>> col ){
		return new CastIterable<Tagged<T>,T>(col){ @Override protected T cast(Tagged<T> x) { return unwrap(x); } };
	}
	
	default Iterable<Tagged<T>> wrap( Iterable<T> col ){
		return new CastIterable<T,Tagged<T>>(col){ @Override protected Tagged<T> cast(T x) { return wrap(x); } };
	}
}