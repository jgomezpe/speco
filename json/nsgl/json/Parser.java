package nsgl.json;

import java.io.IOException;

import nsgl.generic.array.Vector;
import nsgl.language.Lexer;
import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;
import nsgl.language.lexeme.Symbol;

public class Parser extends nsgl.language.Parser{
	protected String S = Symbol.TAG; 
	public static final String JSONEXCEPTION = "JSON_exception";
	public static final String NOVALID = "JSON_novalid";
	public static final String WORD = "JSON_word";
	public static final String OBJECT = "JSON_object";
	public static final String KEY = "JSON_key";
	public static final String VALUE = "JSON_value";
	public static final String OPTION = "JSON_option";
	public static final String ARRAY = "JSON_array";
	public static final String ATTRIBUTE = "JSON_atribute";
	
	public static final String expected = "·Expecting· ";
	public static final String unexpected = "·Unexpected· ";
	public static final String string = "·string·";
	
	public Parser(){}
	
	protected IOException exception(String code){ return token.exception(code); }
		
	protected Typed array() throws IOException {
		Vector<Typed> v = new Vector<Typed>();
		next();
		while( !check_symbol(']') ){
			if(check_symbol(',')) throw exception(unexpected+',');
			v.add(process());
			next();
			if(check_symbol(',')) {
			    next();
			    if(check_symbol(']')) throw exception(unexpected+']');
			}else {
			    if(!check_symbol(']')) throw exception(unexpected+']');
			}
		}
		return new TypedValue<Vector<Typed>>(ARRAY, v);
	}

	protected Typed attribute() throws IOException{
		Typed[] pair = new Typed[2];
		if(!check_type(nsgl.string.Parse.TAG)) throw exception(expected+string);
		pair[0] = token;
		next();
		if(!check_symbol(':')) throw exception(expected+':');
		next();
		pair[1] = process();
		return new TypedValue<Typed[]>(ATTRIBUTE, pair);
	}
	
	protected Typed object() throws IOException {
		Vector<Typed> v = new Vector<Typed>();
		next();
		while( !check_symbol('}') ){
			if(check_symbol(',')) throw exception(unexpected+',');
			v.add(attribute());
			next();
			if(check_symbol(',')) {
			    next();
			    if(check_symbol('}')) throw exception(unexpected+'}');
			}else {
			    if(!check_symbol('}')) throw exception(unexpected+'}');
			}
		}
		return new TypedValue<Vector<Typed>>(OBJECT, v);
	}

	@Override
	protected Typed process() throws IOException {
		if( check_type(S) ) {
			char c = (char)token.value();
			switch( c ){
				case '[': return array();
				case '{': return object();
				default: throw exception(unexpected+c);
			}
		}
		return token;
	}	

	@Override
	public Typed analize( Vector<Token> tokens ) throws IOException{
		tokens = Lexer.remove_space(tokens);
		return super.analize(tokens);
	}		
}