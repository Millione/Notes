import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */

// @lc code=start
class Solution {
    
    private List<List<Integer>> res;
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        findPermuteUnique(nums, new Stack<>());
        return res;
    }

    private void findPermuteUnique(int[] nums, Stack<Integer> stack){
        if(stack.size() == nums.length){
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            stack.push(nums[i]);
            findPermuteUnique(nums, stack);
            stack.pop();
            visited[i] = false;
        }
    }
}
// @lc code=end

