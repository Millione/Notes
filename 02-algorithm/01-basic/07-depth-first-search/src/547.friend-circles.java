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

