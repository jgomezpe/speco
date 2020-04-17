package nsgl.generic.hash;

public class ShallowHashing<T> implements Hashing<T>{
	@Override
	public int hashCode(T obj) { return obj.hashCode(); }
}