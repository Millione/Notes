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

### 402. remove-k-digits

------

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

### 84. largest-rectangle-in-histogram

------

**Description:**

Given *n* non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 

![img](https://assets.leetcode.com/uploads/2018/10/12/histogram.png)
Above is a histogram where width of each bar is 1, given height = `[2,1,5,6,2,3]`.

![img](https://assets.leetcode.com/uploads/2018/10/12/histogram_area.png)
The largest rectangle is shown in the shaded area, which has area = `10` unit.

**Example:**

```
Input: [2,1,5,6,2,3]
Output: 10
```

**Code:**

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        // 单调增栈：用于定位左右小于自己的最近元素
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            // 处理全部单调增情况
            int k = i == n ? 0 : heights[i];
            if (stack.isEmpty() || k >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int top = stack.pop();
                ans = Math.max(ans, heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                i--;
            }
        }
        return ans;
    }
}
```

### 42. trapping-rain-water

------

Given *n* non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

![img](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)
The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. **Thanks Marcos** for contributing this image!

**Example:**

```
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

**Code:**

```java
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i);
            } else {
                int top = stack.pop();
                ans += stack.isEmpty() ? 0 : (Math.min(height[stack.peek()], height[i]) - height[top]) * (i - stack.peek() - 1);
                i--;
            }
        }
        return ans;
    }
}
```

