package org.iproute.graphw;

/**
 * @author tech@intellij.io
 * <p>
 * 有权图接口设计
 **/
public interface WeightGraphDesign<Weight extends Number & Comparable> {

    int v();

    int e();

    void addEdge(MyEdge<Weight> edge);

    boolean hadEdge(int x, int y);

    /**
     * 通过一个顶点找到邻边的迭代器
     *
     * @param v
     * @return
     */
    Iterable<MyEdge<Weight>> adj(int v);

    void show();
}
