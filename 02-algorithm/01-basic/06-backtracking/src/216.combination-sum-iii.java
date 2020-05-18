import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=216 lang=java
 *
 * [216] Combination Sum III
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    private int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        dfs(k, 0, n, new Stack<>());
        return res;
    }
    private void dfs(int depth, int index, int target, Stack<Integer> stack) {
        if (depth == 0) {
            if (target == 0) {
                res.add(new ArrayList<>(stack));
            }
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            stack.push(candidates[i]);
            dfs(depth - 1, i + 1, target - candidates[i], stack);
            stack.pop();
        }
    }
}
// @lc code=end

