import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 */

// @lc code=start
class Solution {

    private List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        if (n < k || n < 1 || k < 0) {
            return res;
        }
        dfs(n, k, 1, new ArrayDeque<>());
        return res;
    }

    private void dfs(int n, int k, int index, Deque<Integer> stack) {
        if (k == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i <= n; i++) {
            stack.push(i);
            dfs(n, k - 1, i + 1, stack);
            stack.pop();
        }
    }
}
// @lc code=end

