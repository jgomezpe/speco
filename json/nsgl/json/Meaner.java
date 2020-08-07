package nsgl.json;

import java.io.IOException;

import nsgl.generic.array.Vector;
import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;
import nsgl.pair.Pair;

public class Meaner implements nsgl.language.Meaner<Object>{
	protected boolean JSON;
	
	public Meaner() { this(false); }
	
	public Meaner(boolean json){
		this.JSON = json;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object apply(Typed obj) throws IOException {
		if( obj instanceof Token ) return ((Token)obj).value();
		TypedValue tv = (TypedValue)obj;
		String type = tv.type();
		if( type.equals(Parser.ARRAY) ){
			Vector<Typed> v = (Vector<Typed>)tv.value(); 
			Object[] attr = new Object[v.size()];		
			for( int i=0; i<v.size(); i++ ) attr[i] = apply(v.get(i));
			return attr;
		}

		if( type.equals(Parser.OBJECT) ){
			Vector<Typed> v = (Vector<Typed>)tv.value(); 
			JXON json = JSON?new JSON():new JXON();
			for( Typed a:v ){
			    Pair<String,Object> attribute = (Pair<String,Object>)apply(a);
			    if(json.valid(attribute.a())) throw ((Token)((Typed[])((TypedValue)a).value())[0]).exception("Attribute redefinition");
			    json.set(attribute);
			}
			return json;
		}

		if( type.equals(Parser.ATTRIBUTE) ) {
		    Typed[] attr = (Typed[])((TypedValue)tv).value();
		    String key = (String)((TypedValue)attr[0]).value();
		    Object value = apply(attr[1]);
		    return new Pair<String, Object>(key, value);
		}
		
		throw new IOException("·Unknown component· " + obj.type());
	}
}
