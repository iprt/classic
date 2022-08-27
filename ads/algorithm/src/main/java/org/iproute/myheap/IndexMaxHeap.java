package org.iproute.myheap;

// 最小索引堆
public class IndexMaxHeap<Item extends Comparable<Item>> {
    // indexes 和 data是相辅相成的

    // 利用数组实现数据存储
    private Item[] data;

    // 要明白index记录的是什么
    private int[] indexes;

    // 记录data数据的索引
    // 不交换真正的值 交换索引
    // 数组里面的元素不变 维护的是数组的索引
    private int count;

    private int capacity;

    // 构造一个空堆，可以容纳capacity个元素
    public IndexMaxHeap(int capacity) {
        this.data = (Item[]) new Comparable[capacity + 1];
        this.indexes = new int[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    // 这个i是从0开始计算的 0,1,2,3,4,5,6
    // 如果不是从0开始插入的呢
    public void insert(int i, Item item) {
        assert (i + 1 >= 1);

        int currentDataIndex = i + 1;
        // i i i data是从1开始的
        // 理解成同时记录
        data[currentDataIndex] = item;
        indexes[count + 1] = currentDataIndex; // i i i

        // count ++ 以后
        this.count++;
        // 为什么对 count 进行 shiftUp
        shiftUp(count);
    }

    private void shiftUp(int index) {
        while (index > 1 && data[indexes[index]].compareTo(data[indexes[index / 2]]) > 0) {
            swapIndexes(index, index / 2);
            index = index / 2;
        }
    }

    // 获得最大值
    public Item getMax() {
        return data[indexes[1]];
    }

    public int getMaxIndex() {
        return indexes[1] - 1;
    }

    // 取出最大值
    public Item extractMax() {
        Item res = data[indexes[1]];
        swapIndexes(1, count);
        // 必须先减小
        this.count--;
        shiftDown(1); // 1 是指的index的第一位
        return res;
    }

    // 取出最大值的索引
    public int extractMaxIndex() {
        int res = indexes[1];
        swapIndexes(1, count);
        this.count--;
        shiftDown(1); // 1 是指的index的第一位
        // 用户存的时候从0开始的
        return res - 1;
    }

    public void change(int index, Item newItem) {
        // index从0开始
        int dataIndex = index + 1;
        data[dataIndex] = newItem;
        // 找到index所在的位置
        for (int j = 1; j <= count; j++) {
            // 找到 indexes 里面的 一个值
            // 满足 indexes[k] = dataIndex
            // 然后对这个位置进行 shiftUp 和 shiftDown
            // 假设是交换data的元素 这个 k 的位置就是 newItem真正所在的位置
            if (indexes[j] == dataIndex) {
                // 在最后值的比较上
                // 用的还是data[indexes[k]] 等价于 data[dataIndex]
                shiftUp(j);
                shiftDown(j);
            }
        }
    }

    // 迭代方式的shiftDown
    // index 是data里面的index
    // 真正取数据的时候套了一层
    private void shiftDown(int k) {
        while (k * 2 <= count) {
            int childIndex = k * 2;
            if (childIndex + 1 <= count && data[indexes[childIndex + 1]].compareTo(data[indexes[childIndex]]) > 0) {
                childIndex = childIndex + 1;
            }
            if (data[indexes[k]].compareTo(data[indexes[childIndex]]) > 0) {
                break;
            } else {
                swapIndexes(k, childIndex);
                k = childIndex;
            }
        }
    }


    private void swapIndexes(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }

    public static void main(String[] args) {
        IndexMaxHeap<Integer> heap = new IndexMaxHeap<>(10);
        heap.insert(0, 0);
        heap.insert(1, 1);
        heap.insert(2, 2);
        heap.insert(3, 3);
        heap.insert(4, 4);
        heap.insert(5, 5);
        heap.insert(6, 6);
        heap.insert(7, 7);
        heap.insert(9, 9);
        heap.insert(8, 8);

        heap.change(0, 10);
        heap.change(1, 11);
        heap.change(2, 12);
        heap.change(3, 13);
        heap.change(4, 14);
        heap.change(5, 15);
        heap.change(6, 16);
        heap.change(7, 17);
        heap.change(8, 18);
        heap.change(9, 19);

        for (int i = 0; i < 10; i++) {
            System.out.println(heap.extractMax());
        }
    }
}
