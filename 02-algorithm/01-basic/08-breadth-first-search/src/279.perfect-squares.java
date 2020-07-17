import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=279 lang=java
 *
 * [279] Perfect Squares
 */

// @lc code=start
class Solution {
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(n);
        visited[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return n;
    }
    
    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,...
     */
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }
}
// @lc code=end

