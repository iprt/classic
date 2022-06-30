package org.iproute.classic.graph.define.hash;

import java.util.Objects;

/**
 * Demo
 *
 * @author winterfell
 * @since 2022/6/14
 */
public class Demo {

    private String namespace;
    private String name;


    private int hash;

    public Demo(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(obj) || !(obj instanceof Demo)) {
            return false;
        }
        if (!this.namespace.equals(((Demo) obj).namespace)) {
            return false;
        }
        return this.name.equals(((Demo) obj).name);
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
