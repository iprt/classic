package org.iproute.graphw;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-27
 * Time    :       19:05
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 * 简单的寻路算法 与无权图的寻路算法同理
 **/
public class MyEasePath {

    private WeightGraphDesign<Double> wg;

    private boolean[] visited;

    private int[] from;

    private int start;

    public MyEasePath(WeightGraphDesign<Double> wg, int start) {
        this.wg = wg;
        this.start = start;
        this.visited = new boolean[wg.v()];
        this.from = new int[wg.v()];
        for (int i = 0; i < wg.v(); i++) {
            this.from[i] = -1;
            this.visited[i] = false;
        }
        dfs(start);
    }

    public boolean hasPath(int dest) {
        return visited[dest];
    }

    public void showPath(int dest) {
        List<Integer> easePath = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int k = dest;
        while (from[k] != -1) { // 开始节点的开始值为 -1
            stack.push(k);
            k = from[k];
        }

        easePath.add(start);
        while (!stack.empty()) {
            int tmp = stack.pop();
            easePath.add(tmp);
        }
        System.out.print("from " + start + " to " + dest + " path is:");
        easePath.forEach(e -> System.out.print(e + "->"));
    }

    // 深度遍历寻路
    private void dfs(int point) {
        visited[point] = true;
        for (MyEdge<Double> e : wg.adj(point)) {
            int other = e.other(point);
            if (visited[other]) {
                continue;
            }
            // 另外一个点从当前点过来
            from[other] = point;
            dfs(other);
        }
    }
}
