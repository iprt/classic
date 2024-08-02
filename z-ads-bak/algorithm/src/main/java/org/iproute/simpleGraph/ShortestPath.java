package org.iproute.simpleGraph;

import java.util.*;

/**
 * @author tech@intellij.io
 * @create 2018-5-28
 */
public class ShortestPath {
    private Graph g;

    private int s;

    // 记录点是否被遍历过
    private boolean[] visited;

    // 遍历点的时候记录下这个点是从哪个点遍历过来
    private int[] from;

    private double[] ord;

    public ShortestPath(Graph g, int s) {
        this.g = g;
        this.s = s;
        this.visited = new boolean[g.V()];
        this.from = new int[g.V()];
        this.ord = new double[g.V()];
        for (int i = 0; i < g.V(); i++) {
            this.visited[i] = false;
            this.from[i] = -1;
            this.ord[i] = -1.0;
        }

        // 广度优先遍历求最短路径
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        ord[s] = 0.0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int i : g.adj(v)) {
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    from[i] = v;
                    // 路径计算，在于指定了源
                    ord[i] = ord[v] + 1.0;
                }
            }
        }
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

    public double length(int w) {
        return ord[w];
    }
}