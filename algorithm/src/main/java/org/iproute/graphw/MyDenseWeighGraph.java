package org.iproute.graphw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-27
 * Time    :       15:54
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 * <p>
 * 邻接举证
 **/
public class MyDenseWeighGraph<Weight extends Number & Comparable> implements WeightGraphDesign<Weight> {

    // 顶点个数
    private int n;

    // 边的个数
    private int m;

    private MyEdge<Weight>[][] g;

    private boolean directed;

    public MyDenseWeighGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.g = (MyEdge<Weight>[][]) new MyEdge[n][n];
        this.directed = directed;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = null;
            }
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
        g[x][y] = edge;
        if (x != y && !directed) {
            g[y][x] = new MyEdge<>(y, x, edge.getWeight());
        }
        this.m++;
    }

    @Override
    public boolean hadEdge(int x, int y) {
        return g[x][y] != null;
    }

    // 邻边迭代器
    @Override
    public Iterable<MyEdge<Weight>> adj(int v) {
        List<MyEdge<Weight>> res = new ArrayList<>();
        for (int i = 0; i < this.v(); i++) {
            MyEdge<Weight> one = g[v][i];
            if (one != null) {
                res.add(one);
            }
        }
        return res;
    }

    @Override
    public void show() {
        for (int i = 0; i < v(); i++) {
            System.out.print("邻接矩阵" + i + "相连的边为:");
            for (int j = 0; j < v(); j++) {
                MyEdge<Weight> e = g[i][j];
                if (e == null) {
                    continue;
                }
                int other = e.other(i);
                Weight weight = e.getWeight();
                System.out.print("[point:" + other + " weight:" + weight + "]  ");
            }
            System.out.println();
        }
    }
}
