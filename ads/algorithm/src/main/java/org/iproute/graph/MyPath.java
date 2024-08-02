package org.iproute.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author tech@intellij.io
 * <p>
 * 指定源访问寻找路径
 **/
public class MyPath {

    private GraphDesign g;

    private int s;

    private boolean[] visited;

    private int[] from;

    // s代表源路径
    public MyPath(GraphDesign g, int s) {
        this.g = g;
        this.s = s;
        this.visited = new boolean[g.v()];
        this.from = new int[g.v()];
        for (int i = 0; i < g.v(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        // 从源深度遍历
        this.dfs(s);
    }

    public boolean hasPath(int w) {
        return visited[w];
    }

    // 显示 s -> w 的路径
    public void showPath(int w) {
        // 反过来从 w找到s
        List<Integer> sw = findPath(w);
        System.out.print("从" + s + "->" + w + "的路径为:");
        sw.forEach(i -> System.out.print(i + "->"));
        System.out.println();
    }

    private List<Integer> findPath(int w) {
        List<Integer> sw = new ArrayList<>();
        // 记录w->s的路径
        Stack<Integer> ws = new Stack<>();
        int p = w;
        while (-1 != p) {
            ws.push(p);
            p = from[p];
        }
        sw.clear();
        while (!ws.empty()) {
            // 记录s -> w的路径
            sw.add(ws.pop());
        }
        return sw;
    }


    private void dfs(int v) {
        visited[v] = true;
        Iterable<Integer> adj = g.adj(v);
        for (int t : adj) {
            if (!visited[t]) {
                // 深度遍历的同时 把路径记录下来
                from[t] = v;
                dfs(t);
            }
        }
    }
}
