class Trie {
    
    static final int MAX = 26;
    
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
            index = prefix.charAt(i) - 'a';
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