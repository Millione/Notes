
/*****************递归*****************/

public void postOrder(TreeNode root) {
    if (root != null) {
        postOrder(root.left);
        postOrder(root.right);
        // write your code here
    }
}

/****************非递归****************/

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

/****************Morris****************/

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