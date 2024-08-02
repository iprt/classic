package dp07_decorator;

/**
 * @author tech@intellij.io
 */
public class CandleCake extends Decorator {

    private Sweet sweet;

    public CandleCake(Sweet sweet) {
        this.sweet = sweet;
    }

    @Override
    public String getDescription() {
        return sweet.getDescription() + "+蜡烛";
    }

    @Override
    public double cost() {
        return sweet.cost() + 15;
    }
}
