package org.iproute.classic.graph;

import org.iproute.classic.graph.algo.Component;
import org.iproute.classic.graph.algo.Dijkstra;
import org.iproute.classic.graph.algo.Ring;
import org.iproute.classic.graph.algo.Traverse;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;
import org.iproute.classic.graph.define.sparse.SparseGraph;
import org.junit.Test;

import java.util.UUID;

/**
 * GraphTest
 *
 * @author winterfell
 * @since 2022/6/12
 */
public class GraphTest {

    private final static boolean DIRECTED = true;
    private final static boolean UNDIRECTED = false;

    @Test
    public void showGraph() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), DIRECTED);

        String namespace = graph.namespace();
        Vertex<String> a = new Vertex<>(namespace, "A", null);
        Vertex<String> b = new Vertex<>(namespace, "B", null);
        graph.connect(a, b, 1.0);

        graph.show();
    }

    @Test
    public void dijkstraByL() {
        Graph<String, Double> graph = createForDijkstra();
        // graph.show();
        Dijkstra dijkstra = new Dijkstra(graph);

        Vertex<String> a = new Vertex<>(graph.namespace(), "A");
        dijkstra.calculateByL(a);

        graph.vertices().forEach(dijkstra::printShortestRoute);
    }

    @Test
    public void dijkstraByR() {
        Graph<String, Double> graph = createForDijkstra();
        // graph.show();

        Dijkstra dijkstra = new Dijkstra(graph);

        Vertex<String> a = new Vertex<>(graph.namespace(), "A");
        dijkstra.calculateByR(a);

        graph.vertices().forEach(dijkstra::printShortestRoute);
    }

    private Graph<String, Double> createForDijkstra() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), UNDIRECTED);
        String namespace = graph.namespace();

        Vertex<String> a = new Vertex<>(namespace, "A");
        Vertex<String> b = new Vertex<>(namespace, "B");
        Vertex<String> c = new Vertex<>(namespace, "C");
        Vertex<String> d = new Vertex<>(namespace, "D");
        Vertex<String> e = new Vertex<>(namespace, "E");


        graph.connect(a, b, 4.0);
        graph.connect(a, d, 2.0);
        graph.connect(b, d, 1.0);
        graph.connect(b, c, 4.0);
        graph.connect(d, c, 1.0);
        graph.connect(d, e, 7.0);
        graph.connect(c, e, 3.0);

        return graph;
    }


    @Test
    public void testHasShortestRoute() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), DIRECTED);
        String namespace = graph.namespace();

        Vertex<String> a = new Vertex<>(namespace, "A");
        Vertex<String> b = new Vertex<>(namespace, "B");
        Vertex<String> c = new Vertex<>(namespace, "C");
        Vertex<String> d = new Vertex<>(namespace, "D");

        graph.connect(a, b, 1.0);
        graph.connect(b, c, 2.0);
        graph.connect(a, c, 3.0);
        graph.connect(d, c, 8.8);

        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.calculateByR(a);

        System.out.println("has shortest path from A to D : " + dijkstra.hasShortestRoute(d));

    }


    @Test
    public void traverse() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), UNDIRECTED);
        String namespace = graph.namespace();
        /*
        1
            1-1
                1-1-1
                1-2-2
            1-2
                1-2-1
                1-2-2
         */
        Vertex<String> root = new Vertex<>(namespace, "1");
        Vertex<String> p1_1 = new Vertex<>(namespace, "1-1");
        Vertex<String> p1_1_1 = new Vertex<>(namespace, "1-1-1");
        Vertex<String> p1_1_2 = new Vertex<>(namespace, "1-1-2");

        Vertex<String> p1_2 = new Vertex<>(namespace, "1-2");
        Vertex<String> p1_2_1 = new Vertex<>(namespace, "1-2-1");
        Vertex<String> p1_2_2 = new Vertex<>(namespace, "1-2-2");

        graph.connect(root, p1_1, 1.0);
        graph.connect(root, p1_2, 1.0);
        graph.connect(p1_1, p1_1_1, 1.0);
        graph.connect(p1_1, p1_1_2, 1.0);
        graph.connect(p1_2, p1_2_1, 1.0);
        graph.connect(p1_2, p1_2_2, 1.0);

        Traverse<String, Double> traverse = new Traverse<>(graph);

        System.out.println("广度遍历");
        traverse.bfs(root, p -> System.out.println(p.getSign()));

        System.out.println("深度遍历");
        traverse.dfs(root, p -> System.out.println(p.getSign()));

    }

    @Test
    public void components() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), UNDIRECTED);

        String namespace = graph.namespace();
        Vertex<String> a = new Vertex<>(namespace, "A", null);
        Vertex<String> b = new Vertex<>(namespace, "B", null);

        Vertex<String> c = new Vertex<>(namespace, "C", null);
        Vertex<String> d = new Vertex<>(namespace, "D", null);

        graph.connect(a, b, 1.0);
        graph.connect(c, d, 2.0);

        graph.show();

        Component<String, Double> component = new Component<>(graph);

        int componentCounts = component.calculate();

        System.out.println("联通分量的个数为 ：" + componentCounts);

        System.out.println("A 到 B 是否有路径 : " + component.hasPath(a, b));
        System.out.println("A 到 C 是否有路径 : " + component.hasPath(a, c));
    }

    @Test
    public void ring() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), DIRECTED);

        String namespace = graph.namespace();

        Vertex<String> a = new Vertex<>(namespace, "A", null);
        Vertex<String> b = new Vertex<>(namespace, "B", null);

        Vertex<String> c = new Vertex<>(namespace, "C", null);
        Vertex<String> d = new Vertex<>(namespace, "D", null);

        // a -> b -> c -> d
        graph.connect(a, b, 1.0);
        graph.connect(b, c, 1.0);
        graph.connect(c, d, 1.0);

        Ring ring = new Ring(graph);

        graph.show();

        ring.calculate(a);

        System.out.println("是否有环：" + ring.hasRing());


        graph.connect(d, a, 1.0);
        graph.show();
        ring.calculate(a);

        System.out.println("是否有环：" + ring.hasRing());

    }

}
