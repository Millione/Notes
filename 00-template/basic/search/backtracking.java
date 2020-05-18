private List<List<Integer>> res;
private boolean[] visited;

public List<List<Integer>> permuteUnique(int[] nums) {
    res = new ArrayList<>();
    visited = new boolean[nums.length];
    Arrays.sort(nums);
    backtracking(nums, new ArrayDeque<>());
    return res;
}

private void backtracking(int[] nums, Deque<Integer> stack){
    // 满足条件
    if(stack.size() == nums.length){
        res.add(new ArrayList<>(stack));
        return;
    }
    for(int i = 0; i < nums.length; i++){
        // 不满足条件
        if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
            continue;
        }
        // 做选择
        visited[i] = true;
        stack.push(nums[i]);

        backtracking(nums, stack);

        // 撤销选择
        stack.pop();
        visited[i] = false;
    }
}