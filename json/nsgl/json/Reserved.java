package nsgl.json;

import java.io.IOException;

import nsgl.character.CharacterSequence;
import nsgl.parse.Regex;

public class Reserved extends Regex{

	public Reserved(){ super("true|false|null", Lexer.RESERVED); }	
	
	@Override
	public Object instance(CharacterSequence input, String matched) throws IOException {
		if( matched.equals("true") ) return true;
		if( matched.equals("false") ) return false;
		if( matched.equals("null") ) return null;
		throw input.exception("·Invalid "+Lexer.RESERVED+"· ", 0);
	}
}