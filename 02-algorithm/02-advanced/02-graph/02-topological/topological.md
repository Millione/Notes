# 拓扑排序

>  在一个有向无环图中，我们将图中的顶点以线性方式进行排序，使得对于任何的顶点$u$到$v$的有向边 , 都可以有$u$在$v$的前面。

## 模板

```java
// bfs
class TopologySort{
   
    private ArrayList<Integer>[] G;
    // 入度数组
    private int[] deg;
    // 顶点数、边数
    private int n, m;
    
    public void topoSort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (deg[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int i = 0; i < G[u].size(); i++) {
                int v = G[u].get(i);
                if (--deg[v] == 0) {
                    queue.add(v);
                }
            }
        }
    }
    
}

// dfs
class TopologySort{
   
	private ArrayList<Integer>[] G;
    // 0:未访问 1:已访问 2:访问中
	private int[] vis;
    // 顶点数、边数
    private int n, m;

    public boolean dfs(int u) {
        vis[u] = 2;
        for (int v : G[u]) {
            if (vis[v] == 2 || (vis[v] == 0 && !dfs(v))) {
                return false;
            }
        }
        vis[u] = 1;
        return true;
    }
    
}
```

## 题目

### 207. course-schedule

------

**Description:**

There are a total of *n* courses you have to take, labeled from `0` to `n-1`.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: `[0,1]`

Given the total number of courses and a list of prerequisite **pairs**, is it possible for you to finish all courses?

**Example 1:**

```
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
```

**Example 2:**

```
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
```

**Note:**

1. The input prerequisites is a graph represented by **a list of edges**, not adjacency matrices. Read more about [how a graph is represented](https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs).
2. You may assume that there are no duplicate edges in the input prerequisites.

[Discussion](https://leetcode.com/problems/course-schedule/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/course-schedule/solution/)

**Code:**

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 */

// @lc code=start
class Solution {
    public boolean canFinish(int n, int[][] p) {
        List<Integer>[] g = new ArrayList[n];
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] t : p) {
            g[t[1]].add(t[0]);
            deg[t[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                queue.offer(i);
            }
        }
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int cur = queue.poll();
            for (int next : g[cur]) {
                if (--deg[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return cnt == n;
    }
}
// @lc code=end
```

### 210. course-schedule-ii

------

**Description:**

There are a total of *n* courses you have to take, labeled from `0` to `n-1`.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: `[0,1]`

Given the total number of courses and a list of prerequisite **pairs**, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

**Example 1:**

```
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
```

**Example 2:**

```
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
```

**Note:**

1. The input prerequisites is a graph represented by **a list of edges**, not adjacency matrices. Read more about [how a graph is represented](https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs).
2. You may assume that there are no duplicate edges in the input prerequisites.

[Discussion](https://leetcode.com/problems/course-schedule-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/course-schedule-ii/solution/)

**Code:**

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 */

// @lc code=start
class Solution {
    public int[] findOrder(int n, int[][] p) {
        List<Integer>[] g = new ArrayList[n];
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] t : p) {
            g[t[1]].add(t[0]);
            deg[t[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[n];
        int cnt = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (++cnt >= n) {
                return new int[0];
            }
            res[cnt] = cur;
            for (int next : g[cur]) {
                if (--deg[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (cnt != n - 1) {
            return new int[0];
        }
        return res;
    }
}
// @lc code=end
```

