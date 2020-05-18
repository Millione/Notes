import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=220 lang=java
 *
 * [220] Contains Duplicate III
 */

// @lc code=start
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            // 解决负值
            long remappedNum = (long)nums[i] -Integer.MIN_VALUE;
            // 防止t = 0，位于同一个桶的差值不超过t
            long bucket = remappedNum / ((long)t + 1);
            // 同时要与相邻的桶比较
            if (map.containsKey(bucket)
                || map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t
                || map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t) {
                    return true;
            }
            // 核心代码逻辑，控制索引差值
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long)nums[i - k] - Integer.MIN_VALUE) / ((long)t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}
// @lc code=end

