import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 */

// @lc code=start
class Solution {

    // 绑定最初索引
    class Pair {
        int index;
        int val;
        public Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int[] smaller = new int[nums.length];
        Pair[] arr = new Pair[nums.length];
        for (int i = 0; i < smaller.length; i++) {
            arr[i] = new Pair(i, nums[i]);
        }
        mergeSort(arr, 0, nums.length - 1, smaller);
        for (int num : smaller) {
            res.add(num);
        }
        return res;
    }
    
    private void mergeSort(Pair[] arr, int l, int r, int[] smaller) {
        if(l >= r) {
            return;
        }
        int mid = l + (r - l) / 2; 
        mergeSort(arr, l, mid, smaller);
        mergeSort(arr, mid + 1, r, smaller); 
        for(int i = l, j = mid + 1; i <= mid; ++i){
            while(j <= r && arr[i].val > arr[j].val) {
                ++j; 
            }
            smaller[arr[i].index] += j - (mid + 1); 
        }
        merge(arr, l, mid, r);
    }

    private void merge(Pair[] arr, int l, int mid, int r) {
        Pair[] aux = new Pair[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int k = 0;
        while (p1 <= mid && p2 <= r) {
            // 保证稳定性
            aux[k++] = arr[p1].val <= arr[p2].val ? arr[p1++] : arr[p2++];
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

