List<Integer> list = Arrays.asList(new Integer[]{0, 1});
// 鸡肋
Integer[] array = list.toArray(new Integer[0]);


// 以int举例，其它基本数据类型类似
String str = null;
int num = 0;

str = String.valueOf(num);
str = Integer.toString(num);
str = num + "";

num = Integer.valueOf(str).intValue();