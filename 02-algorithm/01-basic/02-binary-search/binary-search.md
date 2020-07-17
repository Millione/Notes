# 二分查找

> Although the basic idea of binary search is comparatively straightforward,the details can be surprisingly tricky...

先讲个笑话乐呵一下：

有一天阿东到图书馆借了 N 本书，出图书馆的时候，警报响了，于是保安把阿东拦下，要检查一下哪本书没有登记出借。阿东正准备把每一本书在报警器下过一下，以找出引发警报的书，但是保安露出不屑的眼神：你连二分查找都不会吗？于是保安把书分成两堆，让第一堆过一下报警器，报警器响；于是再把这堆书分成两堆…… 最终，检测了 logN 次之后，保安成功的找到了那本引起警报的书，露出了得意和嘲讽的笑容。于是阿东背着剩下的书走了。

从此，图书馆丢了 N - 1 本书。

> 思路很简单，细节是魔鬼。

|                二分查找相关题目索引(持续更新)                |
| :----------------------------------------------------------: |
| [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays) |
| [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array) |
| [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array) |
| [35. Search Insert Position](https://leetcode.com/problems/search-insert-position) |
|      [69. Sqrt(x)](https://leetcode.com/problems/sqrtx)      |
| [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix) |
| [81. Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii) |
| [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array) |
| [154. Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii) |
| [240. Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii) |
| [719. Find K-th Smallest Pair Distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance) |

## 1. 框架

```java
int binarySearch(int[] arr, int key){
    int left = 0, right = ...;
    while(...){
        int mid = left + (right - left) / 2;
        if (arr[mid] == key) {
            ...;
        }
        else if (arr[mid] < key) {
            left = ...;
        }
        else if (arr[mid] > key) {
            right = ...;
        }
    }
    return ...;
}
```

分析二分查找技巧：不要出现else，而是把所有情况用else if写清楚，这样就可以清楚地展现所有细节。

`...`标记的部分就是可能出现细节问题的地方。

## 2. 模板

### 1. 最基本的二分查找

```java
int binarySearch(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] > key) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return -1;
}
```

### 2. 查找第一个等于key

```java
int firstEqual(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] >= key) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    // 注意判断条件
    if (l < arr.length && arr[l] == key) {
        return l;
    }
    return -1;
}
```

### 3. 查找第一个大于等于key

```java
int firstLargeEqual(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] >= key) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

### 4. 查找第一个大于key

```java
int firstLarge(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        // 注意
        if (arr[mid] > key) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

### 5. 查找最后一个等于key

```java
int lastEqual(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] <= key) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    // 注意判断条件
    if (r >= 0 && arr[r] == key) {
        return r;
    }
    return -1;
}
```

### 6. 查找最后一个小于等于key

```java
int lastEqualSmall(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] <= key) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return r;
}
```

### 7. 查找最后一个小于key

```java
int lastSmall(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        // 注意
        if (arr[mid] < key) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return r;
}
```

## 3. 题目

### 4. median-of-two-sorted-arrays

------

**Description:**

There are two sorted arrays **nums1** and **nums2** of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume **nums1** and **nums2** cannot be both empty.

**Example 1:**

```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```

**Example 2:**

```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```

[Discussion](https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/median-of-two-sorted-arrays/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 *
 * 二分查找难度极大的一道题
 * https://www.bilibili.com/video/BV1wJ411N7U8?from=search&seid=15996420867699793986
 * 可以把问题转化为查找两个数组中第(n1+n2+1)/2大的数
 *
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int l = 0, r = n1 - 1, mid1, mid2;
        // 偶数-->右中值  奇数-->中值
        int k = (n1 + n2 + 1) / 2;
        while (l <= r) {
            mid1 = l + (r - l) / 2;
            mid2 = k - mid1;
            // mid2可能等于n2
            if (nums1[mid1] < nums2[mid2 - 1]) {
                l = mid1 + 1;
            } else {
                r = mid1 - 1;
            }
        }
        // 元素个数
        mid1 = l;
        mid2 = k - mid1;
        // 注意边界和越界情况
        int c1 = Math.max(mid1 <= 0 ? Integer.MIN_VALUE : nums1[mid1 - 1]
                        , mid2 <= 0 ? Integer.MIN_VALUE : nums2[mid2 - 1]);
        if ((n1 + n2) % 2 == 1) {
            return (double)c1;
        }
        int c2 = Math.min(mid1 >= n1 ? Integer.MAX_VALUE : nums1[mid1]
                        , mid2 >= n2 ? Integer.MAX_VALUE : nums2[mid2]);
        return (c1 + c2) / 2.0;
    }
}
// @lc code=end
```

### 33. search-in-rotated-sorted-array

------

**Description:**

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`).

You are given a target value to search. If found in the array return its index, otherwise return `-1`.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of *O*(log *n*).

**Example 1:**

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**

```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

[Discussion](https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/search-in-rotated-sorted-array/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
// @lc code=end
```

### 81. search-in-rotated-sorted-array-II

------

**Description:**

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `[0,0,1,2,2,5,6]` might become `[2,5,6,0,0,1,2]`).

