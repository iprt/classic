package dp09_template;

/**
 * 模板方法模式 共用的方法抽象出来
 *
 * @author tech@intellij.io
 */
public abstract class OneDayWorkTemplate implements Work {

    protected void wakeUp() {
        System.out.println("起床 。。。");
    }

    protected void sleep() {
        System.out.println("睡觉 。。。");
    }

    public void workDetail() {
        this.wakeUp();
        this.work();
        this.sleep();
    }

}
