package dp10_state;

/**
 * @author winterfell
 */
public class StopState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("this is stop state");
    }
}
