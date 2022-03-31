package org.iproute.complexGraph;

import org.iproute.complexGraph.define.Edge;
import org.iproute.complexGraph.define.Graph;
import org.iproute.complexGraph.define.Point;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author :       zhuzhenjie
 * Company :      Beijing Tepia (Wuhan R&D Center)\
 * 最短路径算法的实现 路径要可叠加
 **/
public class Dijkstra {

    /**
     * 图的引用
     */
    private Graph<String, Double> g;

    /**
     * 指定源节点
     */
    private Point<String> source;

    /* ******************* 数据辅助结构 ********************************* */

    /**
     * 1.标记点有没有被访问
     */
    private Map<Point<String>, Boolean> marked;

    /**
     * 2.记录这个点是从哪一条边过来的
     */
    private Map<Point<String>, Edge<String, Double>> from;

    /**
     * 3.记录这个点到source的最短距离
     */
    private Map<Point<String>, Double> distTo;

    /**
     * 4.辅助的数据结构 在点为数字的时候 使用索引堆 主要为了用extractMin的方法
     * <p>
     * 动态的记录点到source的最短路径
     */
    private Map<Point<String>, Double> fakeIndexMinHeap;

    /* ******************************************************************* */

    public Dijkstra(Graph<String, Double> g, Point<String> source) {
        this.g = g;
        this.source = source;

        // 初始化辅助的数据结构
        this.marked = new HashMap<Point<String>, Boolean>() {
            {
                g.getAllPoints().forEach(p -> {
                    put(p, Boolean.FALSE);
                });
            }
        };

        this.from = new HashMap<Point<String>, Edge<String, Double>>() {
            {
                g.getAllPoints().forEach(p -> {
                    put(p, null);
                });
            }
        };

        this.distTo = new HashMap<Point<String>, Double>() {
            {
                g.getAllPoints().forEach(p -> {
                    put(p, 0.0);
                });
            }
        };

        // 针对source初始化
        this.distTo.put(source, 0.0);
        this.marked.put(source, Boolean.TRUE);
        this.fakeIndexMinHeap = new HashMap<Point<String>, Double>() {
            {
                put(source, 0.0);
            }
        };

        while (!fakeIndexMinHeap.isEmpty()) {
            // 取出辅助最小堆到source最短的点
            Point<String> p = this.extractMin();
            this.marked.put(p, Boolean.TRUE);
            for (Edge<String, Double> edge : g.adj(p)) {
                // 找到另外一个点
                Point<String> other = edge.other(p);
                // 这个点没有被访问过 才可以判断路径
                // source -> other 点的路径还没有找到
                if (!marked.get(other)) {
                    // from.get(other) == null 到other的点的路径还没有找过
                    // 松弛操作的核心 !!!
                    if (from.get(other) == null || distTo.get(p) + edge.getRelation() < distTo.get(other)) {
                        distTo.put(other, distTo.get(p) + edge.getRelation());
                        // from.get(other) == null : 第一次记录source 到 other 可能最短路径的边
                        // distTo.get(p) + edge.getRelation() < distTo.get(other) : 不是第一次记录下的边
                        from.put(other, edge);
                        // 辅助数据的记录 为了下一次的extractMin();
                        // distTo.get(p) + edge.getRelation() 等价于 distTo.get(other)
                        this.fakeIndexMinHeap.put(other, distTo.get(other));
                    }
                }
            }
        }

        // 总结下来
        // 求最短路径的核心是松弛操作 实现是用marked,distTo,from,help这些数据结构 在广度遍历中 不断更新 求出最短路径
        // 关键的是每个点不去记录从开始的点的所有距离 而是记录这个点是从哪条边过来的
        // 松弛判断中的distTo也是一种贪心的思想 只记录了到source点的路径最短的是多少
    }

    /**
     * 取出当前记录到source最短路径的点
     *
     * @return 到source最短路径的点
     */
    private Point<String> extractMin() {
        // 在构造器中已经初始化 source最开始直接赋值 不存在为空的情况 除非图为空
        Point<String> min = fakeIndexMinHeap.keySet().stream().findFirst().get();
        for (Point<String> tmp : fakeIndexMinHeap.keySet()) {
            if (fakeIndexMinHeap.get(tmp) < fakeIndexMinHeap.get(min)) {
                min = tmp;
            }
        }
        // extract 操作
        fakeIndexMinHeap.remove(min);
        return min;
    }

    public List<Point<String>> shortestRoute(Point<String> dest) {
        Stack<Point<String>> reverseShortestPath = new Stack<>();
        while (from.get(dest) != null) {
            reverseShortestPath.push(dest);
            dest = from.get(dest).other(dest); // 找到from的点
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
}
