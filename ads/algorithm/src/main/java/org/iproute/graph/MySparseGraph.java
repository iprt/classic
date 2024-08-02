package org.iproute.graph;

import java.util.ArrayList;

/**
 * @author tech@intellij.io
 **/
public class MySparseGraph implements GraphDesign {

    private int n;

    private int m;

    private ArrayList<Integer>[] g;

    private boolean directed;

    public MySparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
    }


    @Override
    public int v() {
        return n;
    }

    @Override
    public int e() {
        return m;
    }

    @Override
    public void addEdge(int x, int y) {
        if (hasEdge(x, y))
            return;

        g[x].add(y);

        // 邻接矩阵有向图要在这里注意
        // 如何x=y 添加了边的话 会造成重复
        if (x != y && !directed) {
            addEdge(y, x);
        }
        this.m++;
    }

    @Override
    public boolean hasEdge(int x, int y) {
        ArrayList<Integer> es = g[x];
        for (int tmp : es) {
            if (tmp == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return g[v];
    }

    @Override
    public void show() {
        for (int i = 0; i < this.v(); i++) {
            ArrayList<Integer> es = g[i];
            System.out.print(String.format("邻接表 %d 的相邻边:", i));
            es.forEach(element -> System.out.print(element + " "));
            System.out.println();
        }
    }
}
