package dp01_observer.basic;

/**
 * @author tech@intellij.io
 */
public class Main {

    public static void main(String[] args) {

        // 定义一个主题
        Subject subject = new SubjectImpl();

        // 定义多个观察者
        Observer ob1 = new ObserverImpl1();
        Observer ob2 = new ObserverImpl2();

        subject.registerObserver(ob1);
        subject.registerObserver(ob2);
        subject.registerObserver(msg -> System.out.println("内部类观察者得到消息" + msg));

        subject.publish("first message");

        System.out.println("---------- ---------- ---------- ---------- ----------");

        // 删除一个观察者
        subject.removeObserver(ob1);
        subject.publish("second message");

    }

}
