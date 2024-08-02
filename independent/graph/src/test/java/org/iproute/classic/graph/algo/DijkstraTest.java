package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;
import org.iproute.classic.graph.define.sparse.SparseGraph;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.iproute.classic.graph.GraphTest.DIRECTED;
import static org.iproute.classic.graph.GraphTest.UNDIRECTED;

/**
 * DijkstraTest
 *
 * @author zhuzhenjie
 * @since 2022/9/11
 */
public class DijkstraTest {

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


}
