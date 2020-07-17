import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

// @lc code=start
class Solution {
    private List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        dfs(n, "", 0, 0);
        return res;
    }
    private void dfs(int n, String str, int l, int r) {
        if (str.length() == 2 * n) {
            res.add(str);
            return;
        }
        if (l < n) {
            dfs(n, str + "(", l + 1, r);
        }
        if (r < l) {
            dfs(n, str + ")", l, r + 1);
        }
    }
}
// @lc code=end

