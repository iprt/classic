package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;

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
    private Map<Vertex<T>, Boolean> visited;

    /**
     * 联通分量的个数
     */
    private int componentCount;

    /**
     * value表示联通的组 用来判断两个点是否连接在一起
     */
    private Map<Vertex<T>, Integer> uf;

    public Component(Graph<T, W> g) {
        this.g = g;
        this.componentInit();
    }

    private void componentInit() {
        Set<Vertex<T>> vertices = this.g.vertices();
        this.visited = new HashMap<>(vertices.size());
        vertices.forEach(v -> this.visited.put(v, false));

        this.uf = new HashMap<>(vertices.size());
        vertices.forEach(v -> this.uf.put(v, -1));

        this.componentCount = 0;
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
        this.g.vertices().forEach(p -> {
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
     * @param v     the v
     * @param group the group
     */
    private void deepTraversal(Vertex<T> v, int group) {
        // 标记为访问了的
        this.visited.put(v, true);
        // 同一个组
        this.uf.put(v, group);

        this.g.adj(v).forEach(edge -> {
            Vertex<T> other = edge.other(v);

            // 边另外的点也没有被访问到
            boolean otherV = visited.get(other);
            if (!otherV) {
                this.deepTraversal(other, group);
            }
        });
    }

    public boolean hasPath(Vertex<T> from, Vertex<T> to) {
        Integer f = this.uf.get(from);
        Integer t = this.uf.get(to);
        if (f == null || t == null) {
            System.out.println("点位错误");
            return false;
        }
        return f.intValue() == t.intValue();
    }

}
