# 双指针

所谓双指针算法，就是指的是在遍历的过程中，不是普通的使用单个指针进行循环访问，而是使用**两个相同方向或者相反方向的指针**进行扫描，从而达到相应的目的。双指针法充分使用了数组有序这一特征，从而在某些情况下能够简化一些运算，降低时间复杂度。

| 双指针类型 |                      题目索引(持续更新)                      |
| :--------: | :----------------------------------------------------------: |
|  对撞指针  | [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water)<br>[15. 3Sum](https://leetcode.com/problems/3sum)<br>[16. 3Sum Closest](https://leetcode.com/problems/3sum-closest)<br>[ 18. 4Sum](https://leetcode.com/problems/4sum)<br>[167. Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted)<br>[344. Reverse String](https://leetcode.com/problems/reverse-string) |
|  快慢指针  | [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters)<br>[88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array)<br>[141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle)<br>[142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii)<br>[209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum)<br>[283. Move Zeroes](https://leetcode.com/problems/move-zeroes)<br>[287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number) |
| 分离双指针 | [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays)<br>[350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii) |

## 类型

### 1. 对撞指针

对撞指针是指在**有序数组**中，将指向最左侧的索引定义为左指针 (left)，最右侧的定义为右指针 (right)，然后从两头向中间进行数组遍历。

#### 167. two-sum-II

------

**Description:**

Given an array of integers that is already **sorted in ascending order**, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

**Note:**

- Your returned answers (both index1 and index2) are not zero-based.
- You may assume that each input would have *exactly* one solution and you may not use the *same* element twice.

**Example:**

```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
```

[Discussion](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input array is sorted
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else{
                r--;
            }
        }
        return null;
    }
}
// @lc code=end
```

#### 344. reverse-string

------

**Description:**

Write a function that reverses a string. The input string is given as an array of characters `char[]`.

Do not allocate extra space for another array, you must do this by **modifying the input array [in-place](https://en.wikipedia.org/wiki/In-place_algorithm)** with O(1) extra memory.

You may assume all the characters consist of [printable ascii characters](https://en.wikipedia.org/wiki/ASCII#Printable_characters).

**Example 1:**

```
Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

**Example 2:**

```
Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

[Discussion](https://leetcode.com/problems/reverse-string/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/reverse-string/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=344 lang=java
 *
 * [344] Reverse String
 */

// @lc code=start
class Solution {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l++] = s[r];
            s[r--] = temp;
        }
    }
}
// @lc code=end
```



### 2. 快慢指针

快慢指针也是双指针，但是两个指针从同一侧开始遍历数组，将这两个指针分别定义为快指针（fast）和慢指针（slow），两个指针以不同的策略移动，直到两个指针的值相等（或其他特殊条件）为止，如 fast 每次增长两个，slow 每次增长一个。

#### 283. move-zeros

------

**Description:**

Given an array `nums`, write a function to move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.

**Example:**

```
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
```

**Note**:

1. You must do this **in-place** without making a copy of the array.
2. Minimize the total number of operations.

[Discussion](https://leetcode.com/problems/move-zeroes/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/move-zeroes/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 *
 * 前向双指针
 *
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}
// @lc code=end
```

#### 148. linked-list-cycle-II

------

**Description:**

Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Note:** Do not modify the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

**Follow-up**:
Can you solve it without using extra space?

[Discussion](https://leetcode.com/problems/linked-list-cycle-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/linked-list-cycle-ii/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
 *
 * 判断是否存在环，若存在找出环入口
 *
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = head.next;
        ListNode next = head.next.next;
        while (pre != next) {
            if (next == null || next.next == null) {
                return null;
            }
            pre = pre.next;
            next = next.next.next;
        }
        // x = L + S  2x  L + S = kn 再走L步回到起点
        while (head != next) {
            head = head.next;
            next = next.next;
        }
        return head;
    }
}
// @lc code=end
```

#### 209. minimum-size-subarray-sum

------

**Description:**

Given an array of **n** positive integers and a positive integer **s**, find the minimal length of a **contiguous** subarray of which the sum ≥ **s**. If there isn't one, return 0 instead.

**Example:** 

```
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
```

**Follow up:**

If you have figured out the *O*(*n*) solution, try coding another solution of which the time complexity is *O*(*n* log *n*). 

