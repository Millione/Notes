# 最短路径问题

## Dijkstra



## Floyd

> Floyd-Warshall 算法（Floyd-Warshall algorithm）是解决任意两点间的最短路径的一种算法，可以正确处理有向图或负权的最短路径问题，同时也被用于计算有向图的传递闭包。

Floyd-Warshall 算法的时间复杂度为$O(N^3)$，空间复杂度为$O(N^2)$。

### 模板

```java
int[][] dist = new int[n][n];
int[][] path = new int[n][n];
// 初始化
for (int i = 0; i < n; i++) {
    Arrays.fill(dist[i], Integer.MAX_VALUE);
    Arrays.fill(path[i], -1);
}
for (int i = 0; i < n; i++) {
    dist[i][i] = 0;
    for (int j : graph[i]) {
        // 权值
        dist[i][j] = graph.getWeight(i, j);
    }
}
// 转移
for (int k = 0; k < n; k++) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                path[i][j] = k;
                dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }
}

// 检测负权环
for (int i = 0; i < n; i++) {
    if (dist[i][i] < 0) {
        hasNegCycle = true;
    }
}

// 求路径
if (path[i][j] != -1) {
	sb.append(path[i][j]);
    getPath(path[i][j], j, sb);
}
```

### 题目

#### 1462. course-schedule-iv

**Description:**

There are a total of `n` courses you have to take, labeled from `0` to `n-1`.

Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: `[1,0]`

Given the total number of courses `n`, a list of direct `prerequisite` **pairs** and a list of `queries` **pairs**.

You should answer for each `queries[i]` whether the course `queries[i][0]` is a prerequisite of the course `queries[i][1]` or not.

Return *a list of boolean*, the answers to the given `queries`.

Please note that if course **a** is a prerequisite of course **b** and course **b** is a prerequisite of course **c**, then, course **a** is a prerequisite of course **c**.

**Example 1:**

![img](https://assets.leetcode.com/uploads/2020/04/17/graph.png)

```
Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
```

**Example 2:**

```
Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites and each course is independent.
```

**Example 3:**

![img](https://assets.leetcode.com/uploads/2020/04/17/graph-1.png)

```
Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
```

**Example 4:**

```
Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
Output: [false,true]
```

**Example 5:**

```
Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
Output: [true,false,true,false]
```

**Constraints:**

- `2 <= n <= 100`
- `0 <= prerequisite.length <= (n * (n - 1) / 2)`
- `0 <= prerequisite[i][0], prerequisite[i][1] < n`
- `prerequisite[i][0] != prerequisite[i][1]`
- The prerequisites graph has no cycles.
- The prerequisites graph has no repeated edges.
- `1 <= queries.length <= 10^4`
- `queries[i][0] != queries[i][1]`

**Code:**

```java
class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] p, int[][] q) {
        List<Boolean> res = new ArrayList<>();
        boolean[][] dp = new boolean[n][n];
        for (int[] t : p) {
            dp[t[0]][t[1]] = true;
        }
    
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = dp[i][j] || dp[i][k] && dp[k][j];
                }
            }
        }

        for (int[] t : q) {
            res.add(dp[t[0]][t[1]]);
        }
        return res;
    }
}
```

## Bellman-Ford



## SPFA



## 参考

[Floyd Dijkstra Bellman-Ford SPFA](https://zhuanlan.zhihu.com/p/33162490)