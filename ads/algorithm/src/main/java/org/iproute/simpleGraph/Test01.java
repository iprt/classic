package org.iproute.simpleGraph;

import org.iproute.simpleGraph.graph.DenseGraph;
import org.iproute.simpleGraph.graph.SparseGraph;


/**
 * @author tech@intellij.io
 * @create 2018-5-25
 */
public class Test01 {

    public static void main(String[] args) {
        test07();
    }

    public static void test07() {
        Graph sg = GraphHelper.readDataInGraph("testG_path.txt", false);
        ShortestPath p = new ShortestPath(sg, 1);
        p.showPath(4);
    }

    public static void test06() {
        Graph sg = GraphHelper.readDataInGraph("testG_path.txt", false);
        Path p = new Path(sg, 1);
        p.showPath(6);

    }

    // 测试连接
    public static void test05() {
        Graph sg = GraphHelper.readDataInGraph("testG1.txt", false);
        Components c = new Components(sg);
        System.out.println("连通分量个数：" + c.count());
        System.out.println(c.isConnected(8, 7));
    }

    // 测试连通分量
    public static void test04() {
        Graph sg = GraphHelper.readDataInGraph("testG2.txt", false);
        Components c = new Components(sg);
        System.out.println(c.count());
    }

    public static void test03() {
        Graph sg2 = GraphHelper.readDataInGraph("testG1.txt", false);
        Components c = new Components(sg2);
        c.dfs(0);
    }

    // 生成测试
    public static void test02() {
        Graph dg2 = GraphHelper.readDataInGraph("testG2.txt", true);
        Graph sg2 = GraphHelper.readDataInGraph("testG2.txt", false);

        dg2.show();
        System.out.println("***********************");
        sg2.show();
    }

    // 随机测试
    public static void test01() {
        int n = 20;
        int m = 100;
        Graph dg = new DenseGraph(n, false);
        Graph sg = new SparseGraph(n, false);

        // 随机添加100次边
        for (int i = 0; i < m; i++) {
            int x = (int) (Math.random() * (n));
            int y = (int) (Math.random() * (n));
            if (x != y) {
                dg.addEdge(x, y);
                sg.addEdge(x, y);
            }
        }
        dg.show();
        System.out.println("***********************");
        sg.show();
    }

}