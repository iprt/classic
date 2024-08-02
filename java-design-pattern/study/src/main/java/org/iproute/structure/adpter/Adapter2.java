package org.iproute.structure.adpter;

/**
 * 对象适配器
 *
 * @author tech@intellij.io
 */
/**
 * @author tech@intellij.io
 */
public class Adapter2 implements Target {

    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        super();
        this.adaptee = adaptee;
    }

    @Override
    public void handleReq() {
        adaptee.request();
    }

}
