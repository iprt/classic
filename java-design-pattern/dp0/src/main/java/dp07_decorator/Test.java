package dp07_decorator;

/**
 * @author winterfell
 */
public class Test {

    public static void main(String[] args) {

        Cake cake = new Cake();

        FruitCake fruitCake = new FruitCake(cake);

        CandleCake candleCake1 = new CandleCake(cake);
        System.out.println("第一次装饰");

        System.out.println("fruitCake: " + fruitCake.getDescription() + " cost:" + fruitCake.cost());
        System.out.println("candleCake1: " + candleCake1.getDescription() + " cost:" + candleCake1.cost());

        CandleCake candleCake2 = new CandleCake(fruitCake);

        System.out.println("第二次装饰");
        System.out.println("candleCake2: " + candleCake2.getDescription() + " cost:" + candleCake2.cost());
    }
}
