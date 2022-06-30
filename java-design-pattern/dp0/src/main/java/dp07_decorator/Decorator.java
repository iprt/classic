package dp07_decorator;

/**
 * 装饰的抽象
 *
 * @author winterfell
 */
public abstract class Decorator extends Sweet {
    /**
     * 复写需要被装饰的方法
     *
     * @return
     */
    @Override
    public abstract String getDescription();
}
