package nsgl.generic.collection;

import nsgl.generic.Collection;

/**
 * <p>Title: Observable</p>
 *
 * <p>Description: A collection of objects that has a membership method</p>
 *
 */
public interface Observable<T> extends Collection<T>{
	/**
	 * Determines if the given object belongs to the collection
	 * @param data Data object to be located
	 * @return <i>true</i>If the object belongs to the collection, <i>false</i> otherwise
	 */
	boolean contains( T data );
}
