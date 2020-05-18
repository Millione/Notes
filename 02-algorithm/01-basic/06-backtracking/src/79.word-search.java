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

