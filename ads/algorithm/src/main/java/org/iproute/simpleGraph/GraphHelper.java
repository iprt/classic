package org.iproute.simpleGraph;

import org.iproute.simpleGraph.graph.DenseGraph;
import org.iproute.simpleGraph.graph.SparseGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tech@intellij.io
 * @create 2018-5-25
 */
public class GraphHelper {

    private static String FOLDER_PATH = "D:" + File.separator + "src" + File.separator + "graph" + File.separator;

    public static Graph readDataInGraph(String filename, boolean ds) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FOLDER_PATH + filename));
            List<String> info = new ArrayList<>();
            String flush;
            while ((flush = reader.readLine()) != null) {
                info.add(flush);
            }
            reader.close();
            int n = Integer.valueOf(info.get(0).split(" ")[0]);
            int m = Integer.valueOf(info.get(0).split(" ")[1]);
            Graph g;
            if (ds) {
                g = new DenseGraph(n, false);
            } else {
                g = new SparseGraph(n, false);
            }
            for (int i = 1; i < m; i++) {
                g.addEdge(Integer.valueOf(info.get(i).split(" ")[0]), Integer.valueOf(info.get(i).split(" ")[1]));
            }
            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}