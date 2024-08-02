package org.iproute.graphw;

import java.util.ArrayList;

/**
 * @author tech@intellij.io
 **/
public class MySparseWeightGraph<Weight extends Number & Comparable> implements WeightGraphDesign<Weight> {

    private int n;

    private int m;

    private ArrayList<MyEdge<Weight>>[] g;

    private boolean directed;

    public MySparseWeightGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.g = (ArrayList<MyEdge<Weight>>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    @Override
    public int v() {
        return this.n;
    }

    @Override
    public int e() {
        return this.m;
    }

    @Override
    public void addEdge(MyEdge<Weight> edge) {
        int x = edge.getX();
        int y = edge.getY();
        if (hadEdge(x, y)) {
            return;
        }
        g[x].add(edge);
        if (x != y && !directed) {
            g[y].add(new MyEdge<>(y, x, edge.getWeight()));
        }
        this.m++;
    }

    @Override
    public boolean hadEdge(int x, int y) {
        ArrayList<MyEdge<Weight>> yList = g[x];
        for (MyEdge<Weight> tmp : yList) {
            if (tmp.other(x) == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<MyEdge<Weight>> adj(int v) {
        return g[v];
    }

    @Override
    public void show() {
        for (int i = 0; i < this.v(); i++) {
            System.out.print("邻接表" + i + " 的邻边:");
            g[i].forEach(e -> {
                int x = e.getX();
                Weight weight = e.getWeight();
                System.out.print("[point:" + e.other(x) + " weight:" + weight + "]  ");
            });
            System.out.println();
        }
    }
}
