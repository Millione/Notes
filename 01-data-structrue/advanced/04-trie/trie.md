# 字典树

字典树，又称单词查找树，Trie树，是一种树形结构，是一种哈希树的变种。典型应用是用于**统计，排序和保存大量的字符串（但不仅限于字符串）**，所以经常被搜索引擎系统用于文本词频统计。它的优点是：利用字符串的公共前缀来节约存储空间，最大限度地减少无谓的字符串比较，查询效率比哈希表高。

![trie](./assets/trie1.png)

**字典树的性质**

1. 根节点（Root）不包含字符，除根节点外的每一个节点都仅包含一个字符；
2. 从根节点到某一节点路径上所经过的字符连接起来，即为该节点对应的字符串；
3. 任意节点的所有子节点所包含的字符都不相同；

**Trie 的应用**

- **字符串检索**：事先将已知的一些字符串（字典）的有关信息保存到 Trie 里，查找另外一些未知字符串是否出现过或者出现频率。
- **字符串最长公共前缀**：Trie 利用多个字符串的公共前缀来节省存储空间，反之，当我们把大量字符串存储到一棵 Trie 上时，我们可以快速得到某些字符串的公共前缀。
- **排序**：Trie 树是一棵多叉树，只要先序遍历整棵树，输出相应的字符串，便是按字典序排序的结果。
- **作为其他数据结构和算法的辅助结构**：如后缀树，AC自动机等。

## 模板

```java
class Trie {
    
    static final int MAx = 26;
    
    private class TrieNode {
        int path;
        int end;
        TrieNode[] next;
        
        public TrieNode() {
            path = 0;
            end = 0;
            next = new TrieNode[MAX];
        }
    }
    
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new TrieNode();
            }
            cur = cur.next[index];
            cur.path++;
        }
        cur.end++;
    }
    
    public int count(String word) {
        if (word == null) {
            return 0;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                return 0;
            }
            cur = cur.next[index];
        }
        return cur.end;
    }
    
    public boolean search(String word) {
        return count(word) > 0;
    }
    
    public int prefixNum(String prefix) {
        if (prefix == null) {
            return 0;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < prefix.length(); i++) {
            index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                return 0;
            }
            cur = cur.next[index];
        }
        return cur.path;
    }
    
    public boolean startsWith(String prefix) {
        return prefixNum(prefix) > 0;
    }
    
    public void remove(String word) {
        if (word == null || !search(word)) {
            return;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (--cur.next[index].path == 0) {
                cur.next[index] = null;
                return;
            }
            cur = cur.next[index];
        }
        cur.end--;
    }
    
}
```

## 题目

### 208. implement-trie-prefix-tree

------

**Description:**

Implement a trie with `insert`, `search`, and `startsWith` methods.

**Example:**

```
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
```

**Note:**

- You may assume that all inputs are consist of lowercase letters `a-z`.
- All inputs are guaranteed to be non-empty strings.

[Discussion](https://leetcode.com/problems/implement-trie-prefix-tree/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/implement-trie-prefix-tree/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=208 lang=java
 *
 * [208] Implement Trie (Prefix Tree)
 */

// @lc code=start
class Trie {
    
    private class TrieNode {
        int path;
        int end;
        TrieNode[] next;
        
        public TrieNode() {
            path = 0;
            end = 0;
            next = new TrieNode[26];
        }
    }
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < word.length();  i++) {
            index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                cur.next[index] = new TrieNode();
            }
            cur = cur.next[index];
            cur.path++;
        }
        cur.end++;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < word.length();  i++) {
            index = word.charAt(i) - 'a';
            if (cur.next[index] == null) {
                return false;
            }
            cur = cur.next[index];
        }
        return cur.end > 0;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        TrieNode cur = root;
        int index = 0;
        for (int i = 0; i < prefix.length();  i++) {
            index = prefix.charAt(i) - 'a';
            if (cur.next[index] == null) {
                return false;
            }
            cur = cur.next[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
// @lc code=end
```

