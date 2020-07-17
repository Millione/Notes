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