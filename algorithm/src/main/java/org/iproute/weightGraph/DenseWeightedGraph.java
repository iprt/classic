package org.iproute.weightGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @create 2018-5-28
 */
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph<Weight> {

    private int n, m;

    private Edge<Weight>[][] g;         // 图的具体数据
    private boolean directed;   // 是否为有向图

    public DenseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new Edge[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = null;
            }
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
    public void addEdge(Edge<Weight> e) {
        if (hasEdge(e.v(), e.w())) {
            return;
        }
        g[e.v()][e.w()] = new Edge<>(e);
        if (e.v() != e.w() && !directed) {
            g[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return g[v][w] != null;
    }

    @Override
    public void show() {
        // 显示图的信息
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != null) {
                    System.out.print(g[i][j].wt() + "    ");
                } else {
                    System.out.print("NULL    ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        List<Edge<Weight>> adjV = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i] != null) {
                adjV.add(g[v][i]);
            }
        }
        return adjV;
    }
}