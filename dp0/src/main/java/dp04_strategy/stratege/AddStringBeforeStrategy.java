package dp04_strategy.stratege;

import dp04_strategy.StringStrategy;

/**
 * @author winterfell
 */
public class AddStringBeforeStrategy implements StringStrategy {
    @Override
    public String action(String old) {
        return "before " + old;
    }
}
