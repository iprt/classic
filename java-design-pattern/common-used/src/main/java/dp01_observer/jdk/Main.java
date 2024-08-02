package dp01_observer.jdk;

import java.util.Observable;

/**
 * @author tech@intellij.io
 */
public class Main {

    public static void main(String[] args) {

        // Observable被观察者
        Observable subject = new Observable() {
            @Override
            public void notifyObservers(Object arg) {
                // 根据源码的阅读需要主动改变
                this.setChanged();
                super.notifyObservers(arg);
            }
        };

        JdkObserver jdkObserver = new JdkObserver();

        subject.addObserver(jdkObserver);

        subject.addObserver((o, arg) -> {

            System.out.println("[inner class] 被观察者自身 o ：" + o);

            System.out.println("[inner class] 被观察者主动传递的值 ：" + arg);
        });

        subject.notifyObservers("this is a message");
    }

}
