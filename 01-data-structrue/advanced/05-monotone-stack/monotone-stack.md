# 单调栈

> 单调栈即满足单调性的栈结构。

**用途：**用来快速寻找一个数组中的每一个元素，左右两边离它最近的比它大（小）的数。

## 模板

```java
/**
 * 单调栈: 寻找数组中的每一个元素　左右两边离它arr[i]最近的比它大的数
 * 栈底到栈顶: 由大到小 (也可以自定义从小到大)
 */

void monotoneStack(int[] arr) {
    int n = arr.length;
    int[] L = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                L[top] = -1;
            } else {
                L[top] = stack.peek();
            }
        }
        stack.push(i);
    }
    while (!stack.isEmpty()) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            L[top] = -1;
        } else {
            L[top] = stack.peek();
        }
    }
}

void monotoneStack(int[] arr) {
    int n = arr.length;
    int[] R = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
        while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                R[top] = -1;
            } else {
                R[top] = stack.peek();
            }
        }
        stack.push(i);
    }
    while (!stack.isEmpty()) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            R[top] = -1;
        } else {
            R[top] = stack.peek();
        }
    }
}
```

## 题目

**Description:**

Given a non-negative integer *num* represented as a string, remove *k* digits from the number so that the new number is the smallest possible.

**Note:**

- The length of *num* is less than 10002 and will be ≥ *k*.
- The given *num* does not contain any leading zero.

**Example 1:**

```
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
```

**Example 2:**

```
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
```

**Example 3:**

```
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
```

[Discussion](https://leetcode.com/problems/remove-k-digits/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/remove-k-digits/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=402 lang=java
 *
 * [402] Remove K Digits
 */

// @lc code=start
public class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n) {
            return "0";            
        }        
        Deque<Character> stack = new ArrayDeque<>();
        int i = 0;
        while (i < n) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        
        // corner case like "1111"
        while (k > 0) {
            stack.pop();
            k--;            
        }
        
        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        
        //remove all the 0 at the head
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
// @lc code=end
```

