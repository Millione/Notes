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