# 树

> 刷题先刷二叉树

## 常用操作

### 1. 建树、调试

```java
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    
    public BST(E[] array){
        root = generateAVL(array, 0, array.length - 1);
        size = array.length;
    }
    
    private TreeNode generateAVL(E[] array, int l, int r){
        if(l > r){
            return null;
        }
        int mid = l + (r - l) / 2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = generateAVL(array, l, mid - 1);
        node.right = generateAVL(array, mid + 1, r);
        return node;
    }
    
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e +"\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("--");
        return res.toString();
    }
}

```

### 2. 递归遍历

前序遍历：

```java
public void preOrder(TreeNode root) {
    if (root != null) {
        // write your code here
        preOrder(root.left);
        preOrder(root.right);
    }
}
```

中序遍历：

```java
public void inOrder(TreeNode root) {
    if (root != null) {
        inOrder(root.left);
        // write your code here
        inOrder(root.right);
    }
}
```

后序遍历：

```java
public void postOrder(TreeNode root) {
    if (root != null) {
        postOrder(root.left);
        postOrder(root.right);
        // write your code here
    }
}
```

### 3. 非递归遍历

前序遍历：

```java
public void preOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while (p != null || !stack.isEmpty()) {
        while (p != null) {
            // write your code here
            stack.push(p);
            p = p.left;
        }
        p = stack.pop();
        p = p.right;
    }
}
```

中序遍历：

```java
public void inOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode p = root;
    while (p != null || !stack.isEmpty()) {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        p = stack.pop();
        // write your code here
        p = p.right;
    }
}
```

后序遍历：

```java
// 第一种双栈
public void postOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    Deque<TreeNode> stack1 = new ArrayDeque<>();
    Deque<TreeNode> stack2 = new ArrayDeque<>();
    TreeNode p = root;
    stack1.push(root);
    while (!stack1.isEmpty()) {
        TreeNode node = stack1.pop();
        stack2.push(node);
        if (node.left != null) {
            stack1.push(node.left);
        }
        if (node.right != null) {
            stack1.push(node.right);
        }
    }
    while (!stack2.isEmpty()) {
        // write your code here
    }
}

// 第二种pre
public void postOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    TreeNode pre = null;
    while (!stack.isEmpty()) {
        TreeNode node = stack.peek();
        if ((node.left == null && node.right == null) || (pre != null && (pre == node.left || pre == node.right))) {
            // write your code here
            stack.pop();
            pre = node;
        } else {
            if (node.right != null) {
                stack.push(node.right);
            }
          	if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
}
```

层序遍历：

```java
public void levelOrder(TreeNode root) {
    if (root == null) {
        return;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int sz = queue.size();
        for (int i = 0; i < sz; i++) {
            TreeNode node = queue.poll();
            // write your code here
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
```

### 4. Morris遍历

前序遍历：

```java
public void preOrder(TreeNode root) {
    TreeNode cur = root, pre = null;
    for (; cur != null;) {
        if (cur.left != null) {
            pre = cur.left;
        	// 寻找前驱结点
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                // write your code here
                pre.right = cur;
                cur = cur.left;
            } else {
                // 删除线索
                pre.right = null;
                cur = cur.right;
            }
        } else {
            // write your code here
            cur = cur.right;
        }
    }
}
```

中序遍历：

```java
public void inOrder(TreeNode root) {
    TreeNode cur = root, pre = null;
    for (; cur != null;) {
        if (cur.left != null) {
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
                pre = pre.right;
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                // write your code here
                pre.right = null;
                cur = cur.right;
            }
        } else {
            // write your code here
            cur = cur.right;
        }
    }
}
```

后序遍历：

```java
public void postOrder(TreeNode root) {
    TreeNode dummy = new TreeNode(-1);
    dummy.left = root;
    TreeNode cur = dummy, pre = null;
    for (; cur != null;) {
        if (cur.left != null) {
            pre = cur.left;
            while (pre.right != null && pre.right != cur) {
            	pre = pre.right;   
            }
            if (pre.right == null) {
                pre.right = cur;
                cur = cur.left;
            } else {
                reverse(cur.left, pre);
                print(pre, cur.left);
                reverse(pre, cur.left);
                pre.right = null;
                cur = cur.right;
            }

        } else {
            cur = cur.right;
        }
    }
}

private void print(TreeNode from, TreeNode to) {
    for (;;from = from.right) {
        // write your code here
        if (from == to) {
            break;
        }
    }
}

private void reverse(TreeNode from, TreeNode to) {
    if (from == to) {
        return;
    }
    TreeNode x = from, y = from.right, z= null;
    x.right = null;
    for (;;) {
        z = y.right;
        y.right = x;
        x = y;
        if (y == to) {
            break;
        }
        y = z;
    }
}
```

## 题目

## 参考

[Morris](https://spground.github.io/2018/01/27/Morris%20binary-tree%20traverse/)