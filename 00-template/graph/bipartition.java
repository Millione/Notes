// 二分图判断
private List<Integer>[] graph;
private int[] colors;

public boolean possibleBipartition(int N, int[][] dislikes) {
    if (N == 1 || dislikes.length == 0) {
        return true;
    }
    graph = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
        graph[i] = new ArrayList<>();
    }
    for (int[] d : dislikes) {
        graph[d[0]].add(d[1]);
        graph[d[1]].add(d[0]);
    }
    colors = new int[N + 1];
    
    for (int i = 1; i <= N; i++) {
        if (colors[i] == 0 && !bfs(i)) {
            return false;
        }
    }

    for (int i = 1; i <= N; i++) {
        if (colors[i] == 0) {
            colors[i] = 1;
            if (!dfs(graph, i)) {
                return false;
            }
        }
    }

    return true;
}

private boolean bfs(int s) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(s);
    colors[s] = 1;
    while (!queue.isEmpty()) {
        int u = queue.poll();
        for (int v : graph[u]) {
            if (colors[v] == 0) {
                colors[v] = - colors[u];
                queue.offer(v);
            } else if (colors[v] == colors[u]) {
                return false;
            }
        }
    }
    return true;
}

private boolean dfs(int u) {
    for (int v : graph[u]) {
        if (colors[v] == 0) {
            colors[v] = - colors[u];
            if (!dfs(graph, v)) {
                return false;
            }
        }
        if (colors[u] == colors[v]) {
            return false;
        }
    }
    return true;
}