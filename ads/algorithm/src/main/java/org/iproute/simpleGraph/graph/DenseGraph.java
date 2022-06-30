package org.iproute.simpleGraph.graph;

import org.iproute.simpleGraph.Graph;

import java.util.ArrayList;

/**
 * @author zzj
 * @create 2018-5-25
 * 邻接矩阵表示图
 * 稠密图的表示方式
 */
public class DenseGraph implements Graph {
    /**
     * n:顶点个数
     * m:边个数
     */
    private int n;
    private int m;

    // boolean 来表示两个点之间有没有边
    private boolean[][] g;

    // 邻接矩阵存储图

    private boolean directed;

    // 初始化图
    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0; // 初始化的时没有边
        this.directed = directed;
        g = new boolean[n][n];
    }

    // 返回顶点的个数
    @Override
    public int V() {
        return n;
    }

    // 返回边的个数
    @Override
    public int E() {
        return m;
    }

    // 向图中添加一条边 从x->y的一条边
    @Override
    public void addEdge(int x, int y) {
        // 先判断x->y是否右边
        if (hasEdge(x, y)) {
            return;
        } else {
            g[x][y] = true;
            if (!directed) {
                g[y][x] = true;
//                addEdge(y, x);
            }
            m++;
        }
    }

    @Override
    public boolean hasEdge(int x, int y) {
        // 从图中搜寻
        return g[x][y];
    }

    // 返回v顶点所有的邻边
    // 迭代器
    @Override
    public Iterable<Integer> adj(int v) {
        ArrayList<Integer> adjV = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }

    @Override
    public void show() {
        for (int i = 0; i < this.V(); i++) {
            System.out.print("邻接矩阵" + i + ":");
            this.adj(i).forEach(e -> System.out.print(e + "    "));
            System.out.println();
        }
    }
}