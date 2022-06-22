package org.iproute.classic.graph.algorithm;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

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

    /**
     * 执行单源最短路径的计算
     */
    public void calculate() {
        // 松弛计算
        long start = System.currentTimeMillis();

        while (!minHeap.isEmpty()) {
            PointDistance minPd = extractMin();

            Point<String> curPoint = minPd.getPoint();
            // 标记当前点是已经访问了的
            marked.put(curPoint, true);

            // 获取这个点的所有邻边
            Iterable<Edge<Double, String>> adj = this.g.adj(curPoint);

            // 对所有的邻边进行遍历
            for (Edge<Double, String> aroundEdge : adj) {
                Point<String> otherPoint = aroundEdge.other(curPoint);

                boolean visit = marked.get(otherPoint);
                if (!visit) {

                    Edge<Double, String> fromE = from.get(otherPoint);
                    if (fromE == null) {
                        // 到源最小的距离就是当前的边
                        double min = distTo.get(curPoint) + aroundEdge.getW();
                        from.put(otherPoint, aroundEdge);
                        distTo.put(otherPoint, min);
                        putMin(otherPoint, min);

                    } else {
                        double oldMin = distTo.get(otherPoint);
                        double newMin = distTo.get(curPoint) + aroundEdge.getW();

                        if (newMin < oldMin) {
                            from.put(otherPoint, aroundEdge);
                            distTo.put(otherPoint, newMin);
                            // TODO 堆中有重复的 点和距离 的问题 极致的性能优化
                            putMin(otherPoint, newMin);
                        }

                    }
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("cost time (ms): " + (end - start));
        this.hasCal = true;
    }

    private void putMin(Point<String> point, double distance) {
        this.minHeap.offer(new PointDistance(point, distance));
    }

    private PointDistance extractMin() {
        return minHeap.poll();
    }

    public void showPath(Point<String> p) {

        if (!hasCal) {
            System.out.println("没有计算");
            return;
        }

        System.out.println("路径长度为: " + distTo.get(p));

        Stack<Edge<Double, String>> stack = new Stack<>();

        Edge<Double, String> tmpE;
        Point<String> tmpP = p;
        while ((tmpE = from.get(tmpP)) != null) {
            stack.push(tmpE);
            tmpP = tmpE.other(tmpP);
        }

        List<Edge<Double, String>> edges = new ArrayList<>(stack.size());

        while (!stack.isEmpty()) {
            edges.add(stack.pop());
        }

        // edges.forEach(e -> {
        //     Point<String> fp = e.getFrom();
        //     Point<String> tp = e.getTo();
        //     System.out.printf("%s -(%s)-> %s    ", fp.getSign(), e.getW(), tp.getSign());
        // });
        // System.out.println();

        edges.forEach(e -> {
            Point<String> fp = e.getFrom();
            System.out.printf("%s -(%s)-> ", fp.getSign(), e.getW());
        });
        System.out.println(p.getSign());
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

        // 所有的distTo初始化为0
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
