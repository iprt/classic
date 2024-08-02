package org.iproute.simpleGraph.graph;

import org.iproute.simpleGraph.Graph;

import java.util.ArrayList;

/**
 * @author tech@intellij.io
 * @create 2018-5-25
 * 邻接矩阵表示稀疏图
 */
public class SparseGraph implements Graph {
    private int n;
    private int m;
    private boolean directed;

    private ArrayList<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
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
    public void addEdge(int x, int y) {
        if (hasEdge(x, y)) {
            return;
        }
        g[x].add(y);
        if (x != y && !directed) {
//            g[y].add(x);
            addEdge(y, x);
        }
        m++;
    }

    @Override
    public boolean hasEdge(int x, int y) {
        for (int i = 0; i < g[x].size(); i++) {
            if (y == g[x].get(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int v) {
//        Collections.sort(g[v]);
        return g[v];
    }

    @Override
    public void show() {
        for (int i = 0; i < this.V(); i++) {
            System.out.print("邻接表" + i + ":");
            this.adj(i).forEach(e -> System.out.print(e + "    "));
            System.out.println();
        }
    }
}