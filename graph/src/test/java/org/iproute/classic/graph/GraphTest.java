package org.iproute.classic.graph;

import org.iproute.classic.graph.algorithm.Component;
import org.iproute.classic.graph.algorithm.Dijkstra;
import org.iproute.classic.graph.algorithm.Ring;
import org.iproute.classic.graph.algorithm.Traverse;
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
        Point<String> a = new Point<>(namespace, "A", null);
        Point<String> b = new Point<>(namespace, "B", null);
        graph.connect(a, b, 1.0);

        System.out.println("是否为有向图：" + graph.direct());

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

    @Test
    public void traverse() {
        boolean direct = false;
        Graph<String, Double> graph = new GraphImpl<>(UUID.randomUUID().toString(), direct);
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
        Point<String> root = new Point<>(namespace, "1");
        Point<String> p1_1 = new Point<>(namespace, "1-1");
        Point<String> p1_1_1 = new Point<>(namespace, "1-1-1");
        Point<String> p1_1_2 = new Point<>(namespace, "1-1-2");

        Point<String> p1_2 = new Point<>(namespace, "1-2");
        Point<String> p1_2_1 = new Point<>(namespace, "1-2-1");
        Point<String> p1_2_2 = new Point<>(namespace, "1-2-2");

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

        boolean direct = false;

        Graph<String, Double> graph = new GraphImpl<>(UUID.randomUUID().toString(), direct);

        String namespace = graph.namespace();
        Point<String> a = new Point<>(namespace, "A", null);
        Point<String> b = new Point<>(namespace, "B", null);

        Point<String> c = new Point<>(namespace, "C", null);
        Point<String> d = new Point<>(namespace, "D", null);

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
        boolean direct = true;
        Graph<String, Double> graph = new GraphImpl<>(UUID.randomUUID().toString(), direct);

        String namespace = graph.namespace();

        Point<String> a = new Point<>(namespace, "A", null);
        Point<String> b = new Point<>(namespace, "B", null);

        Point<String> c = new Point<>(namespace, "C", null);
        Point<String> d = new Point<>(namespace, "D", null);

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
