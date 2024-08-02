package org.iproute.graphw;


import java.util.Vector;

/**
 * @author tech@intellij.io
 * <p>
 * 利用切分定理求最小生成树
 * 针对无向图
 * 定理：切分以后横切边最短的边一定是最小生成树里面的一条边 （反证法证明）
 **/
public class LazyPrim<Weight extends Number & Comparable> {

    private WeightGraphDesign<Weight> g; // 图的引用

    private MinHeap<MyEdge<Weight>> pq; // 最小堆 算法辅助数据结构

    private boolean[] marked; // 标记数组，在算法运行过程中标记节点i是否被访问

    private Vector<MyEdge<Weight>> mst; // 最小生成树所包含的所有的边

    private double mstWeight=0; // 最小生成树的权值

    public LazyPrim(WeightGraphDesign<Weight> g) {
        this.g = g;
        pq = new MinHeap<>(g.e());
        marked = new boolean[g.v()];
        mst = new Vector<>();
        // 算法真正开始的地方
        visit(0);
        // 当这个最小堆中不是空的时候 第一次visit做出了第一次切分
        // 0 与 0 周围的其他的点一个切分
        while (!pq.isEmpty()) {
            // 取出最小的边
            // 假设 c 为 b 的切分 b 为 a 的切分
            // c 的 某一条切分边为 c-a 这一条边不应算在里面
            // a-b 为 a 的最小切分 此时已经过滤掉了 a-c 这一条切分
            // 如果此时 以c为开始切分 切分出来的 a 是c 切分的最小边
            // 构成了 a-b-c-a 的环
            // 不可能生成树
            MyEdge<Weight> minEdge = pq.extractMin();
            // 如果这条边的两端都访问过了 丢掉这条边
            if (marked[minEdge.getX()] == marked[minEdge.getY()]) {
                // 所以这个地方等价于
                // marked[minEdge.getX()] 一定等于true
                // if(marked[minEdge.getX()==true && marked[minEdge.getY() == true]])
                continue;
            }
            // 找到了最小的边并且放在里面
            // 否则这条边应该在最小生成树里面
            mst.add(minEdge);

            // 拿出这条最小边
            // 访问这条边连接的还没有访问过的节点
            if (!marked[minEdge.getX()]) { // 最小边不一定从 c 延伸出来的
                visit(minEdge.getX());
            } else {
                visit(minEdge.getY());
            }
        }


        // 计算权重
        mst.forEach(e->{
            mstWeight += e.getWeight().doubleValue();
        });
    }

    public void showMst() {
        System.out.println("最小生成树的所有边为");
        mst.forEach(e -> {
            System.out.println(String.format("[start:%d end:%d]", e.getX(), e.getY()));
        });
    }

    public double getMstWeight() {
        return mstWeight;
    }

    // 切分定理的运用
    // 一开始从某个一个点开始切分
    // 第一次切分的时候可以把这个点周围相邻的所有的点都加入到里面
    // 如果找到了最小的边 从 x - y 这个连接 以y为起点切分
    // 如果不过滤marked[e.other(y)]的话 y 切分的邻边会 把 y-x 这条边放进去
    // 访问某个节点 并且开始标记这个节点
    private void visit(int x) {
        // 标记这个点已经被访问了
        marked[x] = true;
        // 找出x的所有邻边并放入最小堆中
        for (MyEdge<Weight> e : g.adj(x)) {
            // 针对切分用的 如果x 对应的某一条边的y' 被访问过 可以倒推出 marked中的x是用y'引申过来的
            if (!marked[e.other(x)]) { // 用marked来标记有没有访问
                pq.insert(e);
            }
        }
    }
}
