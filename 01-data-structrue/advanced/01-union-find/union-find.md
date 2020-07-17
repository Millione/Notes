# 并查集

> 朋友的朋友就是朋友。

并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的 **合并** 及 **查询** 问题。 它支持两种操作：

- 查找（Find）：确定某个元素处于哪个子集；
- 合并（Union）：将两个子集合并成一个集合。

## 模板

```java
class UnionFind {
    private int[] parent;
    private int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int size() {
        return parent.length;
    }
    
    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[x] = y;
            if (rank[x] == rank[y]) {
                rank[y]++;
            }
        }
        return true;
    }
    
    public boolean same(int x, int y) {
        return find(x) == find(y);
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}
```

## 题目

### 128. longest-consecutive-sequence

------

**Description:**

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(*n*) complexity.

**Example:**

```
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
```

**Code:**

```java
class Solution {
    Map<Integer, Integer> map;
    public int longestConsecutive(int[] nums) {
        map = new HashMap<>();
        for (int num : nums) {
            map.put(num, num + 1);
        }
        int ans = 0;
        for (int num : nums) {
            int ret = find(num + 1);
            ans = Math.max(ans, ret - num);
        }
        return ans;
    }
    private int find(int x) {
        if (map.containsKey(x)) {
            map.put(x, find(map.get(x)));
            return map.get(x);
        } else {
            return x;
        }
    }
}
```

### 684. redundant-connection

------

**Description:**

In this problem, a tree is an **undirected** graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of `edges`. Each element of `edges` is a pair `[u, v]` with `u < v`, that represents an **undirected** edge connecting nodes `u`and `v`.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge `[u, v]` should be in the same format, with `u < v`.

**Example 1:**

```
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
```

**Example 2:**

```
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
```

**Note:**

The size of the input 2D-array will be between 3 and 1000.

Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

[Discussion](https://leetcode.com/problems/redundant-connection/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/redundant-connection/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=684 lang=java
 *
 * [684] Redundant Connection
 */

// @lc code=start
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length + 1);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        throw new IllegalArgumentException();
    }

    class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int size() {
            return parent.length;
        }
        
        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            }
            return true;
        }
        
        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
// @lc code=end
```

### 685. redudant-connection-ii

------

**Description:**

In this problem, a rooted tree is a **directed** graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of `edges`. Each element of `edges` is a pair `[u, v]` that represents a **directed** edge connecting nodes `u` and `v`, where `u` is a parent of child `v`.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

**Example 1:**

```
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
```

**Example 2:**

```
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
```

**Note:**

The size of the input 2D-array will be between 3 and 1000.

Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

[Discussion](https://leetcode.com/problems/redundant-connection-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/redundant-connection-ii/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
 */

// @lc code=start
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length, edgeRemoved = -1, edgeCycle = -1;
        int[] parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parent[v] != 0) {
                edgeRemoved = i;
                break;
            } else {
                parent[v] = u;
            }
        }
        UnionFind uf = new UnionFind(n + 1);
        for (int i = 0; i < n; i++) {
            if (edgeRemoved == i) {
                continue;
            }
            int u = edges[i][0];
            int v = edges[i][1];
            if (!uf.union(u, v)) {
                edgeCycle = i;
                break;
            }
        }
        /* Handle with the cyclic problem only. */
        if (edgeRemoved == -1) {
            return edges[edgeCycle];
        }
        
        /* Handle with the cyclic problem when we remove the wrong edge. */
        if (edgeCycle != -1) { 
            int v = edges[edgeRemoved][1];
            int u = parent[v];
            return new int[]{u, v};
        } 
        
        /* CHandle with the cyclic problem when we remove the right edge. */
        return edges[edgeRemoved];
    }

    class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int size() {
            return parent.length;
        }
        
        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            }
            return true;
        }
        
        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
// @lc code=end
```

## 参考

[DSU](https://oi-wiki.org/ds/dsu/)