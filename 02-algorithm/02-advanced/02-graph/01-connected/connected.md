# 图的连通性

在图$G$中，两个不同的结点$u$和结点$v$之间若存在一条路，则称结点u和结点v是连通的。

## 强连通分量

针对于**有向图**，在这儿先明确两个定义：强连通和强连通分量。强连通是指有向图$G$中任意两个结点连通。强连通分量是极大的强连通子图。

应用场合：有向图、有两两可达条件、通过把每个强连通分量缩点从而把原图化简成一棵树。

### 模板

```java
class Tarjan {
    
    private int V;
    private List<Integer>[] G;
    private List<List<Integer>> res;
    private boolean[] inStack;
    private Deque<Integer> stack;
    // dfn[u]：深度优先搜索遍历时结点u被搜索的次序
    // low[u]：以u为根的子树中的结点的dfn的最小值
    private int[] dfn;
    private int[] low;
    private int[] sccBelong;
    private int[] sccSz;
    private int dfsCnt;
    private int sccCnt;

    public Tarjan(List<Integer>[] G, int V) {
        this.G = G;
        this.V = V;
        stack = new ArrayDeque<>();
        inStack = new boolean[V];
        dfn = new int[V];
        low = new int[V];
        sccBelong = new int[V];
        sccSz = new int[V];
        dfsCnt = sccCnt = 0;
        res = new ArrayList<>();
    }

    public List<List<Integer>> findScc() {
        // 从下标索引1开始
        for (int i = 1; i < V; i++) {
            if (dfn[i] == 0) {
                dfs(i);
            }
        }
        return res;
    }

    private void dfs(int u) {
        dfn[u] = low[u] = ++dfsCnt;
        inStack[u] = true;
        stack.push(u);
        for (int v : G[u]) {
            if (dfn[v] == 0) {
                dfs(v);
                if (low[v] < low[u]) {
                    low[u] = low[v];
                }
            } else if (inStack[v] && dfn[v] < dfn[u]) {
                low[u] = dfn[v];
            }
        }
        if (dfn[u] == low[u]) {
            List<Integer> tmp = new ArrayList<>();
            sccCnt++;
            for (; ; ) {
                int v = stack.pop();
                inStack[v] = false;
                sccBelong[v] = sccCnt;
                sccSz[sccCnt]++;
                tmp.add(v);
                if (v == u) {
                    break;
                }
            }
            res.add(tmp);
        }
    }

}
```

### 题目

[受欢迎的牛](https://loj.ac/problem/10091)

**题意：**

每一头牛的愿望就是变成一头最受欢迎的牛。现在有$N$头牛，给你$M$对整数$(A,B) $，表示牛$A$认为牛$B$受欢迎。这种关系是具有传递性的，如果$A$认为$B$受欢迎，$B$认为$C$受欢迎，那么牛$A$也认为牛$C$受欢迎。你的任务是求出有多少头牛被除自己之外的所有牛认为是受欢迎的。

**思想：**

直接tarjan**缩点**，然后如果出度为0的点只有一个，输出这个点包含的原先的点的个数，否则为0；

**代码：**

爆栈了！！！

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Deque;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Coer
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            List<Integer>[] G = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                G[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                G[in.nextInt()].add(in.nextInt());
            }
            Tarjan tarjan = new Tarjan(G, n + 1);
            tarjan.findScc();
            int[] outDeg = new int[n + 1];
            for (int u = 1; u <= n; u++) {
                for (int v : G[u]) {
                    if (tarjan.sccBelong[u] == tarjan.sccBelong[v]) {
                        continue;
                    }
                    outDeg[u]++;
                }
            }
            int sum = 0, bh = 0;
            for (int i = 1; i <= tarjan.sccCnt; i++) {
                if (outDeg[i] == 0) {
                    sum++;
                    bh = i;
                }
            }
            if (sum == 1) {
                out.println(tarjan.sccSz[bh]);
            } else {
                out.println(0);
            }
        }

        class Tarjan {
            private int V;
            private List<Integer>[] G;
            private List<List<Integer>> res;
            private boolean[] inStack;
            private Deque<Integer> stack;
            private int[] dfn;
            private int[] low;
            private int[] sccBelong;
            private int[] sccSz;
            private int dfsCnt;
            private int sccCnt;

            public Tarjan(List<Integer>[] G, int V) {
                this.G = G;
                this.V = V;
                stack = new ArrayDeque<>();
                inStack = new boolean[V];
                dfn = new int[V];
                low = new int[V];
                sccBelong = new int[V];
                sccSz = new int[V];
                dfsCnt = sccCnt = 0;
                res = new ArrayList<>();
            }

            public List<List<Integer>> findScc() {
                for (int i = 1; i < V; i++) {
                    if (dfn[i] == 0) {
                        dfs(i);
                    }
                }
                return res;
            }

            private void dfs(int u) {
                dfn[u] = low[u] = ++dfsCnt;
                inStack[u] = true;
                stack.push(u);
                for (int v : G[u]) {
                    if (dfn[v] == 0) {
                        dfs(v);
                        if (low[v] < low[u]) {
                            low[u] = low[v];
                        }
                    } else if (inStack[v] && dfn[v] < dfn[u]) {
                        low[u] = dfn[v];
                    }
                }
                if (dfn[u] == low[u]) {
                    List<Integer> tmp = new ArrayList<>();
                    sccCnt++;
                    for (; ; ) {
                        int v = stack.pop();
                        inStack[v] = false;
                        sccBelong[v] = sccCnt;
                        sccSz[sccCnt]++;
                        tmp.add(v);
                        if (v == u) {
                            break;
                        }
                    }
                    res.add(tmp);
                }
            }

        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
    
}
```

### 参考

[ACM-强连通分量](https://hrbust-acm-team.gitbooks.io/acm-book/content/graph_theory/gt_part1.html)

[OI-强连通分量](https://oi-wiki.org/graph/scc/)

[Tarjan](https://byvoid.com/zhs/blog/scc-tarjan/)

[视频](https://www.bilibili.com/video/BV19J411J7AZ)

## 双连通分量、割点与桥

针对于**无向图**：点双连通分量和边的连联通分量。

在一张连通的无向图中，对于两个点$u$和$v$，如果无论删去哪条边（只能删去一条）都不能使它们不连通，我们就说$u$和$v$**边双连通** 。

在一张连通的无向图中，对于两个点$u$和$v$，如果无论删去哪个点（只能删去一个，且不能删$u$和$v$自己）都不能使它们不连通，我们就说$u$和$v$**点双连通** 。

对于一个无向图，如果把一个点删除后这个图的极大连通分量数增加了，那么这个点就是这个图的**割点**（又称割顶）。

对于一个无向图，如果删掉一条边后图中的连通分量数增加了，则称这条边为**桥**或者割边。

### 模板

**双连通分量：**

对于**点双连通分支**，实际上在**求割点**的过程中就能顺便把每个点双连通分支求出。建立一个栈，存储当前双连通分支，在搜索图时，每找到一条树$dfn$枝边或后向边(非横叉边)，就把这条边加入栈中。如果遇到某时满足$dfn(u)\leq low(v)$，说明u是一个割点，同时把边从栈顶一个个取出，直到遇到了边$(u,v)$，取出的这些边与其关联的点，组成一个点双连通分支。割点可以属于多个点双连通分支，其余点和每条边只属于且属于一个点双连通分支。

```java
class Tarjan {
    
