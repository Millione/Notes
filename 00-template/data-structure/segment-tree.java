public class SegmentTreeRMQPos {
    public int M, H, N;
    public int[] st;
    public int[] pos;

    public SegmentTreeRMQPos(int n) {
        N = n;
        M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
        H = M >>> 1;
        st = new int[M];
        pos = new int[M];
        for (int i = 0; i < N; i++) {
            pos[H + i] = i;
        }
        Arrays.fill(st, 0, M, Integer.MAX_VALUE);
        for (int i = H - 1; i >= 1; i--) {
            propagate(i);
        }
    }

    public SegmentTreeRMQPos(int[] a) {
        N = a.length;
        M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
        H = M >>> 1;
        st = new int[M];
        pos = new int[M];
        for (int i = 0; i < N; i++) {
            st[H + i] = a[i];
            pos[H + i] = i;
        }
        Arrays.fill(st, H + N, M, Integer.MAX_VALUE);
        for (int i = H - 1; i >= 1; i--) {
            propagate(i);
        }
    }

    public void update(int pos, int x) {
        st[H + pos] = x;
        for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1) {
            propagate(i);
        }
    }

    private void propagate(int i) {
        if (st[2 * i] <= st[2 * i + 1]) {
            st[i] = st[2 * i];
            pos[i] = pos[2 * i];
        } else {
            st[i] = st[2 * i + 1];
            pos[i] = pos[2 * i + 1];
        }
    }

    public int minpos;
    public int minval;

    public int minx(int l, int r) {
        minval = Integer.MAX_VALUE;
        minpos = -1;
        if (l >= r) {
            return minval;
        }
        while (l != 0) {
            int f = l & -l;
            if (l + f > r) {
                break;
            }
            int v = st[(H + l) / f];
            if (v < minval || v == minval && pos[(H + l) / f] < minpos) {
                minval = v;
                minpos = pos[(H + l) / f];
            }
            l += f;
        }

        while (l < r) {
            int f = r & -r;
            int v = st[(H + r) / f - 1];
            if (v < minval || v == minval && pos[(H + r) / f - 1] < minpos) {
                minval = v;
                minpos = pos[(H + r) / f - 1];
            }
            r -= f;
        }
        return minval;
    }

    public int min(int l, int r) {
        minpos = -1;
        minval = Integer.MAX_VALUE;
        min(l, r, 0, H, 1);
        return minval;
    }

    private void min(int l, int r, int cl, int cr, int cur) {
        if (l <= cl && cr <= r) {
            if (st[cur] < minval) {
                minval = st[cur];
                minpos = pos[cur];
            }
        } else {
            int mid = cl + cr >>> 1;
            if (cl < r && l < mid) {
                min(l, r, cl, mid, 2 * cur);
            }
            if (mid < r && l < cr) {
                min(l, r, mid, cr, 2 * cur + 1);
            }
        }
    }
    
    public int sumFenwick(int[] ft, int i) {
        int sum = 0;
        for (i++; i > 0; i -= i & -i) {
            sum += ft[i];
        }
        return sum;
    }

    public void addFenwick(int[] ft, int l, int r, int v) {
        addFenwick(ft, l, v);
        addFenwick(ft, r, -v);
    }

    public void addFenwick(int[] ft, int i, int v) {
        if (v == 0 || i < 0) {
            return;
        }
        int n = ft.length;
        for (i++; i < n; i += i & -i) {
            ft[i] += v;
        }
    }
}