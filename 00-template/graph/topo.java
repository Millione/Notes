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