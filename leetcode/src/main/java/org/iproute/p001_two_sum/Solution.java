package org.iproute.p001_two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution
 *
 * @author tech@intellij.io
 * @since 2022/2/28
 */
public class Solution {

    /**
     * 两数之和
     *
     * @param nums   the nums
     * @param target the target
     * @return the int [ ]
     */
    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];

        // 用来记录 (target - 某个数字) 和 位置 的map
        Map<Integer, Integer> hash = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                index[0] = i;
                index[1] = hash.get(nums[i]);
            }
            hash.put(target - nums[i], i);
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new Solution().twoSum(
                                new int[]{2, 7, 11, 15},
                                9
                        ))
        );
    }
}
