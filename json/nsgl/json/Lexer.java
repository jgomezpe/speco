package nsgl.json;

import nsgl.language.lexeme.Space;
import nsgl.language.lexeme.Symbol;

public class Lexer extends nsgl.language.Lexer{
	public static final String RESERVED = "reserved";
	
	public Lexer() { this(false); }
	
	public Lexer(boolean JSON) {
		add( new nsgl.integer.Parse(), 2 );
		add( new nsgl.real.Parse(), 1 );
		add( new nsgl.string.Parse(), 2 );
		if(!JSON) add( new nsgl.blob.Parse(), 2 );
		add( new nsgl.character.Parse(), 2 );
		add( new Space(), 2 );
		add( new Symbol("\\[\\]\\{\\},:"), 2 );
		add( new Reserved(), 2 );
	}
}