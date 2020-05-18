import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 */

// @lc code=start
class Solution {

    class Node {
        int pos;
        int val;
        public Node(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }

    private int[] sums;
    private int n;

    public List<Integer> countSmaller(int[] nums) {
        n = nums.length;
        List<Integer> res = new ArrayList<>();
        sums = new int[n + 1];
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i, nums[i - 1]);
        }
        Arrays.sort(nodes, 1, n + 1, new Comparator<Node>() {

            public int compare(Node o1, Node o2) {
                if (o1.val == o2.val) {
                    // o2.pos - o1.pos 可解决此题小于等于的情况
                    return o1.pos - o2.pos;
                }
                return o1.val - o2.val;
            }

        });
        int[] ref = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ref[nodes[i].pos] = i;
        }
        int[] ans = new int[n];
        for (int i = n; i > 0; i--) {
            ans[i - 1] = query(ref[i]);
            update(ref[i], 1);
        }
        for (int num : ans) {
            res.add(num);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & (-x);
    }
    
    private void update(int x, int add) {
        while (x <= n) {
            sums[x] += add;
            x += lowbit(x);
        }
    }
    
    private int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += sums[x];
            x -= lowbit(x);
        }
        return ret;
    }

}
// @lc code=end

