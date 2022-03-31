package org.iproute.complexGraph;

import org.iproute.complexGraph.define.Graph;
import org.iproute.complexGraph.define.Point;
import org.iproute.complexGraph.define.WeightedGraph;

/**
 * @author :       zhuzhenjie
 **/
public class ComponentsTest {

    public static void main(String[] args) {
        Graph<String, String> wg = new WeightedGraph<>(false);

        Point<String> a = new Point<>("A", "AAA");
        Point<String> b = new Point<>("B", "BBB");
        Point<String> c = new Point<>("C", "CCC");
        Point<String> d = new Point<>("D", "DDD");
        Point<String> e = new Point<>("E", "EEE");
        Point<String> f = new Point<>("F", "FFF");

        wg.connectPoints(a, b, "A-B");
        wg.connectPoints(c, d, "C-D");
        wg.connectPoints(e, f, "E-F");
        wg.connectPoints(b, d, "B-D");
        wg.connectPoints(c, e, "C-E");

        wg.connectPoints(a, c, "A-C");

        Components<String, String> cp = new Components<>(wg);

        System.out.println("连通图的个数：" + cp.getComponentNum());
        System.out.println("判断点和点之间是否连接：" + (cp.isConnected(a, f) ? "有" : "没有"));
        System.out.println("判断图里面是否有环：" + (cp.hasRing() ? "有环" : "没有环"));


    }
}
