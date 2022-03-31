package org.iproute.graph;

import java.util.*;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-27
 * Time    :       15:32
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 * 无权图最短路径
 **/
public class MyShortedPath {

    private GraphDesign g;

    private int s;

    private boolean[] visited;

    private int[] from;

    private double[] ord;

    public MyShortedPath(GraphDesign g, int s) {
        this.g = g;
        this.s = s;
        this.visited = new boolean[g.v()];
        this.from = new int[g.v()];
        this.ord = new double[g.v()];
        for (int i = 0; i < g.v(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1.0;
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
                    from[i] = v;
                    visited[i] = true;
                    ord[i] = ord[v] + 1.0; // 相对于源对应的距离
                }
            }
        }
    }

    public boolean hasPath(int w) {
        return visited[w];
    }

    private List<Integer> path(int w) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }
        while (!stack.empty()) {
            res.add(stack.pop());
        }
        return res;
    }

    public void showPath(int w) {
        List<Integer> pathList = path(w);
        System.out.print("从" + s + "->" + w + "最短的路径为:");
        pathList.forEach(tmp -> System.out.print(tmp + "->"));
        System.out.println();
    }
}
