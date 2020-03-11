package winterfell.sort;

import org.apache.commons.lang3.RandomUtils;

import java.util.stream.IntStream;

/**
 * @author zhuzhenjie
 **/
public class QuickSort {

    private QuickSort() {
    }

    public static <T extends Comparable> void sort(T[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static <T extends Comparable> void sort(T[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int partition = partition(arr, left, right);
        sort(arr, left, partition - 1);
        sort(arr, partition + 1, right);
    }

    private static <T extends Comparable> int partition(T[] arr, int left, int right) {
//        T v = arr[left];
        /*
         *  v 随机化
         */
        int randomCursor = RandomUtils.nextInt(left, right + 1);
        swap(arr, left, randomCursor);
        T v = arr[left];
        int i = left + 1, j = right;
        while (true) {
            while (i <= right && arr[i].compareTo(v) < 0) {
                i++;
            }
            while (j > left + 1 && arr[j].compareTo(v) > 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, left, j);
        return j;
    }

    private static <T extends Comparable> void swap(T[] arr, int a, int b) {
        T t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = {
                8, 6, 4, 2, 7, 5, 3, 1
        };
        sort(arr);
        IntStream.range(0, arr.length)
                .forEach(i -> System.out.println(arr[i]));
    }

}
