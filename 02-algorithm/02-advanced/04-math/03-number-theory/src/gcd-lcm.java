// 非递归
public int gcd(int a, int b) {
    int r;
    while (b != 0) {
        r = a % b;
        a = b;
        b = r;
    }
    return a;
}

public int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}

public int lcm(int a, int b) {
    return a / gcd(a, b) * b;
}

public int ngcd(int arr[], int n) {
    if (n == 1) {
        return arr[0];
    }
    return gcd(arr[n - 1], ngcd(arr, n - 1));
}

public int nlcm(int arr[], int n) {
    if (n == 1) {
        return arr[0];
    }
    return lcm(arr[n - 1], nlcm(arr, n - 1));
}