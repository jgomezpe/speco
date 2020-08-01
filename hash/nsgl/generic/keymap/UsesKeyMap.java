package nsgl.generic.keymap;

import nsgl.generic.hashmap.HashMap;

public class UsesKeyMap<K,V> {
	protected KeyMap<K, V> keymap;
	
	public UsesKeyMap( KeyMap<K, V> keymap ){ this.keymap = keymap; }
	
	public UsesKeyMap(){ this( new HashMap<K,V>() ); }
}