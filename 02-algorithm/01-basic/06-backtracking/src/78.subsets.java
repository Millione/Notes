import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        for (int i = 0; i <= nums.length; ++i) {
            dfs(nums, i, 0, new ArrayDeque<>());
        }
        return res;
    }
    private void dfs(int[] nums, int len, int index, Deque<Integer> stack) {
        if (stack.size() == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < nums.length; ++i) {
            stack.push(nums[i]);
            dfs(nums, len, i + 1, stack);
            stack.pop();
        }
    }
}
// @lc code=end

