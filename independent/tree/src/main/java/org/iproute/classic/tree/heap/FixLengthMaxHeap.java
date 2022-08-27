package org.iproute.classic.tree.heap;

import org.iproute.classic.tree.Heap;

/**
 * 最小堆的实现
 *
 * @author winterfell
 * @since 2022/6/30
 */
public class FixLengthMaxHeap<T extends Comparable<T>> implements Heap<T> {

    private boolean debug;
    private int capacity;
    private int size;

    /**
     * The Values.
     */
    private T[] values;

    @SuppressWarnings("all")
    public FixLengthMaxHeap(int capacity) {
        // 第0位 不存放内容
        values = (T[]) new Comparable[capacity + 1];
        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(T t) {
        int position = size + 1;

        if (position > capacity) {
            // throw new IndexOutOfBoundsException("Heap");
            return;
        }

        this.values[position] = t;
        this.size++;
        shiftUp(position);
    }

    /**
     * 新增元素 shift up 操作
     *
     * @param position the position
     */
    private void shiftUp(int position) {
        if (position == 1) {
            // 起始的位置
            return;
        }
        int parentPosition = position / 2;
        if (values[parentPosition].compareTo(values[position]) < 0) {
            debugPrintln(String.format("shiftUp : current position = %d;parent's position = %d", position, parentPosition));
            swap(parentPosition, position);

            position = parentPosition;
            shiftUp(position);
        }
    }

    @Override
    public T extractMax() {
        if (isEmpty()) {
            debugPrintln("extractMax error: size is zero");
            return null;
        }

        T max = max();

        if (size == 1) {
            // 这种情况不需要shift down
            this.size--;
        } else {
            swap(1, this.size);
            this.size--;
            shiftDown(1);
        }
        return max;
    }


    private void shiftDown(int position) {
        int left = 2 * position;
        int right = 2 * position + 1;

        if (left <= size || right <= size) {
            // 特殊的情况 left = size ; right = size + 1
            if (right == size + 1) {
                if (values[left].compareTo(values[position]) > 0) {
                    swap(position, left);
                }
                // 最后的特殊判断，直接return
                return;
            }

            int choose = values[left].compareTo(values[right]) > 0 ? left : right;
            if (values[choose].compareTo(values[position]) > 0) {
                swap(position, choose);
                shiftDown(choose);
            }
        }
    }

    @Override
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return values[1];
    }


    private void swap(int x, int y) {
        T tmp = values[x];
        values[x] = values[y];
        values[y] = tmp;
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public boolean getDebug() {
        return this.debug;
    }
}
