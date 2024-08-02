package org.iproute.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * java 值传递 和 引用传递的问题
 *
 * @author tech@intellij.io
 * @since 2021/11/15
 */
public class Problem {

    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public Problem() {
        this.list = null;
    }

    public void put() {
        this.put(this.list);
    }

    private void put(List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Problem p = new Problem();
        p.put();

        System.out.println(p.getList());
    }
}
