package nsgl.json;

import java.io.IOException;

import nsgl.blob.Parse;
import nsgl.character.CharacterSequence;
import nsgl.copy.Copyable;
import nsgl.generic.Thing;
import nsgl.generic.hashmap.HashMap;
import nsgl.language.Language;
import nsgl.stringify.Stringifyable;

public class JSON extends HashMap<String, Object> implements Copyable, Thing{	
	public JSON(){}
		
	public JSON( String code )throws IOException{
	    this( new CharacterSequence(code) );
	}
	
	public JSON( CharacterSequence code )throws IOException{
		Lexer lexer = new Lexer();
		Parser parser = new Parser();
		Meaner meaner = new Meaner();
		Language<Object> lang = new Language<Object>(lexer, parser, meaner);		
		Object obj = lang.process(code);
		if( obj instanceof JSON ){
			JSON source = (JSON)obj;
			for( String key:source.keys() ){
				obj = source.get(key);
				set(key, obj);
			}
		}
	}
	
	public JSON( JSON source ){
		config(source);	
	}
	
	@Override
	public Object copy(){ return new JSON(this); }
	
	public double real( String tag ){
		try{
			Object obj = get(tag);
			if( obj instanceof Double ) return (Double)obj;
			if( obj instanceof Integer ) return (Integer)obj;
		}catch(Exception e){}
		return 0;
	}
	
	public int integer( String tag ){ try{ return (Integer)get(tag); }catch(Exception e){ return 0; } } 
	
	public boolean bool( String tag ){ try{ return (Boolean)get(tag); }catch(Exception e){ return false; } }

	public byte[] blob( String tag ){
	    Object obj = get(tag);
	    try{ 
		if( obj instanceof String ) {
		    CharacterSequence seq = new CharacterSequence((String)obj, "");
		    Parse p = new Parse();
		    return (byte[])p.parse(seq);
		}
		return (byte[])get(tag); 
	    }catch(Exception e){ return null; } 
	}

	public String string( String tag ){ try{ return (String)get(tag); }catch(Exception e){ return null; } }

	public Object[] array( String tag ){ try{ return (Object[])get(tag); }catch(Exception e){ return null; } }

	public int[] integers_array( String tag ){ 
		Object[] a = array(tag);
		int[] x = null;
		if( a!=null ){
			x = new int[a.length];
			try{ 
			    for(int i=0; i<a.length; i++ )
				x[i] = (Integer)a[i]; 
			}catch(Exception e){ x = null; }
		} 
		return x;
	}
	public double[] reals_array( String tag ){
		Object[] a = array(tag);
		double[] x = null;
		if( a!=null ){
			x = new double[a.length];
			try{ 
			    for(int i=0; i<a.length; i++ ) 
				x[i] = (a[i] instanceof Double)?(Double)a[i]:(Integer)a[i];
			}catch(Exception e){ x = null; }
		} 
		return x;
	}

	public JSON object( String tag ){ try{ return (JSON)get(tag); }catch(Exception e){ return null; } }

	
	public boolean storable(Object obj){
		if( obj instanceof Object[] ){
			Object[] v = (Object[])obj;
			int i=0;
			while( i<v.length && storable(v[i]) ){ i++; }
			return i==v.length;
		}
		return ( obj == null || obj instanceof JSON || obj instanceof byte[] ||
			 obj instanceof String || obj instanceof Integer || obj instanceof Double || 
			 obj instanceof Boolean || obj instanceof double[] || obj instanceof int[] );
	}
	
	@Override
	public boolean set(String key, Object obj ){
		if( storable(obj ) ){
			if( obj instanceof double[] ){
				double[] a = (double[])obj;
				Object[] x = new Object[a.length];
				for( int i=0; i<a.length; i++ ) x[i] = a[i];
				obj = x;
			}else if( obj instanceof int[] ){
				int[] a = (int[])obj;
				Object[] x = new Object[a.length];
				for( int i=0; i<a.length; i++ ) x[i] = a[i];
				obj = x;
			}
			return super.set(key, obj);
		}
		return false;
	} 
	
	@Override
	public String stringify() {
		StringBuilder sb = new StringBuilder();
		String delimiter = nsgl.blob.Stringifier.delimiter;
		nsgl.blob.Stringifier.delimiter = "\"";
		sb.append('{');
		try{
			boolean prComma = false;
			for( String key : this.keys() ){
				if( prComma ) sb.append(',');
				sb.append(Stringifyable.cast(key).stringify());
				sb.append(':');
				Object obj = this.get(key);
				sb.append(Stringifyable.cast(obj).stringify());
				prComma = true;
			}
		}catch( Exception e ){}	
		sb.append('}');
		nsgl.blob.Stringifier.delimiter = delimiter;
		return sb.toString();
	}

	@Override
	public Object parse(CharacterSequence input) throws IOException { return new JSON(input); }

	@Override
	public JSON json() { return this; }

	@Override
	public void config(JSON json){
		this.clear();
		try{
			for( String key:json.keys() ){
				Object obj = json.get(key);
				Copyable c = Copyable.cast(obj);
				set(key, c.copy());
			}
		}catch(Exception e){ e.printStackTrace(); }	
	}	
}
