package nsgl.json.parser;

import nsgl.language.Lexeme;
import nsgl.language.lexeme.SpaceLexeme;
import nsgl.language.lexeme.SymbolLexeme;
import nsgl.object.parser.ObjectLexer;

public class JSONLexer extends ObjectLexer{
	public static final char RESERVED = 'R';
	
	public JSONLexer() {
		add( new Lexeme(INTEGER, new nsgl.integer.Recover()) );
		add( new Lexeme(REAL, new nsgl.real.Recover()) );
		add( new Lexeme(STRING, new nsgl.string.Recover()) );
		add( new SpaceLexeme() );
		add( new SymbolLexeme("\\[\\]\\{\\},:") );
		add( new WordLexeme() );
	}
}