import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, res = 0;
        while (r < s.length()) {
            char rc = s.charAt(r);
            map.put(rc, map.getOrDefault(rc, 0) + 1);
            r++;
            while (map.get(rc) > 1) {
                char lc = s.charAt(l);
                map.put(lc, map.get(lc) - 1);
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
// @lc code=end

