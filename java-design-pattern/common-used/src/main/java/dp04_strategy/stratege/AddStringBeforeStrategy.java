package dp04_strategy.stratege;

import dp04_strategy.StringStrategy;

/**
 * @author tech@intellij.io
 */
public class AddStringBeforeStrategy implements StringStrategy {
    @Override
    public String action(String old) {
        return "before " + old;
    }
}
