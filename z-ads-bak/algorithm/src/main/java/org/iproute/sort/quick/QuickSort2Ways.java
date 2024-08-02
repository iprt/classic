package org.iproute.sort.quick;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 * 双路快速排序
 */
public class QuickSort2Ways {

    private QuickSort2Ways() {

    }

    // 对arr[l....r] 的部分进行parttion操作
    // 对于返回值p arr[l...p-1] , arr[p+1...r]>arr[p]
    private static int parttion(Comparable[] arr, int l, int r) {

        Comparable v = arr[l];

        // arr[l+1...i)<=v arr(j...r]>=v
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i].compareTo(v) < 0) {
                i++;
            }
            while (j >= l + 1 && arr[j].compareTo(v) > 0) {
                // 最外面的while true结束后
                // 停留在了从后向前看最前面的小于v的那个值
                // 所以j就是对应的正确的位置
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;

    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 递归函数体
    private static void quickSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            // 当规模小于一定程度的时候 采用插入排序
            return;
        }
        int p = parttion(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);

    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        // 闭区间
        quickSort(arr, 0, n - 1);

    }

    public static void main(String[] args) {
        Integer[] arr = {4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
