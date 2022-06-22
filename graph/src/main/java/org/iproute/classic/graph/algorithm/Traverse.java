package org.iproute.classic.graph.algorithm;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Point;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 图的遍历，针对无向图 单联通分量
 *
 * @author winterfell
 * @since 2022/6/22
 */
public class Traverse<T, W extends Comparable<W>> {

    private Graph<T, W> g;

    private Map<Point<T>, Boolean> visited;

    public Traverse(Graph<T, W> g) {
        this.g = g;
    }

    private void init() {
        Set<Point<T>> ps = this.g.allPoints();
        this.visited = new HashMap<>(ps.size());
        ps.forEach(p -> this.visited.put(p, false));
    }

    /**
     * 广度遍历
     *
     * @param base the base 基点
     */
    public void bfs(Point<T> base, Consumer<Point<T>> action) {
        this.init();
        this.doBfs(base, action);
    }

    private void doBfs(Point<T> base, Consumer<Point<T>> action) {
        Queue<Point<T>> queue = new LinkedList<>();

        queue.add(base);

        while (!queue.isEmpty()) {
            Point<T> p = queue.poll();
            this.visited.put(p, true);
            action.accept(p);

            Iterable<Edge<W, T>> edges = this.g.adj(p);
            for (Edge<W, T> edge : edges) {
                Point<T> other = edge.other(p);
                boolean v = this.visited.get(other);
                if (!v) {
                    queue.add(other);
                }
            }
        }
    }

    /**
     * 深度遍历
     *
     * @param base the base 基点
     */
    public void dfs(Point<T> base, Consumer<Point<T>> action) {
        this.init();
        this.doDfs(base, action);
    }

    private void doDfs(Point<T> p, Consumer<Point<T>> action) {
        this.visited.put(p, true);
        action.accept(p);
        Iterable<Edge<W, T>> edges = this.g.adj(p);
        edges.forEach(edge -> {
            Point<T> other = edge.other(p);
            Boolean v = visited.get(other);
            if (!v) {
                doBfs(other, action);
            }
        });
    }

}
