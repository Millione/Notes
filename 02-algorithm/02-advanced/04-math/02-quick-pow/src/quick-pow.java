// 计算 (a^n) % mod
public long powMod(long a, long n, long mod) {
    long res = 0;
    while (n > 0) {
        if ((n & 1) != 0) {
            res = res * a % mod;
        }
        a = a * a % mod;
        n >>= 1;
    }
    return res;
}

// 计算 (a * b) % mod
public long mulMod(long a, long n, long mod) {
    long res = 0;
    while (b > 0) {
        if ((b & 1) != 0) {
            res = (res + a) % mod;
        }
        a = (a << 1) % mod;
        b >>= 1;
    }
    return res;
}

// 计算 (a^n) % mod  配合 mul
public long powMod(long a, long n, long mod) {
    long res = 1;
    while (n > 0) {
        if ((n & 1) != 0) {
            res = mulMod(res, a, mod) % mod;
        }
        a = mulMod(a, a, mod) % mod;
        n >>= 1;
    }
    return res;
}