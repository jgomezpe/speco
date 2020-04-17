package nsgl.json.parser;

import java.io.IOException;

import nsgl.exception.IO;
import nsgl.language.Lexeme;
import nsgl.recover.RegexRecover;

public class WordLexeme extends Lexeme{

	public WordLexeme(){ 
		super(JSONLexer.RESERVED, 
		new RegexRecover("true|false|null") {
		
			@Override
			public Object instance(String input) throws IOException {
				if( input.equals("true") ) return true;
				if( input.equals("false") ) return false;
				if( input.equals("null") ) return null;
				throw IO.exception(JSONParser.NOVALID, input);
			}
		}); 
	}
}