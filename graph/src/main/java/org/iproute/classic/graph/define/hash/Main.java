package org.iproute.classic.graph.define.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * Main
 *
 * @author winterfell
 * @since 2022/6/14
 */
public class Main {

    public static void main(String[] args) {

        Set<Demo> demos = new HashSet<>();

        demos.add(new Demo("A", "zhuzhenjie"));
        demos.add(new Demo("A", "zhuzhenjie"));
        demos.add(new Demo("B", "zhuzhenjie"));

        demos.forEach(System.out::println);
    }

}
