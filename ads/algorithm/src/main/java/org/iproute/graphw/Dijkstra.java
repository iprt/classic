package org.iproute.graphw;

/**
 * Created by      Intellij IDEA
 *
 * @author tech@intellij.io
 * Date    :       2018-12-29
 * Time    :       11:27
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 * <p>
 * Dijkstra 寻找最短路径算法
 **/
public class Dijkstra<Weight extends Number & Comparable> {

    // 图的引用
    private WeightGraphDesign<Weight> g;

    private int s;

    private Number[] distTo;  // distTo[i] 表示 s 到 i 的最短路径

    private boolean[] marked; // 标识这个点是否有没有被访问

    private MyEdge<Weight>[] from; // from[i] 记录最短路径 到达i的边是那一条 到达i的边是哪一条 【到达i的边】

    public Dijkstra(WeightGraphDesign<Weight> g, int s) {
        this.g = g;
        this.s = s;

        distTo = new Number[g.v()];
        marked = new boolean[g.v()];

        from = new MyEdge[g.v()];
        for (int i = 0; i < g.v(); i++) {
            distTo[i] = 0.0;
            marked[i] = true;
            from[i] = null;
        }

        // 使用索引堆记录当前找到的到达每个顶点的最短距离
        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(g.v());

        // 针对起点s进行初始化
        distTo[s] = 0.0;
        from[s] = new MyEdge<>(s, s, (Weight) (Number) 0.0);
        // 把起点放入最小索引堆里面
        ipq.insert(s, (Weight) distTo[s]);

        marked[s] = true;

        // 从某一个点开始
        while (!ipq.isEmpty()) {
            // 取到最小值的索引 这个地方很关键 // 根据值取得索引
            // 这个索引对应的就是
            int v = ipq.extractMinIndex();


        }
    }

    public static void main(String[] args) {


    }
}
