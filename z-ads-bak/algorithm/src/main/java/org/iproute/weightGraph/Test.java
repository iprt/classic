package org.iproute.weightGraph;

/**
 * @author tech@intellij.io
 * @create 2018-5-28
 */
public class Test {

    public static void main(String[] args) {
        test2();
    }


    public static void test2() {
        WeightedGraph wg = ReadWeightGraph.readInGraph("testG1.txt", false);
        LazyPrimMST lp = new LazyPrimMST(wg);
        System.out.println(lp.result());
        lp.mstEdges().forEach(e -> System.out.print(e + "\t"));
    }

    public static void test1() {

        WeightedGraph wg = ReadWeightGraph.readInGraph("testG1.txt", false);
        wg.show();

    }

}