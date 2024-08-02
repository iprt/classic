package org.iproute.sort.heap;

/**
 * Created by winterfell on 2017/9/25.
 * 自定义二叉堆
 */
public class MaxHeap<Item extends Comparable> {

    /*private*/ Item[] data;

    /*private*/ int count;
    /*private*/ int capacity;

    // 构造一个空堆，可以容纳capacity个元素
    public MaxHeap(int capacity) {
        this.data = (Item[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    // heapify
    public MaxHeap(Item[] arr){
        int n = arr.length;
        data = (Item[])new Comparable[n+1];
        capacity = n;
        for(int i=0;i<n;i++){
            data[i+1] = arr[i];
        }
        count = n;
        // heapify的操作
        // 基于完全二叉树的性质操作而来
        for(int i = count/2;i>=1;i--){
            shiftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个boolean值 表示堆中的数组是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 向最大堆中插入一个元素
    public void insert(Item item) {
        assert (count + 1 <= capacity);
        // 为什么是count+1 因为第一个序列是从1开始的
        data[count + 1] = item;
        count++;
        // 经典操作 shift up
        shiftUp(count);
    }

    // 获取最大堆中的堆顶元素
    public Item getMax() {
        assert count > 0;
        return data[1];
    }


    // 从最大堆中取出堆顶元素
    public Item extractMax() {
        assert count > 0;
        Item ret = data[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    // 交换索引堆中 i 和 j的元素
    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    // **************************
    // 最大核心辅助函数
    // **************************
    private void shiftUp(int k) {
        // 从最下面交换到最上面
        // k/2 取到父节点
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k / 2, k);
            k = k / 2;
        }

    }

    private void shiftDown(int k) {

        //判断永远存在叶子节点的节点
        //  2*k <= count
        // 必须是小于等于
        // 会考察到最后面
        while (2 * k <= count) {
            int j = 2 * k; // 在此轮循环中交换data[k]和data[j]的位置
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) { // 取子节点的大的值的序号
                // j+1<=count 是为了判断是否只有一个子节点
                // 如果j+1 > count 说明叶子节点只有一个 j的值不需要变化
                // 如果j+1的值大的话，需要交换的位置就是j+1
                j = j + 1;
            }
            // 判断与j是否要进行交换
            if (data[k].compareTo(data[j]) >= 0) {
                // 如果父亲节点的值比两个子节点的值都打的话，不需要交换了
                // 直接break出去
                break;
            }
            swap(k, j);
            // 刷新k
            k = j;
            // 对数组再次判断

        }
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(100);
        int n = 100;
        int M = 100;
        for (int i = 0; i < n; i++) {
            maxHeap.insert((int) (Math.random() * M));
        }
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
            System.out.println(arr[i] + " ");
        }
    }
}
