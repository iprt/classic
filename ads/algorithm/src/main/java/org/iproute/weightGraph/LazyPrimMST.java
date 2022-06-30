package org.iproute.weightGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @create 2018-5-28
 * 利用切分定理求最小生成树
 */
public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph<Weight> G;    // 图的引用

    private MinHeap<Edge<Weight>> pq;   // 最小堆, 算法辅助数据结构

    private boolean[] marked;           // 标记数组, 在算法运行过程中标记节点i是否被访问

    private List<Edge<Weight>> mst;   // 最小生成树所包含的所有边

    private Number mstWeight;           // 最小生成树的权值

    // 构造函数, 使用Prim算法求图的最小生成树
    public LazyPrimMST(WeightedGraph<Weight> graph) {

        // 算法初始化
        G = graph;
        pq = new MinHeap<Edge<Weight>>(G.E());
        marked = new boolean[G.V()];
        mst = new ArrayList<Edge<Weight>>();

        // Lazy Prim
        // 从0节点开始访问
        // 这是一个起点 最小堆中有了内容
        visit(0);
        while (!pq.isEmpty()) {
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = pq.extractMin();
            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            // 切分同侧
            // 这一步保证了v 和 w一定是在切分的两侧
            // 并查集的思想
            if (marked[e.v()] == marked[e.w()]) {
                continue;
            }
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(e);
            // 找边的端点在红色边还是蓝色变
            // 被标记的是红色，没有被标记的是蓝色
            if (marked[e.v()]) {
                visit(e.w());
            } else {
                visit(e.v());
            }
        }
        mstWeight = 0.0;
        for (Edge<Weight> e : mst) {
            mstWeight = mstWeight.doubleValue() + e.wt().doubleValue();
        }
    }

    private void visit(int v) {
        // 标记这个节点被访问了
        marked[v] = true;
        for (Edge<Weight> e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    // 返回最小生成树的所有边
    List<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    Number result() {
        return mstWeight;
    }

    ;
}