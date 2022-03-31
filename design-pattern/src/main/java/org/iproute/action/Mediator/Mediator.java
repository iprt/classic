package org.iproute.action.Mediator;

/**
 * 中介者
 *
 * @author : zhuzhenjie
 **/
public interface Mediator {

    void register(String dname, Department d);

    void command(String command);
}
