# 动态规划

> 动态规划与分治方法相似，都是通过组合子问题的解来求解原问题。分治方法将问题划分为互不相交的子问题，递归的求解子问题，再讲他们组合起来，求出原问题的解。与之相反的，动态规划适用于子问题重叠的情况，即不同的子问题具有公共的子子问题。

|             基础动态规划相应题目索引（持续更新）             |
| :----------------------------------------------------------: |
| [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence) |
| [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence) |
| [72. Edit Distance](https://leetcode.com/problems/edit-distance) |
| [44. Wildcard Matching](https://leetcode.com/problems/wildcard-matching) |

## 思想

**动态规划中的重要性质：**

1. **具有最优子结构**

   最优子结构想表达的是，如果一个问题的最优解包含其子问题的最优解，那么我们就称这个问题有最优子结构性质。也就是说，一个问题的最优解一定是由其各个子问题的最优解组合而成的。

   关于是否满足最优子结构的判断：

   1. 证明问题最优解的第一个组成部分是一个选择。因为这样的选择会产生多个待解的资额问题。
   2. 假定已经产生了一个最优的选择。
   3. 判断根据这个最优的选择会产生哪些子问题。这些子问题应该如何表示。
   4. 利用反证法。证明子问题如果不是最优解，那么可以将这个解从原问题中去掉，换上相对的最优解。从而产生了一个比假定更优的解，和假定是最优解矛盾。

2. **有重叠子问题**

   这个问题相对就容易理解很多。如果我们在递归求解的过程中，反复的求解了相同的子问题，那么就称这个问题有重叠子问题。

3. **状态无后效性**

   状态的无后效性指的是，当一个状态被确定之后，一定不会被在其之后确定的状态所影响。也就是说，每个状态的确定都不会影响到之前已经确定的状态。 

只有一个问题满足了这三个条件之后，我们才能考虑用动态规划算法去解决它。

**设计动态规划算法的一般步骤：**

设计一个动态规划算法整体上分为两个步骤：

1. **设计状态表达式**

   状态表达式的选择又取决于我们如何给这个问题划分求解的阶段，也就是为这个问题的每一个阶段，设计一个独一无二的表达式来表示他们，并且这个状态表达式的选择应该是满足无后效性的。

2. **设计状态转移方程**

   状态转移方程应该描述：

   1. 一个状态的解可以在哪些状态中选择
   2. 以何种条件在这些状态中选择出一个或多个，作为组成解状态的子状态
   3. 被选择出的一个或多个状态以何种方式组合起来，形成了当前状态的解

状态表达式就是对我们所说的问题的一般描述；状态转移方程就是我们对一个问题如何由其子问题组成所做出的决策的一般描述。

当这两个问题被解决了之后，一个动态规划算法的框架已经基本成型了。剩下的工作就是：

1. 确定边界条件
2. 计算时间和空间复杂度
3. 判断是否满足题目要求，如果不满足应该如何优化，或者放弃这个思路

## 题目

### 300. longest-increasing-subsequence

------

**Description:**

Given an unsorted array of integers, find the length of longest increasing subsequence.

**Example:**

```
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```

**Note:**

- There may be more than one LIS combination, it is only necessary for you to return the length.
- Your algorithm should run in O(*n2*) complexity.

**Follow up:** Could you improve it to O(*n* log *n*) time complexity?

**Code:**

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

### 1143. longest-common-subsequence

------

**Description:**

Given two strings `text1` and `text2`, return the length of their longest common subsequence.

A *subsequence* of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A *common subsequence* of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.

**Example 1:**

```
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
```

**Example 2:**

```
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
```

**Example 3:**

```
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
```

**Constraints:**

- `1 <= text1.length <= 1000`
- `1 <= text2.length <= 1000`
- The input strings consist of lowercase English characters only.

**Code:**

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // dp[i][j]: text1 的前 i 个字符与 text2 的前 j 个字符最长公共子串
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
```

### 72. edit-distance

------

**Description:**

Given two words *word1* and *word2*, find the minimum number of operations required to convert *word1* to *word2*.

You have the following 3 operations permitted on a word:

1. Insert a character
2. Delete a character
3. Replace a character

**Example 1:**

```
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```

**Example 2:**

```
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```

**Code:**

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
```

### 44. wildcard-matching

------

**Description:**

Given an input string (`s`) and a pattern (`p`), implement wildcard pattern matching with support for `'?'` and `'*'`.

```
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
```

The matching should cover the **entire** input string (not partial).

**Note:**

- `s` could be empty and contains only lowercase letters `a-z`.
- `p` could be empty and contains only lowercase letters `a-z`, and characters like `?` or `*`.

**Example 1:**

```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```
Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
```

**Example 3:**

```
Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
```

**Example 4:**

```
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
```

**Example 5:**

```
Input:
s = "acdcb"
p = "a*c?b"
Output: false
```

**Code:**

```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;
        for (int j = n - 1; j >= 0; j--) {
            if (p.charAt(j) != '*') {
                break;
            } else {
                dp[m][j]=true;
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[0][0];
    }
}
```

