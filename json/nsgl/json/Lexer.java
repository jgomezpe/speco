package nsgl.json;

import nsgl.language.lexeme.Space;
import nsgl.language.lexeme.Symbol;

public class Lexer extends nsgl.language.Lexer{
	public static final String RESERVED = "reserved";
	
	public Lexer() {
		add( new nsgl.number.Parse());
		add( new nsgl.string.Parse());
		add( new nsgl.character.Parse());
		add( new Space());
		add( new Symbol("\\[\\]\\{\\},:"));
		add( new Reserved());
	}
}