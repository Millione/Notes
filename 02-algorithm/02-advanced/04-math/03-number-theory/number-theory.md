# 数论

> 数论是纯粹数学的分支之一，主要研究整数的性质。

## 最大公约数、最小公倍数

```java
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
```

## 素数筛法

```java
// 经典筛法
public List<Integer> primary(boolean[] isPrime, int n) {
    List<Integer> prime = new ArrayList<>();
    isPrime[0] = isPrime[1] = false;
    boolean flag;
    for (int i = 2; i <= n; i++) { 
        flag = true;
        // 根号i的时间复杂度
        for (int j = 2; j * j <= i; j++) {
            if (i % j == 0) {
                isPrime[i] = false;
                flag = false;
                break;
            }
        }
        if (flag) {
            prime.add(i);
            isPrime[i] = true;
        }
    }
    return prime;
}

// 经典的埃式筛法
public List<Integer> sieve(boolean[] isPrime, int n) {
    List<Integer> prime = new ArrayList<>();
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i <= n; i++) {
        if (isPrime[i]) {
            prime.add(i);
            for (int j = 2 * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }
    return prime;
}

// 优化筛法
public List<Integer> sieve2(boolean[] isPrime, int n) {
    ArrayList<Integer> prime = new ArrayList<>();
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i <= n; i++) {
        if (isPrime[i]) {
            prime.add(i);
        }
        for (int j = 0; j < prime.size() && prime.get(j) <= n / i; j++) {
            isPrime[prime.get(j) * i] = false;
            if (i % prime.get(j) == 0) {
                break;
            }
        }
    }
    return prime;
}
```

