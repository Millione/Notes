int[][] dp = new int[n][n];

for (int i = 0; i <= n; i++) {
    // 初始化dp状态
}
// 枚举区间长度
for (int len = 2; len <= n; len++) {
    // 枚举区间起点
    for (int i = 0; i + len <= n; i++) {
        // 区间终点
        int j = i + len - 1;
        // 枚举分割点
        for (int k = i + 1; k < j; k++) {
            // dp状态转移
        }
    }
}