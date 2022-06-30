package dp01_observer.basic2;


/**
 * @author winterfell
 */
public class ListenerImpl1 implements Listener {

    @Override
    public void update(Subject subject) {
        Status status = subject.status();

        if (status.equals(Status.START)) {
            System.out.println("listener 1 : status start");
        }

        if (status.equals(Status.END)) {
            System.out.println("listener 1 : status end");
        }
    }
}
