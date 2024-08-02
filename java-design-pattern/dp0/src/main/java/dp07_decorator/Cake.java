package dp07_decorator;

/**
 * @author tech@intellij.io
 */
public class Cake extends Sweet {

    @Override
    public String getDescription() {
        return "一个蛋糕";
    }

    @Override
    public double cost() {
        return 100;
    }
}
