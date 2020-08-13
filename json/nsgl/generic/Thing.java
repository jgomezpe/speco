package nsgl.generic;

import java.io.IOException;

import nsgl.character.CharacterSequence;
import nsgl.json.JSON;
import nsgl.parse.Parseable;
import nsgl.stringify.Stringifyable;

public interface Thing extends Stringifyable, Parseable{
    JSON json();
    void json(JSON json) throws IOException;
    
    @Override
    default Object parse(CharacterSequence input) throws IOException {
	json(new JSON(input));
        return this;
    }
    
    @Override
    default String stringify() { return json().stringify(); }
}