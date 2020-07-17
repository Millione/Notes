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

