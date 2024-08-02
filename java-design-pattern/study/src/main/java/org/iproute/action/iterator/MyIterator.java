package org.iproute.action.iterator;

/**
 * @author tech@intellij.io
 **/
public interface MyIterator {
    void first();

    void last();

    boolean hasNext();

    void next();

    Object getCurrentObject();
}
