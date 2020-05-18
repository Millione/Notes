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