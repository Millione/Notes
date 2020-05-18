import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=241 lang=java
 *
 * [241] Different Ways to Add Parentheses
 */

// @lc code=start
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int leftVal : left) {
                    for (int rightVal : right) {
                        switch (c) {
                            case '+' : 
                                res.add(leftVal + rightVal);
                                break;
                            case '-' :
                                res.add(leftVal - rightVal);
                                break;
                            case '*' :
                                res.add(leftVal * rightVal);
                                break;
                        }
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }
}
// @lc code=end

