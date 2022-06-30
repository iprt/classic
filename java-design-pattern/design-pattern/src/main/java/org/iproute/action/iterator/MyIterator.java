package org.iproute.action.iterator;

/**
 * @author : zhuzhenjie
 **/
public interface MyIterator {
    void first();

    void last();

    boolean hasNext();

    void next();

    Object getCurrentObject();
}
