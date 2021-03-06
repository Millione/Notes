public String manacher(String s) {
    int n = s.length();
    StringBuilder sb = new StringBuilder();
    sb.append("$");
    sb.append("#");
    for (int i = 0; i < n; i++) {
        sb.append(s.substring(i, i + 1));
        sb.append("#");
    }
    sb.append("0");
    int mx = 0, id = 0;
    int resLen = 0, resCenter = 0; 
    int[] p = new int[sb.length()];
    for (int i = 1; i < sb.length(); i++) {
        p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
        while (i + p[i] < sb.length() && i - p[i] >= 0) {
            if (sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
                p[i]++;
            } else {
                break;
            }
        }
        if (mx < i + p[i]) {
            mx = i + p[i];
            id = i;
        }
        if (resLen < p[i]) {
            resLen = p[i];
            resCenter = i;
        }
    }
    return s.substring((resCenter - resLen) / 2, (resCenter + resLen) / 2 - 1);
}