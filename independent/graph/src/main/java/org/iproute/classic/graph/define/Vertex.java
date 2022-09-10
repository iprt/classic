package org.iproute.classic.graph.define;

/**
 * 定义图中的点
 *
 * @author winterfell
 * @since 2022/6/13
 */
public class Vertex<T> implements Namespace {

    private int hash;
    private final String namespace;

    /**
     * 描述点内的内容
     */
    private T content;

    /**
     * 定义一个唯一标识
     * <p>
     * 对于这个标识，要生成这个点的hashCode,便于后续邻接表的Map的使用
     */
    private final String sign;

    @Override
    public String namespace() {
        return this.namespace;
    }

    public Vertex(String namespace, String sign) {
        this.namespace = namespace;
        this.sign = sign;
    }

    public Vertex(String namespace, String sign, T content) {
        this.namespace = namespace;
        this.sign = sign;
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public int hashCode() {
        int h = hash;
        if (h == 0) {
            h = 31 * this.namespace.hashCode() + this.sign.hashCode();
            hash = h;
        }
        return h;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) {
            return false;
        }
        if (!this.namespace.equals(((Vertex<?>) obj).namespace)) {
            return false;
        }
        return this.sign.equals(((Vertex) obj).sign);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "sign='" + sign + '\'' +
                '}';
    }
}
