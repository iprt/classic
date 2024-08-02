package org.iproute.classic.graph.define;

/**
 * 定义图中的边
 * <p>
 * W: 权重，权重是需要可以比较的
 * <p>
 * T：点存放的内容
 *
 * @author tech@intellij.io
 * @since 2022/6/13
 */
public class Edge<W extends Comparable<W>, T> implements Namespace {

    private int hash;

    private final String namespace;
    /**
     * 一条边存在两个点，from --> to
     */
    private final Vertex<T> from;
    private final Vertex<T> to;

    /**
     * 这条边的权重
     */
    private final W w;


    // 提供边的初始化方法

    public Edge(String namespace, Vertex<T> from, Vertex<T> to, W w) {
        this.namespace = namespace;
        this.from = from;
        this.to = to;
        this.w = w;
    }


    /**
     * 复制操作？
     *
     * @param replica the replica
     */
    public Edge(String namespace, Edge<W, T> replica) {
        this.namespace = namespace;
        this.from = replica.from;
        this.to = replica.to;
        this.w = replica.w;
    }

    @Override
    public String namespace() {
        return this.namespace;
    }

    /**
     * 根据这条边的一个顶点获取到另外一个顶点
     *
     * @param x the x
     * @return the point
     */
    public Vertex<T> other(Vertex<T> x) {
        if (x == null) {
            return null;
        }
        return x.equals(from) ? to : (x.equals(to) ? from : null);
    }

    /*
     * 提供对应的get方法
     */

    public Vertex<T> getFrom() {
        return from;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public W getW() {
        return w;
    }


    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0) {
            h = 31 * 31 * 31 * namespace.hashCode()
                    + 31 * 31 * from.hashCode()
                    + 31 * to.hashCode()
                    + w.hashCode();
            hash = h;
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }

        if (this.namespace.equals(((Edge<?, ?>) obj).namespace)) {
            return false;
        }

        return this.from.equals(((Edge<?, ?>) obj).from)
                && this.to.equals(((Edge<?, ?>) obj).to)
                && this.w.equals(((Edge<?, ?>) obj).w);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", w=" + w +
                '}';
    }
}
