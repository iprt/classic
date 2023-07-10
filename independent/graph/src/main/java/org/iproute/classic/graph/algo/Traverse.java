package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;

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

    private final Graph<T, W> g;

    private Map<Vertex<T>, Boolean> visited;

    public Traverse(Graph<T, W> g) {
        this.g = g;
    }

    private void init() {
        Set<Vertex<T>> ps = this.g.vertices();
        this.visited = new HashMap<>(ps.size());
        ps.forEach(p -> this.visited.put(p, false));
    }

    /**
     * 广度遍历
     *
     * @param base the base 基点
     */
    public void bfs(Vertex<T> base, Consumer<Vertex<T>> action) {
        this.init();
        this.doBfs(base, action);
    }

    private void doBfs(Vertex<T> base, Consumer<Vertex<T>> action) {
        Queue<Vertex<T>> queue = new LinkedList<>();

        queue.add(base);

        while (!queue.isEmpty()) {
            Vertex<T> p = queue.poll();
            this.visited.put(p, true);
            action.accept(p);

            Iterable<Edge<W, T>> edges = this.g.adj(p);
            for (Edge<W, T> edge : edges) {
                Vertex<T> other = edge.other(p);
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
    public void dfs(Vertex<T> base, Consumer<Vertex<T>> action) {
        this.init();
        this.doDfs(base, action);
    }

    private void doDfs(Vertex<T> p, Consumer<Vertex<T>> action) {
        this.visited.put(p, true);
        action.accept(p);
        Iterable<Edge<W, T>> edges = this.g.adj(p);
        edges.forEach(edge -> {
            Vertex<T> other = edge.other(p);
            Boolean v = visited.get(other);
            if (!v) {
                doBfs(other, action);
            }
        });
    }

}
