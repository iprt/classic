package org.iproute.graph;

/**
 * @author tech@intellij.io
 **/
public interface GraphDesign {

    // point number
    int v();

    // edge number
    int e();

    void addEdge(int x, int y);

    boolean hasEdge(int x, int y);

    Iterable<Integer> adj(int v);

    void show();
}
