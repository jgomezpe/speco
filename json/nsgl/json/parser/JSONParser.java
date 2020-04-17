package nsgl.json.parser;

import java.io.IOException;

import nsgl.generic.array.Vector;
import nsgl.exception.IO;
import nsgl.language.Lexer;
import nsgl.language.Parser;
import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;

public class JSONParser extends Parser{
	protected char S = '#'; 
	public static final String JSONEXCEPTION = "JSON_exception";
	public static final String NOVALID = "JSON_novalid";
	public static final String WORD = "JSON_word";
	public static final String OBJECT = "JSON_object";
	public static final String KEY = "JSON_key";
	public static final String VALUE = "JSON_value";
	public static final String OPTION = "JSON_option";
	public static final String ARRAY = "JSON_array";
	
	public JSONParser(){}

	protected Typed array() throws IOException {
		Vector<Typed> v = new Vector<Typed>();
		next();
		while( token.type() != S || token.value().charAt(0)!=']' ){
			if(token.type() == S && token.value().charAt(0)==',' ) throw IO.exception(IO.UNEXPECTED, ',', token.pos());
			v.add(process());
			if(next().type() == S && token.value().charAt(0)==',' && next().type() == S && token.value().charAt(0)!='[') 
				throw IO.exception(IO.UNEXPECTED, token.value(), token.pos());
		}
		return new TypedValue<Vector<Typed>>('V', v);
	}

	protected Typed attribute() throws IOException{
		Typed[] pair = new Typed[2];
		if(token.type()!='S') throw IO.exception(IO.UNEXPECTED, token.value(), token.pos());
		pair[0] = token;
		if(next().type() != S || token.value().charAt(0)!=':') throw IO.exception(IO.UNEXPECTED, token.value(), token.pos());
		next();
		pair[1] = process();
		return new TypedValue<Typed[]>('A', pair);
	}
	
	protected Typed object() throws IOException {
		Vector<Typed> v = new Vector<Typed>();
		next();
		while( token.type() != S || token.value().charAt(0)!='}' ){
			if(token.type() == S && token.value().charAt(0)==',' ) throw IO.exception(IO.UNEXPECTED, ',', token.pos());
			v.add(attribute());
			if(next().type() == S && token.value().charAt(0)==',' && next().type() != 'S') 
				throw IO.exception(IO.UNEXPECTED, token.value(), token.pos());
		}
		return new TypedValue<Vector<Typed>>('J', v);
	}

	@Override
	protected Typed process() throws IOException {
		if( token.type()==S ) {
			char c = token.value().charAt(0);
			switch( c ){
				case '[': return array();
				case '{': return object();
				default: throw IO.exception(IO.UNEXPECTED, c, token.pos());
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