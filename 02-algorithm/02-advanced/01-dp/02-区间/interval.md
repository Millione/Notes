# 区间DP

区间DP，顾名思义，就是需要在一个区间上去进行状态转移，先在小区间进行DP得到最优解，然后再利用小区间的最优解合并求大区间的最优解。

区间 DP 的特点：

**合并** ：即将两个或多个部分进行整合，当然也可以反过来；

**特征** ：能将问题分解为能两两合并的形式；

**求解** ：对整个问题设最优值，枚举合并点，将问题分解为左右两个部分，最后合并两个部分的最优值得到原问题的最优值。

## 模板

状态$dp[i][j]$表示将下标位置$i$到$j$的所有元素合并能获得的价值的最大值，那么 ，$f(i,j)=max\{f(i,k)+f(k+1,j)+cost\}$为将这两组元素合并起来的代价。

```java
int[][] dp = new int[n][n];

for (int i = 0; i <= n; i++) {
    // 初始化dp状态
}
// 枚举区间长度
for (int len = 2; len <= n; len++) {
    // 枚举区间起点
    for (int i = 0; i + len <= n; i++) {
        // 区间终点
        int j = i + len - 1;
        // 枚举分割点
        for (int k = i + 1; k < j; k++) {
            // dp状态转移
        }
    }
}
```

## 题目

### 516. longest-palindromic-subsequence

------

**Description:**

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

**Example 1:**
Input:

```
"bbbab"
```

Output:

```
4
```

One possible longest palindromic subsequence is "bbbb".

**Example 2:**
Input:

```
"cbbd"
```

Output:

```
2
```

One possible longest palindromic subsequence is "bb".

**Code:**

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```

### 1039. minimum-score-triangulation-of-polygon

------

**Description:**

Given `N`, consider a convex `N`-sided polygon with vertices labelled `A[0], A[i], ..., A[N-1]` in clockwise order.

Suppose you triangulate the polygon into `N-2` triangles. For each triangle, the value of that triangle is the **product** of the labels of the vertices, and the *total score* of the triangulation is the sum of these values over all `N-2` triangles in the triangulation.

Return the smallest possible total score that you can achieve with some triangulation of the polygon.

**Example 1:**

```
Input: [1,2,3]
Output: 6
Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
```

**Example 2:**

![img](https://assets.leetcode.com/uploads/2019/05/01/minimum-score-triangulation-of-polygon-1.png)

```
Input: [3,7,4,5]
Output: 144
Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
```

**Example 3:**

```
Input: [1,3,1,4,1,5]
Output: 13
Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
```

**Note:**

1. `3 <= A.length <= 50`
2. `1 <= A[i] <= 100`

**Code:**

```java
class Solution {
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        // 区间 dp : 长度为 len 的区间 [l,r], 取点 k 划分区间求极值 
        for (int len = 3; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                for (int k = l + 1; k < r; k++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k][r] + A[l] * A[k] * A[r]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```

### 312. burst-balloons

------

**Description:**

Given `n` balloons, indexed from `0` to `n-1`. Each balloon is painted with a number on it represented by array `nums`. You are asked to burst all the balloons. If the you burst balloon `i` you will get `nums[left] * nums[i] * nums[right]` coins. Here `left` and `right` are adjacent indices of `i`. After the burst, the `left` and `right` then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

**Note:**

- You may imagine `nums[-1] = nums[n] = 1`. They are not real therefore you can not burst them.
- 0 ≤ `n` ≤ 500, 0 ≤ `nums[i]` ≤ 100

**Example:**

```
Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
```

**Code:**

```java
class Solution {
    public int maxCoins(int[] A) {
        int[] nums = new int[A.length + 2];
        int n = 1;
        for (int x : A) {
            if (x > 0) {
                nums[n++] = x;
            }
        }
        nums[0] = nums[n++] = 1;
        // 区间 dp : 长度为 len 的区间 [l,r], 取点 k 划分区间求极值 
        int[][] dp = new int[n][n];

        for (int len = 3; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                for (int k = l + 1; k < r; k++) {
                    dp[l][r] = Math.max(dp[l][r], dp[l][k] + dp[k][r] + nums[l] * nums[k] * nums[r]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```

### 664. strange-printer

------

**Description:**

There is a strange printer with the following two special requirements:

1. The printer can only print a sequence of the same character each time.
2. At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.

Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

**Example 1:**

```
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
```

**Example 2:**

```
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
```

**Hint**: Length of the given string will not exceed 100.

**Code:**

```java
class Solution {
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;
            }
        }
        for (int len = 3; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = r - l + 1;
                for (int k = l; k < r; k++) {
                    if (s.charAt(k) == s.charAt(r)) {
                        dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k + 1][r] - 1);
                    } else {
                        dp[l][r] = Math.min(dp[l][r], dp[l][k] + dp[k + 1][r]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
```

### 87. scramble-string

------

**Description:**

Given a string *s1*, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of *s1* = `"great"`:

```
    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
```

To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node `"gr"` and swap its two children, it produces a scrambled string `"rgeat"`.

```
    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
```

We say that `"rgeat"` is a scrambled string of `"great"`.

Similarly, if we continue to swap the children of nodes `"eat"` and `"at"`, it produces a scrambled string `"rgtae"`.

```
    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
```

We say that `"rgtae"` is a scrambled string of `"great"`.

Given two strings *s1* and *s2* of the same length, determine if *s2* is a scrambled string of *s1*.

**Example 1:**

```
Input: s1 = "great", s2 = "rgeat"
Output: true
```

**Example 2:**

```
Input: s1 = "abcde", s2 = "caebd"
Output: false
```

**Code:**

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++) {
                    for (int k = 1; k < len; k++) {
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return dp[0][0][n];
    }    
}
```

