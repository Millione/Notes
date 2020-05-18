
/*****************数组*****************/

int[] insertionSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int val = arr[i], j = i - 1;
        // 查找插入位置
        for (; j >= 0 && arr[j] > val; --j) {
            // 数据移动
        	arr[j + 1] = arr[j];
        }
        arr[j + 1] = val;
    }
    return arr;
}

/**************二分插入排序*************/

int[] insertionSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
        int l = 0, r = i - 1;
        int val = arr[i];
        // 找第一个大于val的位置
		while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        for (int j = i - 1; j >= l; --j) {
            // 数据移动
        	arr[j + 1] = arr[j];
        }
        arr[l] = val;
    }
    return arr;
}

/*****************链表*****************/

ListNode insertionSort(ListNode head) {
    if (head == null) {
        return head;
    }
    ListNode helper = new ListNode(0);
    // 当前要被插入的结点
    ListNode cur = head;
    // 插入位置在pre和pre.next之间
    ListNode pre = helper;
    // 下一个要插入的结点
    ListNode next = null;
    while (cur != null) {
        next = cur.next;
        // 找到正确插入的位置
        while (pre.next != null && cur.val >= pre.next.val) {
            pre = pre.next;
        }
        // 插入操作
        cur.next = pre.next;
        pre.next = cur;
        pre = helper;
        cur = next;
    }
    return helper.next;
}