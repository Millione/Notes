import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */

// @lc code=start
class Solution {
    private List<List<Integer>> res;
    private boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        findPermute(nums, new Stack<>());
        return res;
    }

    private void findPermute(int[] nums, Stack<Integer> stack){
        if(stack.size() == nums.length){
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                stack.push(nums[i]);
                findPermute(nums, stack);
                stack.pop();
                visited[i] = false;
            }
        }
    }
}
// @lc code=end

