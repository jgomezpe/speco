package nsgl.generic.hash;

/**
 * <p>Title: Hashing</p>
 *
 * <p>Description: A hashing method</p>
 *
 */
public interface Hashing<T> {
	/**
	 * Gets a hash code for an object
	 * @param obj Object computing its hash code
	 * @return A hash code for the object
	 */
	int hashCode( T obj );	
}