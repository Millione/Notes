import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=438 lang=java
 *
 * [438] Find All Anagrams in a String
 */

// @lc code=start
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return res;
        }
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needle = new HashMap<>();
        for (char c : p.toCharArray()) {
            needle.put(c, needle.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = 0, match = 0;
        while (r < s.length()) {
            char rc = s.charAt(r);
            if (needle.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                // equals
                if (window.get(rc).equals(needle.get(rc))) {
                    match++;
                }
            }
            r++;
            while (match == needle.size()) {
                if (r - l == p.length()) {
                    res.add(l);
                }
                char lc = s.charAt(l);
                if (needle.containsKey(lc)) {
                    window.replace(lc, window.get(lc) - 1);
                    if (window.get(lc) < needle.get(lc)) {
                        match--;
                    }
                }
                l++;
            }
        }
        return res;
    }
}
// @lc code=end

