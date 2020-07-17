import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
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
                break;
            }
            if (i != index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            stack.push(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], stack);
            stack.pop();
        }
    }
}
// @lc code=end

