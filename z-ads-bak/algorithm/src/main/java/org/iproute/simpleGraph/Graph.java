package org.iproute.simpleGraph;

/**
 * @author tech@intellij.io
 * @create 2018-5-25
 */
public interface Graph {

    // 点的数量
    int V();

    // 边的数量
    int E();

    void addEdge(int x, int y);

    boolean hasEdge(int x, int y);

    Iterable<Integer> adj(int v);

    void show();

}