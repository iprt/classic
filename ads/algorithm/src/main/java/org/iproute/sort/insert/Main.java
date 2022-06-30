package org.iproute.sort.insert;

import org.iproute.sort.helper.SortTestHelper;
import org.iproute.sort.select.SelectionSort;

/**
 * Created by winterfell on 2017/9/24.
 * 测试插入排序和选择排序的时间
 * 没有优化之前是频繁的交换
 */
public class Main {

    public static void main(String[] args) {
        // 选择排序和插入排序的时间比较
        int n = 10000;
        Integer[] test = SortTestHelper.generateNearlyOrderedArray(n, 100);
        Integer[] test2 = SortTestHelper.copy(test);
        Integer[] test3 = SortTestHelper.copy(test);

        SortTestHelper.testSort(InsertionSort.class, test);
        SortTestHelper.testSort(InsertionSort2.class, test2);
        SortTestHelper.testSort(SelectionSort.class, test3);


        // InsertionSort : 225ms
        // SelectionSortDemo : 73ms

//        InsertionSort : 257ms
//        SelectionSort : 77ms


    }


}
