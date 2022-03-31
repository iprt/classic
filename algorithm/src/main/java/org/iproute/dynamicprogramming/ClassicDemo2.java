package org.iproute.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @create 2018-5-25
 * 背包问题动态规划
 */
public class ClassicDemo2 {

    static String str = "abcabcbb";

    public static void main(String[] args) {

        String[] strs = str.split("");

        System.out.println(strs.length);


    }

    private static int getMaxLength(String s) {
        String[] ss = s.split("");
        Set noRepeat = new HashSet() {
            {
                add(ss[0]);
            }
        };
        int max = noRepeat.size();
        int start = 0, end = 1;
        for (int i = 1; i < ss.length; i++) {
            noRepeat.add(ss[i]);
            if (noRepeat.size() > max) {
                max = noRepeat.size();
                end = i;
            } else {
                start += 1;
            }
        }
        return 0;
    }
}