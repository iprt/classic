package org.iproute.complexGraph;

import org.iproute.complexGraph.define.Graph;
import org.iproute.complexGraph.define.Point;
import org.iproute.complexGraph.define.WeightedGraph;

/**
 * @author :       zhuzhenjie
 * Company :      Beijing Tepia (Wuhan R&D Center)
 **/
public class DijkstraTest {

    public static void main(String[] args) {
        Graph<String, Double> graph = new WeightedGraph<>(false);

        Point<String> A = new Point<>("A");
        Point<String> B = new Point<>("B");
        Point<String> C = new Point<>("C");
        Point<String> D = new Point<>("D");
        Point<String> E = new Point<>("E");
        Point<String> F = new Point<>("F");

        graph.connectPoints(A, B, 14.0);
        graph.connectPoints(A, C, 9.0);
        graph.connectPoints(A, D, 7.0);
        graph.connectPoints(B, C, 2.0);
        graph.connectPoints(C, D, 10.0);
        graph.connectPoints(B, E, 9.0);
        graph.connectPoints(C, F, 11.0);
        graph.connectPoints(D, F, 15.0);
        graph.connectPoints(E, F, 6.0);

        Dijkstra dijkstra = new Dijkstra(graph, A);

        System.out.println(
                dijkstra.shortestRoute("E")
                        .stream()
                        .reduce((from, to) -> from + "->" + to)
                        .get()
        );

    }
}
