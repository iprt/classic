package org.iproute.sort.select;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/23.
 * 选择排序
 */
public class SelectionSortDemo {
    // 算法不允许产生任何实例
    private SelectionSortDemo() {

    }

    public static void main(String[] args) {
        int[] arr = {8, 7, 1, 5, 6};
        SelectionSortDemo.sort(arr);
        System.out.println(Arrays.toString(arr));


    }


    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[i]) {
                    // 数值交换
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }
}
