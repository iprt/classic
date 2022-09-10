package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;
import org.iproute.classic.graph.define.sparse.SparseGraph;
import org.junit.Test;

import java.util.UUID;

import static org.iproute.classic.graph.GraphTest.DIRECTED;

/**
 * RingTest
 *
 * @author zhuzhenjie
 * @since 2022/9/11
 */
public class RingTest {

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
