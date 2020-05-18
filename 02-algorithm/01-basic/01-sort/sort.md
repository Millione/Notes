# 排序

先给出排序经常会用到的swap函数，用于交换数组中两个位置的值。

```java
void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

下表是排序算法思想相应LeetCode的题目索引（持续更新）。

|   算法   |                             题号                             |
| :------: | :----------------------------------------------------------: |
| 内部排序 | [148. Sort List](https://leetcode.com/problems/sort-list)<br>[912. Sort an Array](https://leetcode.com/problems/sort-an-array) |
| 归并排序 | [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists)<br>[23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists)<br>[88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array)<br>[315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self)<br>[493. Reverse Pairs](https://leetcode.com/problems/reverse-pairs) |
| 快速排序 | [75. Sort Colors](https://leetcode.com/problems/sort-colors)<br>[215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array) |
|  堆排序  | [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array) |
| 计数排序 |    [274. H-Index](https://leetcode.com/problems/h-index)     |
|  桶排序  | [164. Maximum Gap](https://leetcode.com/problems/maximum-gap)<br>[220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii) |
| 基数排序 | [164. Maximum Gap](https://leetcode.com/problems/maximum-gap) |

下图是排序算法的性能及稳定性总结。

![排序算法总结](assets/sort-all.png)



## 1. 冒泡排序

### 思想

从前往后比较相邻元素，如果第一个比第二个大，就交换。重复前面的步骤，每次都从最后累积减少一个元素。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

int[] bubbleSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int j = n - 1; j > 0; --j) {
        boolean isSort = true;
        // 冒泡
        for (int i = 0; i < j; ++i) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                isSort = false;
            }
        }
        // 提前结束
        if (isSort) {
            break;
        }
    }
    return arr;
}

/*****************链表*****************/

ListNode bubbleSort(ListNode head) {
    ListNode cur = head, tail = null;
    // 双指针
    while (cur != tail) {
        while (cur.next != tail) {
            boolean isSort = true;
            if (cur.val > cur.next.val) {
                int tmp = cur.val;
                cur.val = cur.next.val;
                cur.next.val = tmp;
                isSort = false;
            }
            cur = cur.next;
        }
        if (isSort) {
            break;
        }
        // 下次遍历的尾结点是当前结点，每次减少访问最后结点
        tail = cur;
        cur = head;
    }
    return head;
}
```



## 2. 选择排序

### 思想

在未排序序列中找到最小(大)元素放到排序序列起始位置，再从剩余未排序元素中继续寻找最小(大)元素放到已排序序列的末尾，重复步骤直到排序完毕。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

int[] selectSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int i = 0; i < n; ++i) {
        int minIdx = i;
        // 找到区间最小值索引
        for (int j = i + 1; j < n; ++j) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        if(minIdx != i) {
            swap(arr, minIdx, i);
        }
    }
    return arr;
}

/*****************链表*****************/

ListNode selectSort(ListNode head) {
    ListNode cur = head;
    // 相当于双指针
    while (cur != null) {
        ListNode tmpNode = new ListNode(cur.val);
        ListNode next = cur.next;
        while (next != null) {
            if (next.val < tmpNode.val) {
                tmpNode = next;
            }
            next = next.next;
        }
        // 最小值交换
        if (cur.val != tmpNode.val) {
            int tmp = tmpNode.val;
        	tmpNode.val = cur.val;
        	cur.val = tmp;
        }
        cur = cur.next;
    }
    return head;
}
```



## 3. 插入排序

### 思想

将序列分为已排序区间和未排序区间，取未排序区间中的元素，在已排序区间中找到合适的位置将其插入，保证已排序区间一直有序。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

int[] insertionSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int val = arr[i], j = i - 1;
        // 查找插入位置
        for (; j >= 0 && arr[j] > val; --j) {
            // 数据移动
        	arr[j + 1] = arr[j];
        }
        arr[j + 1] = val;
    }
    return arr;
}

/*****************二分插入排序*****************/

int[] insertionSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int l = 0, r = i - 1;
        int val = arr[i];
        // 找第一个大于val的位置
		while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        for (int j = i - 1; j >= l; --j) {
            // 数据移动
        	arr[j + 1] = arr[j];
        }
        arr[l] = val;
    }
    return arr;
}

/*****************链表*****************/

ListNode insertionSort(ListNode head) {
    if (head == null) {
        return head;
    }
    ListNode helper = new ListNode(0);
    // 当前要被插入的结点
    ListNode cur = head;
    // 插入位置在pre和pre.next之间
    ListNode pre = helper;
    // 下一个要插入的结点
    ListNode next = null;
    while (cur != null) {
        next = cur.next;
        // 找到正确插入的位置
        while (pre.next != null && cur.val >= pre.next.val) {
            pre = pre.next;
        }
        // 插入操作
        cur.next = pre.next;
        pre.next = cur;
        pre = helper;
        cur = next;
    }
    return helper.next;
}
```



