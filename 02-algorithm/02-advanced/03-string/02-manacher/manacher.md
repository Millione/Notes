# 马拉车

> 以O(n)时间复杂度解决回文子串问题

## 模板

```java
public String manacher(String s) {
    int n = s.length();
    StringBuilder sb = new StringBuilder();
    sb.append("$");
    sb.append("#");
    for (int i = 0; i < n; i++) {
        sb.append(s.substring(i, i + 1));
        sb.append("#");
    }
    sb.append("0");
    int mx = 0, id = 0;
    int resLen = 0, resCenter = 0; 
    int[] p = new int[sb.length()];
    for (int i = 1; i < sb.length(); i++) {
        p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
        while (i + p[i] < sb.length() && i - p[i] >= 0) {
            if (sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
                p[i]++;
            } else {
                break;
            }
        }
        if (mx < i + p[i]) {
            mx = i + p[i];
            id = i;
        }
        if (resLen < p[i]) {
            resLen = p[i];
            resCenter = i;
        }
    }
    return s.substring((resCenter - resLen) / 2, (resCenter + resLen) / 2 - 1);
}
```

## 题目

### 5. longest-palindromic-substring

------

**Description:**

Given a string **s**, find the longest palindromic substring in **s**. You may assume that the maximum length of **s** is 1000.

**Example 1:**

```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

**Example 2:**

```
Input: "cbbd"
Output: "bb"
```

[Discussion](https://leetcode.com/problems/longest-palindromic-substring/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/longest-palindromic-substring/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        sb.append("#");
        for (int i = 0; i < n; i++) {
            sb.append(s.substring(i, i + 1));
            sb.append("#");
        }
        sb.append("0");
        int mx = 0, id = 0;
        int resLen = 0, resCenter = 0;
        int[] p = new int[sb.length()];
        for (int i = 1; i < sb.length(); i++) {
            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            while (i + p[i] < sb.length() && i - p[i] >= 0) {
                if (sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
                    p[i]++;
                } else {
                    break;
                }
            }
            
            if (mx < i + p[i]) {
                mx = i + p[i];
                id = i;
            }
            if (resLen < p[i]) {
                resLen = p[i];
                resCenter = i;
            }
        }
        return s.substring((resCenter - resLen) / 2, (resCenter + resLen) / 2 - 1);
    }
}
// @lc code=end
```

## 参考

[原理](https://segmentfault.com/a/1190000008484167)