/*
 * @lc app=leetcode id=493 lang=java
 *
 * [493] Reverse Pairs
 */

// @lc code=start
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }
    private int mergeSort(int[] arr, int l, int r){
        if(l >= r) {
            return 0;
        } 
        int mid = l + (r - l) / 2; 
        int cnt = mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r); 
        for(int i = l, j = mid + 1; i <= mid; ++i){
            while(j <= r && arr[i] / 2.0 > arr[j]) {
                ++j; 
            }
            cnt += j - (mid + 1); 
        }
        merge(arr, l, mid, r);
        return cnt; 
    }
    private void merge(int[] arr, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int k = 0;
        while (p1 <= mid && p2 <= r) {
            // 保证稳定性
            aux[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            aux[k++] = arr[p1++];
        }
        while (p2 <= r) {
            aux[k++] = arr[p2++];
        }
        for (int i = 0; i < k; ++i) {
            arr[l + i] = aux[i];
        }
    }
}
// @lc code=end

