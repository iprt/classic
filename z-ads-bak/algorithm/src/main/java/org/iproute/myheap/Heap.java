package org.iproute.myheap;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

// 可伸缩的线程安全的堆
public class Heap<Item extends Comparable> implements Iterable<Item> {

    private static final int INIT_SIZE = 2;

    // 利用锁保证多线程环境下堆的安全性
    private Lock lock = new ReentrantLock();

    private Item[] data;

    private int[] indexes;

    // count是数组大小的基准
    private int count;

    private int capacity;

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public void insert(Item item) {
        // 插入数据的时候上锁
        lock.lock();
        try {
            if (count >= capacity) {
                this.expandDataSize();
            }
            // 依次向后添加 currentIndex 是从 0 开始的
            int currentDataIndex = this.count;
            this.data[currentDataIndex] = item;
            this.indexes[currentDataIndex] = currentDataIndex;
            this.count++;
            shiftUp(currentDataIndex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 锁必须在finally 模块中释放
            lock.unlock();
        }
    }

    public Item getMax() {
        return count <= 0 ? null : this.data[indexes[0]];
    }

    public Item extractMax() {
        if (count <= 0) {
            return null;
        }
        Item res = data[indexes[0]];
        swapIndex(0, count - 1);
        this.count--;
        shiftDown(0);
        return res;
    }

    private void shiftUp(int k) {
        // big value up
        while (k > 0 && data[indexes[k]].compareTo(data[indexes[(k - 1) / 2]]) > 0) {
            swapIndex((k - 1) / 2, k);
            k = (k - 1) / 2;
        }
    }

    private void shiftDown(int k) {
        while ((2 * k + 1) < count) {
            int j = 2 * k + 1;
            // chose bigger value form children
            if (j + 1 < count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                j = j + 1;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) > 0) {
                break;
            } else {
                swapIndex(k, j);
                k = j;
            }
        }
    }

    public Heap() {
        this(INIT_SIZE);
    }

    public Heap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.data = (Item[]) new Comparable[capacity];
        this.indexes = new int[capacity];
    }


    // 数组扩容
    // 索引扩容
    private void expandDataSize() {
        // 数组扩容
        int newCapacity = (capacity + 1) * 2;
        Item[] newData = (Item[]) new Comparable[newCapacity];
        int[] newIndexes = new int[newCapacity];
        System.arraycopy(data, 0, newData, 0, capacity);
        System.arraycopy(indexes, 0, newIndexes, 0, capacity);
        capacity = newCapacity;
        data = newData;
        indexes = newIndexes;
    }

    private void swapIndex(int x, int y) {
        int t = indexes[x];
        indexes[x] = indexes[y];
        indexes[y] = t;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public Item next() {
                return extractMax();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Objects.requireNonNull(action);
        final Item[] items = data;
        for (int i = 0; i < count; i++) {
            action.accept(items[i]);
        }
    }

    @Override
    public Spliterator<Item> spliterator() {
        return null;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);

        for (Integer t : heap) {
            System.out.println(t);
        }

        System.out.println(heap.size());
    }
}
