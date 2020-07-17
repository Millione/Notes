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

