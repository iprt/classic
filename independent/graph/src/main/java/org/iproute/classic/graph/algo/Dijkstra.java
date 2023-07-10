package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra 单源最短路径
 *
 * @author winterfell
 * @since 2022/6/14
 */
public class Dijkstra {

    private final Graph<String, Double> g;

    private Vertex<String> source;

    /**
     * 1 标记点是否被访问
     */
    private Map<Vertex<String>, Boolean> marked;

    /**
     * 2.记录这个点是从哪一条边过来的
     * <p>
     * 这个地方要反向思考一下
     * <p>
     * key存储的是点，value存储的是边，可以理解成这个点从另外哪个点过来的，为什么存储边呢，因为边里面有权重的信息，可以复用
     */
    private Map<Vertex<String>, Edge<Double, String>> whereFrom;

    /**
     * 3.记录这个点到source的最短距离, 和source有关
     * <p>
     * 点要源的距离
     */
    private Map<Vertex<String>, Double> distToSource;

    private PriorityQueue<DistanceToSource> dtsHeap;

    public Dijkstra(Graph<String, Double> graph) {
        this.g = graph;
    }

    /**
     * 初始化辅助的数据结构
     */
    private void initAuxiliary(Vertex<String> source) {
        this.source = source;

        int pSize = g.verticesNum();
        // 所有点初始化为没有被标记过
        this.marked = new HashMap<>(pSize);
        g.vertices().forEach(p -> this.marked.put(p, false));

        // 所有点的记录边的来源 初始化全部为空
        this.whereFrom = new HashMap<>(pSize);
        g.vertices().forEach(p -> this.whereFrom.put(p, null));

        this.distToSource = new HashMap<>(pSize);

        // 初始化最小堆
        this.dtsHeap = new PriorityQueue<>();
    }

    /**
     * 执行单源最短路径的计算, 循环的方式
     */
    public void calculateByL(Vertex<String> source) {
        // 初始化辅助数据结构
        this.initAuxiliary(source);

        this.marked.put(source, true);
        this.distToSource.put(source, 0.0);
        this.putMin(source, 0.0);

        long start = System.currentTimeMillis();

        // 松弛计算
        while (!dtsHeap.isEmpty()) {
            DistanceToSource minDts = extractMin();
            Vertex<String> v = minDts.vertex;
            // 对点所有的邻边进行遍历
            for (Edge<Double, String> edge : this.g.adj(v)) {
                Vertex<String> to = edge.getTo();

                boolean hasMarked = marked.get(to);
                if (hasMarked) {
                    continue;
                }

                Double toMin = distToSource.get(to);
                if (toMin == null) {
                    // 到源最小的距离就是当前的边
                    whereFrom.put(to, edge);
                    distToSource.put(to, distToSource.get(v) + edge.getW());
                } else {
                    double maybeMin = distToSource.get(v) + edge.getW();
                    if (maybeMin < toMin) {
                        whereFrom.put(to, edge);
                        distToSource.put(to, maybeMin);
                    }
                }

                putMin(to, distToSource.get(to));
            }

            // 标记当前点是已经访问了的
            marked.put(v, true);
        }
        long end = System.currentTimeMillis();
        System.out.println("cost time (ms): " + (end - start));

    }


    public void calculateByR(Vertex<String> source) {
        this.initAuxiliary(source);

        this.marked.put(source, true);
        this.distToSource.put(source, 0.0);

        this.doCalculateByR(source);
    }

    /**
     * 递归的方式计算
     *
     * @param p the p
     */
    private void doCalculateByR(Vertex<String> p) {
        for (Edge<Double, String> edge : this.g.adj(p)) {
            Vertex<String> to = edge.getTo();
            Boolean hasMarked = this.marked.get(to);
            if (hasMarked) {
                // System.out.println("点位已被标记，不需要再做松弛操作 " + to);
                continue;
            }

            Double toMin = this.distToSource.get(to);
            if (toMin == null) {
                // 之前这个点没有计算过
                whereFrom.put(to, edge);
                distToSource.put(to, edge.getW() + distToSource.get(p));

            } else {
                // 这个点之前计算过
                double maybeMin = distToSource.get(p) + edge.getW();
                if (maybeMin < toMin) {
                    whereFrom.put(to, edge);
                    distToSource.put(to, maybeMin);
                }
            }

            this.putMin(to, distToSource.get(to));
        }
        // p 被计算后以后就不在需要计算了
        this.marked.put(p, true);

        if (this.dtsHeap.isEmpty()) {
            return;
        }

        DistanceToSource minOfRemaining = this.dtsHeap.poll();
        doCalculateByR(minOfRemaining.vertex);
    }


    private void putMin(Vertex<String> vertex, double distance) {
        this.dtsHeap.offer(new DistanceToSource(vertex, distance));
    }

    private DistanceToSource extractMin() {
        return dtsHeap.poll();
    }


    public boolean hasShortestRoute(Vertex<String> dest) {
        if (dest == null || !this.g.vertices().contains(dest)) {
            System.out.println("点位不存在");
            return false;
        }
        // return this.distToSource.get(dest) != null;
        // or
        // return this.whereFrom.get(dest) != null;
        return this.marked.get(dest);
    }

    /**
     * 获取最短路径
     *
     * @param dest the dest
     * @return the path
     */
    public List<Edge<Double, String>> shortestRoute(Vertex<String> dest) {
        if (!hasShortestRoute(dest)) {
            return Collections.emptyList();
        }

        List<Edge<Double, String>> r = new ArrayList<>();
        Edge<Double, String> fe;
        while ((fe = whereFrom.get(dest)) != null) {
            r.add(fe);
            dest = fe.getFrom();
        }
        List<Edge<Double, String>> y = new ArrayList<>();
        for (int i = 0; i < r.size(); i++) {
            y.add(r.get(r.size() - i - 1));
        }
        return y;
    }


    public void printShortestRoute(Vertex<String> dest) {
        if (!hasShortestRoute(dest)) {
            return;
        }
        System.out.printf("从 %s 到 %s 的最短路径长度为: %s\n", this.source.getSign(), dest.getSign(), distToSource.get(dest));
        List<Edge<Double, String>> shortestRoute = shortestRoute(dest);

        for (Edge<Double, String> edge : shortestRoute) {
            System.out.printf("%s -(%s)-> ", edge.getFrom().getSign(), edge.getW());
        }

        if (this.source.equals(dest)) {
            System.out.printf("%s -(%s)-> ", this.source.getSign(), 0.0);
        }
        System.out.println(dest.getSign());
        System.out.println();
    }


    /**
     * 辅助的内部类，记录点到源的“最短”距离，在计算的过程中不断刷新
     */
    private static class DistanceToSource implements Comparable<DistanceToSource> {
        private final Vertex<String> vertex;
        private final Double distance;

        public DistanceToSource(Vertex<String> vertex, Double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(DistanceToSource o) {
            return this.distance.compareTo(o.distance);
        }
    }
}
