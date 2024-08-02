package org.iproute.graphw;


/**
 * Created by      Intellij IDEA
 *
 * @author tech@intellij.io
 * Date    :       2018-12-28
 * Time    :       17:44
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 **/
public class LazyPrimTest {

    public static void main(String[] args) {

        WeightGraphDesign<Double> wg = MyReadWeightGraph.getWeighGraph("testWG2.txt", false);

        LazyPrim<Double> lazyPrim = new LazyPrim<>(wg);

        lazyPrim.showMst();

        System.out.println("最小生成树的权值weight:" + lazyPrim.getMstWeight());

    }

}
