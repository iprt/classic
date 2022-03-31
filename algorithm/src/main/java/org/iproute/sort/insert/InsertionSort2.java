package org.iproute.sort.insert;

import org.iproute.sort.helper.SortTestHelper;

/**
 * Created by winterfell on 2017/9/24.
 * 插入排序的优化
 * 后面的数字不断往前插入 前面已经排好顺序了
 * 插入排序的优点在于提前终止了内存的循环
 */
public class InsertionSort2 {

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 寻找元素arr[i]合适的插入位置
//            for (int j = i; j > 0; j--) {
//                if(arr[j].compareTo(arr[j-1])<0 ){
//                    swap(arr,j,j-1);
//                }else{
//                    break;
//                }
//            }
            // 寻找元素arr[i] 适合插入的位置 把当前的值复制出来
            // j保存元素e应该插入的位置
            Comparable e = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                // j = i  是个重要的前提
                // 一定要记住 插入排序是从后面选值和前面比较的
                // e , arr[i] , arr[j] 三者一开始是一样的
                // 所以一开始比较的是 j-1 后面和前面比是比小 所以前面和后面比是比大
                // 与 最后一个值比较 保存 通过赋值省略了交换过程
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }


    private InsertionSort2() {
    }

    public static void main(String[] args) {

        SortTestHelper.testSort(InsertionSort2.class,SortTestHelper.generateRandomArray(10,10,20));
    }


}
