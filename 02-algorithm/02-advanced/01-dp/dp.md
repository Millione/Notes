# 动态规划

> 算法里的莫扎特，思维中的贝多芬。

动态规划可谓千变万化，纵使虐我千百遍，拿它还是没办法。特此记录下自己的学习历程，如何从dp新手一步一步变成dp苦手，以供参考。

|  题目   | 类型 |
| :-----: | :--: |
| ABCFGHI | 基础 |
|   DE    | 背包 |
|   JM    | 优化 |
|   KL    | 博弈 |
|    N    | 区间 |
|         |      |
|         |      |
|         |      |
|         |      |
|         |      |
|         |      |

[题目链接](https://atcoder.jp/contests/dp)

## A - Frog 1

**题意：**

有$n$块石头排成一排，第$i$块石头的高度为$h_i$。青蛙现在站在第$1$块石头上。它可以从第$i$块石头跳到第$i+1$或者$i+2$块石头上。从第$i$块石头跳到第$j$块需要消耗$|h_j−h_i|$点体力。请求出跳到第$n$块石头所需的最小体力值。

**数据限制：**

- 输入值为整型数据。

- $1\leq n\leq 10^5$
- $1\leq h_i\leq 10^4$

**输入：**

```
n
h1 h2 ... hn
```

**思想：**

$dp[i]$表示**跳到第$i$块石头所需的最小体力值**，转移方程如下：
$$
dp[i]=\left\{
\begin{array}{l}
0 & i=1\\
|h_2-h_1| & i=2\\
min(dp[i-1]+|h_i-h_{i-1}|,dp[i-2]+|h_i-h_{i-2}) & i\geq 3
\end{array}
\right.
$$
**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] h = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                h[i] = in.nextInt();
            }
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[1] = 0;
            for (int i = 2; i < n + 1; i++) {
                for (int j = Math.max(1, i - k); j < i; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + Math.abs(h[i] - h[j]));
                }
            }
            out.println(dp[n]);
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

## B - Frog 2

**题意：**

有$n$块石头排成一排，第$i$块石头的高度为$h_i$。青蛙现在站在第$1$块石头上。它可以从第$i$块石头跳到第$i+1,i+2,...,i+k$块石头上。从第$i$块石头跳到第$j$块需要消耗$|h_j−h_i|$点体力。请求出跳到第$n$块石头所需的最小体力值。

**数据限制：**

- 输入值为整型数据。

- $1\leq n\leq 10^5$
- $1\leq k\leq 100$
- $1\leq h_i\leq 10^4$

**输入：**

```
n k
h1 h2 ... hn
```

**思想：**

$dp[i]$表示跳到第$i$块石头所需的最小体力值，转移方程如下：
$$
dp[i]=\left\{
\begin{array}{l}
0 & i=1\\
min(dp[j]+|h_i-h_j|\ |\ i-k\leq j<i) & i\geq 2
\end{array}
\right.
$$
**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] h = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                h[i] = in.nextInt();
            }
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[1] = 0;
            for (int i = 2; i < n + 1; i++) {
                for (int j = Math.max(1, i - k); j < i; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + Math.abs(h[i] - h[j]));
                }
            }
            out.println(dp[n]);
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

## C - Vacation

**题意：**

太郎的暑假有$n$天，第$i$天他可以选择以下三种活动之一：

- 游泳，获得$a_i$点幸福值。
- 捉虫，获得$b_i$点幸福值。
- 写作业，获得$c_i$点幸福值。

但他不能连续两天进行同一种活动，请求出最多可以获得多少幸福值。

**数据限制：**

- 输入值为整型数据。

- $1\leq n\leq 10^5$
- $1\leq a_i,b_i,c_i\leq 10^4$

**输入：**

```
n 
a1 b1 c1
a2 b2 c2
.  .  .
an bn cn
```

**思想：**

$dp[i][k]$表示**在第$i$天进行$k$活动的前提下，前$i$天的最大幸福值**。可得，
$$
dp[i][k]=\left\{
\begin{array}{l}
v[i][k] & i=1 \\
max(dp[i-1][j]\ |\ j\neq k)+v[i][k] & i\geq2
\end{array}
\right.
$$
**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[][] v = new int[n + 1][3];
            for (int i = 1; i < n + 1; i++) {
                v[i][0] = in.nextInt();
                v[i][1] = in.nextInt();
                v[i][2] = in.nextInt();
            }
            int[][] dp = new int[n + 1][3];
            for (int i = 0; i < 3; i++) {
                dp[1][i] = v[1][i];
            }
            for (int i = 2; i < n + 1; i++) {
                for (int cur = 0; cur < 3; cur++) {
                    for (int pre = 0; pre < 3; pre++) {
                        if (pre != cur) {
                            dp[i][cur] = Math.max(dp[i][cur], dp[i - 1][pre] + v[i][cur]);
                        }
                    }
                }
            }
            out.println(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])));
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

