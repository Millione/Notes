# 滑动窗口

> 滑动窗口算法可以用以解决数组/字符串的子元素问题，它可以将嵌套的循环问题，转换为单循环问题，降低时间复杂度。

|                滑动窗口相应题目索引(持续更新)                |
| :----------------------------------------------------------: |
| [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters) |
| [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring) |
| [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum) |
| [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement) |
| [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string) |

## 思想

滑动窗口算法的思路是这样：

1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。

2、我们先不断地增加 right 指针扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。

3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。

4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。

这个思路其实也不难，**第 2 步相当于在寻找一个「可行解」，然后第 3 步在优化这个「可行解」，最终找到最优解。**左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动。

```java
int left = 0, right = 0;

while (right < s.size()) {
    window.add(s[right]);
    right++;

    while (valid) {
        window.remove(s[left]);
        left++;
    }
}
```

## 题目

### 76. minimum-window-substring

------

**Description:**

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

**Example:**

```
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
```

**Note:**

- If there is no such window in S that covers all characters in T, return the empty string `""`.
- If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

[Discussion](https://leetcode.com/problems/minimum-window-substring/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/minimum-window-substring/solution/)

**Code:**

```java
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
        // 记录最短子串的开始位置和长度
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
                // 更新
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
```

### 438. find-all-anagrams-in-a-string

------

**Description:**

Given a string **s** and a **non-empty** string **p**, find all the start indices of **p**'s anagrams in **s**.

Strings consists of lowercase English letters only and the length of both strings **s** and **p** will not be larger than 20,100.

The order of output does not matter.

**Example 1:**

```
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
```

**Example 2:**

```
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
```

[Discussion](https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/find-all-anagrams-in-a-string/solution/)

**Code:**

```java
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
```

### 3. longest-substring-without-repeating-characters

------

**Description:**

Given a string, find the length of the **longest substring** without repeating characters.

**Example 1:**

```
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
```

**Example 2:**

```
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3:**

```
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

[Discussion](https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/)

**Code:**

```java
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
```

### 424. longest-repeating-character-replacement

------

**Description:**

Given a string `s` that consists of only uppercase English letters, you can perform at most `k` operations on that string.

In one operation, you can choose **any** character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

**Note:**
Both the string's length and *k* will not exceed 104.

**Example 1:**

```
Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
```

**Example 2:**

```
Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
```

[Discussion](https://leetcode.com/problems/longest-repeating-character-replacement/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/longest-repeating-character-replacement/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=424 lang=java
 *
 * [424] Longest Repeating Character Replacement
 */

// @lc code=start
class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int l = 0, maxCount = 0, maxLen = 0;
        for (int r = 0; r < s.length(); r++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(r) - 'A']);
            // 窗口移动
            while (r - l + 1 - maxCount > k) {
                count[s.charAt(l) - 'A']--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}
// @lc code=end
```

