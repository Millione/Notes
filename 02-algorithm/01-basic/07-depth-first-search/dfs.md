# 深度优先搜索

> DFS（深度优先搜索） 常用来解决可达性的问题。

两个要点：

- 栈：用栈来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点。可以使用递归栈。
- 标记：和 BFS 一样同样需要对已经遍历过的节点进行标记。

|              深度优先搜索相应题目索引(持续更新)              |
| :----------------------------------------------------------: |
| [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions) |
| [200. Number of Islands](https://leetcode.com/problems/number-of-islands) |
| [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow) |
| [547. Friend Circles](https://leetcode.com/problems/friend-circles) |
| [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island) |

## 模板

```java
void dfs()	// 参数用来表示状态  
{  
    if(到达终点状态)  
    {  
        ...		// 根据题意添加  
        return;  
    }  
    if(越界或者是不合法状态)  
        return;  
    if(特殊状态)	// 剪枝
        return ;
    for(扩展方式)  
    {  
        if(扩展方式所达到状态合法)  
        {  
            修改操作;	// 根据题意来添加  
            标记；  
            dfs（）；  
            (还原标记)；  // 是否还原标记根据题意  
            //如果加上（还原标记）就是 回溯法  
        }  
    }  
}  
```

## 题目

#### 695. max-area-of-island

------

**Description:**

Given a non-empty 2D array `grid` of 0's and 1's, an **island** is a group of `1`'s (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

**Example 1:**

```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
```

Given the above grid, return `6`. Note the answer is not 11, because the island must be connected 4-directionally.

**Example 2:**

```
[[0,0,0,0,0,0,0,0]]
```

Given the above grid, return `0`.

**Note:** The length of each dimension in the given `grid` does not exceed 50.

[Discussion](https://leetcode.com/problems/max-area-of-island/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/max-area-of-island/solution/)

**Code:**

```java
{% raw %}
/*
 * @lc app=leetcode id=695 lang=java
 *
 * [695] Max Area of Island
 */

// @lc code=start
class Solution {
    private int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int m, n;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    ans = Math.max(ans, dfs(grid, i, j, 1));
                } 
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int area) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        for (int[] d : dir) {
            area += dfs(grid, i + d[0], j + d[1], 1);
        }
        return area;
    }
}
// @lc code=end
{% endraw %}
```

#### 200. number-of-island

------

**Description:**

Given a 2d grid map of `'1'`s (land) and `'0'`s (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

**Example 1:**

```
Input:
11110
11010
11000
00000

Output: 1
```

**Example 2:**

```
Input:
11000
11000
00100
00011

Output: 3
```

[Discussion](https://leetcode.com/problems/number-of-islands/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/number-of-islands/solution/)

**Code:**

```java
{% raw %}
/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {
    private int m, n;
    private int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : dir) {
            dfs(grid, i + d[0], j + d[1]);
        }
    }
}
// @lc code=end
{% endraw %}
```

#### 547. friend-circles

------

**Description:**

There are **N** students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a **direct** friend of B, and B is a **direct** friend of C, then A is an **indirect** friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a **N\*N** matrix **M** representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are **direct** friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

**Example 1:**

```
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. The 2nd student himself is in a friend circle. So return 2.
```

**Example 2:**

```
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
```



**Note:**

1. N is in range [1,200].
2. M[i][i] = 1 for all students.
3. If M[i][j] = 1, then M[j][i] = 1.

[Discussion](https://leetcode.com/problems/friend-circles/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/friend-circles/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=547 lang=java
 *
 * [547] Friend Circles
 */

// @lc code=start
class Solution {
    private int n;
    private boolean[] visited;
    
    public int findCircleNum(int[][] M) {
        n = M.length;
        visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                res++;
                dfs(M, i);
            }
        }
        return res;
    }

    private void dfs(int[][] M, int i) {
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                dfs(M, j);
            }
        }
    }
}
// @lc code=end
```

#### 130. surrounded-regions

------

**Description:**

Given a 2D board containing `'X'` and `'O'` (**the letter O**), capture all regions surrounded by `'X'`.

A region is captured by flipping all `'O'`s into `'X'`s in that surrounded region.

**Example:**

```
X X X X
X O O X
X X O X
X O X X
```

After running your function, the board should be:

```
X X X X
X X X X
X X X X
X O X X
```

**Explanation:**

Surrounded regions shouldn’t be on the border, which means that any `'O'` on the border of the board are not flipped to `'X'`. Any `'O'` that is not on the border and it is not connected to an `'O'` on the border will be flipped to `'X'`. Two cells are connected if they are adjacent cells connected horizontally or vertically.

[Discussion](https://leetcode.com/problems/surrounded-regions/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/surrounded-regions/solution/)

**Code:**

```java
{% raw %}
/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 */

// @lc code=start
class Solution {

    private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int m, n;
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
    
        m = board.length;
        n = board[0].length;
    
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }
    
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'T';
        for (int[] d : dir) {
            dfs(board, r + d[0], c + d[1]);
        }
    }
}
// @lc code=end
{% endraw %}
```

#### 417. pacific-atlantic-water-flow

------

**Description:**

Given an `m x n` matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

**Note:**

1. The order of returned grid coordinates does not matter.
2. Both *m* and *n* are less than 150.

**Example:**

```
Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
```

[Discussion](https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/pacific-atlantic-water-flow/solution/)

**Code:**

```java
{% raw %}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=417 lang=java
 *
 * [417] Pacific Atlantic Water Flow
 */

// @lc code=start
class Solution {

    private int[][] matrix;
    private int m, n;
    private int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public List<List<Integer>> pacificAtlantic (int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];
    
        for (int i = 0; i < m; i++) {
            dfs(i, 0, canReachP);
            dfs(i, n - 1, canReachA);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, canReachP);
            dfs(m - 1, j, canReachA);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachA[i][j] && canReachP[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    
    private void dfs (int r, int c, boolean[][] canReach) {
        if (canReach[r][c]) {
            return;
        }
        canReach[r][c] = true;
        for (int[] d : dir) {
            int nextRow = r + d[0];
            int nextCol = c + d[1];
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n
                    || matrix[r][c] > matrix[nextRow][nextCol]) {
                continue;
            }
            dfs(nextRow, nextCol, canReach);
        }
    }
}
// @lc code=end
{% endraw %}
```

