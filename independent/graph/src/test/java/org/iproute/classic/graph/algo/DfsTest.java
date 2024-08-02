package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;
import org.iproute.classic.graph.define.sparse.SparseGraph;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.iproute.classic.graph.GraphTest.UNDIRECTED;

/**
 * DfsTest
 *
 * @author zhuzhenjie
 * @since 2022/9/14
 */
public class DfsTest {

    @Test
    public void testFindRoutes1() {

        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), UNDIRECTED);

        String namespace = graph.namespace();
        Vertex<String> a = new Vertex<>(namespace, "A", null);
        Vertex<String> b = new Vertex<>(namespace, "B", null);

        Vertex<String> c = new Vertex<>(namespace, "C", null);
        Vertex<String> d = new Vertex<>(namespace, "D", null);
        Vertex<String> e = new Vertex<>(namespace, "E", null);
        Vertex<String> f = new Vertex<>(namespace, "F", null);


        graph.connect(a, b, 1.0);
        graph.connect(b, c, 1.0);
        graph.connect(b, d, 1.0);
        graph.connect(c, e, 1.0);
        graph.connect(d, e, 1.0);


        graph.connect(c, f, 1.0);

        //            F
        //          /
        //         C
        //       /   \
        // A - B      E
        //       \   /
        //         D

        Dfs dfs = new Dfs(graph);

        dfs.printRoutesByV(a, e);
        System.out.println();
        dfs.printRoutesByE(a, e);
    }

    @Test
    public void testFindRoutes2() {

        Graph<String, Double> graph = new SparseGraph<>(UUID.randomUUID().toString(), UNDIRECTED);

        String namespace = graph.namespace();
        Vertex<String> a = new Vertex<>(namespace, "A", null);
        Vertex<String> b = new Vertex<>(namespace, "B", null);

        Vertex<String> c = new Vertex<>(namespace, "C", null);
        Vertex<String> d = new Vertex<>(namespace, "D", null);
        Vertex<String> e = new Vertex<>(namespace, "E", null);


        graph.connect(a, b, 1.0);
        graph.connect(a, c, 1.0);
        graph.connect(a, d, 1.0);

        graph.connect(b, e, 1.0);
        graph.connect(c, e, 1.0);
        graph.connect(d, e, 1.0);

        graph.connect(b, c, 1.0);
        graph.connect(c, d, 1.0);

        //      B
        //   /  |  \
        // A - C - E
        //  \  |  /
        //     D
        Dfs dfs = new Dfs(graph);

        // dfs.findAllRoutes(a, e);
        dfs.printRoutesByV(a, e);
        System.out.println();
        dfs.printRoutesByE(a, e);

    }

}
