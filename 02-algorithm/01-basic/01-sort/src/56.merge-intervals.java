import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < intervals.length; ++i) {
            int num1 = intervals[i][0];
            int num2 = intervals[i][1];
            while (i < intervals.length - 1 && num2 >= intervals[i + 1][0]) {
                num2 = Math.max(num2, intervals[i + 1][1]);
                ++i;
            }
            res.add(new int[]{num1, num2});
        }
        // 动态创建大小等于size，性能最好。
        return res.toArray(new int[0][]);
    }
}
// @lc code=end

