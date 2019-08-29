package winterfell.graph.define;

/**
 * @author zhuzhenjie
 * 图中的点
 */
public class Point<Element> {
    /**
     * 唯一标识
     */
    private String sign;

    /**
     * 点的内容
     */
    private Element element;

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Point(String sign) {
        this.sign = sign;
        if (null == sign) {
            throw new RuntimeException("唯一标识不可为空");
        }
    }

    public Point(String sign, Element element) {
        this.sign = sign;
        this.element = element;
        if (null == sign) {
            throw new RuntimeException("唯一标识不可为空");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return this.getSign().equals(p.getSign());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.getCode(sign);
    }

    @Override
    public String toString() {
        return "Point{" +
                "sign='" + sign + '\'' +
                ", element=" + element +
                '}';
    }
}
