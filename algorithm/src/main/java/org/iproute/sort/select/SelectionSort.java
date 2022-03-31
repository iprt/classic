package org.iproute.sort.select;

import org.iproute.sort.helper.SortTestHelper;

import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/24.
 * 使用模板编写算法
 */
public class SelectionSort {

    private SelectionSort() {

    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // 是用compareTo方法比较两个Comparable对象的大小
                // 寻找到最小值的位置 j
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Student[] d = new Student[4];
        d[0] = new Student("D", 90);
        d[1] = new Student("C", 100);
        d[2] = new Student("B", 95);
        d[3] = new Student("A", 95);

        SelectionSort.sort(d);
        System.out.println(Arrays.toString(d));



        // 排序算法的性能测试
        int n = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(n,0,10000);
        SortTestHelper.testSort("winterfell.sort.select.SelectionSort",arr);


    }


}
