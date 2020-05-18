# 树状数组

> 树状数组是一种用于高效处理对一个存储数字的列表进行更新及求前缀和的数据结构。

树状数组是一个查询和修改复杂度都为$log(n)$的数据结构。主要用于查询任意两位之间的所有元素之和，但是每次只能修改一个元素的值；经过简单修改可以在log(n)的复杂度下进行范围修改，但是这时只能查询其中一个元素的值。

![img](https://oi-wiki.org/ds/images/fenwick1.png)

## 模板

### 1. 单点修改、区间查询

```java
class BinaryIndexedTree{
    
    private int[] sums;
    private int n;
    // 数组array下标从1开始
    public BinaryIndexedTree(int[] array) {
        n = array.length - 1;
        sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] += array[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums[j] += sums[i];
            }
        }
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    public void update(int x, int add) {
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }
    
    public int queryRange(int x, int y) {
        return query(y) - query(x - 1);
    }
    
}
```

### 2. 区间修改、单点查询

```java
class BinaryIndexedTree{
    
    private int[] sums;
    private int n;
    // 传入的是差分数组
    public BinaryIndexedTree(int[] array) {
        n = array.length - 1;
        sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] += array[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums[j] += sums[i];
            }
        }
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    public void update(int x, int add) {
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    public void updateRange(int x, int y, int add) {
        update(x, add);
        update(y + 1, -add);
    }
    
    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }
    
}
```

### 3. 区间修改、区间查询

```java
class BinaryIndexedTree{

    private long[] sums1;
    private long[] sums2;
    private int n;

    public BinaryIndexedTree(int[] array) {
        n = array.length - 1;
        sums1 = new long[n + 1];
        sums2 = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sums1[i] += array[i];
            sums2[i] += i * array[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums1[j] += sums1[i];
                sums2[j] += sums2[i];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, long add) {
        long add1 = x * add;
        while (x <= n) {
            sums1[x] += add;
            sums2[x] += add1;
            x += lowbit(x);
        }
    }

    public long query(int x) {
        long ret = 0;
        long x1 = x;
        while (x != 0) {
            ret += sums1[x] * (x1 + 1);
            ret -= sums2[x];
            x -= lowbit(x);
        }
        return ret;
    }

    public void updateRange(int x, int y, long add) {
        update(x, add);
        update(y + 1, -add);
    }

    public long queryRange(int x, int y) {
        return query(y) - query(x - 1);
    }

}
```

## 技巧

### 1. 建树

```java
// O(n)建树
public void init() {
    for (int i = 1; i <= n; ++i) {
    	sums[i] += array[i];
    	int j = i + lowbit(i);
    	if (j <= n) {
    		sums[j] += sums[i];
    	}
    }
}
```

### 2. 时间戳优化

```java
private int[] tag;
private int Tag;

public void reset() {
    ++Tag;
}
public void update(int x, int add) {
    while (x <= n) {
        if (tag[k] != Tag) {
            sums[x] = 0;
        }
        sums[x] += add;
        tag[x] = Tag;
        x += lowbit(x);
    }
}
    
public int query(int x) {
    int ret = 0;
    while (x != 0) {
        if (tag[x] == Tag) {
            ret += sums[x];
        }
        x -= lowbit(x);
    }
    return ret;
}
```

### 3. 二维树状

```java
public void update(int x,int y,int add) {
	for (int i = x; i <= n; i += lowbit(i)) {
        for (int j = y; j <= n; j += lowbit(j)) {
            sums[i][j] += add;
        }
    }
}

public int query(int x, int y) {
    int ret = 0;
    for (int i = x; i > 0; i -= lowbit(i)) {
        for (int j = y; j > 0; j -= lowbit(j)) {
            ret += sums[i][j];
        }
    }
    return ret;
}
```

## 题目

### 307. range-sum-query-mutable

------

**Description:**

Given an integer array *nums*, find the sum of the elements between indices *i* and *j* (*i* ≤ *j*), inclusive.

The *update(i, val)* function modifies *nums* by updating the element at index *i* to *val*.

**Example:**

```
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
```

**Note:**

1. The array is only modifiable by the *update* function.
2. You may assume the number of calls to *update* and *sumRange* function is distributed evenly.

[Discussion](https://leetcode.com/problems/range-sum-query-mutable/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/range-sum-query-mutable/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=307 lang=java
 *
 * [307] Range Sum Query - Mutable
 */

// @lc code=start
class NumArray {

    private int[] sums;
    private int[] nums;
    private int n;

    public NumArray(int[] array) {
        n = array.length;
        nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = array[i - 1];
        }
        sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] += nums[i];
            int j = i + lowbit(i);
            if (j <= n) {
                sums[j] += sums[i];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, int val) {
        x++;
        int add = val - nums[x];
        nums[x] = val;
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    public int sumRange(int x, int y) {
        x++;
        y++;
        return query(y) - query(x - 1);
    }

    private int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
// @lc code=end
```

### 315. count-of-smaller-numbers after self

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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 */

// @lc code=start
class Solution {

    class Node {
        int pos;
        int val;
        public Node(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }

    private int[] sums;
    private int n;

    public List<Integer> countSmaller(int[] nums) {
        n = nums.length;
        List<Integer> res = new ArrayList<>();
        sums = new int[n + 1];
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i, nums[i - 1]);
        }
        Arrays.sort(nodes, 1, n + 1, new Comparator<Node>() {

            public int compare(Node o1, Node o2) {
                if (o1.val == o2.val) {
                    // o2.pos - o1.pos 可解决此题小于等于的情况
                    return o1.pos - o2.pos;
                }
                return o1.val - o2.val;
            }

        });
        int[] ref = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ref[nodes[i].pos] = i;
        }
        int[] ans = new int[n];
        for (int i = n; i > 0; i--) {
            ans[i - 1] = query(ref[i]);
            update(ref[i], 1);
        }
        for (int num : ans) {
            res.add(num);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & (-x);
    }
    
    private void update(int x, int add) {
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    private int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }

}
// @lc code=end
```

### 327. count-of-range-sum

------

**Description:**

Given an integer array `nums`, return the number of range sums that lie in `[lower, upper]` inclusive.
Range sum `S(i, j)` is defined as the sum of the elements in `nums` between indices `i` and `j` (`i` ≤ `j`), inclusive.

**Note:**
A naive algorithm of *O*(*n*2) is trivial. You MUST do better than that.

**Example:**

```
Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3 
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
```

[Discussion](https://leetcode.com/problems/count-of-range-sum/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/count-of-range-sum/solution/)

**Code:**

```java

```

## 参考

[“高级”数据结构——树状数组！](https://www.cnblogs.com/RabbitHu/p/BIT.html)

[树状数组详细解析](http://www.cecdns.com/post/5784.html)

[树状数组黑科技讲义](https://magolor.cn/2018/08/27/2018-08-27-blog-01/#%E5%89%8D%E8%A8%80)

[树状数组](https://oi-wiki.org/ds/fenwick/)