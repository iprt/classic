package org.iproute.graphw;

/**
 * @author tech@intellij.io
 * 有权的图从边开始定义
 **/
public class MyEdge<W extends Comparable> implements Comparable<MyEdge> {

    private int x;

    private int y;

    private W weight;

    public MyEdge(int x, int y, W weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int other(int x){
        if( x!=this.x && x !=this.y ){
            return -1;
        }
        return x == this.x ? this.y : this.x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public W getWeight() {
        return weight;
    }

    public void setWeight(W weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(MyEdge o) {
        if (this.weight.compareTo(o.getWeight()) < 0) {
            return -1;
        } else if (this.weight.compareTo(o.getWeight()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
