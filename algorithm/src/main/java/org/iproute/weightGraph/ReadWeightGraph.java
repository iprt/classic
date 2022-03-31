package org.iproute.weightGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzj
 * @create 2018-5-28
 */
public class ReadWeightGraph {

    static String folder = "D:\\src\\graph\\wt\\";

    public static WeightedGraph<Double> readInGraph(String fileName, boolean flag) {
        WeightedGraph<Double> g;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(folder + fileName));
            List<String> info = new ArrayList<>();
            String flush;
            while ((flush = reader.readLine()) != null) {
                info.add(flush);
            }
            String first = info.get(0);
            int n = Integer.valueOf(first.split(" ")[0]);
            int m = Integer.valueOf(first.split(" ")[1]);
            if (flag) {
                g = new DenseWeightedGraph<>(n, false);
            } else {
                g = new SparseWeightedGraph<>(n, false);
            }
            for (int i = 1; i < m; i++) {
                String data = info.get(i);
                int x = Integer.valueOf(data.split(" ")[0]);
                int y = Integer.valueOf(data.split(" ")[1]);
                double weight = Double.valueOf(data.split(" ")[2]);
                g.addEdge(new Edge<>(x, y, weight));
            }
            return g;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}