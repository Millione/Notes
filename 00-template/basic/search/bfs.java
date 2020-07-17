private int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
private int m, n;
private boolean[][] visited;

class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public int bfs(int[][] grid) {
    m = grid.length;
    n = grid[0].length;
    visited = new boolean[m][n];
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(0, 0));
    visited[0][0] = true;
    int level = 0;
    while (!queue.isEmpty()) {
        level++;
        int sz = queue.size();
        for (int i = 0; i < sz; i++) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            // 不满足条件
            if (grid[x][y] == 1) {
                continue;
            }
            // 到达终点
            if (x == m - 1 && y == n - 1) {
                return level;
            }
            // 扩散
            for (int[] d : dir) {
                int xx = x + d[0];
                int yy = y + d[1];
                if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                    continue;
                }
                if (!visited[xx][yy]) {
                    // 标记
                    visited[xx][yy] = true;
                    queue.offer(new Node(xx, yy));
                }
            }
        }
    }
    return -1;
}