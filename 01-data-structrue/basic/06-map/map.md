# 映射

> 存储键值对映射

## HashMap、LinkedHashMap、TreeMap

### 1. get

```java
public V get(Object key)
```

- **Parameters:**

  `key` - the key whose associated value is to be returned

- **Returns:**

  the value to which the specified key is mapped, or `null` if this map contains no mapping for the key

### 2. containsKey

```java
public boolean containsKey(Object key)
```

- **Parameters:**

  `key` - The key whose presence in this map is to be tested

- **Returns:**

  `true` if this map contains a mapping for the specified key.

### 3. put

```java
public V put(K key, V value)
```

- **Parameters:**

  `key` - key with which the specified value is to be associated

  `value` - value to be associated with the specified key

- **Returns:**

  the previous value associated with `key`, or `null` if there was no mapping for `key`. (A `null` return can also indicate that the map previously associated `null` with `key`.)

### 4. putAll

```java
public void putAll(Map<? extends K,? extends V> m)
```

- **Parameters:**

  `m` - mappings to be stored in this map

- **Throws:**

  `NullPointerException` - if the specified map is null

### 5. remove

```java
public V remove(Object key)
```

- **Parameters:**

  `key` - key whose mapping is to be removed from the map

- **Returns:**

  the previous value associated with `key`, or `null` if there was no mapping for `key`. (A `null` return can also indicate that the map previously associated `null` with `key`.)

### 6. containsValue

```java
public boolean containsValue(Object value)
```

- **Parameters:**

  `value` - value whose presence in this map is to be tested

- **Returns:**

  `true` if this map maps one or more keys to the specified value

### 7. keySet

```java
public Set<K> keySet()
```

- **Returns:**

  a set view of the keys contained in this map

### 8. values

```java
public Collection<V> values()
```

- **Returns:**

  a view of the values contained in this map

### 9. entrySet

```java
public Set<Map.Entry<K,V>> entrySet()
    
for (Map.Entry<K, V> entry: coursesMap.entrySet()) {  
    System.out.println(entry.getKey());  
    System.out.println(entry.getValue());  
}
```

- **Returns:**

  a set view of the mappings contained in this map

## 题目