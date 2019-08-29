package winterfell.graph.define;

/**
 * @author zhuzhenjie
 * 图中的边
 */
public class Edge<Element, Relation extends Comparable> implements Comparable<Edge<Edge, Relation>> {

    /**
     * 一条边的两个顶点
     */
    private Point<Element> from, to;

    /**
     * 点和点之间的关系
     */
    private Relation relation;

    /**
     * 拿到边以后给定边的一个顶点返回边的另外一个顶点
     *
     * @param x 给定的一个顶点
     * @return 另外一个顶点
     */
    public Point<Element> other(Point<Element> x) {
        return from.equals(x) ? to : (to.equals(x) ? from : null);
    }

    public Edge(Point<Element> from, Point<Element> to, Relation relation) {
        this.from = from;
        this.to = to;
        this.relation = relation;
    }

    public Edge(Edge<Element, Relation> edge) {
        this.from = edge.getFrom();
        this.to = edge.getTo();
        this.relation = edge.getRelation();
    }

    public Point<Element> getFrom() {
        return from;
    }

    public void setFrom(Point<Element> from) {
        this.from = from;
    }

    public Point<Element> getTo() {
        return to;
    }

    public void setTo(Point<Element> to) {
        this.to = to;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", relation=" + relation +
                '}';
    }

    @Override
    public int compareTo(Edge<Edge, Relation> o) {
        return this.relation.compareTo(o.relation);
    }

    /**
     * equals 不对Relation的值做比较
     *
     * @param o other
     * @return 是否equals
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge other = (Edge) o;
            return this.getFrom().equals(other.getFrom())
                    && this.getTo().equals(other.getTo());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.getCode(
                from.getSign(),
                to.getSign()
        );
    }
}
