import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        Arrays.sort(nums);
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
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            stack.push(nums[i]);
            dfs(nums, len, i + 1, stack);
            stack.pop();
        }
    }
}
// @lc code=end

