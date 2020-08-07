package nsgl.json;

import java.io.IOException;

import nsgl.character.CharacterSequence;

public class JSON extends JXON{
	public JSON(){}
	
	public JSON( String code )throws IOException{ this( new CharacterSequence(code) ); }
	
	public JSON( CharacterSequence code )throws IOException{ super( code, true ); }
	
	public JSON( JSON source ){ super(source); }
	
	@Override
	public Object copy(){ return new JSON(this); }
	
	public JSON getJSON( String tag ){ try{ return (JSON)get(tag); }catch(Exception e){ return null; } }
	
	@Override
	public boolean storable(Object obj){ return obj instanceof JSON || (super.storable(obj) && !(obj instanceof JXON || obj instanceof byte[])); }
	
	@Override
	public Object parse(CharacterSequence input) throws IOException { return new JSON(input); }	
}