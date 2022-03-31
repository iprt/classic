package dp04_strategy;

/**
 * @author winterfell
 */
public class StringContext {

    /**
     * 策略的抽象
     */
    private StringStrategy stringStrategy;

    public StringContext(StringStrategy stringStrategy) {
        this.stringStrategy = stringStrategy;
    }

    /**
     * 主要用来修改策略
     *
     * @param stringStrategy the string strategy
     */
    public void setStringStrategy(StringStrategy stringStrategy) {
        this.stringStrategy = stringStrategy;
    }

    public String getNewString(String oldString) {
        return stringStrategy.action(oldString);
    }

}
