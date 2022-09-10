package org.iproute.classic.graph.define.sparse;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Namespace;
import org.iproute.classic.graph.define.Vertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 图的实现
 *
 * @author winterfell
 * @since 2022/6/14
 */
public class SparseGraph<T, W extends Comparable<W>> implements Graph<T, W>, Namespace {

    /**
     * 图的隔离
     */
    private final String namespace;
    private final boolean direct;

    /**
     * 邻接矩阵
     * <p>
     * key 为 点
     * value 为 边的集合
     */
    private final Map<Vertex<T>, Set<Edge<W, T>>> graph;

    /**
     * 存储一张图中所有的边
     */
    private final Set<Edge<W, T>> allEdges;

    /**
     * 存储一场图中所有的点
     */
    private final Set<Vertex<T>> allVertices;


    /**
     * namespace: 图隔离
     *
     * @param namespace the namespace
     * @param direct    the direct
     */
    public SparseGraph(String namespace, boolean direct) {
        this.namespace = namespace;
        this.direct = direct;

        this.graph = new HashMap<>();
        this.allEdges = new HashSet<>();
        this.allVertices = new HashSet<>();

    }

    @Override
    public String namespace() {
        return this.namespace;
    }

    @Override
    public boolean direct() {
        return this.direct;
    }

    @Override
    public void show() {
        System.out.println("是否为有向图：" + this.direct());
        System.out.println("点的个数为 : " + this.verticesNum());
        System.out.println("边的个数为 : " + this.edgesNum());
        System.out.println("--- show graph start ---");

        graph.forEach((p, es) -> {
            StringJoiner sj = new StringJoiner("    ");
            es.forEach(e -> {
                sj.add(String.format("%s->%s: %s", e.getFrom().getSign(), e.getTo().getSign(), e.getW()));
            });
            System.out.println(p.getSign() + " : " + sj.toString());
        });

        System.out.println("--- show graph end ---");
        System.out.println();
    }

    @Override
    public int verticesNum() {
        return this.allVertices.size();
    }

    @Override
    public int edgesNum() {
        return this.allEdges.size();
    }

    @Override
    public Set<Vertex<T>> vertices() {
        return this.allVertices;
    }

    @Override
    public Set<Edge<W, T>> edges() {
        return this.allEdges;
    }

    @Override
    public void addEdge(Edge<W, T> edge) {

        if (!this.namespace.equals(edge.namespace())) {
            System.out.println("namespace error");
            return;
        }

        Vertex<T> from = edge.getFrom();
        Vertex<T> to = edge.getTo();
        W w = edge.getW();

        // 自环边，不做操作
        if (from.equals(to)) {
            return;
        }

        Set<Edge<W, T>> fromEdges = this.graph.get(from);
        // 邻接表的初始化
        if (Objects.isNull(fromEdges)) {
            fromEdges = new HashSet<>();
            this.graph.put(from, fromEdges);
        }

        // 真正添加边的操作
        fromEdges.add(edge);
        this.allEdges.add(edge);

        // 无向图 反过来再添加一次边
        if (!direct) {
            Set<Edge<W, T>> toEdges = this.graph.get(to);
            if (Objects.isNull(toEdges)) {
                toEdges = new HashSet<>();
                this.graph.put(to, toEdges);
            }

            toEdges.add(new Edge<>(this.namespace, to, from, w));
            this.allEdges.add(edge);
        }

        // 添加点
        this.allVertices.add(from);
        this.allVertices.add(to);
    }

    @Override
    public void connect(Vertex<T> from, Vertex<T> to, W w) {
        if (!this.namespace.equals(from.namespace()) || !this.namespace.equals(to.namespace())) {
            System.out.println("namespace error");
            return;
        }

        Edge<W, T> e = new Edge<>(namespace, from, to, w);
        this.addEdge(e);
    }

    @Override
    public boolean hasEdge(Vertex<T> x, Vertex<T> y) {
        return false;
    }

    @Override
    public Iterable<Edge<W, T>> adj(Vertex<T> vertex) {
        Set<Edge<W, T>> edges = this.graph.get(vertex);
        return Objects.isNull(edges)
                ? Collections.emptyList()
                : edges;
    }
}
