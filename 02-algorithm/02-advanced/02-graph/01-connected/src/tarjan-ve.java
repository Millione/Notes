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