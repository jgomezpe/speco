package nsgl.json;

import java.io.IOException;

import nsgl.character.CharacterSequence;
import nsgl.copy.Copyable;
import nsgl.generic.hashmap.HashMap;
import nsgl.language.Language;
import nsgl.parse.Parseable;
import nsgl.stringify.Stringifyable;

public class JXON extends HashMap<String, Object> implements Copyable, Stringifyable, Parseable{	
	public JXON(){}
		
	public JXON( String code )throws IOException{
	    this( new CharacterSequence(code) );
	}
	
	public JXON( CharacterSequence code )throws IOException{ this(code, false); }

	public JXON( CharacterSequence code, boolean JSON )throws IOException{
		Lexer lexer = new Lexer(JSON);
		Parser parser = new Parser();
		Meaner meaner = new Meaner(JSON);
		Language<Object> lang = new Language<Object>(lexer, parser, meaner);		
		Object obj = lang.process(code);
		if( obj instanceof JXON ){
			JXON source = (JXON)obj;
			for( String key:source.keys() ){
				obj = source.get(key);
				set(key, obj);
			}
		}
	}
	
	public JXON( JXON source ){
		try{
			for( String key:source.keys() ){
				Object obj = source.get(key);
				Copyable c = Copyable.cast(obj);
				set(key, c.copy());
			}
		}catch(Exception e){}	
	}
	
	@Override
	public Object copy(){ return new JXON(this); }
	
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

	public byte[] getBlob( String tag ){ try{ return (byte[])get(tag); }catch(Exception e){ return null; } }

	public String getString( String tag ){ try{ return (String)get(tag); }catch(Exception e){ return null; } }

	public Object[] getArray( String tag ){ try{ return (Object[])get(tag); }catch(Exception e){ return null; } }

	public int[] getIntArray( String tag ){ 
		Object[] a = getArray(tag);
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
	public double[] getRealArray( String tag ){
		Object[] a = getArray(tag);
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

	public JXON getJXON( String tag ){ try{ return (JXON)get(tag); }catch(Exception e){ return null; } }

	
	public boolean storable(Object obj){
		if( obj instanceof Object[] ){
			Object[] v = (Object[])obj;
			int i=0;
			while( i<v.length && storable(v[i]) ){ i++; }
			return i==v.length;
		}
		return ( obj == null || obj instanceof JXON || obj instanceof byte[] ||
			 obj instanceof String || obj instanceof Integer || obj instanceof Double || 
			 obj instanceof double[] || obj instanceof int[] );
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
				Object obj = this.get(key);
				sb.append(Stringifyable.cast(obj).stringify());
				prComma = true;
			}
		}catch( Exception e ){}	
		sb.append('}');
		return sb.toString();
	}

	@Override
	public Object parse(CharacterSequence input) throws IOException { return new JXON(input); }	}
