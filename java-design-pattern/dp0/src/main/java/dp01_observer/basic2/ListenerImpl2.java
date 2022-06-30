package dp01_observer.basic2;

/**
 * ListenerImple2
 *
 * @author winterfell
 * @since 2021/7/8
 */
public class ListenerImpl2 implements Listener {

    @Override
    public void update(Subject subject) {

        // 这里还可以主动去操作subject（被观察者）
        System.out.println("listener 2 : " + subject.getClass());

    }
}
