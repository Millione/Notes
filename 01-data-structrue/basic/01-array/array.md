# 数组

> 数组(Array)是一种线性表数据结构。它用一组连续的内存空间，来存储一组具有相同类型的数据。
>
> 原地操作，从后先前。

数组用一块连续的内存空间，来存储相同类型的一组数据，最大的特点就是支持随机访问，但插入、删除操作也因此变得比较低效，平均情况时间复杂度为 O(n)。在平时的业务开发中，我们可以直接使用编程语言提供的容器类，但是，如果是特别底层的开发，直接使用数组可能会更合适。

**动态数组：**均摊时间复杂度一般都等于最好情况时间复杂度。因为在大部分情况下，入栈操作的时间复杂度 O 都是 O(1)，只有在个别时刻才会退化为 O(n)，所以把耗时多的入栈操作的时间均摊到其他入栈操作上，平均情况下的耗时就接近 O(1)。

![时间复杂度](./assets/time.png)

## 实现

### 1. 基本构造

```java
public class Array<E> {

    private E[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量capacity=10
    public Array() {
        this(10);
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
    
    // 将数组空间的容量变成newCapacity大小
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++)
            newData[i] = data[i];
        data = newData;
    }
    
}
```

### 2. 增加元素

**优化：**如果数组中存储的数据没有任何规律，数组只是被当作一个存储数据的集合，如果将某个数据插入到第K个位置，则可以将第K位的数据搬移到数组元素的最后，把新元素直接放入到第K个位置。

```java
	// 在index索引的位置插入一个新元素e
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if(size == data.length)
            resize(2 * data.length);

        for(int i = size - 1; i >= index ; i --)
            data[i + 1] = data[i];

        data[index] = e;

        size ++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e){
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }
```

### 3. 删除元素

**优化：**每次的删除操作并不是真正地搬移数据，只是记录数据已经被删除。当数组没有更多空间存储数据时，我们再触发执行一次真正地删除操作，这样就大大减少了删除操作导致的数据搬移。

> 如果你了解JVM，你会发现这就是JVM标记清除垃圾回收算法的核心思想。

```java
	// 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for(int i = index + 1 ; i < size ; i ++)
            data[i - 1] = data[i];
        size --;
        data[size] = null; // loitering objects != memory leak

        if(size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }
```

### 4. 修改元素

```java
	// 修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }
```

### 5. 查询元素

```java
	// 获取index索引位置的元素
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

	// 查找数组中是否有元素e
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }
```

## ArrayList

### 1. contains

```java
public boolean contains(Object o)
```

- **Parameters:**

  `o` - element whose presence in this list is to be testedss

- **Returns:**

  `true` if this list contains the specified element

### 2. indexOf

```java
public int indexOf(Object o)
```

- **Parameters:**

  `o` - element to search for

- **Returns:**

  the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element

### 3. lastIndexOf

```java
public int lastIndexOf(Object o)
```

- **Parameters:**

  `o` - element to search for

- **Returns:**

  the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element

### 4. clone

```java
public Object clone()
```

- **Returns:**

  a clone of this `ArrayList` instance

### 5. toArray

```java
public <T> T[] toArray(T[] a)
    
T[] E = (T[])arraylist.toArray(new T[0]);
```

- **Type Parameters:**

  `T` - the component type of the array to contain the collection

- **Parameters:**

  `a` - the array into which the elements of the list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.

- **Returns:**

  an array containing the elements of the list

### 6. clear

```java
public void clear()
```

- **Specified by:**

  `clear` in interface `Collection`

- **Specified by:**

  `clear` in interface `List`

### 7. addAll

```java
public boolean addAll(Collection<? extends E> c)
```

- **Parameters:**

  `c` - collection containing elements to be added to this list

- **Returns:**

  `true` if this list changed as a result of the call

### 8. removeAll

```java
public boolean removeAll(Collection<?> c)
```

- **Parameters:**

  `c` - collection containing elements to be removed from this list

- **Returns:**

  `true` if this list changed as a result of the call

### 9. retainAll

```java
public boolean retainAll(Collection<?> c)
```

- **Parameters:**

  `c` - collection containing elements to be retained in this list

- **Returns:**

  `true` if this list changed as a result of the call

### 10. subList

```java
public List<E> subList(int fromIndex, int toIndex)
```

- **Parameters:**

  `fromIndex` - low endpoint (inclusive) of the subList

  `toIndex` - high endpoint (exclusive) of the subList

- **Returns:**

  a view of the specified range within this list

## Arrays

