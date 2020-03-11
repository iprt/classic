package winterfell.leetcode.demo001;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winterfell
 */
public class Solution {

    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int header = 0;
        for (int currentPosition = 0; currentPosition < s.length(); currentPosition++) {

            char currentChar = s.charAt(currentPosition);

            if (map.containsKey(currentChar)) {

                // 当出现了重复的字符串的时候 就重新计算位置
                header = Math.max(map.get(currentChar) + 1, header);

                if ((currentPosition - header + 1) > maxLength) {
                    maxLength = currentPosition - header + 1;
                }

            } else {

                if ((currentPosition - header + 1) > maxLength) {
                    maxLength = currentPosition - header + 1;
                }

            }


            // 不断更新位置
            map.put(currentChar, currentPosition);
        }
        return maxLength;
    }


    public static void main(String[] args) {

        String s = "abcabcdbb";

        System.out.println(new Solution().lengthOfLongestSubstring(s));

    }
}
