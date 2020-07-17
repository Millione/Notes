// 大数加法
public String add(String str1, String str2) {
    char[] s1 = str1.toCharArray();
    char[] s2 = str2.toCharArray();
    int n1 = s1.length, n2 = s2.length;
    int maxL = Math.max(n1, n2);
    int[] a = new int[maxL + 1];
    int[] b = new int[maxL + 1];
    for (int i = 0; i < n1; i++) {
        a[i] = s1[n1 - i - 1] - '0';
    }
    for (int i = 0; i < n2; i++) {
        b[i] = s2[n2 - i - 1] - '0';
    }
    for (int i = 0; i < maxL; i++) {
        if (a[i] + b[i] >= 10) {
            int tmp = a[i] + b[i];
            a[i] = tmp % 10;
            a[i + 1] += tmp / 10;
        } else {
            a[i] += b[i];
        }
    }
    StringBuilder sb = new StringBuilder();
    if (a[maxL] != 0) {
        sb.append((char)(a[maxL] + '0'));
    }
    for (int i = maxL - 1; i >= 0; i--) {
        sb.append((char)(a[i] + '0'));
    }
    return sb.toString();
}