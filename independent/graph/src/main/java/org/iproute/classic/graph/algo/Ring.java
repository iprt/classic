package org.iproute.classic.graph.algo;

import org.iproute.classic.graph.define.Edge;
import org.iproute.classic.graph.define.Graph;
import org.iproute.classic.graph.define.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 判断是否有环，顾名思义，针对有向图
 * <p>
 * 通过 dfs 来判断是否有环
 *
 * @author winterfell
 * @since 2022/6/25
 */
public class Ring {
    private final Graph<String, Double> g;

    private int hasRing;

    /**
     * 标记点被访问的次数
     * <p>
     * 初始化为0，表示都没有被访问过
     * <p>
     * value = 1 表示该点被访问过一次
     * <p>
     * value = 2 表示该店被访问过两次
     */
    private Map<Vertex<String>, Integer> visitedStatus;

    public Ring(Graph<String, Double> g) {
        this.g = g;

        this.init();
    }

    /**
     * 初始化辅助的数据结构
     */
    private void init() {
        this.hasRing = 0;
        Set<Vertex<String>> vertices = this.g.vertices();

        this.visitedStatus = new HashMap<>(vertices.size());

        vertices.forEach(p -> {
            // this.visited.put(p, false);
            this.visitedStatus.put(p, 0);
        });
    }


    /**
     * 环的计算
     *
     * @param s 入度为0的点？
     */
    public void calculate(Vertex<String> s) {
        if (!this.g.direct()) {
            System.out.println("无向图，不需要计算");
            return;
        }

        this.dfs(s);
    }

    public boolean hasRing() {
        if (!this.g.direct()) {
            System.out.println("该图是无向图，一定有环");
            return true;
        }
        return this.hasRing > 0;
    }

    private void dfs(Vertex<String> p) {
        this.visitedStatus.put(p, 1);

        for (Edge<Double, String> edge : this.g.adj(p)) {
            Vertex<String> other = edge.other(p);
            Integer v = this.visitedStatus.get(other);
            if (v != 1) {
                if (v == 0) {
                    // 深度遍历
                    this.dfs(other);
                }

                if (v == 2) {
                    this.hasRing++;
                    break;
                }
            }

        }
        this.visitedStatus.put(p, 2);
    }
}

/*

整体思路：
一条深度遍历路线中如果有结点被第二次访问到，那么有环

深度遍历一遍 每个节点都会经过3个状态 没有被访问 访问过 自己节点后面的节点都被访问了

 */