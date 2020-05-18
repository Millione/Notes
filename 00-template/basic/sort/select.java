
/*****************数组*****************/

int[] selectSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int i = 0; i < n; ++i) {
        int minIdx = i;
        // 找到区间最小值索引
        for (int j = i + 1; j < n; ++j) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        if(minIdx != i) {
            swap(arr, minIdx, i);
        }
    }
    return arr;
}

/*****************链表*****************/

ListNode selectSort(ListNode head) {
    ListNode cur = head;
    // 相当于双指针
    while (cur != null) {
        ListNode tmpNode = new ListNode(cur.val);
        ListNode next = cur.next;
        while (next != null) {
            if (next.val < tmpNode.val) {
                tmpNode = next;
            }
            next = next.next;
        }
        // 最小值交换
        if (cur.val != tmpNode.val) {
            int tmp = tmpNode.val;
        	tmpNode.val = cur.val;
        	cur.val = tmp;
        }
        cur = cur.next;
    }
    return head;
}