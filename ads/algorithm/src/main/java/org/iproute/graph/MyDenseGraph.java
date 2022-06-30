package org.iproute.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-26
 * Time    :       15:05
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 **/
public class MyDenseGraph implements GraphDesign {

    // 顶点个数
    private int n;
    // 边的个数
    private int m;

    // 表示两个顶点之前有没有边
    private boolean[][] g;

    // 表示是不是有向图
    private boolean directed;

    public MyDenseGraph(int n, boolean directed) {
        this.n = n;
        // 初始化的时候没有边
        this.m = 0;
        this.directed = directed;
        this.g = new boolean[n][n];
    }

    /**
     * 返回顶点的个数
     *
     * @return
     */
    @Override
    public int v() {
        return n;
    }

    /**
     * 返回边的个数
     *
     * @return
     */
    @Override
    public int e() {
        return m;
    }


    @Override
    public void addEdge(int x, int y) {
        // 添加一条从x->y的边
        if (hasEdge(x, y))
            return;

        g[x][y] = true;
        if (!directed) {
            g[y][x] = true;
        }
        this.m++;
    }

    @Override
    public boolean hasEdge(int x, int y) {
        return g[x][y];
    }

    // 返回v顶点所有的邻边
    @Override
    public Iterable<Integer> adj(int v) {
        List<Integer> adjV = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }

    @Override
    public void show() {
        for (int i = 0; i < this.v(); i++) {
            System.out.print(String.format("邻接矩阵 %d 的相邻边："));
            this.adj(i).forEach(e -> System.out.print(e + "  "));
            System.out.println();
        }
    }

    public boolean isDirected() {
        return directed;
    }
}
