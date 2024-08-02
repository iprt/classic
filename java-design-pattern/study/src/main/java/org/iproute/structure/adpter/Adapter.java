package org.iproute.structure.adpter;

/**
 * 适配器
 * (转接口)
 * 类适配器模式
 *
 * @author tech@intellij.io
 */
/**
 * @author tech@intellij.io
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void handleReq() {
        this.request();
    }
}
