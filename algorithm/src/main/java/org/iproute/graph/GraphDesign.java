package org.iproute.graph;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-26
 * Time    :       15:01
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
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
