package nsgl.generic;

import java.io.IOException;

import nsgl.character.CharacterSequence;
import nsgl.json.JXON;
import nsgl.parse.Parseable;
import nsgl.stringify.Stringifyable;

public interface Thing extends Stringifyable, Parseable{
    JXON jxon();
    void jxon(JXON json) throws IOException;
    
    @Override
    default Object parse(CharacterSequence input) throws IOException {
	jxon(new JXON(input));
        return this;
    }
    
    @Override
    default String stringify() { return jxon().stringify(); }
}