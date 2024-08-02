package org.iproute.graph;


import org.iproute.graph.define.Edge;
import org.iproute.graph.define.Graph;
import org.iproute.graph.define.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 最短路径算法的实现 路径要可叠加
 *
 * @author zhuzhenjie
 **/
public class Dijkstra {

    /**
     * 图的引用
     */
    private final Graph<String, Double> g;

    /**
     * 指定源节点
     */
    private final Point<String> source;

    /**
     * 数据辅助结构
     * 1.标记点有没有被访问
     */
    private final Map<Point<String>, Boolean> marked;

    /**
     * 2.记录这个点是从哪一条边过来的
     */
    private final Map<Point<String>, Edge<String, Double>> from;

    /**
     * 3.记录这个点到source的最短距离
     */
    private final Map<Point<String>, Double> distTo;

    /**
     * 4.辅助的数据结构 在点为数字的时候 使用索引堆 主要为了用extractMin的方法
     * 动态的记录点到source的最短路径
     */
//    private Map<Point<String>, Double> fakeIndexMinHeap;

    private final PriorityQueue<PointDistance> minHeap;

    public Dijkstra(Graph<String, Double> graph, Point<String> source) {
        this.g = graph;
        this.source = source;

        // 初始化辅助的数据结构
        this.marked = new HashMap<Point<String>, Boolean>() {
            {
                g.allPoints().forEach(p -> {
                    put(p, Boolean.FALSE);
                });
            }
        };

        this.from = new HashMap<Point<String>, Edge<String, Double>>() {
            {
                g.allPoints().forEach(p -> {
                    put(p, null);
                });
            }
        };

        this.distTo = new HashMap<Point<String>, Double>() {
            {
                g.allPoints().forEach(p -> {
                    put(p, 0.0);
                });
            }
        };

        // 针对source初始化
        this.distTo.put(source, 0.0);
        this.marked.put(source, Boolean.TRUE);
//        this.fakeIndexMinHeap = new HashMap<Point<String>, Double>() {
//            {
//                put(source, 0.0);
//            }
//        };
        this.minHeap = new PriorityQueue<>();
        this.minHeap.offer(new PointDistance() {
            {
                setPoint(source);
                setDistance(0.0);
            }
        });

        long start = System.currentTimeMillis();
        while (!minHeap.isEmpty()) {
            // 取出辅助最小堆到source最短的点
            Point<String> p = extractMin();

            this.marked.put(p, Boolean.TRUE);
            for (Edge<String, Double> edge : g.adj(p)) {
                // 找到另外一个点
                Point<String> other = edge.other(p);
                /*
                 * 这个点没有被访问过 才可以判断路径
                 * source -> other 点的路径还没有找到
                 */
                if (!marked.get(other)) {
                    /*
                     * from.get(other) == null 到other的点的路径还没有找过
                     * 松弛操作的核心 !!!
                     */
                    if (from.get(other) == null || distTo.get(p) + edge.getRelation() < distTo.get(other)) {
                        distTo.put(other, distTo.get(p) + edge.getRelation());
                        /*
                         from.get(other) == null : 第一次记录source 到 other 可能最短路径的边
                        distTo.get(p) + edge.getRelation() < distTo.get(other) : 不是第一次记录下的边
                         */
                        from.put(other, edge);
                        /*
                         * 辅助数据的记录 为了下一次的extractMin();
                         * distTo.get(p) + edge.getRelation() 等价于 distTo.get(other)
                         */
                        minHeap.offer(new PointDistance() {
                            {
                                setPoint(other);
                                setDistance(distTo.get(other));
                            }
                        });
                    }
                }
            }
        }

        /*
         * 总结下来
         * 求最短路径的核心是松弛操作 实现是用marked,distTo,from,help这些数据结构 在广度遍历中 不断更新 求出最短路径
         * 关键的是每个点不去记录从开始的点的所有距离 而是记录这个点是从哪条边过来的
         * 松弛判断中的distTo也是一种贪心的思想 只记录了到source点的路径最短的是多少
         */
        long end = System.currentTimeMillis();
    }

    /**
     * 取出当前记录到source最短路径的点
     *
     * @return 到source最短路径的点
     */
    private Point<String> extractMin() {
        // 在构造器中已经初始化 source最开始直接赋值 不存在为空的情况 除非图为空
//        Point<String> min = fakeIndexMinHeap.keySet().stream().findFirst().get();
//        for (Point<String> tmp : fakeIndexMinHeap.keySet()) {
//            if (fakeIndexMinHeap.get(tmp) < fakeIndexMinHeap.get(min)) {
//                min = tmp;
//            }
//        }
//        // extract 操作
//        fakeIndexMinHeap.remove(min);
        PointDistance min = minHeap.poll();
//        return min;
        assert min != null;
        return min.getPoint();
    }

    public List<Point<String>> shortestRoute(Point<String> dest) {
        Stack<Point<String>> reverseShortestPath = new Stack<>();
        while (from.get(dest) != null) {
            reverseShortestPath.push(dest);
            // 找到from的点
            dest = from.get(dest).other(dest);
        }
        return new ArrayList<Point<String>>() {
            {
                add(source);
                while (!reverseShortestPath.empty()) {
                    add(reverseShortestPath.pop());
                }
            }
        };
    }

    public List<String> shortestRoute(String dest) {
        return shortestRoute(new Point<>(dest))
                .stream()
                .map(Point::getSign)
                .collect(Collectors.toList());
    }

    public Double distance(Point<String> dest) {
        return distTo.get(dest);
    }

    public void show(Point<String> dest) {
        show(dest.getSign());
    }

    public void show(String dest) {
        List<String> route = shortestRoute(dest);
        String arrow = "->";
        for (int i = 0; i < route.size(); i++) {
            if (i == route.size() - 1) {
                System.out.print(route.get(i));
            } else {
                System.out.print(route.get(i) + arrow);
            }
        }
    }

    public boolean hasPath(Point<String> dest) {
        if (Objects.isNull(marked.get(dest))) {
            return false;
        }
        return marked.get(dest);
    }

    public boolean hasPath(String dest) {
        return marked.get(new Point<String>(dest));
    }

    public double shortestPathWeight(Point<String> dest) {
        return distTo.get(dest);
    }

    public double shortestPathWeight(String dest) {
        return distTo.get(new Point<String>(dest));
    }

    public static class PointDistance implements Comparable<PointDistance> {
        private Point<String> point;

        private Double distance;

        public Point<String> getPoint() {
            return point;
        }

        public void setPoint(Point<String> point) {
            this.point = point;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(PointDistance other) {
            return this.distance.compareTo(other.distance);
        }
    }
}