## 4. 希尔排序

### 思想

选择一个增量序列$t_1, t_2, ......, t_k$，按增量序列个数k对序列进行k趟排序，最后$t_k=1$将整个序列作为一个表来处理。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

int[] shellSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    // 增量序列每次减半
    for (int gap = n; gap > 0; gap /= 2) {
        // 对每个序列做插入排序
        for (int end = gap; end < n; ++end) {
            int val = arr[end], i = end - gap;
            for (; i >= 0 && arr[i] > val; i -= gap) {
                arr[i + gap] = arr[i];
            }
            arr[i + gap] = val;
        }
    }
    return arr;
}
```



## 5. 归并排序

### 思想

如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一起，这样整个数组就都有序了。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

int[] mergeSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    mergeSort(arr, 0, n - 1);
    return arr;
}

void mergeSort(int[] arr, int l, int r) {
    if (l >= r) {
        return;
    }
    int mid = l + (r - l) / 2;
    mergeSort(arr, l, mid);
    mergeSort(arr, mid + 1, r);
    merge(arr, l, mid, r);
}

void merge(int[] arr, int l, int mid, int r) {
    int[] aux = new int[r - l + 1];
    int p1 = l, p2 = mid + 1;
    int k = 0;
    while (p1 <= mid && p2 <= r) {
        // 保证稳定性
        aux[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= mid) {
        aux[k++] = arr[p1++];
    }
    while (p2 <= r) {
        aux[k++] = arr[p2++];
    }
    for (int i = 0; i < k; ++i) {
        arr[l + i] = aux[i];
    }
}

/*****************自底向上*****************/

int[] mergeSortBU(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    // 区间个数，1..2..4..8
    for (int sz = 1; sz <= n; sz += sz) {
        // 对[i, i + sz - 1]和[i + sz, i + 2 * sz - 1]归并
        for (int i = 0; sz + i < n; i += sz + sz) {
            // min防数组越界
            merge(arr, i, i + sz - 1, Math.min(n - 1, i + 2 * sz - 1));
        }
    }
    return arr;
}

/*****************链表*****************/

ListNode mergeSort(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }
    ListNode pre = null, cur = head, next = head;
    // 链表分为两半
    while(next != null && next.next != null){
        pre = cur;
        cur = cur.next;
        next = next.next.next;
    }
    pre.next = null;
    // 对每一半分别排序
    ListNode l1 = mergeSort(head);
    ListNode l2 = mergeSort(cur);
    // 合并
    return merge(l1, l2);
}
/**
  * merge the two sorted linked list l1 and l2,
  * then append the merged sorted linked list to the dummy head
  * return the head of the merged sorted linked list
*/
ListNode merge(ListNode l1, ListNode l2){
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while(l1 != null && l2 != null){
        if(l1.val < l2.val){
            cur.next = l1;
            l1 = l1.next;
        }
        else{
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }
    cur.next = l1 == null ? l2 : l1;
    return dummy.next;
}

/*****************链表自底向上*****************/

ListNode mergeSort(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int len = 0;
    while (head != null) {
        head = head.next;
        ++len;
    }
    for (int step = 1; step < len; step <<= 1) {
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        while (cur != null) {
            ListNode left = cur;
            ListNode right = split(left, step);
            cur = split(right, step);
            // 拼接分组排序链表
            prev = merge(left, right, prev);
        } 
    }
    return dummy.next;
}
/**
  * Divide the linked list into two lists,
  * while the first list contains first n ndoes
  * return the second list's head
*/
ListNode split(ListNode head, int step) {
    if (head == null) {
        return null;
    }
    for (int i = 1; head.next != null && i < step; ++i) {
        head = head.next;
    }
    ListNode right = head.next;
    head.next = null;
    return right;
}

/**
  * merge the two sorted linked list left and right,
  * then append the merged sorted linked list to the node head
  * return the tail of the merged sorted linked list
*/
ListNode merge(ListNode left, ListNode right, ListNode prev) {
    ListNode cur = prev;
    while (left != null && right != null) {
        if (left.val < right.val) {
            cur.next = left;
            left = left.next;
        }
        else {
            cur.next = right;
            right = right.next;
        }
        cur = cur.next;
    }

    cur.next = left == null ? right : left;
    while (cur.next != null) {
        cur = cur.next;
    }
    return cur;
}
```

