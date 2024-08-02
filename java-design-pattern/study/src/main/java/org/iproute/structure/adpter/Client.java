package org.iproute.structure.adpter;

/**
 * 相当于笔记本电刀
 *
 * @author tech@intellij.io
 */
/**
 * @author tech@intellij.io
 */
public class Client {

    public void test1(Target t) {
        t.handleReq();
    }

    public static void main(String[] args) {
        Client c = new Client();
//		Target t = new Adapter();
//		c.test1(t);
        Adaptee adaptee = new Adaptee();
        Target t = new Adapter2(adaptee);
        c.test1(t);
    }

}
