package org.iproute.segment;

/**
 * @author zhuzhenjie
 * 线段树的融合器
 **/
public interface Merger<E> {

    E merge(E a, E b);
}
