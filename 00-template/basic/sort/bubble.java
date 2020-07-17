
/*****************数组*****************/

int[] bubbleSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    for (int j = n - 1; j > 0; --j) {
        boolean isSort = true;
        // 冒泡
        for (int i = 0; i < j; ++i) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                isSort = false;
            }
        }
        // 提前结束
        if (isSort) {
            break;
        }
    }
    return arr;
}

/*****************链表*****************/

ListNode bubbleSort(ListNode head) {
    ListNode cur = head, tail = null;
    // 双指针
    while (cur != tail) {
        while (cur.next != tail) {
            boolean isSort = true;
            if (cur.val > cur.next.val) {
                int tmp = cur.val;
                cur.val = cur.next.val;
                cur.next.val = tmp;
                isSort = false;
            }
            cur = cur.next;
        }
        if (isSort) {
            break;
        }
        // 下次遍历的尾结点是当前结点，每次减少访问最后结点
        tail = cur;
        cur = head;
    }
    return head;
}