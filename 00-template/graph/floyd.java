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