### 题目

#### 315. count-of-smaller-numbers-after-self

------

**Description:**

You are given an integer array *nums* and you have to return a new *counts* array. The *counts* array has the property where `counts[i]` is the number of smaller elements to the right of `nums[i]`.

**Example:**

```
Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
```

[Discussion](https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/count-of-smaller-numbers-after-self/solution/)

**Code:**

```java
import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 *
 * 在归并过程中统计逆序数
 *
 */

// @lc code=start
class Solution {

    // 绑定最初索引
    class Pair {
        int index;
        int val;
        public Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int[] smaller = new int[nums.length];
        Pair[] arr = new Pair[nums.length];
        for (int i = 0; i < smaller.length; i++) {
            arr[i] = new Pair(i, nums[i]);
        }
        mergeSort(arr, 0, nums.length - 1, smaller);
        for (int num : smaller) {
            res.add(num);
        }
        return res;
    }
    
    private void mergeSort(Pair[] arr, int l, int r, int[] smaller) {
        if(l >= r) {
            return;
        }
        int mid = l + (r - l) / 2; 
        mergeSort(arr, l, mid, smaller);
        mergeSort(arr, mid + 1, r, smaller); 
        for(int i = l, j = mid + 1; i <= mid; ++i){
            while(j <= r && arr[i].val > arr[j].val) {
                ++j; 
            }
            smaller[arr[i].index] += j - (mid + 1); 
        }
        merge(arr, l, mid, r);
    }

    private void merge(Pair[] arr, int l, int mid, int r) {
        Pair[] aux = new Pair[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int k = 0;
        while (p1 <= mid && p2 <= r) {
            // 保证稳定性
            aux[k++] = arr[p1].val <= arr[p2].val ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            aux[k++] = arr[p1++];
        }
        while (p2 <= r) {
            aux[k++] = arr[p2++];
        }
        for (int i = 0; i < k; ++i) {
            arr[l + i] = aux[i];
        }
    }
}
// @lc code=end
```

#### 493. reverse-pairs

------

**Description:**

Given an array `nums`, we call `(i, j)` an **important reverse pair** if `i < j` and `nums[i] > 2*nums[j]`.

You need to return the number of important reverse pairs in the given array.

**Example 1:**

```
Input: [1,3,2,3,1]
Output: 2
```

**Example 2:**

```
Input: [2,4,3,5,1]
Output: 3
```

**Note:**

1. The length of the given array will not exceed `50,000`.
2. All the numbers in the input array are in the range of 32-bit integer.

