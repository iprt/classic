package winterfell.graph.define;

import java.util.Set;

/**
 * 权重图抽象
 *
 * @author zhuzhenjie
 */
public interface Graph<Element, Relation extends Comparable> {

    /**
     * 获得边的个数
     *
     * @return 边的个数
     */
    int getEdgeNums();

    /**
     * 获得顶点的个数
     *
     * @return 顶点个数
     */
    int getPointNums();

    /**
     * 获取所有的点
     *
     * @return 所有点
     */
    Set<Point<Element>> allPoints();

    /**
     * 获取所有的边
     *
     * @return 所有的边
     */
    Set<Edge<Element, Relation>> allEdges();

    /**
     * 添加一条边
     *
     * @param edge 边
     */
    void addEdge(Edge<Element, Relation> edge);

    /**
     * 建立两个点之间的连接
     *
     * @param from     第一个点
     * @param to       第二个点
     * @param relation 权重
     */
    void connectPoints(Point<Element> from, Point<Element> to, Relation relation);

    /**
     * 判断两个点是否有连接边
     *
     * @param from 第一个点
     * @param to   第二个点
     * @return true 有 false 没有
     */
    boolean hasEdge(Point<Element> from, Point<Element> to);

    /**
     * 邻边迭代器
     * 根据一个点获取这个点所有的边
     *
     * @param p 某个点
     * @return 邻边迭代器
     */
    Iterable<Edge<Element, Relation>> adj(Point<Element> p);

    /**
     * 显示这张图的详细信息
     */
    void show();

}
