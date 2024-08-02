package org.iproute.sort.helper;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by winterfell on 2017/9/23.
 */
public class SortTestHelper {

    private SortTestHelper() {
    }

    // 生成有n个元素的随机数组 每个数组的随机范围为[rangeL,rangeR]
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }

    // 生成近乎有序的数组
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;

        for (int i = 0; i < swapTimes; i++) {
            int posx = (int) Math.random() * n;
            int posy = (int) Math.random() * n;
            swap(arr, posx, posy);
        }
        return arr;
    }

    public static Integer[] copy(Integer[] arr){
        int n = arr.length;
        Integer[] arr_ = new Integer[n];

        for(int i=0;i<n;i++){
            arr_[i] = arr[i];
        }
        return arr_;
    }


    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    // 打印arr数组所有的内容
    public static void printArray(Object[] arr[]) {

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "  ");
        }

        return;
    }

    // 判断数组是否有序
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void testSort(Class sortClass, Comparable[] arr) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();


        // 通过Java的反射机制，通过排序的类名，运行排序的函数
        try {
            // 通过sortClassName获得排序函数的Class对象
//            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得方法
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});
            // 排序参数只有一个，是可比数组arr
            Object[] params = new Object[]{arr};
            long startTime = System.nanoTime();
            sortMethod.invoke(null, params);
            long endTime = System.nanoTime();
            double ms = ((double) (endTime - startTime)) / 1000000.0;
            System.out.println(sortClass.getSimpleName() + " : " + ms + "ms");
            if(arr.length <= 15){
                System.out.println(Arrays.toString(arr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSort(String sortClassName, Comparable[] arr) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();


        // 通过Java的反射机制，通过排序的类名，运行排序的函数
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得方法
            Method sortMethod = sortClass.getMethod("sort", new Class[]{Comparable[].class});
            // 排序参数只有一个，是可比数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();

            sortMethod.invoke(null, params);

            long endTime = System.currentTimeMillis();

            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Integer[] arr = SortTestHelper.generateRandomArray(20, 0, 100);
        System.out.println(Arrays.toString(arr));
    }
}

// for coding.net
// rm -rf ~/code/source/ADS/src/
// cp -r ~/code/source/Algorithm/src/ ~/code/source/ADS/src/