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