package org.iproute.classic.graph;

import org.iproute.classic.graph.algorithm.Dijkstra;
import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Point;
import org.iproute.classic.graph.define.impl.GraphImpl;
import org.junit.Test;

import java.util.UUID;

/**
 * GraphTest
 *
 * @author winterfell
 * @since 2022/6/12
 */
public class GraphTest {

    @Test
    public void showGraph() {

        Graph<String, Double> graph = new GraphImpl<>(UUID.randomUUID().toString(), true);

        String namespace = graph.namespace();

        Edge<Double, String> ab = new Edge<>(
                namespace,
                new Point<>(namespace, "a", null),
                new Point<>(namespace, "b", null),
                1.0
        );

        graph.addEdge(ab);

        graph.show();

    }

    @Test
    public void dijkstra() {
        Graph<String, Double> graph = new GraphImpl<>(UUID.randomUUID().toString(), false);

        String namespace = graph.namespace();

        Point<String> a = new Point<>(namespace, "A", "A");
        Point<String> b = new Point<>(namespace, "B", "B");
        Point<String> c = new Point<>(namespace, "C", "C");
        Point<String> d = new Point<>(namespace, "D", "D");
        Point<String> e = new Point<>(namespace, "E", "E");


        graph.connect(a, b, 4.0);
        graph.connect(a, d, 2.0);
        graph.connect(b, d, 1.0);
        graph.connect(b, c, 4.0);
        graph.connect(d, c, 1.0);
        graph.connect(d, e, 7.0);
        graph.connect(c, e, 3.0);

        graph.show();

        Dijkstra dijkstra = new Dijkstra(graph, a);

        dijkstra.calculate();

        dijkstra.showPath(b);

    }
}
