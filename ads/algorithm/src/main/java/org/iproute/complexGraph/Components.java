package org.iproute.complexGraph;

import org.iproute.complexGraph.define.Edge;
import org.iproute.complexGraph.define.Graph;
import org.iproute.complexGraph.define.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :       zhuzhenjie
 * 计算图是不是联通的
 **/
public class Components<Element, Relation extends Comparable> {

    /**
     * 图的引用
     */
    private Graph<Element, Relation> g;

    /**
     * 判断点是否被访问过
     */
    private Map<Point<Element>, Boolean> visited;

    /**
     * 联通分量的个数
     */
    private int ccount;

    /**
     * value表示联通的组 用来判断两个点是否连接在一起
     */
    private Map<Point<Element>, Integer> uf;

    /**
     * 组 - 组里面的点 通过uf 初始化
     */
    private Map<Integer, List<Point<Element>>> group2Points;


    /**
     * 某个点被访问的状态
     * 0 表示这个节点没有被访问过，1表示这个节点被访问过，2表示这个节点的后面的节点都被访问过
     */
    private Map<Point<Element>, Integer> pointStatus;

    /**
     * 是否有环
     */
    private boolean hasRing;

    public Components(Graph<Element, Relation> g) {
        this.g = g;
        this.ccount = 0;
        this.visited = new HashMap<Point<Element>, Boolean>() {
            {
                g.getAllPoints().forEach(p -> {
                    put(p, Boolean.FALSE);
                });
            }
        };
        // 并查集
        this.uf = new HashMap<Point<Element>, Integer>() {
            {
                g.getAllPoints().forEach(p -> {
                    put(p, -1);
                });
            }
        };
        // 所有的节点都没有被访问
        this.pointStatus = new HashMap<Point<Element>, Integer>() {
            {
                g.getAllPoints().forEach(p -> put(p, 0));
            }
        };

        for (Point<Element> p : g.getAllPoints()) {
            // 如果这个点没有被遍历过
            if (!visited.get(p)) {
                deepTraversal(p);
                this.ccount++;
            }
        }
        // 初始化没有环
        this.hasRing = false;
        // 通过uf初始化group2Points
        this.group2Points = new HashMap<Integer, List<Point<Element>>>() {
            {
                uf.forEach((point, group) -> {
                    List<Point<Element>> points = this.get(group) == null ? new ArrayList<>() : this.get(group);
                    points.add(point);
                    this.put(group, points);
                });
            }
        };

        for (List<Point<Element>> pointList : group2Points.values()) {
            if (hasRing) {
                break;
            }
            deepTraversalForRing(pointList.get(0));
        }

    }

    /**
     * 获取联通数量
     *
     * @return 联通分量的数量
     */
    public int getComponentNum() {
        return ccount;
    }

    /**
     * 获取联通分量的个数，通过组
     *
     * @return 通过组的方式获得联通分量的个数
     */
    public int getComponentNum2() {
        return group2Points.size();
    }


    /**
     * 判断两点是否连接
     *
     * @param p1 点1
     * @param p2 点2
     * @return true 两个点连接再一起，false两个点没有连接在一起
     */
    public boolean isConnected(Point<Element> p1, Point<Element> p2) {
        // 如果这两个点本来就不存在
        if (!this.g.getAllPoints().contains(p1) || !this.g.getAllPoints().contains(p2)) {
            return false;
        }
        return uf.get(p1).equals(uf.get(p2));
    }

    /**
     * 判断是否有环
     *
     * @return
     */
    public boolean hasRing() {
        return this.hasRing;
    }


    /**
     * 深度遍历求联通分量 利用并查集
     *
     * @param point 随机选取的点
     */
    private void deepTraversal(Point<Element> point) {
        // 标明这个点被访问过
        this.visited.put(point, true);
        // 同一组用count的值数表示 这是一个小技巧
        this.uf.put(point, ccount);

        g.adj(point).forEach(edge -> {
            // 一定要加如果没有被访问
            if (!visited.get(edge.other(point))) {
                deepTraversal(edge.other(point));
            }
        });
    }

    /**
     * 深度遍历利用状态来判断是否有环
     * <p>
     * 深度遍历一遍 每个节点都会经过3个状态 没有被访问 访问过 自己节点后面的节点都被访问了
     */
    private void deepTraversalForRing(Point<Element> point) {
        // 这个点被访问过了
        this.pointStatus.put(point, 1);
        // 遍历子节点
        for (Edge<Element, Relation> edge : this.g.adj(point)) {
            Point<Element> childP = edge.other(point);
            if (this.pointStatus.get(childP) != 1) {
                if (this.pointStatus.get(childP) == 2) {
                    this.hasRing = true;
                    break;
                } else if (this.pointStatus.get(childP) == 0) {
                    deepTraversalForRing(childP);
                }
            }
        }
        // 这个点后面所有的点被访问过了
        this.pointStatus.put(point, 2);
    }
}
