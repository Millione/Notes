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

### 105. construct-binary-tree-from-preorder-and-inorder-traversal

------

**Description:**

Given preorder and inorder traversal of a tree, construct the binary tree.

**Note:**
You may assume that duplicates do not exist in the tree.

For example, given

```
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```

Return the following binary tree:

```
    3
   / \
  9  20
    /  \
   15   7
```

[Discussion](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/)

**Code:**

```java
import java.util.Arrays;

/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }
    
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}
// @lc code=end
```

### 106. construct-binary-tree-from-inorder-and-postorder-traversal

------

**Description:**

Given inorder and postorder traversal of a tree, construct the binary tree.

**Note:**
You may assume that duplicates do not exist in the tree.

For example, given

```
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
```

Return the following binary tree:

```
    3
   / \
  9  20
    /  \
   15   7
```

[Discussion](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
    }
    private TreeNode build(int postEnd, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (postEnd < 0 || inStart > inEnd) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[postEnd]);
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == node.val) {
                index = i;
            }
        }
        node.left = build(postEnd - 1 - inEnd + index, inStart, index - 1, inorder, postorder);
        node.right = build(postEnd - 1, index + 1, inEnd, inorder, postorder);
        return node;
    }
}
// @lc code=end
```

### 117. populating-next-right-pointers-in-each-node-ii

------

**Description:**

Given a binary tree

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

**Follow up:**

- You may only use constant extra space.
- Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

**Example 1:**

![img](https://assets.leetcode.com/uploads/2019/02/15/117_sample.png)

```
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
```

**Constraints:**

- The number of nodes in the given tree is less than `6000`.
- `-100 <= node.val <= 100`

[Discussion](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/?currentPage=1&orderBy=most_votes&query=) | [Solution](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/solution/)

**Code:**

```java
/*
 * @lc app=leetcode id=117 lang=java
 *
 * [117] Populating Next Right Pointers in Each Node II
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        Node head = null, pre = null, cur = root;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            pre = null;
        }
        return root;
    }
}
// @lc code=end
```

### 99. recover-binary-search-tree

------

**Description:**

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

**Example 1:**

```
Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
```

**Example 2:**

```
Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
```

**Follow up:**

- A solution using O(*n*) space is pretty straight forward.
- Could you devise a constant space solution?

**Code:**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null, p = null;
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
                    if (p != null && p.val > cur.val) {
				        if (first == null) {
                            first = p;
                            second = cur;
                        }
				        else {
                            second = cur;
                        }
				    }
				    p = cur;
                    
                    pre.right = null;
                    cur = cur.right;
                }
            } else {
                if (p != null && p.val > cur.val) {
                    if (first == null) {
                        first = p;
                        second = cur;
                    }
                    else {
                        second = cur;
                    }
                }
                p = cur;
                
                cur = cur.right;
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
```

## 参考

[Morris](https://spground.github.io/2018/01/27/Morris%20binary-tree%20traverse/)