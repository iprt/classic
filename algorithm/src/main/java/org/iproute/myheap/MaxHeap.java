package org.iproute.myheap;

// 最大堆
public class MaxHeap<Item extends Comparable> {

    // 利用数组实现数据存储
    private Item[] data;

    // 记录data数据的索引
    // 不交换真正的值 交换索引
    // 数组里面的元素不变 维护的是数组的索引
    private int count;

    private int capacity;

    // 构造一个空堆，可以容纳capacity个元素
    public MaxHeap(int capacity) {
        this.data = (Item[]) new Comparable[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public void insert(Item item) {
        int currentPoint = count + 1;
        if (currentPoint > capacity) {
            throw new RuntimeException("堆容量满");
        }
        // 1 直接插入到数组后面
        // 2 然后进行up操作
        // 3 为什么是count+1 因为数组的第一位元素不存储数据
        this.data[currentPoint] = item;
        this.count++;
        upTheData2(currentPoint);
    }

    public Item getMax() {
        return data[1];
    }

    public Item extractMax() {
        Item res = data[1];
        swap(1, count);
        // 必须先减小
        this.count--;
        downTheData2(1);
        return res;
    }

    //*** 辅助方法


    // 迭代方式的shiftUp
    // 只与父亲对比
    private void upTheData(int k) {
        while (k > 1 && data[k].compareTo(data[k / 2]) > 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    // 递归方式的shiftUp
    // 利用递归的方式
    private void upTheData2(int k) {
        if (k > 1 && data[k].compareTo(data[k / 2]) > 0) {
            swap(k, k / 2);
            upTheData(k / 2);
        }
    }

    // 迭代方式的shiftDown
    private void downTheData(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            // 取两个子节点可能存在的最大的一个
            // j+1 <= count 代表存在这个点
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j = j + 1;
            }
            // 比较k 和 它其中一个子节点的值
            if (data[k].compareTo(data[j]) > 0) {
                break;
            } else {
                swap(j, k);
                k = j;
            }
        }
    }

    // 递归方式的shiftDown
    private void downTheData2(int k) {
        if (2 * k <= count) { // 代表子节点存在
            int j = 2 * k;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j = j + 1;
            }
            if (data[k].compareTo(data[j]) > 0) {
                return;
            } else {
                swap(k, j);
                downTheData2(j);
            }
        }
    }

    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

}
