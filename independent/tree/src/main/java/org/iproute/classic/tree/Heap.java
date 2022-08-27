package org.iproute.classic.tree;

/**
 * Heap
 *
 * @author zhuzhenjie
 * @since 2022/8/27
 */
public interface Heap<T extends Comparable<T>> extends Debugger{

    /**
     * Size int.
     *
     * @return the int
     */
    int size();

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    boolean isEmpty();

    /**
     * Add.
     *
     * @param t the t
     */
    void add(T t);

    /**
     * Extract max t.
     *
     * @return the t
     */
    T extractMax();

    /**
     * Max t.
     *
     * @return the t
     */
    T max();

}
