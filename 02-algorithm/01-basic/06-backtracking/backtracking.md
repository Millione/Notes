# 回溯

**解决一个回溯问题，实际上就是一个决策树的遍历过程**。你只需要思考 3 个问题：

1、路径：也就是已经做出的选择。

2、选择列表：也就是你当前可以做的选择。

3、结束条件：也就是到达决策树底层，无法再做选择的条件。

代码方面，回溯算法的框架：

```
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

**其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」**，特别简单。

|                回溯算法对应题目索引(持续更新)                |
| :----------------------------------------------------------: |
| [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses) |
| [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver) |
| [39. Combination Sum](https://leetcode.com/problems/combination-sum) |
| [40. Combination Sum II](https://leetcode.com/problems/combination-sum-ii) |
| [46. Permutations](https://leetcode.com/problems/permutations) |
| [47. Permutations II](https://leetcode.com/problems/permutations-ii) |
|    [51. N-Queens](https://leetcode.com/problems/n-queens)    |
| [77. Combinations](https://leetcode.com/problems/combinations) |
|     [78. Subsets](https://leetcode.com/problems/subsets)     |
| [79. Word Search](https://leetcode.com/problems/word-search) |
|  [90. Subsets II](https://leetcode.com/problems/subsets-ii)  |
| [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii) |

## 类型

### 1. 子集

问题很简单，输入一个**可能包含重复数字**的数组，要求算法输出这些数字的所有子集。

#### 78. subsets

------

**Description:**

Given a set of **distinct** integers, *nums*, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

**Example:**

```
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

[Discussion](https://leetcode.com/problems/subsets/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/subsets/solution/)

**Code:**

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        for (int i = 0; i <= nums.length; ++i) {
            dfs(nums, i, 0, new ArrayDeque<>());
        }
        return res;
    }
    private void dfs(int[] nums, int len, int index, Deque<Integer> stack) {
        if (stack.size() == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < nums.length; ++i) {
            stack.push(nums[i]);
            dfs(nums, len, i + 1, stack);
            stack.pop();
        }
    }
}
// @lc code=end
```

#### 90. subsets-ii

------

**Description:**

Given a collection of integers that might contain duplicates, **nums**, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

**Example:**

```
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

[Discussion](https://leetcode.com/problems/subsets-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/subsets-ii/solution/)

**Code:**

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; ++i) {
            dfs(nums, i, 0, new ArrayDeque<>());
        }
        return res;
    }
    private void dfs(int[] nums, int len, int index, Deque<Integer> stack) {
        if (stack.size() == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < nums.length; ++i) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            stack.push(nums[i]);
            dfs(nums, len, i + 1, stack);
            stack.pop();
        }
    }
}
// @lc code=end
```

### 2. 排列

输入一个**可能包含重复数字**的数组 `nums`，返回这些数字的全部排列。

#### 46. permutations

------

**Description:**

Given a collection of **distinct** integers, return all possible permutations.

**Example:**

```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

[Discussion](https://leetcode.com/problems/permutations/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/permutations/solution/)

**Code:**

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    private boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        findPermute(nums, new ArrayDeque<>());
        return res;
    }

    private void findPermute(int[] nums, Deque<Integer> stack){
        if(stack.size() == nums.length){
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                stack.push(nums[i]);
                findPermute(nums, stack);
                stack.pop();
                visited[i] = false;
            }
        }
    }
}
// @lc code=end
```

#### 90. permutations-ii

------

**Description:**

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

**Example:**

```
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

[Discussion](https://leetcode.com/problems/permutations-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/permutations-ii/solution/)

**Code:**

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */

// @lc code=start
class Solution {
    
    private List<List<Integer>> res;
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        findPermuteUnique(nums, new ArrayDeque<>());
        return res;
    }

    private void findPermuteUnique(int[] nums, Deque<Integer> stack){
        if(stack.size() == nums.length){
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            stack.push(nums[i]);
            findPermuteUnique(nums, stack);
            stack.pop();
            visited[i] = false;
        }
    }
}
// @lc code=end
```

### 3. 组合

输入两个数字 `n, k`，算法输出 `[1..n]` 中 k 个数字的所有组合。

#### 77. combinations

------

**Description:**

Given two integers *n* and *k*, return all possible combinations of *k* numbers out of 1 ... *n*.

**Example:**

```
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

