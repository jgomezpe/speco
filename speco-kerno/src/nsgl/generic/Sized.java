package nsgl.generic;

public interface Sized{
	/**
	 * Determines the number of objects stored by the collection
	 * @return Number of objects stored by the collection. If -1 is returned then the size of the collection is not defined.
	 */
	int size();
}