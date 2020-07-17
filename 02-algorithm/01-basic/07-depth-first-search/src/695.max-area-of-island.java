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

