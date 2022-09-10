package org.iproute.classic.graph;

import org.iproute.classic.graph.algo.Component;
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
    public final static boolean DIRECTED = true;
    public final static boolean UNDIRECTED = false;

    @Test
    public void showGraph() {
        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), DIRECTED);

        String namespace = graph.namespace();
        Vertex<String> a = new Vertex<>(namespace, "A", null);
        Vertex<String> b = new Vertex<>(namespace, "B", null);
        graph.connect(a, b, 1.0);

        graph.show();
    }



}
