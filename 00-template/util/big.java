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