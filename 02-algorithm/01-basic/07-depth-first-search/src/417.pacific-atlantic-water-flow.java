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

