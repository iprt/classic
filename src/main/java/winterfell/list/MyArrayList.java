package winterfell.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zhuzhenjie
 **/
public class MyArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 初始化 capacity
     */
    private int capacity;

    /**
     * 当前容量
     */
    private int currentCapacity;

    private int size;

    private Object[] data;

    public MyArrayList(int capacity) {
        this.currentCapacity = capacity <= 0 ? DEFAULT_CAPACITY : capacity;
        this.capacity = this.currentCapacity;
        this.size = 0;
        this.data = new Object[currentCapacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
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
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(data, 0, arr, 0, size);
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) toArray();
        }
        System.arraycopy(data, 0, a, 0, size);
        // toArray的奇怪操作 确定长度
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        // 尝试扩容
        this.expansion();
        data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return remove(i) != null;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] cs = c.toArray();
        int newSize = this.size + c.size();
        if (newSize > currentCapacity) {
            int newCapacity = this.currentCapacity + size;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(this.data, 0, newData, 0, size);
            this.data = newData;
        }
        System.arraycopy(cs, 0, this.data, size, cs.length);
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        Object[] cs = c.toArray();
        int newSize = this.size + c.size();
        Object[] old = this.toArray(new Object[size]);
        if (newSize > currentCapacity) {
            int newCapacity = this.currentCapacity + size;
            this.currentCapacity = newCapacity;
            Object[] newData = new Object[newCapacity];
            this.data = newData;
        }
        System.arraycopy(cs, 0, this.data, index, this.data.length);
        if (index < size - 1) {
            System.arraycopy(old, index + 1, this.data, newSize - index, old.length - index - 1);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            this.remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] retain = new Object[c.size()];


        return false;
    }

    @Override
    public void clear() {
        this.data = new Object[capacity];
        this.size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E old = (E) data[index];
        data[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        Object[] before = new Object[index];
        Object[] after = new Object[size - index - 1];
        if (size + 1 > currentCapacity) {


        }

    }

    @Override
    public E remove(int index) {
        E removed = (E) data[index];
        System.arraycopy(data, index + 1, data, index, size - index + 1);
        data[size--] = 0;
        this.shrinkage();
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    /**
     * [fromIndex,toIndex)
     *
     * @param fromIndex fromIndex
     * @param toIndex   toIndex
     * @return List<E>
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (toIndex > size || fromIndex < 0 || fromIndex >= toIndex) {
            throw new RuntimeException("index error");
        }
        List<E> subList = new MyArrayList<E>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add((E) data[i]);
        }
        return subList;
    }

    /**
     * 数组扩容
     */
    private void expansion() {
        if (size >= currentCapacity) {
            int newCapacity = this.currentCapacity + this.currentCapacity >> 1;
            Object[] data = new Object[newCapacity];
            System.arraycopy(this.data, 0, data, 0, size);
            this.data = data;
            this.currentCapacity = newCapacity;
        }
    }

    /**
     * 数组缩容
     */
    private void shrinkage() {
        if (size <= currentCapacity >> 1 && size >= DEFAULT_CAPACITY) {
            int capacity = this.capacity >> 1;
            Object[] data = new Object[capacity];
            System.arraycopy(this.data, 0, data, 0, size);
            this.data = data;
            this.capacity = capacity;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index error");
        }
    }


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("java");

        String[] arr = new String[0];

        String[] listarr = list.toArray(arr);

        System.out.println(listarr.equals(arr));

        System.out.println(2 << 1);

        System.out.println();

    }
}