### 1. sort

```java
public static void sort(Object[] a)
```

- **Parameters:**

  `a` - the array to be sorted

- **Throws:**

  `ClassCastException` - if the array contains elements that are not *mutually comparable* (for example, strings and integers)

  `IllegalArgumentException` - (optional) if the natural ordering of the array elements is found to violate the [`Comparable`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html) contract

```java
public static <T> void sort(T[] a, Comparator<? super T> c)
    
Arrays.sort(new Comparator<T>() {
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    } 
});
```

- **Type Parameters:**

  `T` - the class of the objects to be sorted

- **Parameters:**

  `a` - the array to be sorted

  `c` - the comparator to determine the order of the array. A `null` value indicates that the elements' [natural ordering](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html) should be used.

- **Throws:**

  `ClassCastException` - if the array contains elements that are not *mutually comparable* using the specified comparator

  `IllegalArgumentException` - (optional) if the comparator is found to violate the [`Comparator`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html) contract

### 2. binarySearch

```java
public static int binarySearch(Object[] a, Object key)
```

- **Parameters:**

  `a` - the array to be searched

  `key` - the value to be searched for

- **Returns:**

  index of the search key, if it is contained in the array; otherwise, `(-(insertion point) - 1)`. The *insertion point* is defined as the point at which the key would be inserted into the array: the index of the first element greater than the key, or `a.length` if all elements in the array are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.

- **Throws:**

  `ClassCastException` - if the search key is not comparable to the elements of the array.

### 3. equals

```java
public static boolean equals(Object[] a, Object[] a2)
```

- **Parameters:**

  `a` - one array to be tested for equality

  `a2` - the other array to be tested for equality

- **Returns:**

  `true` if the two arrays are equal

### 4. fill

```java
public static void fill(Object[] a, Object val)
```

- **Parameters:**

  `a` - the array to be filled

  `val` - the value to be stored in all elements of the array

- **Throws:**

  `ArrayStoreException` - if the specified value is not of a runtime type that can be stored in the specified array

### 5. copyOfRange

```java
public static <T> T[] copyOfRange(T[] original, int from, int to)
```

- **Type Parameters:**

  `T` - the class of the objects in the array

- **Parameters:**

  `original` - the array from which a range is to be copied

  `from` - the initial index of the range to be copied, inclusive

  `to` - the final index of the range to be copied, exclusive. (This index may lie outside the array.)

- **Returns:**

  a new array containing the specified range from the original array, truncated or padded with nulls to obtain the required length

- **Throws:**

  `ArrayIndexOutOfBoundsException` - if `from < 0` or `from > original.length`

  `IllegalArgumentException` - if `from > to`

  `NullPointerException` - if `original` is null

- **Since:**

  1.6

### 6. asList

```java
public static <T> List<T> asList(T... a)
    
List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
```

- **Type Parameters:**

  `T` - the class of the objects in the array

- **Parameters:**

  `a` - the array by which the list will be backed

- **Returns:**

  a list view of the specified array

### 7. toString

```java
public static String toString(Object[] a)
```

- **Parameters:**

  `a` - the array whose string representation to return

- **Returns:**

  a string representation of `a`

- **Since:**

  1.5

## 题目

### 1089. duplicate-zeros

------

**Description:**

Given a fixed length array `arr` of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array **in place**, do not return anything from your function.

**Example 1:**

```
Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
```

**Example 2:**

```
Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]
```

**Note:**

1. `1 <= arr.length <= 10000`
2. `0 <= arr[i] <= 9`

[Discussion](https://leetcode.com/problems/duplicate-zeros/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/duplicate-zeros/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=1089 lang=java
 *
 * [1089] Duplicate Zeros
 */

// @lc code=start
class Solution {
    public void duplicateZeros(int[] arr) {
        int cnt = 0;
        for (int num : arr) {
            if (num == 0) {
                cnt++;
            }
        }
        int n = arr.length;
        int i = n - 1;
        while (cnt != 0) {
            if (arr[i] == 0) {
                cnt--;
                if (i + cnt < n) {
                    arr[i + cnt] = 0;
                }
                if (i + cnt + 1 < n) {
                    arr[i + cnt + 1] = 0;
                }
            } else {
                if (i + cnt < n) {
                    arr[i + cnt] = arr[i];
                }
            }
            i--;
        }
    }
}
// @lc code=end
```



## 参考

[ArrayList JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)

[Arrays JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Arrays.html)

[Leetcode](https://leetcode.com/explore/learn/card/fun-with-arrays/)