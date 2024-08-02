package org.iproute.simpleGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author tech@intellij.io
 * @create 2018-5-28
 */
public class Path {

    private Graph g;

    private int s;

    // 记录点是否被遍历过
    private boolean[] visited;

    // 遍历点的时候记录下这个点是从哪个点遍历过来
    private int[] from;

    // 初始化的时候要指定源
    public Path(Graph g, int s) {

        this.visited = new boolean[g.V()];
        this.from = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        this.g = g;
        this.s = s;

        // 寻路算法
        this.dfs(s);
    }

    public boolean haPath(int w) {
        return visited[w] == true;
    }

    void path(int w, List<Integer> list) {
        Stack<Integer> s = new Stack<>();
        int p = w;
        // 源节点的值仍然为-1
        while (p != -1) {
            s.push(p);
            p = from[p];
        }

        list.clear();
        while (!s.empty()) {
            list.add(s.pop());
        }
    }

    void showPath(int w) {
        List<Integer> list = new ArrayList<>();
        path(w, list);
        System.out.print("从" + s + "->" + w + "的路径为:");
        for (int i : list) {
            System.out.print(i + "->");
        }
    }


    private void dfs(int v) {
        visited[v] = true;
        Iterable<Integer> adj = g.adj(v);
        for (int tmp : adj) {
            if (!visited[tmp]) {
                // 更新从哪里来的点
                from[tmp] = v;
                dfs(tmp);
            }
        }
    }
}