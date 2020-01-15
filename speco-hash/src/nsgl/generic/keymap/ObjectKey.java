package nsgl.generic.keymap;

public class ObjectKey implements Key<String, Object>{
	@Override
	public String key(Object obj){ return obj.toString(); }
}