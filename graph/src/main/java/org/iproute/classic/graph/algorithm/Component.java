package org.iproute.classic.graph.algorithm;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * DfsComponent 深度遍历求无向图的联通分量
 *
 * @author winterfell
 * @since 2022/6/22
 */
public class Component<T, W extends Comparable<W>> {

    private Graph<T, W> g;

    /**
     * 点是否被访问过
     */
    private Map<Point<T>, Boolean> visited;

    /**
     * 联通分量的个数
     */
    private int componentCount;

    /**
     * value表示联通的组 用来判断两个点是否连接在一起
     */
    private Map<Point<T>, Integer> uf;

    public Component(Graph<T, W> g) {
        this.g = g;
        this.componentInit();
    }

    private void componentInit() {
        Set<Point<T>> points = this.g.allPoints();
        this.visited = new HashMap<>(points.size());
        points.forEach(p -> this.visited.put(p, false));

        this.uf = new HashMap<>(points.size());
        points.forEach(p -> this.uf.put(p, -1));

        this.componentCount = -1;
    }

    /**
     * 计算图的联通分量
     *
     * @return the int
     */
    public int calculate() {
        if (this.g.direct()) {
            System.out.println("图是有向图，不能求联通分量");
            return this.componentCount;
        }

        // 初始化为0
        this.componentCount = 0;

        // 随便拿出拿个点开始遍历
        this.g.allPoints().forEach(p -> {
            boolean v = this.visited.get(p);
            // 随便拿出一个点，如果图都是联通的，深度遍历一次就全部遍历并标记完了
            if (!v) {
                componentCount++;
                deepTraversal(p, componentCount);
            }
        });

        return componentCount;
    }

    /**
     * Deep traversal. 深度遍历
     *
     * @param p     the p
     * @param group the group
     */
    private void deepTraversal(Point<T> p, int group) {
        // 标记为访问了的
        this.visited.put(p, true);
        // 同一个组
        this.uf.put(p, group);

        this.g.adj(p).forEach(edge -> {
            Point<T> other = edge.other(p);

            // 边另外的点也没有被访问到
            boolean otherV = visited.get(other);
            if (!otherV) {
                this.deepTraversal(other, group);
            }
        });
    }

}
