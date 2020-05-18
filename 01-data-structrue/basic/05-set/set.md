# 集

> 不包含重复元素。

如果无排序要求可以选用HashSet；如果想取出元素的顺序和放入元素的顺序相同，那么可以选用LinkedHashSet。如果想插入、删除立即排序或者按照一定规则排序可以选用TreeSet。

## HashSet、LinkedHashSet、TreeSet

### 1. contains

```java
public boolean contains(Object o)
```

- **Parameters:**

  `o` - element whose presence in this set is to be tested

- **Returns:**

  `true` if this set contains the specified element

### 2. add

```java
public boolean add(E e)
```

- **Parameters:**

  `e` - element to be added to this set

- **Returns:**

  `true` if this set did not already contain the specified element

### 3. remove

```java
public boolean remove(Object o)
```

- **Parameters:**

  `o` - object to be removed from this set, if present

- **Returns:**

  `true` if the set contained the specified element

### 4. containsAll

```java
boolean containsAll(Collection<?> c)
```

- **Parameters:**

  `c` - collection to be checked for containment in this set

- **Returns:**

  `true` if this set contains all of the elements of the specified collection

### 5. addAll

```java
boolean addAll(Collection<? extends E> c)
```

- **Parameters:**

  `c` - collection containing elements to be added to this set

- **Returns:**

  `true` if this set changed as a result of the call

### 6. retainAll

```java
boolean retainAll(Collection<?> c)
```

- **Parameters:**

  `c` - collection containing elements to be retained in this set

- **Returns:**

  `true` if this set changed as a result of the call

### 7. removeAll

```java
boolean removeAll(Collection<?> c)
```

- **Parameters:**

  `c` - collection containing elements to be removed from this set

- **Returns:**

  `true` if this set changed as a result of the call

## 题目



## 参考

[HashSet JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html)

[LinkedHashSet JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashSet.html)

[TreeSet JDK11](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeSet.html)