[Discussion](https://leetcode.com/problems/reverse-pairs/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/reverse-pairs/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=493 lang=java
 *
 * [493] Reverse Pairs
 *
 * 归并过程中统计
 *
 */

// @lc code=start
class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }
    private int mergeSort(int[] arr, int l, int r){
        if(l >= r) {
            return 0;
        } 
        int mid = l + (r - l) / 2; 
        int cnt = mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r); 
        for(int i = l, j = mid + 1; i <= mid; ++i){
            while(j <= r && arr[i] / 2.0 > arr[j]) {
                ++j; 
            }
            cnt += j - (mid + 1); 
        }
        merge(arr, l, mid, r);
        return cnt; 
    }
    private void merge(int[] arr, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        int p1 = l, p2 = mid + 1;
        int k = 0;
        while (p1 <= mid && p2 <= r) {
            // 保证稳定性
            aux[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            aux[k++] = arr[p1++];
        }
        while (p2 <= r) {
            aux[k++] = arr[p2++];
        }
        for (int i = 0; i < k; ++i) {
            arr[l + i] = aux[i];
        }
    }
}
// @lc code=end
```



## 6. 快速排序

### 思想

在序列中选一个元素为基准值(pivot)，将所有元素比pivot小的放在前面，比pivot大的放在后面，pivot放中间(partition 操作)，递归地进行排序。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

int[] qucikSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    quickSort(arr, 0, n - 1);
    return arr;
}

void quickSort(int[] arr, int l, int r) {
    if (l >= r) {
        return;
    }
    // 随机选择pivot，防止退化为O(n²)
    swap(arr, l, l + (int)(Math.random() * (r - l + 1)));
    int[] p = partition(arr, l, r);
    quickSort(arr, l, p[0]);
    quickSort(arr, p[1], r);
}

int[] partition(int[] arr, int l, int r) {
    // 用arr[l]作为划分点
    int val = arr[l];
    int start = l, end = r + 1;
    int cur = l + 1;
    // 交换导致的不稳定性
    while (cur < end) {
        if (arr[cur] < val) {
            swap(arr, ++start, cur++);
        } else if (arr[cur] > val) {
            swap(arr, --end, cur);
        } else {
            cur++;
        }
    }
    swap(arr, l, start);
    // 返回下次开始的位置，一左一右
    return new int[]{start - 1, end};
}

void shuffle(int arr[]) {
    final Random random = new Random();
    for (int idx = 1; idx < arr.length; ++idx) {
        final int r = random.nextInt(idx + 1);
        swap(arr, idx, r);
    }
}

/*****************链表*****************/

ListNode quickSort(ListNode head){
    if(head == null || head.next == null) {
		return head;
    }
    
    // 划分为三个子序列
    ListNode fakesmall = new ListNode(0), small = fakesmall;
    ListNode fakelarge = new ListNode(0), large = fakelarge;
    ListNode fakeequal = new ListNode(0), equal = fakeequal;
    // pivot
    ListNode cur = head;
    while(cur != null){
        if(cur.val < head.val){
            small.next = cur;
            small = small.next;
        }
        else if(cur.val == head.val){
            equal.next = cur;
            equal = equal.next;
        }
        else{
            large.next = cur;
            large = large.next;
        }
        cur = cur.next;
    }
    
    // put an end.
    small.next = equal.next = large.next = null;
    // merge them and return.
    return merge(merge(quickSort(fakesmall.next), quickSort(fakelarge.next)),fakeequal.next) ;
}

ListNode merge(ListNode l1, ListNode l2){
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while(l1 != null && l2 != null){
        if(l1.val < l2.val){
            cur.next = l1;
            l1 = l1.next;
        }
        else{
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }
    cur.next = l1 == null ? l2 : l1;
    return dummy.next;
}
```

### 题目

#### 75. sort-colors

------

**Description:**