    private int V;
    private List<Integer>[] G;
    private Deque<Integer> stack;
    private List<List<Integer>> bcc;
    // dfn[u]：深度优先搜索遍历时结点u被搜索的次序
    // low[u]：以u为根的子树中的结点的dfn的最小值
    private int[] dfn;
    private int[] low;;
    private int dfsCnt;
    private int bccCnt;

    public Tarjan(List<Integer>[] G, int V) {
        this.G = G;
        this.V = V;
        stack = new ArrayDeque<>();
        bcc = new ArrayList<>();
        dfn = new int[V];
        low = new int[V];
        dfsCnt = bccCnt = 0;
    }

    public List<List<Integer>> findBccVE() {
        // 从下标索引1开始
        for (int i = 1; i < V; i++) {
            if (dfn[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private void dfs(int u, int fa) {
        dfn[u] = low[u] = ++dfsCnt;
        stack.push(u);
        for (int v : G[u]) {
            if (dfn[v] == 0) {
                dfs(v, u);
                if (low[v] < low[u]) {
                    low[u] = low[v];
                }
                if (fa != -1 && low[v] >= dfn[u]) {
                    bccCnt++;
                    List<Integer> tmp = new ArrayList<>();
                    while (!stack.isEmpty() && stack.peek() != v) {
                        tmp.add(stack.pop());
                    }
                    tmp.add(stack.pop());
                    tmp.add(u);
                    bcc.add(tmp);
                }
            } else if (fa != v && dfn[v] < dfn[u]) {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }

}
```

对于**边双连通分支**，求法更为简单。只需在**求出所有的桥**以后，把桥边删除，原图变成了多个连通块，则每个连通块就是一个边双连通分支。桥不属于任何一个边双连通分支，其余的边和每个顶点都属于且只属于一个边双连通分支。

```java
class Tarjan {
    
    private int V;
    private List<Integer>[] G;
    private Deque<Integer> stack;
    // dfn[u]：深度优先搜索遍历时结点u被搜索的次序
    // low[u]：以u为根的子树中的结点的dfn的最小值
    private int[] dfn;
    private int[] low;
    private int dfsCnt;

    public Tarjan(List<Integer>[] G, int V) {
        this.G = G;
        this.V = V;
        stack = new ArrayDeque<>();
        dfn = new int[V];
        low = new int[V];
        dfsCnt = 0;
    }

    public List<List<Integer>> findBccE() {
        // 从下标索引1开始
        for (int i = 1; i < V; i++) {
            if (dfn[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private void dfs(int u, int fa) {
        dfn[u] = low[u] = ++dfsCnt;
        stack.push(u);
        for (int v : G[u]) {
            if (dfn[v] == 0) {
                dfs(v, u);
                if (low[v] < low[u]) {
                    low[u] = low[v];
                }
            } else if (fa != v && dfn[v] < dfn[u]) {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
        if (dfn[u] == low[u]) {
            while (!stack.isEmpty() && stack.peek() != u) {
                low[stack.pop()] = low[u];
            }
        }
    }

}
```

**割点与桥：**

```java
/**
 * 当isPoint[x]为真时，x为割点。
 * 当isBridge[x]为真时，(father[x], x)为桥。
 */
class Tarjan {
    
    private int V;
    private List<Integer>[] G;
    private boolean[] isPoint;
    private boolean[] isBridge;
    // dfn[u]：深度优先搜索遍历时结点u被搜索的次序
    // low[u]：以u为根的子树中的结点的dfn的最小值
    private int[] dfn;
    private int[] low;
    private int[] father;
    private int dfsCnt;
    private int pointCnt;
    private int bridgeCnt;

    public Tarjan(List<Integer>[] G, int V) {
        this.G = G;
        this.V = V;
        isPoint = new boolean[V];
        isBridge = new boolean[V];
        dfn = new int[V];
        low = new int[V];
        father = new int[V];
        dfsCnt = pointCnt = bridgeCnt = 0;
    }

    public void findVE() {
        // 从下标索引1开始
        for (int i = 1; i < V; i++) {
            if (dfn[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private void dfs(int u, int fa) {
        father[u] = fa;
        dfn[u] = low[u] = ++dfsCnt;
        int child = 0;
        for (int v : G[u]) {
            if (dfn[v] == 0) {
                child++;
                dfs(v, u);
                if (low[v] < low[u]) {
                    low[u] = low[v];
                }
                if (fa == -1 && child >= 2) {
                    isPoint[u] = true;
                    pointCnt++;
                }
                if (fa != -1 && low[v] >= dfn[u]) {
                    isPoint[u] = true;
                    pointCnt++;
                }
                if (low[v] > dfn[u]) {
                    isBridge[v] = true;
                    bridgeCnt++;
                }
            } else if (fa != v && dfn[v] < dfn[u]) {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }

}
```

### 题目

[Road Construction](http://poj.org/problem?id=3352)

**题意：**

给出一个没有重边的无向图，求至少加入几条边使整个图成为一个边双连通分量。

**思想：**

把图中所有的边双连通分量缩成一个点，原图就缩成了一棵树，要加的边数就是`(所有度为1的点的个数 + 1) / 2`

[割点](https://www.luogu.com.cn/problem/P3388)

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Coer
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            List<Integer>[] G = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                G[i] = new ArrayList<>();
            }
            for (int i = 1; i <= m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                G[u].add(v);
                G[v].add(u);
            }
            Tarjan tarjan = new Tarjan(G, n + 1);
            tarjan.findVE();
            out.println(tarjan.pointCnt);
            for (int i = 1; i <= n; i++) {
                if (tarjan.isPoint[i]) {
                    out.print(i + " ");
                }
            }
        }

        class Tarjan {
            private int V;
            private List<Integer>[] G;
            private boolean[] isPoint;
            private boolean[] isBridge;
            private int[] dfn;
            private int[] low;
            private int[] father;
            private int dfsCnt;
            private int pointCnt;
            private int bridgeCnt;

            public Tarjan(List<Integer>[] G, int V) {
                this.G = G;
                this.V = V;
                isPoint = new boolean[V];
                isBridge = new boolean[V];
                dfn = new int[V];
                low = new int[V];
                father = new int[V];
                dfsCnt = pointCnt = bridgeCnt = 0;
            }

            public void findVE() {
                // 从下标索引1开始
                for (int i = 1; i < V; i++) {
                    if (dfn[i] == 0) {
                        dfs(i, -1);
                    }
                }
            }

            private void dfs(int u, int fa) {
                father[u] = fa;
                dfn[u] = low[u] = ++dfsCnt;
                int child = 0;
                for (int v : G[u]) {
                    if (dfn[v] == 0) {
                        child++;
                        dfs(v, u);
                        if (low[v] < low[u]) {
                            low[u] = low[v];
                        }
                        if (fa == -1 && child >= 2) {
                            isPoint[u] = true;
                            pointCnt++;
                        }
                        if (fa != -1 && low[v] >= dfn[u]) {
                            isPoint[u] = true;
                            pointCnt++;
                        }
                        if (low[v] > dfn[u]) {
                            isBridge[v] = true;
                            bridgeCnt++;
                        }
                    } else if (fa != v && dfn[v] < dfn[u]) {
                        low[u] = Math.min(low[u], dfn[v]);
                    }
                }
            }

        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
```

### 参考

[双连通分量](https://hrbust-acm-team.gitbooks.io/acm-book/content/graph_theory/gt_part2.html)

[视频](https://www.bilibili.com/video/BV1Q7411e7bM)