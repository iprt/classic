package org.iproute.classic.graph.algorithm;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra 单源最短路径
 *
 * @author winterfell
 * @since 2022/6/14
 */
public class Dijkstra {

    /**
     * 是否已经计算
     */
    private boolean hasCal;

    private Graph<String, Double> g;

    private final Point<String> source;

    /**
     * 1 标记点是否被访问
     */
    private Map<Point<String>, Boolean> marked;

    /**
     * 2.记录这个点是从哪一条边过来的
     * <p>
     * 这个地方要反向思考一下
     * <p>
     * key存储的是点，value存储的是边，可以理解成这个点从另外哪个点过来的，为什么存储边呢，因为边里面有权重的信息，可以复用
     */
    private Map<Point<String>, Edge<Double, String>> from;

    /**
     * 3.记录这个点到source的最短距离, 和source有关
     * <p>
     * 点要源的距离
     */
    private Map<Point<String>, Double> distTo;

    private PriorityQueue<PointDistance> minHeap;

    public Dijkstra(Graph<String, Double> graph, Point<String> source) {
        this.g = graph;
        this.source = source;
        // 初始化辅助数据结构
        this.initAuxiliary();

        // 单源初始化
        this.initSource();

        this.hasCal = false;
    }

    public void calculate() {
        // 松弛计算
        long start = System.currentTimeMillis();

        while (!minHeap.isEmpty()) {
            // 1.取出到源头的最小的点
            PointDistance minPoint = extractMin();
            // 2.标记为已经访问了的点
            Point<String> tmpMinPoint = minPoint.getPoint();
            this.marked.put(tmpMinPoint, true);

            // 3.遍历这个点的所有临边
            Iterable<Edge<Double, String>> adj = this.g.adj(tmpMinPoint);

            adj.forEach(edge -> {
                // 拿出边另外一边的点
                Point<String> other = edge.other(tmpMinPoint);

                boolean marked = this.marked.get(other);

                if (!marked) {
                    Edge<Double, String> whichEdge = from.get(other);
                    double tmpW = distTo.get(tmpMinPoint) + whichEdge.getW();
                    if (from.get(other) == null
                            || tmpW < distTo.get(other)
                    ) {
                        distTo.put(other, tmpW);
                        from.put(other, edge);

                        minHeap.offer(new PointDistance(other, tmpW));
                    }
                }

            });

        }

        long end = System.currentTimeMillis();
        System.out.println("cost time (ms): " + (end - start));
        this.hasCal = true;
    }

    private PointDistance extractMin() {
        return minHeap.poll();
    }

    /**
     * 初始化 辅助数据结构
     */
    private void initAuxiliary() {
        int pSize = g.getPointCount();
        // 所有点初始化为没有被标记过
        this.marked = new HashMap<>(pSize);
        g.allPoints().forEach(p -> this.marked.put(p, false));

        // 所有点的记录边的来源 初始化全部为空
        this.from = new HashMap<>(pSize);
        g.allPoints().forEach(p -> this.from.put(p, null));

        //
        this.distTo = new HashMap<>(pSize);
        g.allPoints().forEach(p -> this.distTo.put(p, 0.0));

    }

    /**
     * 初始化源
     */
    private void initSource() {
        // 初始化堆
        this.minHeap = new PriorityQueue<>();

        // 初始化一个自己到自己为0的数据，很巧妙，有递归开始的思路
        this.distTo.put(source, 0.0);
        this.marked.put(source, true);

        this.minHeap.offer(new PointDistance(source, 0.0));
    }

    /**
     * 辅助的内部类，记录点到源的“最短”距离，在计算的过程中不断刷新
     */
    private static class PointDistance implements Comparable<PointDistance> {
        private Point<String> point;
        private Double distance;

        public Point<String> getPoint() {
            return point;
        }

        public Double getDistance() {
            return distance;
        }

        public PointDistance(Point<String> point, Double distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(PointDistance o) {
            return this.distance.compareTo(o.distance);
        }
    }
}
