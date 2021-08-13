package speco.function.iterative;

import speco.function.Function;

public interface Step<I,O> extends Function<O,O>{
    O init(I input);
}
