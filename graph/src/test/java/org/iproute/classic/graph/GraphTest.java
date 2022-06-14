package org.iproute.classic.graph;

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
}
