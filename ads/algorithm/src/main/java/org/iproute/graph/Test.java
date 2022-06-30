package org.iproute.graph;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-26
 * Time    :       16:08
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 **/
public class Test {

    public static void main(String[] args) {

        GraphDesign g = ReadGraph.getGraph("testG3.txt", false);

        g.show();

        MyComponents components = new MyComponents(g);

        System.out.println("联通分量为:" + components.count());

        MyPath myPath = new MyPath(g, 0);

        myPath.showPath(9);

        MyShortedPath myShortedPath = new MyShortedPath(g, 0);

        myShortedPath.showPath(9);






    }
}
