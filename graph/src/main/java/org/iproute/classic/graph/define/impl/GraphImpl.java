package org.iproute.classic.graph.define.impl;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Namespace;
import org.iproute.classic.graph.define.Point;

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
public class GraphImpl<T, W extends Comparable<W>> implements Graph<T, W>, Namespace {

    /**
     * 图的隔离
     */
    private final String namespace;
    private boolean direct;

    /**
     * 邻接矩阵
     * <p>
     * key 为 点
     * value 为 边的集合
     */
    private Map<Point<T>, Set<Edge<W, T>>> graph;

    /**
     * 存储一张图中所有的边
     */
    private Set<Edge<W, T>> allEdges;

    /**
     * 存储一场图中所有的点
     */
    private Set<Point<T>> allPoints;


    /**
     * namespace: 图隔离
     *
     * @param namespace the namespace
     * @param direct    the direct
     */
    public GraphImpl(String namespace, boolean direct) {
        this.namespace = namespace;
        this.direct = direct;

        this.graph = new HashMap<>();
        this.allEdges = new HashSet<>();
        this.allPoints = new HashSet<>();

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

        System.out.println("点的个数为 : " + this.getPointCount());
        System.out.println("边的个数为 : " + this.getEdgeCount());
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
    public int getPointCount() {
        return this.allPoints.size();
    }

    @Override
    public int getEdgeCount() {
        return this.allEdges.size();
    }

    @Override
    public Set<Point<T>> allPoints() {
        return this.allPoints;
    }

    @Override
    public Set<Edge<W, T>> allEdges() {
        return this.allEdges;
    }

    @Override
    public void addEdge(Edge<W, T> edge) {

        if (!this.namespace.equals(edge.namespace())) {
            System.out.println("namespace error");
            return;
        }

        Point<T> from = edge.getFrom();
        Point<T> to = edge.getTo();
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
        this.allPoints.add(from);
        this.allPoints.add(to);
    }

    @Override
    public void connect(Point<T> from, Point<T> to, W w) {

        if (!this.namespace.equals(from.namespace()) || !this.namespace.equals(to.namespace())) {
            System.out.println("namespace error");
            return;
        }


        Edge<W, T> e = new Edge<>(namespace, from, to, w);
        this.addEdge(e);
    }

    @Override
    public boolean hasEdge(Point<T> x, Point<T> y) {
        return false;
    }

    @Override
    public Iterable<Edge<W, T>> adj(Point<T> point) {
        Set<Edge<W, T>> edges = this.graph.get(point);
        return Objects.isNull(edges)
                ? Collections.emptyList()
                : edges;
    }
}