Given an array with *n* objects colored red, white or blue, sort them **[in-place](https://en.wikipedia.org/wiki/In-place_algorithm)** so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

**Note:** You are not suppose to use the library's sort function for this problem.

**Example:**

```
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```

**Follow up:**

- A rather straight forward solution is a two-pass algorithm using counting sort.
  First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
- Could you come up with a one-pass algorithm using only constant space?

[Discussion](https://leetcode.com/problems/sort-colors/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/sort-colors/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */

// @lc code=start
class Solution {
    public void sortColors(int[] nums) {
        // 三路快排
        if (nums == null || nums.length == 0) {
            return;
        }
        // [0, zero]:0  (zero, one]:1  [two, nums.length - 1]:2
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 1) {
                one++;
            } else if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end
```

#### 215. kth-largest-element-in-an-array

------

**Description:**

Find the **k**th largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

**Example 1:**

```
Input: [3,2,1,5,6,4] and k = 2
Output: 5
```

**Example 2:**

```
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
```

**Note:** 
You may assume k is always valid, 1 ≤ k ≤ array's length.

[Discussion](https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/kth-largest-element-in-an-array/solution/)

**Code:**

```java
import java.util.Random;

/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    private int quickSelect(int[] nums, int l, int r, int k){
        int pivot = l;
        for(int i = l; i < r; i++){
            if(nums[i] <= nums[r]){
                swap(nums, i, pivot++);
            }
        }
        swap(nums, pivot, r);
        int count = r - pivot + 1;
        if(count == k) {
            return nums[pivot];
        }
        else if(count < k) {
            return quickSelect(nums, l, pivot - 1, k - count);
        }
        else {
            return quickSelect(nums, pivot + 1, r, k);
        }
    }

    private void shuffle(int arr[]) {
        final Random random = new Random();
        for (int idx = 1; idx < arr.length; ++idx) {
            final int r = random.nextInt(idx + 1);
            swap(arr, idx, r);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end
```



## 7. 堆排序

### 思想

将待排序序列构造成一个大顶堆，每次移走堆顶，重复n-1次建堆，完成排序。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

// 大顶堆，每个结点的值都大于等于其左右孩子结点的值
int[] heapSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
        return arr;
    }
    int n = arr.length;
    //上浮方式建堆
    for (int i = 0; i < arr.length; i++) {
        siftUp(arr, i);
    }
    int size = n - 1;
    swap(arr, 0, size);
    while (size > 0) {
        siftDown(arr, 0, size);
        swap(arr, 0, --size);
    }
    return arr;
}

// 上浮
void siftUp(int[] arr, int i) {
    // 当前结点为i，父亲结点为(i-1)/2
    while (arr[i] > arr[(i - 1) / 2]) {
        swap(arr, i, (i - 1) / 2);
        i = (i - 1) / 2;
    }
}

// 下沉
private void siftDown(int[] arr, int i, int heapSize) {
    // 父亲结点为i，左孩子结点为2*i+1，右孩子结点为2*i+2
    int l = 2 * i + 1;
    // 每次保证堆的性质
    while (l < heapSize) {
        int maxIndex = l + 1 < heapSize && arr[l + 1] > arr[l] ? l + 1 : l;
        maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        if (maxIndex == i) {
            break;
        }
        swap(arr, i, maxIndex);
        i = maxIndex;
        l = 2 * i + 1;
    }
}

/*****************heapfiy优化*****************/

int[] heapSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
        return arr;
    }
    int n = arr.length;
    int size = n - 1;
    for (int i = (size - 1) / 2; i >= 0; --i) {
        // 注意这儿是n，因为还没有swap
        siftDown(arr, i, n);
    }
    swap(arr, 0, size);
    while (size > 0) {
        siftDown(arr, 0, size);
        swap(arr, 0, --size);
    }
    return arr;
}

void siftDown(int[] arr, int i, int heapSize) {
    //从arr[i] 开始往下调整
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    int maxIdx = i;
    if (l < heapSize && arr[l] > arr[maxIdx]) {
        maxIdx = l;
    }
    if (r < heapSize && arr[r] > arr[maxIdx]) {
        maxIdx = r;
    }
    if (maxIdx != i) {
        swap(arr, i, maxIdx);
        siftDown2(arr, maxIdx, heapSize);
    }
}
```



## 8. 计数排序

**计数排序是桶排序的一种特殊情况，**适用于将要排序的n个数据的数据范围不大的情形，且不适用于有负数的情形。

### 思想

先获取序列A中最小值min和最大值max，再开辟长度为(max - min + 1)的数组B，其中B中index元素记录的值是A中index出现的次数，累加数组B(其中B[index]存储小于等于index的元素个数)，最后从后往前遍历序列A，将其放到辅助数组aux相应位置。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @return sorted array of {@code arr}.
 */

// 常用
int[] countSort1(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int min = arr[0], max = arr[0];
    // 最大最小值
	for (int i = 1; i < n; ++i) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
    }
    // 计数数组
    int[] count = new int[max - min + 1];
    // 辅助数组
    int[] aux = new int[n];
    for (int num : arr) {
        count[num - min]++;
    }
    int index = 0;
    // 累加，count[i]存储小于等于i的元素个数
    for (int i = 1; i < count.length; ++i) {
        count[i] += count[i - 1];
    }
    // 关键步骤，自己该放置在哪个位置
    for (int i = arr.length - 1; i >= 0; --i) {
        aux[--count[arr[i] - min]] = arr[i];
    }
    return aux;
}


int[] countSort2(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int min = arr[0], max = arr[0];
    // 最大最小值
	for (int i = 1; i < n; ++i) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
    }
    // 计数数组
    int[] count = new int[max - min + 1];
    for (int num : arr) {
        count[num - min]++;
    }
    int index = 0;
    // 遍历输出
    for (int i = 0; i < count.length; ++i) {
        while (count[i] > 0) {
            arr[index++] = i + min;
            count[i]--;
        }
    }
    return arr;
}
```

### 题目

#### 274. h-index

------

**Description:**

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the [definition of h-index on Wikipedia](https://en.wikipedia.org/wiki/H-index): "A scientist has index *h* if *h* of his/her *N* papers have **at least** *h* citations each, and the other *N − h* papers have **no more than** *h* citations each."

**Example:**

```
Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
```

**Note:** If there are several possible values for *h*, the maximum one is taken as the h-index.

[Discussion](https://leetcode.com/problems/h-index/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/h-index/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 *
 * 对出现的引用次数进行计数，返回结果至多为数组长度n
 *
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] counts = new int[n + 1];
        for (int num : citations) {
            if (num >= n) {
                counts[n]++;
            } else {
                counts[num]++;
            }
        }
        int cnt = 0;
        // 控制h of papers至少h citations
        for (int i = n; i >= 0; --i) {
            cnt += counts[i];
            if (cnt >= i) {
                return i;
            }
        }
        return 0;
    }
}
// @lc code=end
```



## 9. 桶排序

**桶排序适用于外部排序中**。所谓的外部排序就是数据存储在外部磁盘中，数据量比较大，内存有限，无法将数据全部加载到内存中。

**桶排序对排序的数据要求非常苛刻**。需要足够均匀，否则桶内排序就不是常量了。

### 思想

先设置固定数量的空桶，再把数据放到对应的桶中，对每个不为空的桶中数据进行排序，最后拼接不为空的桶中数据，输出结果。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @param bucketCount numbers of buckets.
 * @return sorted array of {@code arr}.
 */

int[] bucketSort(int[] arr, int bucketCount) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int[] res = new int[n];
    int min = arr[0], max = arr[0];
    // 最大最小值
    for (int i = 1; i < n; ++i) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
    }
    // 桶容量
    int gap = (int)Math.ceil((double)(max - min) / bucketCount);
    List[] buckets = new ArrayList[bucketCount];
    // 元素放入相应桶中
    for (int i = 0; i < n; ++i) {
        int idx = (arr[i] - min) / gap;
        if (buckets[idx] == null) {
            buckets[idx] = new ArrayList<>();
        }
        buckets[idx].add(arr[i]);
    }
    int k = 0;
    for (int i = 0; i < bucketCount; ++i) {
        if (buckets[i] == null) {
                continue;
            }
        // 集合排序
        Collections.sort(buckets[i]);
        for (int j = 0; j < buckets[i].size(); ++j) {
            res[k++] = (int)buckets[i].get(j);
        }
    }
    return res;
}
```

### 题目

#### 164. maximum-gap

------

**Description:**

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

**Example 1:**

```
Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
```

**Example 2:**

```
Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
```

**Note:**

- You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
- Try to solve it in linear time/space.

[Discussion](https://leetcode.com/problems/maximum-gap/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/maximum-gap/solution/)

**Code:**

```java
import java.util.Arrays;

/*
 * @lc app=leetcode id=164 lang=java
 *
 * [164] Maximum Gap
 * 
 * 假设数组中有N个元素，最大值为max，最小值为min，则在排序数组中相邻元素最大间隔不超过ceiling[(max - min) / (N - 1)]
 * 
 */

// @lc code=start
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int min = nums[0], max = nums[0];
        for (int i = 1; i < n; ++i) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        // 桶容量
        int gap = (int)Math.ceil((double)(max - min) / (n - 1));
        int[] bucketsMin = new int[n - 1];
        int[] bucketsMax = new int[n - 1];
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);
        // 存储每个桶中最大值、最小值
        for (int num : nums) {
            if (num == min || num == max) {
                continue;
            }
            int idx = (num - min) / gap;
            bucketsMin[idx] = Math.min(bucketsMin[idx], num);
            bucketsMax[idx] = Math.max(bucketsMax[idx], num);
        }
        int res = Integer.MIN_VALUE;
        int preMax = min;
        // 遍历桶，计算出最大间隔
        for (int i = 0; i < n - 1; ++i) {
            if (bucketsMin[i] == Integer.MAX_VALUE && bucketsMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            res = Math.max(res, bucketsMin[i] - preMax);
            preMax = bucketsMax[i];
        }
        res = Math.max(res, max - preMax);
        return res;
    }
}
// @lc code=end
```

#### 220. contains-duplicate-iii

------

**Description:**

Given an array of integers, find out whether there are two distinct indices *i* and *j* in the array such that the **absolute** difference between **nums[i]** and **nums[j]** is at most *t* and the **absolute** difference between *i* and *j* is at most *k*.

**Example 1:**

```
Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
```

**Example 2:**

```
Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
```

**Example 3:**

```
Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
```

[Discussion](https://leetcode.com/problems/contains-duplicate-iii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/contains-duplicate-iii/solution/)

**Code:**

```java
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=220 lang=java
 *
 * [220] Contains Duplicate III
 *
 * 控制映射到同一个桶中不同元素的差值不超过t，同时桶的数量不超过k(移除最早进桶元素)
 *
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
```



## 10. 基数排序

**基数排序要求数据可以分割出独立的“位”来比较，**而且位之间有递进的关系，即若数据a的高位比数据b的高位大，那剩下的低位就不用比较了。

**基数排序要求每一位数据范围不能太大，**要可以用线性算法来排序。

### 思想

将待比较元素补齐为统一长度，从最低位开始依次进行一次排序，当最高位排序完成后，序列就成了有序序列。

### 模板

```java
/**
 * Return the sorted array of {@code arr}.
 * 
 * @param arr the array to sort.
 * @param len max length of array's element.
 * @return sorted array of {@code arr}.
 */

