package org.iproute.classic.graph.define;

import java.util.Set;

/**
 * Graph 图的描述
 * <p>
 * 目前只用邻接矩阵来描述图
 * <p>
 * 对图进行抽象化定义
 *
 * @author tech@intellij.io
 * @since 2022/6/13
 */
public interface Graph<T, W extends Comparable<W>> {

    /**
     * Namespace string.
     *
     * @return the string
     */
    String namespace();

    /**
     * 是否为有向图
     *
     * @return the boolean
     */
    boolean direct();

    /**
     * 展示这张图的所有信息
     */
    void show();

    /**
     * 获取点的个数
     *
     * @return the point count
     */
    int verticesNum();

    /**
     * 获取边的个数
     *
     * @return the edge count
     */
    int edgesNum();


    /**
     * 拿到所有的点
     *
     * @return the set
     */
    Set<Vertex<T>> vertices();

    /**
     * 拿到所有的边
     *
     * @return the set
     */
    Set<Edge<W, T>> edges();

    /**
     * 在图中添加一条边
     *
     * @param edge the edge
     */
    void addEdge(Edge<W, T> edge);

    /**
     * 连接两点
     *
     * @param from the from
     * @param to   the to
     * @param w    the w
     */
    void connect(Vertex<T> from, Vertex<T> to, W w);


    /**
     * 判断两点直接是否有边
     *
     * @param x the from
     * @param y the to
     * @return the boolean
     */
    boolean hasEdge(Vertex<T> x, Vertex<T> y);


    /**
     * 邻边迭代器
     * <p>
     * 根据一个点获取这个点所有的边
     *
     * @param vertex the point
     * @return the edge
     */
    Iterable<Edge<W, T>> adj(Vertex<T> vertex);

}
