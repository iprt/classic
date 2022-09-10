package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;
import org.iproute.classic.graph.define.sparse.SparseGraph;
import org.junit.Test;

import java.util.UUID;

import static org.iproute.classic.graph.GraphTest.UNDIRECTED;

/**
 * ComponentTest
 *
 * @author zhuzhenjie
 * @since 2022/9/11
 */
public class ComponentTest {

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
}
