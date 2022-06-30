package org.iproute.complexGraph;

/**
 * @author :       zhuzhenjie
 **/
public class MinHeap<Item extends Comparable> {

    private Item[] data;

    private int count;

    private int capacity;

    public MinHeap() {
        this(10);
    }

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.data = (Item[]) new Comparable[capacity];
        this.count = 0;
    }

    public int size() {
        return this.count;
    }

    /**
     * 判断这个堆是不是空的
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * 添加一个元素
     *
     * @param item
     */
    public void add(Item item) {
        if (capacity >= count) {
            capacity = (capacity + 1) * 2;
            Item[] newData = (Item[]) new Comparable[capacity];
            System.arraycopy(data, 0, newData, 0, count);
            this.data = newData;
        }
        this.data[count] = item;
        up(count);
        count++;
    }


    /**
     * 获取到最小的元素
     *
     * @return 最小的元素
     */
    public Item getMinElement() {
        return isEmpty() ? null : data[0];
    }

    /**
     * 弹出最小的元素
     *
     * @return 最小的元素
     */
    public Item extractMin() {
        if (isEmpty())
            return null;

        Item res = data[0];
        swap(0, count - 1);
        this.count--;
        down(0);
        return res;
    }

    private void up(int index) {
        while (index > 0
                && data[index].compareTo(data[(index - 1) / 2]) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void down(int index) {
        while ((2 * index + 1) < count) {
            int j = 2 * index + 1;
            if (j + 1 < count && data[j + 1].compareTo(data[j]) < 0) {
                j = j + 1;
            }
            if (data[index].compareTo(data[j]) < 0) {
                break;
            }
            swap(index, j);
            index = j;
        }
    }

    private void swap(int x, int y) {
        Item t = data[x];
        data[x] = data[y];
        data[y] = t;
    }

    public static void main(String[] args) {

        MinHeap<Integer> mih = new MinHeap<>(1);

        mih.add(4);
        mih.add(1);
        mih.add(3);
        mih.add(2);

        while (!mih.isEmpty()) {
            System.out.println(mih.extractMin());
        }
    }
}
