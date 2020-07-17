/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
 */

// @lc code=start
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length, edgeRemoved = -1, edgeCycle = -1;
        int[] parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (parent[v] != 0) {
                edgeRemoved = i;
                break;
            } else {
                parent[v] = u;
            }
        }
        UnionFind uf = new UnionFind(n + 1);
        for (int i = 0; i < n; i++) {
            if (edgeRemoved == i) {
                continue;
            }
            int u = edges[i][0];
            int v = edges[i][1];
            if (!uf.union(u, v)) {
                edgeCycle = i;
                break;
            }
        }
        /* Handle with the cyclic problem only. */
        if (edgeRemoved == -1) {
            return edges[edgeCycle];
        }
        
        /* Handle with the cyclic problem when we remove the wrong edge. */
        if (edgeCycle != -1) { 
            int v = edges[edgeRemoved][1];
            int u = parent[v];
            return new int[]{u, v};
        } 
        
        /* CHandle with the cyclic problem when we remove the right edge. */
        return edges[edgeRemoved];
    }

    class UnionFind {
        private int[] parent;
        private int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int size() {
            return parent.length;
        }
        
        public boolean union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                parent[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            }
            return true;
        }
        
        public boolean same(int x, int y) {
            return find(x) == find(y);
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
// @lc code=end

