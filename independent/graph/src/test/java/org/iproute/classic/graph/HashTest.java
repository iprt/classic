package org.iproute.classic.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * HashTest
 *
 * @author tech@intellij.io
 * @since 2022/6/30
 */
public class HashTest {

    @Test
    public void hashTest() {
        Set<HashDemo> hashDemos = new HashSet<>();

        hashDemos.add(new HashDemo("A", "zhuzhenjie"));
        hashDemos.add(new HashDemo("A", "zhuzhenjie"));
        hashDemos.add(new HashDemo("B", "zhuzhenjie"));

        hashDemos.forEach(System.out::println);
    }

    private static class HashDemo {

        private final String namespace;
        private final String name;


        private int hash;

        public HashDemo(String namespace, String name) {
            this.namespace = namespace;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (Objects.isNull(obj) || !(obj instanceof HashDemo)) {
                return false;
            }
            if (!this.namespace.equals(((HashDemo) obj).namespace)) {
                return false;
            }
            return this.name.equals(((HashDemo) obj).name);
        }

        @Override
        public int hashCode() {
            int h = hash;
            if (h == 0) {

                h = 31 * this.namespace.hashCode() + this.name.hashCode();

                hash = h;
            }
            return hash;
        }

        @Override
        public String toString() {
            return "Hello{" +
                    "namespace='" + namespace + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