[Discussion](https://leetcode.com/problems/combinations/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/combinations/solution/)

**Code:**

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 */

// @lc code=start
class Solution {

    private List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        if (n < k || n < 1 || k < 0) {
            return res;
        }
        dfs(n, k, 1, new ArrayDeque<>());
        return res;
    }

    private void dfs(int n, int k, int index, Deque<Integer> stack) {
        if (k == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i <= n; i++) {
            stack.push(i);
            dfs(n, k - 1, i + 1, stack);
            stack.pop();
        }
    }
}
// @lc code=end
```

### 4. 二维

使用一个数组来表示方向位移的技巧。

#### 37. sudoku-solver

------

**Description:**

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy **all of the following rules**:

1. Each of the digits `1-9` must occur exactly once in each row.
2. Each of the digits `1-9` must occur exactly once in each column.
3. Each of the the digits `1-9` must occur exactly once in each of the 9 `3x3` sub-boxes of the grid.

Empty cells are indicated by the character `'.'`.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)
A sudoku puzzle...

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)
...and its solution numbers marked in red.

**Note:**

- The given board contain only digits `1-9` and the character `'.'`.
- You may assume that the given Sudoku puzzle will have a single unique solution.
- The given board size is always `9x9`.

[Discussion](https://leetcode.com/problems/sudoku-solver/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/sudoku-solver/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 */

// @lc code=start
class Solution {
    private boolean[][] rows, cols, boxes;
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        rows = new boolean[9][10];
        cols = new boolean[9][10];
        boxes = new boolean[9][10];
        // 初始化
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[j / 3 * 3 + i / 3][num] = true;
            }
        }
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int i, int j) {
        if (i == 9) {
            return true;
        }
        int jj = (j + 1) % 9;
        int ii = jj != 0 ? i : i + 1;
        if (board[i][j] != '.') {
            return dfs(board, ii, jj);
        }
        for (int num = 1; num < 10; ++num) {
            // 剪枝
            if (rows[i][num] || cols[j][num] || boxes[j / 3 * 3 + i / 3][num]) {
                continue;
            }
            // 遍历
            board[i][j] = (char)(num + '0');
            rows[i][num] = cols[j][num] = boxes[j / 3 * 3 + i / 3][num] = true;
            if (dfs(board, ii, jj)) {
                return true;
            }
            // 回溯
            rows[i][num] = cols[j][num] = boxes[j / 3 * 3 + i / 3][num] = false;
            board[i][j] = '.';
        }
        return false;
    }
}
// @lc code=end
```

#### 51. n-queens

------

**Description:**

The *n*-queens puzzle is the problem of placing *n* queens on an *n*×*n* chessboard such that no two queens attack each other.

![img](https://assets.leetcode.com/uploads/2018/10/12/8-queens.png)

Given an integer *n*, return all distinct solutions to the *n*-queens puzzle.

Each solution contains a distinct board configuration of the *n*-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space respectively.

**Example:**

```
Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
```

[Discussion](https://leetcode.com/problems/n-queens/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/n-queens/solution/)

**Code:**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */

// @lc code=start
class Solution {
    private int N;
    private boolean[] cols, d1, d2;
    private List<List<String>> res;
    private char[][] board;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        cols = new boolean[n];
        d1 = new boolean[n * 2 - 1];
        d2 = new boolean[n * 2 - 1];
        N = n;
        board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        dfs(0);
        return res;
    }

    private void dfs(int r) {
        if (r == N) {
            res.add(convert(board));
            return;
        }
        for (int c = 0; c < N; c++) {
            int id1 = c + r;
            int id2 = c - r + N - 1;
            if (cols[c] || d1[id1] || d2[id2]) {
                continue;
            }
            cols[c] = d1[id1] = d2[id2] = true;
            board[r][c] = 'Q';

            dfs(r + 1);

            cols[c] = d1[id1] = d2[id2] = false;
            board[r][c] = '.';
        }
    }

    private List<String> convert(char[][] board) {
        List<String> ans = new ArrayList<>();
        for (char[] row : board) {
            ans.add(new String(row));
        }
        return ans;
    }
}
// @lc code=end
```

#### 79. word-search

------

**Description:**

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

**Example:**

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

[Discussion](https://leetcode.com/problems/word-search/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/word-search/solution/)

**Code:**

```java
{% raw %}
/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 */

// @lc code=start
class Solution {
    private int[][] cor = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int n, m;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        if (word == null || word.length() == 0) {
             return true;
        }
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j){
        if(index == word.length() - 1){
            return word.charAt(index) == board[i][j];
        }
        if(word.charAt(index) == board[i][j]){
            visited[i][j] = true;
            for(int k = 0; k < 4; k++){
                int ii = cor[k][0] + i;
                int jj = cor[k][1] + j;
                if(inArea(ii, jj) && (!visited[ii][jj]) && dfs(board, word, index + 1, ii, jj)){
                    return true;
                }
            }
            visited[i][j] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < n && y >=0 && y < m;
    }
}
// @lc code=end
{% endraw %}
```

## 题目

#### 22. generate-parenthesis

------

**Description:**

Given *n* pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given *n* = 3, a solution set is:

```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

[Discussion](https://leetcode.com/problems/generate-parentheses/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/generate-parentheses/solution/)

**Code:**

```java
import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

// @lc code=start
class Solution {
    private List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(n, "", 0, 0);
        return res;
    }
    private void dfs(int n, String str, int l, int r) {
        if (str.length() == 2 * n) {
            res.add(str);
            return;
        }
        if (l < n) {
            dfs(n, str + "(", l + 1, r);
        }
        if (r < l) {
            dfs(n, str + ")", l, r + 1);
        }
    }
}
// @lc code=end
```