import java.util.ArrayDeque;
import java.util.Queue;

/*
 * @lc app=leetcode id=1091 lang=java
 *
 * [1091] Shortest Path in Binary Matrix
 */

// @lc code=start
class Solution {
    class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));
        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Node node = queue.poll();
                int x = node.x;
                int y = node.y;
                if (grid[x][y] == 1) {
                    continue;
                }
                if (x == m - 1 && y == n - 1) {
                    return ans;
                }
                grid[x][y] = 1; // 标记
                for (int[] d : dir) {
                    int xx = x + d[0];
                    int yy = y + d[1];
                    if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                        continue;
                    }
                    queue.add(new Node(xx, yy));
                }
            }
        }
        return -1;
    }
}
// @lc code=end

