package org.iproute.avltree;

/**
 * @author zhuzhenjie
 **/
public interface Tree<K extends Comparable, V> {

    int size();

    void add(K key, V value);

    boolean isEmpty();

    boolean contains(K key);

    V getMin();

    V getMax();

    void remove(K key);

}
