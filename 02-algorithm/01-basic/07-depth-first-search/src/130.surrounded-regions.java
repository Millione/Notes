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
        for (int j = 1; j < n - 1; j++) {
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

