package org.iproute.graphw;


/**
 * Created by      Intellij IDEA
 *
 * @author tech@intellij.io
 * Date    :       2018-12-27
 * Time    :       18:33
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 **/
public class Test {

    public static void main(String[] args) {

        WeightGraphDesign<Double> wg = MyReadWeightGraph.getWeighGraph("testWG1.txt", true);

        wg.show();

        MyEasePath myEasePath = new MyEasePath(wg, 0);

        myEasePath.showPath(2);

    }

}
