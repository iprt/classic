package org.iproute.graph;

/**
 * @author tech@intellij.io
 **/
public class MyComponents {

    private final GraphDesign g;

    // 某个点是否被访问了
    private final boolean[] visited;

    private int ccount;

    private int[] id;

    public MyComponents(GraphDesign g) {
        this.g = g;
        // 顶点个数
        this.visited = new boolean[g.v()];
        this.id = new int[g.v()];
        this.ccount = 0; // 初始化联通分量的计数

        // 初始化每个点都没有被访问
        for (int i = 0; i < g.v(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        for (int i = 0; i < g.v(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }

    }

    public int count() {
        return this.ccount;
    }

    public boolean isConnected(int x, int y) {
        return id[x] == id[y];
    }

    public void dfs(int v) {
        visited[v] = true;
        // use union find collection to find the connection of two point
        id[v] = ccount;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
