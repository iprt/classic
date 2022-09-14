package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Dfs 寻找两点的所有子路径
 *
 * @author zhuzhenjie
 * @since 2022/9/14
 */
public class Dfs {

    private final Graph<String, Double> g;

    private Vertex<String> dest;

    public Dfs(Graph<String, Double> g) {
        this.g = g;
    }

    public List<List<Vertex<String>>> findRoutesByV(Vertex<String> src, Vertex<String> dest) {
        if (src == null || dest == null) {
            return Collections.emptyList();
        }

        if (src.equals(dest)) {
            return Collections.emptyList();
        }

        if (!this.g.vertices().contains(src) || !this.g.vertices().contains(dest)) {
            return Collections.emptyList();
        }

        this.dest = dest;

        Map<Vertex<String>, Boolean> visited = new HashMap<>(this.g.verticesNum());
        this.g.vertices().forEach(v -> visited.put(v, false));

        VertexRoutes vertexRoutes = new VertexRoutes(src, new ArrayList<>());
        this.dfsRecordV(src, visited, vertexRoutes);

        return vertexRoutes.rts;
    }

    public List<List<Edge<Double, String>>> findRoutesByE(Vertex<String> src, Vertex<String> dest) {
        if (src == null || dest == null) {
            return Collections.emptyList();
        }

        if (src.equals(dest)) {
            return Collections.emptyList();
        }

        if (!this.g.vertices().contains(src) || !this.g.vertices().contains(dest)) {
            return Collections.emptyList();
        }

        this.dest = dest;

        Map<Vertex<String>, Boolean> visited = new HashMap<>(this.g.verticesNum());
        this.g.vertices().forEach(v -> visited.put(v, false));

        EdgeRoutes edgeRoutes = new EdgeRoutes(src, new ArrayList<>());
        this.dfsRecordE(src, visited, edgeRoutes);

        return edgeRoutes.rts;
    }


    public void printRoutesByV(Vertex<String> src, Vertex<String> dest) {
        List<List<Vertex<String>>> allRoutes = findRoutesByV(src, dest);
        System.out.printf("一共有 %d 条路径\n", allRoutes.size());
        allRoutes.forEach(route -> {
            System.out.print(src.getSign());
            route.forEach(v -> System.out.print(" -> " + v.getSign()));
            System.out.println();
        });
    }

    public void printRoutesByE(Vertex<String> src, Vertex<String> dest) {
        List<List<Edge<Double, String>>> allRoutes = findRoutesByE(src, dest);
        System.out.printf("一共有 %d 条路径\n", allRoutes.size());

        allRoutes.forEach(route -> {
            Optional<Double> reduce = route.stream().map(Edge::getW).reduce(Double::sum);
            reduce.ifPresent(routeWeight -> System.out.println("路径的长度为 : " + routeWeight));

            System.out.print(src.getSign());
            route.forEach(e -> System.out.printf(" -(%s)-> %s", e.getW(), e.getTo().getSign()));
            System.out.println();
        });
    }


    /**
     * 所有的路径 等价于所有 子路径之和
     *
     * @param v       the v
     * @param visited the visited
     * @param vRoutes  the sub vRoutes
     */
    private void dfsRecordV(Vertex<String> v, Map<Vertex<String>, Boolean> visited, VertexRoutes vRoutes) {
        visited.put(v, true);

        for (Edge<Double, String> e : this.g.adj(v)) {
            Vertex<String> other = e.getTo();
            if (other.equals(this.dest)) {
                // 找到了一条路径，记录路径
                List<Vertex<String>> find = new ArrayList<>(1);
                // C-E 这条边
                find.add(other);
                vRoutes.rts.add(find);
                visited.put(other, true);
            } else if (!visited.get(other)) {
                Map<Vertex<String>, Boolean> subVisited = new HashMap<>(this.g.verticesNum());
                subVisited.putAll(visited);

                VertexRoutes subRoutes = new VertexRoutes(other, new ArrayList<>(1));

                this.dfsRecordV(other, subVisited, subRoutes);

                // 找到了子路径
                if (subRoutes.rts.size() > 0) {
                    // 路径合并
                    for (List<Vertex<String>> contains : subRoutes.rts) {
                        List<Vertex<String>> mergeRoute = new ArrayList<>(contains.size() + 1);
                        // B-C这条边
                        mergeRoute.add(other);
                        mergeRoute.addAll(contains);

                        vRoutes.rts.add(mergeRoute);
                    }
                }
                // for release memory
                subRoutes.rts.clear();
                subVisited.clear();
            }
        }
    }

    /**
     * 记录每个点的子路径，存在子路径的时候
     */
    private static class VertexRoutes {
        Vertex<String> v;
        List<List<Vertex<String>>> rts;

        public VertexRoutes(Vertex<String> v, List<List<Vertex<String>>> rts) {
            this.v = v;
            this.rts = rts;
        }
    }


    private void dfsRecordE(Vertex<String> v, Map<Vertex<String>, Boolean> visited, EdgeRoutes vRoutes) {
        visited.put(v, true);

        for (Edge<Double, String> edge : this.g.adj(v)) {
            Vertex<String> to = edge.getTo();
            if (to.equals(this.dest)) {
                // 子路径
                List<Edge<Double, String>> findRoute = new ArrayList<>(1);
                findRoute.add(edge);

                vRoutes.rts.add(findRoute);
                // 标记为已经访问的
                visited.put(to, true);
            } else if (!visited.get(to)) {
                Map<Vertex<String>, Boolean> subVisited = new HashMap<>(this.g.verticesNum());
                subVisited.putAll(visited);
                EdgeRoutes subRoutes = new EdgeRoutes(to, new ArrayList<>(1));
                this.dfsRecordE(to, subVisited, subRoutes);
                if (subRoutes.rts.size() > 0) {
                    for (List<Edge<Double, String>> contains : subRoutes.rts) {
                        List<Edge<Double, String>> mergeRoute = new ArrayList<>(contains.size() + 1);
                        mergeRoute.add(edge);
                        mergeRoute.addAll(contains);

                        vRoutes.rts.add(mergeRoute);
                    }
                }

                // release memory
                subRoutes.rts.clear();
                subVisited.clear();
            }
        }

    }

    /**
     * 记录每个点的子路径，存在子路径的时候
     */
    private static class EdgeRoutes {
        Vertex<String> v;
        List<List<Edge<Double, String>>> rts;

        public EdgeRoutes(Vertex<String> v, List<List<Edge<Double, String>>> rts) {
            this.v = v;
            this.rts = rts;
        }
    }

}
