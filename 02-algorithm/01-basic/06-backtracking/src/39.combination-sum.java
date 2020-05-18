import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        dfs(candidates, 0, target, new Stack<>());
        return res;
    }
    private void dfs(int[] candidates, int index, int target, Stack<Integer> stack) {
        if (target == 0) {
            // 注意new
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                continue;
            }
            stack.push(candidates[i]);
            dfs(candidates, i, target - candidates[i], stack);
            stack.pop();
        }
    }
}
// @lc code=end

