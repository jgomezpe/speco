package nsgl.json;

import nsgl.json.JSON;
import nsgl.generic.hashmap.HashMap;

public class Factory<T> implements JSON2Instance<T>{
	public static final String TYPE = "type";
	
	protected HashMap<String, JSON2Instance<T>> map = new HashMap<String,JSON2Instance<T>>();
	protected HashMap<String, String> alias = new HashMap<String,String>();
	protected String defaultTag=null;
	
	public void register( String tag, String class_name, JSON2Instance<T> instance ){
		map.set(tag, instance);
		this.alias.set(class_name, tag);
		defaultTag = tag;
	}
	
	public void clear(){ 
		map.clear();
		alias.clear();
	}
	
	public T load(JSON json ){
		String type = defaultTag;
		try{ type = (String)json.get(TYPE); }catch(Exception e ){}
		try{ 
			JSON2Instance<T> instance = map.get(type);
			return instance.load(json);
		}catch(Exception e){ return null; }
	}
	
	public JSON store(T obj){
		String tag = alias.get(obj.getClass().getName());
		JSON2Instance<T> instance = map.get(tag);
		if( instance != null ){
			JSON json = instance.store(obj);
			if(alias.size()>1) json.set(TYPE, tag);
			return json;
		}
		return null;
	}
}