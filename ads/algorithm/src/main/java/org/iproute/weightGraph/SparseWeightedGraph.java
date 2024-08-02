package org.iproute.weightGraph;

import java.util.ArrayList;

/**
 * @author tech@intellij.io
 * @create 2018-5-28
 */
public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    private int n;

    private int m;

    private boolean directed;

    private ArrayList<Edge<Weight>>[] g;


    public SparseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = (ArrayList<Edge<Weight>>[]) new ArrayList[n];
        // 初始化邻接表
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    @Override
    public int V() {
        return n;
    }

    @Override
    public int E() {
        return m;
    }

    @Override
    public void addEdge(Edge e) {
        if (hasEdge(e.v(), e.w())) {
            return;
        }
        g[e.v()].add(new Edge<>(e));
        if (e.v() != e.w() && !directed) {
            g[e.w()].add(new Edge(e.w(), e.v(), e.wt()));
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        ArrayList<Edge<Weight>> d = g[v];
        for (Edge<Weight> tmp : d) {
            if (tmp.other(v) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++) {
                Edge e = g[i].get(j);
                System.out.print("( to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        return g[v];
    }
}