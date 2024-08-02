package org.iproute.graph;

/**
 * @author tech@intellij.io
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
