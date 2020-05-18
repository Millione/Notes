import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */

// @lc code=start
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, head = 0, len = Integer.MAX_VALUE;
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = map.size();
        while (r < s.length()) {
            char rc = s.charAt(r);
            if (map.containsKey(rc)) {
                map.put(rc, map.get(rc) - 1);
                if (map.get(rc) == 0) {
                    count--;
                }
            }
            r++;
            while(count == 0){
                char lc = s.charAt(l);
                if (map.containsKey(lc)) {
                    map.put(lc, map.get(lc) + 1);
                    if (map.get(lc) > 0) {
                        count++;
                    }
                }
                if (r - l < len) {
                    len = r - l;
                    head = l;
                }
                l++;
            }
        }
        // java begin end
        return len == Integer.MAX_VALUE ? "" : s.substring(head, head + len);
    }
}
// @lc code=end

