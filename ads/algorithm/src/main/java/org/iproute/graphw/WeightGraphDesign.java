package org.iproute.graphw;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-27
 * Time    :       15:51
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
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