[Discussion](https://leetcode.com/problems/minimum-size-subarray-sum/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/minimum-size-subarray-sum/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 *
 * 双指针滑动窗口
 *
 */

// @lc code=start
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = -1;
        int sum = 0;
        int res = nums.length + 1;
        while (i < nums.length) {
            if (j + 1 < nums.length && sum < s) {
                sum += nums[++j];
            } else {
                sum -= nums[i++];
            }
            if (sum >= s) {
                res = Math.min(res, j - i + 1);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
}
// @lc code=end
```

#### 88. merge-sorted-array

------

**Description:**

Given two sorted integer arrays *nums1* and *nums2*, merge *nums2* into *nums1* as one sorted array.

**Note:**

- The number of elements initialized in *nums1* and *nums2* are *m* and *n* respectively.
- You may assume that *nums1* has enough space (size that is greater or equal to *m* + *n*) to hold additional elements from *nums2*.

**Example:**

```
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

[Discussion](https://leetcode.com/problems/merge-sorted-array/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/merge-sorted-array/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 *
 * 同前向型-快慢指针类似，不同的是快慢指针从后往前，用于合并和替换类型题，防止之前的数据被覆盖。
 *
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
}
// @lc code=end
```



### 3. 分离双指针

输入是**两个数组 / 链表**，两个指针分别在两个容器中移动；根据问题的不同，初始位置可能都在头部，或者都在尾部，或一头一尾。

#### 349. intersection-of-two-arrays

------

**Description:**

Given two arrays, write a function to compute their intersection.

**Example 1:**

```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```

**Example 2:**

```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
```

**Note:**

- Each element in the result must be unique.
- The result can be in any order.

[Discussion](https://leetcode.com/problems/intersection-of-two-arrays/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/intersection-of-two-arrays/solution/)

**Code:**

```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=349 lang=java
 *
 * [349] Intersection of Two Arrays
 */

// @lc code=start
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
}
// @lc code=end
```

#### 350. intersection-of-two-arrays-II

------

**Description:**

Given two arrays, write a function to compute their intersection.

**Example 1:**

```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```

**Example 2:**

```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
```

**Note:**

- Each element in the result should appear as many times as it shows in both arrays.
- The result can be in any order.

**Follow up:**

- What if the given array is already sorted? How would you optimize your algorithm?
- What if *nums1*'s size is small compared to *nums2*'s size? Which algorithm is better?
- What if elements of *nums2* are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

[Discussion](https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/intersection-of-two-arrays-ii/solution/)

**Code:**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=350 lang=java
 *
 * [350] Intersection of Two Arrays II
 */

// @lc code=start
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[list.size()];
        int k = 0;
        for (int num : list) {
            result[k++] = num;
        }
        return result;
    }
}
// @lc code=end
```



## 题目

#### 11. container-with-most-water

------

**Description:**

Given *n* non-negative integers *a1*, *a2*, ..., *an* , where each represents a point at coordinate (*i*, *ai*). *n* vertical lines are drawn such that the two endpoints of line *i* is at (*i*, *ai*) and (*i*, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

**Note:** You may not slant the container and *n* is at least 2.

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

**Example:**

```
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

[Discussion](https://leetcode.com/problems/container-with-most-water/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/container-with-most-water/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                res = Math.max(res, height[l] * (r - l));
                l++;
            } else {
                res = Math.max(res, height[r] * (r - l));
                r--;
            }
        }
        return res;
    }
}
// @lc code=end
```

#### 147. linked list cycle

------

**Description:**

Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.

**Example 1:**

```
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

**Example 2:**

```
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

**Example 3:**

```
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
```

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

**Follow up:**

Can you solve it using *O(1)* (i.e. constant) memory?

[Discussion](https://leetcode.com/problems/linked-list-cycle/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/linked-list-cycle/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
 *
 * 判断是否存在环
 *
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode pre = head;
        ListNode next = head.next.next;
        while (pre != next) {
            if (next == null || next.next == null) {
                return false;
            }
            pre = pre.next;
            next = next.next.next;
        }
        return true;
    }
}
// @lc code=end
```

#### 287. find-the-duplicate-number

------

**Description:**

Given an array *nums* containing *n* + 1 integers where each integer is between 1 and *n* (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

**Example 1:**

```
Input: [1,3,4,2,2]
Output: 2
```

**Example 2:**

```
Input: [3,1,3,4,2]
Output: 3
```

**Note:**

1. You **must not** modify the array (assume the array is read only).
2. You must use only constant, *O*(1) extra space.
3. Your runtime complexity should be less than *O*(*n²*).
4. There is only one duplicate number in the array, but it could be repeated more than once.

[Discussion](https://leetcode.com/problems/find-the-duplicate-number/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/find-the-duplicate-number/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=287 lang=java
 *
 * [287] Find the Duplicate Number
 *
 * 快慢指针找环入口
 *
 */

// @lc code=start
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums.length >= 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
        return -1;
    }
}
// @lc code=end
```

