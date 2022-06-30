package org.iproute.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       zhuzhenjie
 * Date    :       2018-12-26
 * Time    :       16:06
 * Version :       1.0
 * Company :      Beijing Tepia (Wuhan R&D Center)
 * <p>
 * 读图的数据
 **/
public class ReadGraph {

    public static GraphDesign getGraph(String txtName, boolean isDense) {
        try {
            List<String> lines = getLines(txtName);
            String firstLine = lines.get(0);
//            String[] v_e = ve.split(" ");
//            int v = Integer.valueOf(v_e[0]);
//            int e = Integer.valueOf(v_e[1]);
            int v = Integer.valueOf(firstLine);
            GraphDesign g = null;
            if (isDense) {
                g = new MyDenseGraph(v, false);
            } else {
                g = new MySparseGraph(v, false);
            }
            for (int i = 1; i < lines.size(); i++) {
                String xy = lines.get(i);
                String[] x_y = xy.split(" ");
                g.addEdge(Integer.valueOf(x_y[0]), Integer.valueOf(x_y[1]));
            }
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static List<String> getLines(String txtName) throws Exception {

        List<String> lines = new ArrayList<>();

        String userDir = System.getProperty("user.dir");

        BufferedReader reader = new BufferedReader(new FileReader(
                userDir + File.separator + "resources" + File.separator + txtName
        ));

        String line = null;
        while (null != (line = reader.readLine())) {
            if (!line.equals("")) {
                lines.add(line);
            }
        }

        reader.close();

        return lines;

    }


}
