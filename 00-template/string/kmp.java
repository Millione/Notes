// next[j]表示字符串前j+1位最长公共前后缀长度
private int[] getNext(String p) {
    int m = p.length();
    int[] next = new int[m];
    next[0] = 0;
    int i, j;
    for (j = 1, i = 0; j < m; j++) {
        while (i > 0 && p.charAt(j) != p.charAt(i)) {
            i = next[i - 1];
        }
        if (p.charAt(j) == p.charAt(i)) {
            i++;
        }
        next[j] = i;
    }
}

// 返回匹配串出现次数
public int kmp(String s, String p) {
    int ans = 0;
    int n = s.length();
    int m = p.length();
    int[] next = getNext(p);
    for (int i = 0, j = 0; i < n; i++) {
        while (j > 0 && p.charAt(j) != s.charAt(i)) {
            j = next[j - 1];
        }
        if (p.charAt(j) == s.charAt(i)) {
            j++;
        }
        if (j == m) {
            ans++;
            // 此处j=0处理不可重叠情形，可重叠直接注释掉即可
            j = 0;
        }
    }
    return ans;
}