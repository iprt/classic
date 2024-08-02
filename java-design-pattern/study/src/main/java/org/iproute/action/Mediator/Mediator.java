package org.iproute.action.Mediator;

/**
 * 中介者
 *
 * @author tech@intellij.io
 **/
public interface Mediator {

    void register(String dname, Department d);

    void command(String command);
}
