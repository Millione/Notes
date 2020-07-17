
/*****************递归*****************/

public void inOrder(TreeNode root) {
    if (root != null) {
        inOrder(root.left);
        // write your code here
        inOrder(root.right);
    }
}

/****************非递归****************/

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

/****************Morris****************/

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