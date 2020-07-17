# 分治

> 将一个规模为N的问题分解为K个规模较小的子问题，这些子问题相互独立且与原问题性质相同。

|               分治算法相应题目索引（持续更新）               |
| :----------------------------------------------------------: |
| [95. Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii) |
| [241. Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses) |

## 思想

分治算法可以分三步走：分解 -> 解决 -> 合并

1. 分解原问题为结构相同的子问题。
2. 分解到某个容易求解的边界之后，进行递归求解。
3. 将子问题的解合并成原问题的解。

## 题目

### 95. unique-binary-search-trees-ii

------

**Description:**

Given an integer *n*, generate all structurally unique **BST's** (binary search trees) that store values 1 ... *n*.

**Example:**

```
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

[Discussion](https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/unique-binary-search-trees-ii/solution/)

**Code:**

```java
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode id=95 lang=java
 *
 * [95] Unique Binary Search Trees II
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new LinkedList<TreeNode>();
        }
        return generateSubTree(1, n);
    }
    public List<TreeNode> generateSubTree(int l, int r) {
        List<TreeNode> res = new LinkedList<>();
        if (l > r) {
            res.add(null);
            return res;
        }
        for (int i = l; i <= r; i++) {
            List<TreeNode> left = generateSubTree(l, i - 1);
            List<TreeNode> right = generateSubTree(i + 1, r);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    res.add(node);
                }
            }
        }
        return res;
    }
}
// @lc code=end
```

### 241. different-ways-to-add-parentheses

------

**Description:**

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are `+`, `-` and `*`.

**Example 1:**

```
Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
```

**Example 2:**

```
Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
```

[Discussion](https://leetcode.com/problems/different-ways-to-add-parentheses/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/different-ways-to-add-parentheses/solution/)

**Code:**

```java
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
```

