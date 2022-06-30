package dp05_adapter.basic;

/**
 * @author winterfell
 */
public class Main {

    public static void main(String[] args) {

        Target old = new Adaptee();

        Target adapter = new Adapter(old);


        System.out.println(old.getValue());

        System.out.println("#############################");

        System.out.println(adapter.getValue());

    }

}
