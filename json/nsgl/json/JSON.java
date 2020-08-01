package nsgl.json;

import java.io.IOException;

import nsgl.character.CharacterSequence;
import nsgl.copy.Copyable;
import nsgl.generic.hashmap.HashMap;
import nsgl.language.Language;
import nsgl.parse.Parseable;
import nsgl.stringify.Stringifyable;

public class JSON extends HashMap<String, Object> implements Copyable, Stringifyable, Parseable{
	protected static Language<Object> lang = null;

	public JSON(){}
	
	public JSON( String code )throws IOException{
	    this( new CharacterSequence(code));
	}
	
	public JSON( CharacterSequence code )throws IOException{
		if( lang == null ){
			Lexer lexer = new Lexer();
			Parser parser = new Parser();
			Meaner meaner = new Meaner();
			lang = new Language<Object>(lexer, parser, meaner);		
		}
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
		try{
			for( String key:source.keys() ){
				Object obj = source.get(key);
				Copyable c = Copyable.cast(obj);
				set(key, c.copy());
			}
		}catch(Exception e){}	
	}
	
	@Override
	public Object copy(){ return new JSON(this); }
	
	public double getReal( String tag ){
		try{
			Object obj = get(tag);
			if( obj instanceof Double ) return (Double)obj;
			if( obj instanceof Integer ) return (Integer)obj;
		}catch(Exception e){}
		return 0;
	}
	
	public int getInt( String tag ){ try{ return (Integer)get(tag); }catch(Exception e){ return 0; } } 
	
	public boolean getBool( String tag ){ try{ return (Boolean)get(tag); }catch(Exception e){ return false; } }

	public String getString( String tag ){ try{ return (String)get(tag); }catch(Exception e){ return null; } }

	public Object[] getArray( String tag ){ try{ return (Object[])get(tag); }catch(Exception e){ return null; } }

	public int[] getIntArray( String tag ){ 
		Object[] a = getArray(tag);
		int[] x = null;
		if( a!=null ){
			x = new int[a.length];
			try{ for(int i=0; i<a.length; i++ ) x[i] = (Integer)a[i]; }catch(Exception e){ x = null; }
		} 
		return x;
	}
	public double[] getRealArray( String tag ){
		Object[] a = getArray(tag);
		double[] x = null;
		if( a!=null ){
			x = new double[a.length];
			try{ for(int i=0; i<a.length; i++ ) x[i] = (Double)a[i]; }catch(Exception e){ x = null; }
		} 
		return x;
	}

	public JSON getJSON( String tag ){ try{ return (JSON)get(tag); }catch(Exception e){ return null; } }

	
	public boolean storable(Object obj){
		if( obj == null || obj instanceof double[] || obj instanceof int[]) return true;
		if( obj instanceof Object[] ){
			Object[] v = (Object[])obj;
			int i=0;
			while( i<v.length && storable(v[i]) ){ i++; }
			return i==v.length;
		}
		return ( obj instanceof String || obj instanceof Integer || obj instanceof Double ||  obj instanceof Boolean || obj instanceof JSON );
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
		sb.append('{');
		try{
			boolean prComma = false;
			for( String key : this.keys() ){
				if( prComma ) sb.append(',');
				sb.append(Stringifyable.cast(key).stringify());
				sb.append(':');
				sb.append(Stringifyable.cast(this.get(key)).stringify());
				prComma = true;
			}
		}catch( Exception e ){}	
		sb.append('}');
		return sb.toString();
	}

	@Override
	public Object parse(CharacterSequence input) throws IOException { return new JSON(input); }	
}