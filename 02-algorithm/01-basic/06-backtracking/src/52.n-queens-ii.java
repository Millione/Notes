import java.util.Arrays;

/*
 * @lc app=leetcode id=52 lang=java
 *
 * [52] N-Queens II
 */

// @lc code=start
class Solution {
    private int N;
    private boolean[] cols, d1, d2;
    private int res;
    private char[][] board;

    public int totalNQueens(int n) {
        res = 0;
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
            res++;
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
}
// @lc code=end

