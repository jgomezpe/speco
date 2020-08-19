package nsgl.generic;

import nsgl.json.JSON;
import nsgl.stringify.Stringifyable;

public interface JSONCastable extends Stringifyable{
    JSON json();
    @Override
    default String stringify() { return json().stringify(); }
}