import java.util.Random;

/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    private int quickSelect(int[] nums, int l, int r, int k){
        int pivot = l;
        for(int i = l; i < r; i++){
            if(nums[i] <= nums[r]){
                swap(nums, i, pivot++);
            }
        }
        swap(nums, pivot, r);
        int count = r - pivot + 1;
        if(count == k) return nums[pivot];
        else if(count < k) return quickSelect(nums, l, pivot - 1, k - count);
        else return quickSelect(nums, pivot + 1, r, k);

    }
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private void shuffle(int arr[]) {
        final Random random = new Random();
        for (int idx = 1; idx < arr.length; ++idx) {
            final int r = random.nextInt(idx + 1);
            swap(arr, idx, r);
        }
    }
}
// @lc code=end