int[] redixSort(int[] arr, int len) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int exp = 10, R = 10;
    for (int i = 0; i < len; ++i) {
        List[] digits = new ArrayList[R * 2];
        for (int j = 0; j < n; ++j) {
            // 特定位上的值
            int bucket = (arr[j] / exp) % 10 + R;
            if (digits[bucket] == null) {
                digits[bucket] = new ArrayList();
            }
            digits[bucket].add(arr[j]);
        }
        int index = 0;
        // 完成一次排序后拷贝
        for (int k = 0; k < digits.length; ++k) {
            if (digits[k] == null) {
                continue;
            }
            for (int l = 0; l < digits[k].size(); ++l) {
                arr[index++] = (int)digits[k].get(l);
            }
        }
        exp *= 10;
    }
    return arr;
}
```

### 题目

#### 164. maximum-gap

------

**Code:**

```java
/*
 * @lc app=leetcode id=164 lang=java
 *
 * [164] Maximum Gap
 * 
 * 基数排序 + 计数排序
 * 
 */

// @lc code=start
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int m = nums[0];
        for (int i = 1; i < n; ++i) {
            m = Math.max(m, nums[i]);
        }
        int exp = 1;
        int R = 10;
        int[] aux = new int[n];
        // 从低位到高位
        while (m / exp > 0) {
            int[] count = new int[R];
            // 计数排序
            for (int i = 0; i < n; ++i) {
                count[(nums[i] / exp) % 10]++;
            }
            for (int i = 1; i < R; ++i) {
                count[i] += count[i - 1];
            }
            for (int i = n - 1; i >= 0; --i) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }
            for (int i = 0; i < n; ++i) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }
        int max = 0;
        for (int i = 1; i < n; ++i) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }
        return max;
    }
}
// @lc code=end
```



## 参考

[数据结构可视化](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html)

[冒泡、选择、插入](https://aleej.com/2019/10/14/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95%E4%B9%8B%E7%BE%8E%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0/)

[归并、快排](https://aleej.com/2019/10/16/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95%E4%B9%8B%E7%BE%8E%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0/)

 [计数、桶、基数](https://aleej.com/2019/10/22/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%B8%8E%E7%AE%97%E6%B3%95%E4%B9%8B%E7%BE%8E%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0/)

[十大排序算法](https://mp.weixin.qq.com/s/vn3KiV-ez79FmbZ36SX9lg)