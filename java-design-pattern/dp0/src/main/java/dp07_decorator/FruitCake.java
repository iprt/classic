package dp07_decorator;

/**
 * 真正的装饰
 *
 * @author tech@intellij.io
 */
public class FruitCake extends Decorator {

    /**
     * 需要被装饰的类
     */
    private Sweet sweet;


    public FruitCake(Sweet sweet) {
        this.sweet = sweet;
    }

    @Override
    public String getDescription() {
        return sweet.getDescription() + "+水果";
    }

    @Override
    public double cost() {
        return sweet.cost() + 10;
    }


}
