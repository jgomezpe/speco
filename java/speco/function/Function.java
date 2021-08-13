package speco.function;

import speco.object.Cast;

/**
 * <p>Abstract definition of a function</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 * @param <S> Codomain of the function
 * @param <T> Domain of the function
 */
public interface Function<S, T> {	
    /**
     * Determines if the fitness function is deterministic or not, i.e.,
     * if the value of the function for a given value can change in time (non-stationary) 
     * or not (stationary)
     * @return <i>true</i> if the fitness function does not changes with time, <i>false</i> otherwise
     */
    default boolean deterministic(){ return true; }

    /**
     * Computes the function
     * @param x Parameter of the function
     * @return Computed value of the function
     */
     T apply(S x);	    
 
    // Collections 
    default T[] set_apply( S[] x ){
	@SuppressWarnings("unchecked")
	T[] r = (T[])new Object[x.length];
	for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
	return r;
    }
	
    /**
     * Computes the function for a given collection of objects
     * @param set Objects to be computed
     * @return Values of the function, associated to the given collection of objects
     */	
    @SuppressWarnings("unchecked")
    default Iterable<T> set_apply( Iterable<?> set ){
	return new Cast<S,T>( (Iterable<S>)set ){
	    @Override public T cast( S x ) { return apply(x); } 
	};
    }
}