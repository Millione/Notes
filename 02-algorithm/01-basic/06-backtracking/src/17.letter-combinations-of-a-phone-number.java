import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 */

// @lc code=start
class Solution {

    private List<String> res = new ArrayList<String>();
    private final String[] table = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        dfs(digits, 0, new StringBuilder());
        return res;
    }
    
    private void dfs(String digits, int index, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String s = table[digits.charAt(index) - '0'];
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            dfs(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
// @lc code=end

