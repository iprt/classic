package dp01_observer.basic2;

/**
 * @author tech@intellij.io
 */
public class Main {

    public static void main(String[] args) {

        Subject subject = new SubjectImpl();

        Listener listener1 = new ListenerImpl1();
        Listener listener2 = new ListenerImpl2();

        subject.addListener(listener1);
        subject.addListener(listener2);

        subject.addListener(s -> System.out.println("listener inner : do nothing"));

        subject.publishStatus(Status.START);

        subject.publishStatus(Status.START);

        subject.publishStatus(Status.END);


        System.out.println("---------- ---------- ---------- ---------- ----------");
        subject.removeListener(listener1);
        subject.publishStatus(Status.START);
    }

}
