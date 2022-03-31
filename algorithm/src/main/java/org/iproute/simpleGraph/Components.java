package org.iproute.simpleGraph;

/**
 * @author zzj
 * @create 2018-5-25
 */
// 求无权图的连通分量
public class Components {

    Graph g;
    private boolean[] visited;
    // 连通分量
    private int ccount;
    // 利用并查集求两个点是否在连接
    private int[] id;

    public Components(Graph g) {
        this.g = g;
        visited = new boolean[g.V()];
        id = new int[g.V()];
        this.ccount = 0;

        // 算法初始化
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        for (int i = 0; i < g.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    public int count() {
        return ccount;
    }

    public boolean isConnected(int x, int y) {
        return id[x] == id[y];
    }

    // 深度遍历
    public void dfs(int v) {
        visited[v] = true;
        // 利用并查集求两点是否连接
        id[v] = ccount;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
//                System.out.println(i + "  ");
                dfs(i);
            }
        }
    }
}