You are given a target value to search. If found in the array return `true`, otherwise return `false`.

**Example 1:**

```
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
```

**Example 2:**

```
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
```

**Follow up:**

- This is a follow up problem to [Search in Rotated Sorted Array](), where `nums` may contain duplicates.
- Would this affect the run-time complexity? How and why?

[Discussion](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=81 lang=java
 *
 * [81] Search in Rotated Sorted Array II
 */

// @lc code=start
class Solution {
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 保证一方有序，有可能导致O(n)时间复杂度
            if (nums[mid] == nums[l] && nums[mid] == nums[r]) {
                ++l;
                --r;
            }
            else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
// @lc code=end
```

### 34. find-first-and-last-position-of-element-in-sorted-array

------

**Description:**

Given an array of integers `nums` sorted in ascending order, find the starting and ending position of a given `target` value.

Your algorithm's runtime complexity must be in the order of *O*(log *n*).

If the target is not found in the array, return `[-1, -1]`.

**Example 1:**

```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```

**Example 2:**

```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

[Discussion](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */

// @lc code=start
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l1 = 0, r1 = nums.length - 1;
        int[] res = {-1, -1};
        while (l1 <= r1) {
            int mid = l1 + (r1 - l1) / 2;
            if (nums[mid] >= target) {
                r1 = mid - 1;
            } else {
                l1 = mid + 1;
            }
        }
        if (l1 <= nums.length - 1 && nums[l1] == target) {
            res[0] = l1;
        }
        int l2 = 0, r2 = nums.length - 1;
        while(l2 <= r2){
            int mid = l2 + (r2 - l2) / 2;
            if (nums[mid] <= target) {
                l2 = mid + 1;
            } else {
                r2 = mid - 1;
            }
        }
        if (r2 >= 0 && nums[r2] == target) {
            res[1] = r2;
        }
        return res;
    }
}
// @lc code=end
```

### 35. search-insert-position

------

**Description:**

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

**Example 1:**

```
Input: [1,3,5,6], 5
Output: 2
```

**Example 2:**

```
Input: [1,3,5,6], 2
Output: 1
```

**Example 3:**

```
Input: [1,3,5,6], 7
Output: 4
```

**Example 4:**

```
Input: [1,3,5,6], 0
Output: 0
```

[Discussion](https://leetcode.com/problems/search-insert-position/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/search-insert-position/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 */

// @lc code=start
class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
// @lc code=end
```

### 69. sqrt-x

------

**Description:**

Implement `int sqrt(int x)`.

Compute and return the square root of *x*, where *x* is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

**Example 1:**

```
Input: 4
Output: 2
```

**Example 2:**

```
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
```

[Discussion](https://leetcode.com/problems/sqrtx/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/sqrtx/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 */

// @lc code=start
class Solution {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int l = 1, r = Integer.MAX_VALUE;
        while (true) {
            int mid = l + (r - l) / 2;
            // 防整形溢出
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) {
                    return mid;
                }
                l = mid + 1;
            }
        }
    }
}
// @lc code=end
```

### 74. search-a-2-d-matrix

------

**Description:**

Write an efficient algorithm that searches for a value in an *m* x *n* matrix. This matrix has the following properties:

- Integers in each row are sorted from left to right.
- The first integer of each row is greater than the last integer of the previous row.

**Example 1:**

```
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
```

**Example 2:**

```
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
```

[Discussion](https://leetcode.com/problems/search-a-2d-matrix/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/search-a-2d-matrix/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 */

// @lc code=start
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1, mid, val;
        while (l <= r) {
            mid = l + (r - l) / 2;
            // 二维数组中位置
            val = matrix[mid / n][mid % n];
            if (val > target) {
                r = mid - 1;
            } else if (val < target) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
```

### 240. search-a-2-d-matrix-II

------

**Description:**

Write an efficient algorithm that searches for a value in an *m* x *n* matrix. This matrix has the following properties:

- Integers in each row are sorted in ascending from left to right.
- Integers in each column are sorted in ascending from top to bottom.

**Example:**

Consider the following matrix:

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

Given target = `5`, return `true`.

Given target = `20`, return `false`.

[Discussion](https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/search-a-2d-matrix-ii/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=240 lang=java
 *
 * [240] Search a 2D Matrix II
 */

// @lc code=start
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        for (int i = matrix.length - 1, j = 0; i >= 0 && j < matrix[0].length;) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
```

