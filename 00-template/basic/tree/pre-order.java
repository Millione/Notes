
/*****************递归*****************/

public void preOrder(TreeNode root) {
    if (root != null) {
        // write your code here
        preOrder(root.left);
        preOrder(root.right);
    }
}

/****************非递归****************/

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

/****************Morris****************/

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