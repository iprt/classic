package org.iproute.complexGraph.define;

import java.util.*;

/**
 * @author zhuzhenjie
 * 图的实现 邻接表
 */
public class WeightedGraph<Element, Relation extends Comparable> implements Graph<Element, Relation> {

    /**
     * 边的个数
     */
    private int edgeNums;

    /**
     * 是否是有向图
     */
    private boolean direct;

    /**
     * 临街矩阵表示的图 利用set作为边的集合提高效率
     */
    private Map<Point<Element>, Set<Edge<Element, Relation>>> graph;

    /**
     * 所有的边
     */
    private Set<Edge<Element, Relation>> allEdges;

    /**
     * 构造一张图，确认是否是有向图
     *
     * @param direct 是否有向或者无向图
     */
    public WeightedGraph(boolean direct) {
        this.direct = direct;
        this.edgeNums = 0;
        this.graph = new HashMap<>();
        this.allEdges = new HashSet<>();
    }

    /**
     * 获取边的个数
     *
     * @return 边的个数
     */
    @Override
    public int getEdgeNums() {
        return edgeNums;
    }

    /**
     * 获取点的个数
     *
     * @return 点的个数
     */
    @Override
    public int getPointNums() {
        return this.graph.size();
    }

    @Override
    public Set<Point<Element>> getAllPoints() {
        return this.graph.keySet();
    }

    @Override
    public Set<Edge<Element, Relation>> getAllEdges() {
        return this.allEdges;
    }

    /**
     * 添加一条边
     * 判断是否是自己指向自己的边
     *
     * @param edge 边
     */
    @Override
    public void addEdge(Edge<Element, Relation> edge) {

        Point<Element> from = edge.getFrom();
        Point<Element> to = edge.getTo();
        Relation relation = edge.getRelation();

        // 指向自己的边不添加 已经存在的边不添加
        if (from.equals(to)) {
            return;
        }
        // edge的边之前已经存在的处理
        if (this.hasEdge(from, to)) {
            // tips:使用list作为边的集合 问题在于替换边的时候需要遍历寻找出边并替代以前的内容，使用Set作为集合直接把边删除放入 更新权重
            Set<Edge<Element, Relation>> edges = graph.get(from);
            // 先remove
            edges.remove(new Edge<>(from, to, relation));
            // 再添加 更新权重
            edges.add(edge);
            // 如果是无向图 a->b 的权值修改的同时修改 b->a 的权值
            if (!direct) {
                edges = graph.get(to);
                edges.remove(new Edge<>(to, from, relation));
                edges.add(new Edge<>(to, from, relation));
            }
            return;
        }
        Set<Edge<Element, Relation>> fromEdges;
        if (graph.containsKey(from)) {
            fromEdges = graph.get(from);
        } else {
            fromEdges = new HashSet<>();
            graph.put(from, fromEdges);
        }

        if (!graph.containsKey(to)) {
            // 为另外一个边添加集合
            graph.put(to, new HashSet<>());
        }
        /*
         * 邻接表添加边
         * 使用Set作为集合直接把边放入 更新权重
         */
        fromEdges.add(edge);
        this.allEdges.add(edge);
        if (!direct) {
            Set<Edge<Element, Relation>> toEdges;
            if (graph.containsKey(to)) {
                toEdges = graph.get(to);
            } else {
                toEdges = new HashSet<>();
                graph.put(to, toEdges);
            }
            Edge<Element, Relation> reverseEdge = new Edge<>(to, from, relation);
            toEdges.add(reverseEdge);
            this.allEdges.add(reverseEdge);
        }
        this.edgeNums++;
    }

    @Override
    public void connectPoints(Point<Element> from, Point<Element> to, Relation relation) {
        Edge<Element, Relation> edge = new Edge<>(from, to, relation);
        this.addEdge(edge);
    }

    /**
     * 判断点a和点b之间有没有边
     *
     * @param from
     * @param to
     * @return
     */
    @Override
    public boolean hasEdge(Point<Element> from, Point<Element> to) {
        Set<Edge<Element, Relation>> aes = (Set<Edge<Element, Relation>>) this.adj(from);
        if (aes == null) {
            return false;
        }
        return aes.contains(new Edge<Element, Relation>(from, to, null));
    }

    /**
     * 获取点p的所有邻边
     *
     * @param p 点p
     * @return 点p所有的领边
     */
    @Override
    public Iterable<Edge<Element, Relation>> adj(Point<Element> p) {
        return graph.get(p);
    }

    /**
     * 是否为有向图
     *
     * @return 是否为有向图
     */
    public boolean isDirect() {
        return direct;
    }

    @Override
    public void show() {
        graph.forEach((p, es) -> {
            StringJoiner sj = new StringJoiner("    ");
            es.forEach(e -> {
                sj.add("{" + e.other(p).toString() + ",Relation:" + e.getRelation().toString() + "}");
            });
            System.out.println(p + ":" + sj.toString());
        });
    }

    public static void main(String[] args) {
        Graph<String, String> wg = new WeightedGraph<>(false);

        Point<String> a1 = new Point<>("a", "1");
        Point<String> a2 = new Point<>("a", "2");
        Point<String> b1 = new Point<>("b", "1");
        Point<String> b2 = new Point<>("b", "2");

        wg.connectPoints(a1, b1, "a1 and b1");
        wg.connectPoints(a2, b1, "a2 and b1");
        wg.connectPoints(a2, b2, "a2 and b2");
        System.out.println("图的边的个数：" + wg.getEdgeNums());
        System.out.println("图的点的个数：" + wg.getPointNums());
        wg.show();
    }
}
