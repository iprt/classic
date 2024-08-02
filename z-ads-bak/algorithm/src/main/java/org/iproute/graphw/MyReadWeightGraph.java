package org.iproute.graphw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author tech@intellij.io
 * Date    :       2018-12-27
 * Time    :       18:33
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 * 读取有权图的方法类
 **/
public class MyReadWeightGraph {
    public static WeightGraphDesign<Double> getWeighGraph(String filename, boolean isDense) {
        List<String> lines = readLines(filename);
        String firstLine = lines.get(0);
        int pointNums = Integer.valueOf(firstLine);
        WeightGraphDesign<Double> wg = null;
        if (isDense) {
            wg = new MyDenseWeighGraph<>(pointNums, false);
        } else {
            wg = new MySparseWeightGraph<>(pointNums, false);
        }
        for (int i = 1; i < lines.size(); i++) {
            String tmp = lines.get(i);
            String[] xyw = tmp.split(" ");
            wg.addEdge(new MyEdge<Double>(
                    Integer.valueOf(xyw[0]),
                    Integer.valueOf(xyw[1]),
                    Double.valueOf(xyw[2]))
            );
        }
        return wg;
    }

    public static List<String> readLines(String fileName) {
        String userDir = System.getProperty("user.dir");
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    userDir + File.separator + "resources" + File.separator + fileName
            ));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!"".equals(line)) {
                    lines.add(line);
                }
            }
            reader.close();
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
