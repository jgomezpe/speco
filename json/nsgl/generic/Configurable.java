package nsgl.generic;

import java.io.IOException;

import nsgl.character.CharacterSequence;
import nsgl.json.JSON;
import nsgl.parse.Parseable;

public interface Configurable extends Parseable{
    void config(JSON json);
    
    @Override
    default Object parse(CharacterSequence input) throws IOException {
	config(new JSON(input));
        return this;
    }
}