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

