package dp02_factory.abstractFactory;

/**
 * The type Main.
 *
 * @author winterfell
 */
public class Main {

    public static void main(String[] args) {

        Computer excellent = new ExcellentComputer();

        System.out.println(excellent.createCpu().cpuName());


        Computer simple = new SimpleComputer();

        System.out.println(simple.createGraphicsCard().graphicsCardName());
    }

}
