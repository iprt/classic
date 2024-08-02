package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;
import org.iproute.classic.graph.define.sparse.SparseGraph;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.iproute.classic.graph.GraphTest.UNDIRECTED;

/**
 * TraverseTest
 *
 * @author zhuzhenjie
 * @since 2022/9/11
 */
public class TraverseTest {


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
}
