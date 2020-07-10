# 高精度运算

在 Java 中的高精度运算可以直接调用 API 实现，其中 BigInteger 用于大整数运算， BigDecimal 用于高浮点型数据运算。

```java
// BigDecimal 类似
// 构造对象最好使用 String，其它类型可转换为 String 进行构造，保证精度
BigInteger a = new BigInteger("123");
BigInteger b = BigInteger.vauleOf(123);
BigInteger c = new BigInteger(String.valueOf(0));

// 四则运算
c = a.add(b);
c = a.subtract(b);
c = a.divide(b);
c = a.multiply(b);

// 比较大小
a.compareTo(b);
a.equals(b);

// 常用方法
c = a.mod(b);
c = a.gcd(b);
c = a.max(b);
c = a.min(b);
c = a.abs();
c = a.negate();
c = a.pow(b);

// 转换
int d = a.intValue();
```

尽管已经有了如此方便的操作，下面还是给出具体实现细节，也可作为模板使用。

## 模板

```java
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

// 大数乘法
public String mul(String str1, String str2){
    char[] s1 = str1.toCharArray();
    char[] s2 = str2.toCharArray();
    int n1 = s1.length, n2 = s2.length;
    int[] a = new int[n1];
    int[] b = new int[n2];
    int[] c = new int[n1 + n2];
    for (int i = 0; i < n1; i++) {
        a[i] = s1[n1 - i - 1] - '0';
    }
    for (int i = 0; i < n2; i++) {
        b[i] = s2[n2 - i - 1] - '0';
    }
    for (int i = 0; i < n1; i++) {
        for (int j = 0; j < n2; j++) {
            c[i + j] += a[i] * b[j];
        }
    }
    for (int i = 0; i < n1 + n2 - 1; i++) {
        if (c[i] >= 10) {
            c[i + 1] += c[i] / 10;
            c[i] %= 10;
        }
    }
    int i;
    for (i = n1 + n2 - 1; i >= 0; i--) {
        if (c[i] != 0) {
            break;
        }
    }
    StringBuilder sb = new StringBuilder();
    for(; i >= 0; i--) {
        sb.append((char)(c[i] + '0'));
    }
    return sb.toString();
}

// 大数阶乘
public String bigFactorial(int n) {
    int[] res = new int[100001];
    int digit = 1;
    res[0] = 1;
    for (int i = 2; i <= n; i++) {
        int carry = 0;
        for (int j = 0; j < digit; j++) {
            int temp = res[j] * i + carry; //每一位的运算结果
            res[j] = temp % 10;   //将最低位保留在原位置
            carry = temp / 10;   //计算进位, 等下这个进位会累加到j+1
        }
        while (carry != 0) {
            res[digit] = carry % 10;
            carry /= 10;
            digit++;
        }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = digit - 1; i >= 0; i--) {
        sb.append((char)(res[i] + '0'));
    }
    return sb.toString();
}
// 大数计算阶乘位数
private int factorialDigit(int n) {
    double sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += Math.log10(i); 
    }
    return (int) sum + 1;
}
```

## 题目