package winterfell.graph.define;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author zhuzhenjie
 * 图的实现 邻接表
 */
public class WeightedGraph<Element, Relation extends Comparable> implements Graph<Element, Relation> {


    /**
     * 是否是有向图
     */
    private boolean direct;

    /**
     * 临接矩阵表示的图 利用set作为边的集合提高效率
     */
    private Map<Point<Element>, Set<Edge<Element, Relation>>> graph;

    /**
     * 所有的边
     */
    private Set<Edge<Element, Relation>> allEdges;

    /**
     * 构造一张图，确认是否是有向图
     *
     * @param direct 是否有向或者无向图
     */
    public WeightedGraph(boolean direct) {
        this.direct = direct;
        this.graph = new HashMap<Point<Element>, Set<Edge<Element, Relation>>>() {
            private Method put;
            private Method hash;
            private Method getNode;

            {
                try {
                    hash = HashMap.class.getDeclaredMethod("hash", Object.class);
                    getNode = HashMap.class.getDeclaredMethod("getNode", int.class, Object.class);
                    put = HashMap.class.getDeclaredMethod("put", Object.class, Object.class);
                    hash.setAccessible(true);
                    getNode.setAccessible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Set<Edge<Element, Relation>> get(Object key) {
                Set<Edge<Element, Relation>> relations = super.get(key);
                if (relations == null) {
                    relations = new HashSet<Edge<Element, Relation>>() {

                        @Override
                        @SuppressWarnings("all")
                        public boolean add(Edge<Element, Relation> edges) {
                            if (this.contains(edges)) {
                                this.remove(edges);
                            }
                            return super.add(edges);
                        }
                    };
                    try {
                        put.invoke(this, key, relations);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return relations;
            }

            @Override
            @SuppressWarnings("all")
            public boolean containsKey(Object key) {
                boolean contains = super.containsKey(key);
                if (contains) {
                    try {
                        Point<Element> key0 = (Point<Element>) key;
                        Point<Element> currentkey =
                                ((Entry<Point<Element>, Set<Edge<Element, Relation>>>) getNode.invoke(
                                        this,
                                        hash.invoke(this, key),
                                        key)).getKey();

                        if (!currentkey.getElement().equals(key0.getElement())) {
                            currentkey.setElement(key0.getElement());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return contains;
            }
        };
        this.allEdges = new HashSet<Edge<Element, Relation>>() {

            @Override
            @SuppressWarnings("all")
            public boolean add(Edge<Element, Relation> edge) {
                if (this.contains(edge)) {
                    this.remove(edge);
                }
                return super.add(edge);
            }
        };
    }

    /**
     * 获取边的个数
     *
     * @return 边的个数
     */
    @Override
    public int getEdgeNums() {
        return this.allEdges.size();
    }

    /**
     * 获取点的个数
     *
     * @return 点的个数
     */
    @Override
    public int getPointNums() {
        return this.graph.size();
    }

    @Override
    public Set<Point<Element>> allPoints() {
        return this.graph.keySet();
    }

    @Override
    public Set<Edge<Element, Relation>> allEdges() {
        return this.allEdges;
    }

    /**
     * 添加一条边
     * 判断是否是自己指向自己的边
     *
     * @param edge 边
     */
    @Override
    public void addEdge(Edge<Element, Relation> edge) {

        Point<Element> from = edge.getFrom();
        Point<Element> to = edge.getTo();
        Relation relation = edge.getRelation();
        // 指向自己的边不添加 已经存在的边不添加
        if (from.equals(to)) {
            return;
        }
        this.graph.containsKey(from);
        this.graph.containsKey(to);
        Set<Edge<Element, Relation>> fromEdges = graph.get(from);
        /*
         * 邻接表添加边
         * 使用Set作为集合直接把边放入 更新权重
         */
        fromEdges.add(edge);
        this.allEdges.add(edge);
        Set<Edge<Element, Relation>> toEdges = graph.get(to);

        if (!direct) {
            Edge<Element, Relation> reverseEdge = new Edge<>(to, from, relation);
            toEdges.add(reverseEdge);
            this.allEdges.add(reverseEdge);
        }
    }

    @Override
    public void connectPoints(Point<Element> from, Point<Element> to, Relation relation) {
        Edge<Element, Relation> edge = new Edge<>(from, to, relation);
        this.addEdge(edge);
    }

    /**
     * 判断点a和点b之间有没有边
     *
     * @param from
     * @param to
     * @return
     */
    @Override
    public boolean hasEdge(Point<Element> from, Point<Element> to) {
        Set<Edge<Element, Relation>> aes = (Set<Edge<Element, Relation>>) this.adj(from);
        if (aes == null) {
            return false;
        }
        return aes.contains(new Edge<Element, Relation>(from, to, null));
    }

    /**
     * 获取点p的所有邻边
     *
     * @param p 点p
     * @return 点p所有的领边
     */
    @Override
    public Iterable<Edge<Element, Relation>> adj(Point<Element> p) {
        return graph.get(p);
    }

    /**
     * 是否为有向图
     *
     * @return 是否为有向图
     */
    public boolean isDirect() {
        return direct;
    }

    @Override
    public void show() {
        graph.forEach((p, es) -> {
            StringJoiner sj = new StringJoiner("    ");
            es.forEach(e -> {
                sj.add("{" + e.other(p).toString() + ",Relation:" + e.getRelation().toString() + "}");
            });
            System.out.println(p + ":" + sj.toString());
        });
    }

    public static void main(String[] args) {

        Graph<String, String> wg = new WeightedGraph<>(false);

        Point<String> a1 = new Point<>("a", "a1");
        Point<String> a2 = new Point<>("a", "a2");
        Point<String> b1 = new Point<>("b", "b1");
        Point<String> b2 = new Point<>("b", "b2");

        wg.connectPoints(a1, b1, "a1-b1");
        wg.connectPoints(a2, b2, "a2-b2");
        System.out.println("图的边的个数：" + wg.getEdgeNums());
        System.out.println("图的点的个数：" + wg.getPointNums());
        wg.show();

        wg.allEdges().forEach(edge -> System.out.println(edge));


    }

}