## D - Knapsack 1

**题意：**

经典$01$背包问题。有$n$个物品，每个物品有重量$w_i$和价值$v_i$。背包的容量为$W$，换句话说，可以放进背包的物品的重量总和不能超过$W$。请选择若干物品放入背包并且最大化被选取物品的价值总和。

**数据限制：**

- 输入值为整型数据。

- $1\leq n\leq 100$
- $1\leq W\leq 10^5$
- $1\leq w_i\leq W$
- $1\leq v_i\leq 10^9$

**输入：**

```
n W 
w1 v1
w2 v2
.  .
wn vn

```

**思想：**

$dp[i][j]$表示**只考虑前$i$个物品的情况下，背包容量是$j$所能凑出的最大价值之和**。可分成两类情况考虑，

1. 拿第$i$件物品；
2. 不拿第$i$件物品。

$$
dp[i][j]=\left\{
\begin{array}{l}
0 & i=0 \\
max(dp[i-1][j-w_i]+v_i,dp[i-1][j] & i\geq1
\end{array}
\right.
$$

**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int W = in.nextInt();
            int[] w = new int[n + 1];
            int[] v = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                w[i] = in.nextInt();
                v[i] = in.nextInt();
            }
            // 只考虑前i个物品的情况下，背包容量是j所能凑出的最大价值之和
            long[][] dp = new long[n + 1][W + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= W; j++) {
                    if (w[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                    }
                }
            }
            out.println(dp[n][W]);
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

## E - Knapsack 2

**题意：**

同上题，仅数据范围不同。

**数据限制：**

- 输入值为整型数据。

- $1\leq n\leq 100$
- $1\leq W\leq 10^9$
- $1\leq w_i\leq W$
- $1\leq v_i\leq 10^3$

**输入：**

```
n W 
w1 v1
w2 v2
.  .
wn vn
```

**思想：**

由于$W$达到了$10^9$的量级，之前的$O(nW)$算法无法通过，但由于每样物品的价值上限仅为$10^3$，我们可以另辟蹊径。$dp[i][j]$表示**只考虑前$i$个物品的情况下，总价值是$j$所需的最小容量**。依旧可分成两类情况考虑，

1. 拿第$i$件物品；
2. 不拿第$i$件物品。

$$
dp[i][j]=\left\{
\begin{array}{l}
0 & i=0 \\
min(dp[i-1][j-v_i]+w_i,dp[i-1][j] & i\geq1
\end{array}
\right.
$$

**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int W = in.nextInt();
            int V = n * 1000;
            int[] w = new int[n + 1];
            int[] v = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                w[i] = in.nextInt();
                v[i] = in.nextInt();
            }
            // 只考虑前i个物品的情况下，总价值是j所需最少容量值
            long[][] dp = new long[n + 1][V + 1];
            for (int i = 0; i <= V; i++) {
                if (i == 0) {
                    dp[0][i] = 0;
                } else {
                    dp[0][i] = Long.MAX_VALUE;
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= V; j++) {
                    if (v[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        if (dp[i - 1][j - v[i]] == Long.MAX_VALUE) {
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                        }
                    }
                }
            }
            int ans = 0;
            // 找出满足容量条件的最大价值
            for (int tot = 0; tot <= V; tot++) {
                if (dp[n][tot] <= W) {
                    ans = tot;
                }
            }
            out.println(ans);
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

## F - LCS

**题意：**

给定字符串$s$和$t$，求最长公共子序列（不需要连续）。

**数据限制：**

- $s$和$t$是包含小写字母的字符串。

- $1\leq |s|,|t|\leq 3000$

**输入：**

```
s
t
```

**思想：**

$dp[i][j]$表示$s$的前$i$个字符和$t$的前$j$个字符的LCS长度，可得，
$$
dp[i][j]=\left\{
\begin{array}{l}
0 & i=0 \ or\ j=0 \\
dp[i-1][j-1]+1 & s[i]=t[j] \\
max(dp[i-1][j],dp[i][j-1]) & otherwise
\end{array}
\right.
$$
**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String s = in.next();
            String t = in.next();
            int n = s.length();
            int m = t.length();
            int[][] dp = new int[n + 1][m + 1];
            // 计算最优长度
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            // 恢复答案
            char[] res = new char[dp[n][m]];
            int i = n, j = m;
            while (dp[i][j] > 0) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    res[dp[i][j] - 1] = s.charAt(i - 1);
                    i--;
                    j--;
                } else {
                    if (dp[i][j] == dp[i - 1][j]) {
                        i--;
                    } else {
                        j--;
                    }
                }
            }
            for (char c : res) {
                out.print(c);
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
    }
}
```

## G - Longest Path

**题意：**

给定一个$n$个点，$m$条边的有向无环图，求图中最长路径的长度。定义路径长
度为边的数量。

**数据限制：**

- 输入值为整型数据。
- $1\leq N\leq 10^5$
- $1\leq M\leq 10^5$
- $1\leq x_i,y_i\leq N$

**输入：**

![img](https://img.atcoder.jp/dp/longest_0_muffet.png)

![img](https://img.atcoder.jp/dp/longest_1_muffet.png)

![img](https://img.atcoder.jp/dp/longest_2_muffet.png)

**思想：**

$dp[i]$表示以节点$i$为终点的路径中最长的长度，可得，
$$
dp[i]=max\{dp[j]+1\ |\ 存在边j\rightarrow i\}
$$
有向无环图最重要的性质就是没有环，这使得我们可以对节点进行拓扑排序，
并按照拓扑序的顺序计算$dp$状态的值。

**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.io.BufferedReader;
import java.util.LinkedList;
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
        TaskG solver = new TaskG();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskG {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Integer>[] G = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                G[i] = new ArrayList<>();
            }
            int[] deg = new int[n + 1];
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                G[u].add(v);
                deg[v]++;
            }
            int[] dp = new int[n + 1];
            int res = 0;
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
                    dp[v] = Math.max(dp[v], dp[u] + 1);
                    res = Math.max(res, dp[v]);
                    if (--deg[v] == 0) {
                        queue.add(v);
                    }
                }
            }
            out.println(res);
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

## H - Grid 1

**题意：**

给定一个$h$行$w$列的网格，现在位于(1, 1)，每次可以向下或右走一格，求有多少种方式（对$10^9+7$取模）走到$(h,w)$。

网格中有若干个不能经过的障碍，但不会出现在起点和终点。

**数据限制：**

- 输入值为整型数据。
- $1\leq h,w\leq 1000$

**输入：**

![img](https://img.atcoder.jp/dp/grid_0_0_muffet.png)

**思想：**

$dp[i][j]$表示走到$(i,j)$的方案数，可得，
$$
dp[i][j]=\left\{
\begin{array}{l}
0 & (i,j)是障碍 \\
dp[i-1][j]+dp[i][j-1] & otherwise
\end{array}
\right.
$$
如果无障碍，生成dp即为杨辉三角。

**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskH solver = new TaskH();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskH {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int h = in.nextInt();
            int w = in.nextInt();
            char[][] a = new char[h + 1][w + 1];
            for (int i = 1; i <= h; i++) {
                String s = in.next();
                for (int j = 1; j <= w; j++) {
                    a[i][j] = s.charAt(j - 1);
                }
            }
            int[][] dp = new int[h + 1][w + 1];
            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (i == 1 && j == 1) {
                        dp[i][j] = 1;
                        continue;
                    }
                    if (a[i][j] == '.') {
                        dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
                    }
                }
            }
            out.println(dp[h][w]);
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

## I - Coins

**题意：**

有$n$枚硬币排成一排。现在同时抛所有硬币，第$i$枚硬币向上的概率是$p_i$，向
下的概率是$1−p_i$，求向上的硬币数量比向下的多的概率。

**数据限制：**

- $1\leq n\leq 2999$
- $n$是奇数

**输入：**

```
n
p1 p2 ... pn
```

**思想：**

$dp[i][j]$表示$i$个硬币有$j$个朝上的概率，转移方程：
$$
dp[i][j]=dp[i-1][j-1]*p_i+dp[i-1][j]*(1-p_i)
$$
**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskI solver = new TaskI();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskI {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            double[] p = new double[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = in.nextDouble();
            }
            double[][] dp = new double[n + 1][n + 1];
            dp[0][0] = 1.0;
            for (int i = 1; i <= n; i++) {
                dp[i][0] = dp[i - 1][0] * (1 - p[i]);
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j - 1] * p[i] + dp[i - 1][j] * (1 - p[i]);
                }
            }
            double ans = 0.0;
            for (int j = 1; j <= n; j++) {
                if (j > n - j) {
                    ans += dp[n][j];
                }
            }
            out.println(ans);
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}
```

## J - Sushi

## K - Stones

**题意：**

给定一个有$n$个正整数的集合$A={a_1,a_2 ... a_n}$，现在有$k$块石头和两名玩家。两人轮流进行以下操作，不能进行操作者输：

- 从$A$中指定一个正整数$a_i$，并移走恰好$a_i$块石头。

注：每个$a_i$可以重复使用。

**数据限制：**

- 输入值为整型数据。
- $1\leq n\leq 100$
- $1\leq k\leq 100$
- $1\leq a_1<a_2<...<a_n\leq 100$

**输入：**

```
n k
a1 a2 ... an
```

**思想：**

$dp[i]$表示剩$i$个石头时当前操作者是否必胜，则$dp[i]$必胜当且仅当$i\geq a[j]$且$dp[i-a[j]]$必败。

**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskK solver = new TaskK();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskK {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            boolean[] dp = new boolean[k + 1];
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i >= a[j] && dp[i - a[j]] == false) {
                        dp[i] = true;
                    }
                }
            }
            if (dp[k] == true) {
                out.println("First");
            } else {
                out.println("Second");
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

## L - Deque

**题意：**

给定一个双端队列$a=(a_1,a_2 . . .  a_n)$。现在有两名玩家轮流进行以下操作，直至队列为空：

- 从队首或队尾取走一个数$c$，并且本次收益为$c$。

设先/后手的总收益分别为$X$和$Y$ 。先手$L$想要最大化$X−Y$，后手想要最大化$Y−X$，即两人都想最大化自己与对手的差值。现两名玩家均以最优策略操作，请问最终$X−Y$的值是多少？

**数据限制：**

- 输入值为整型数据。
- $1\leq n\leq 3000$
- $1\leq a_i\leq 10^9$

**输入：**

```
n
a1 a2 ... an
```

**思想：**

$dp[i][j]$表示序列$a_i,a_{i+1},...,a_j$先手总收益减去后手总收益的最大值，转移方程：
$$
dp[i][j]=max(a[i]-dp[i+1][j],a[j]-dp[i][j-1])
$$
注意：当前先手变为下轮后手。

**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskL solver = new TaskL();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskL {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            long[][] dp = new long[n + 1][n + 1];
            helper(a, dp, 1, n);
            out.println(dp[1][n]);
        }

        private void helper(int[] a, long[][] dp, int l, int r) {
            if (dp[l][r] != 0) {
                return;
            } else {
                if (l == r) {
                    dp[l][r] = a[l];
                } else {
                    helper(a, dp, l + 1, r);
                    helper(a, dp, l, r - 1);
                    dp[l][r] = Math.max(a[l] - dp[l + 1][r], a[r] - dp[l][r - 1]);
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

## M - Candies

**题意：**

现在有$n$个小孩和$k$颗糖。第$i$个小孩获得的糖数必须在$[0,a_i]$之内，求一共有多少种把糖发完的方法。

**数据限制：**

- 输入值为整型数据。
- $1\leq n\leq 100$
- $0\leq k\leq 10^5$
- $1\leq a_i\leq k$

**输入：**

```
n k
a1 a2 ... an
```

**思想：**

$dp[i][j]$表示前$i$个人一共发了$j$颗糖的方法，转移方程即枚举当前人可获得的糖数$c_i$
$$
dp[i][j]=\sum_{c=0}^{a_i}dp[i-1][j-c]
$$
暴力转移复杂度过高，高达$10^{12}$。考虑到：
$$
dp[i][j-1]=dp[i-1][j-1-c]+dp[i-1][j-c]+...+dp[i-1][j-1] \\
dp[i][j]=dp[i-1][j-c]+...+dp[i-1][j-1]+dp[i-1][j]
$$
故：
$$
dp[i][j]=dp[i][j-1]-dp[i-1][j-1-c]+dp[i-1][j]
$$
**代码：**

```java
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        TaskM solver = new TaskM();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskM {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int mod = 1000000007;
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            int[][] dp = new int[n + 1][k + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
                for (int j = 1; j <= k; j++) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    if (j - 1 - a[i] >= 0) {
                        dp[i][j] -= dp[i - 1][j - 1 - a[i]];
                    }
                    dp[i][j] = (dp[i][j] % mod + mod) % mod;
                }
            }
            out.println(dp[n][k]);
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

