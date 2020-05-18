
/*****************数组*****************/

int[] mergeSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    mergeSort(arr, 0, n - 1);
    return arr;
}

void mergeSort(int[] arr, int l, int r) {
    if (l >= r) {
        return;
    }
    int mid = l + (r - l) / 2;
    mergeSort(arr, l, mid);
    mergeSort(arr, mid + 1, r);
    merge(arr, l, mid, r);
}

void merge(int[] arr, int l, int mid, int r) {
    int[] aux = new int[r - l + 1];
    int p1 = l, p2 = mid + 1;
    int k = 0;
    while (p1 <= mid && p2 <= r) {
        // 保证稳定性
        aux[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= mid) {
        aux[k++] = arr[p1++];
    }
    while (p2 <= r) {
        aux[k++] = arr[p2++];
    }
    for (int i = 0; i < k; ++i) {
        arr[l + i] = aux[i];
    }
}

/*************数组自底向上*************/

int[] mergeSortBU(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    // 区间个数，1..2..4..8
    for (int sz = 1; sz <= n; sz += sz) {
        // 对[i, i + sz - 1]和[i + sz, i + 2 * sz - 1]归并
        for (int i = 0; sz + i < n; i += sz + sz) {
            // min防数组越界
            merge(arr, i, i + sz - 1, Math.min(n - 1, i + 2 * sz - 1));
        }
    }
    return arr;
}

/*****************链表*****************/

ListNode mergeSort(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }
    ListNode pre = null, cur = head, next = head;
    // 链表分为两半
    while(next != null && next.next != null){
        pre = cur;
        cur = cur.next;
        next = next.next.next;
    }
    pre.next = null;
    // 对每一半分别排序
    ListNode l1 = mergeSort(head);
    ListNode l2 = mergeSort(cur);
    // 合并
    return merge(l1, l2);
}

ListNode merge(ListNode l1, ListNode l2){
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while(l1 != null && l2 != null){
        if(l1.val < l2.val){
            cur.next = l1;
            l1 = l1.next;
        }
        else{
            cur.next = l2;
            l2 = l2.next;
        }
        cur = cur.next;
    }
    cur.next = l1 == null ? l2 : l1;
    return dummy.next;
}

/*************链表自底向上**************/

ListNode mergeSort(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    int len = 0;
    while (head != null) {
        head = head.next;
        ++len;
    }
    for (int step = 1; step < len; step <<= 1) {
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        while (cur != null) {
            ListNode left = cur;
            ListNode right = split(left, step);
            cur = split(right, step);
            // 拼接分组排序链表
            prev = merge(left, right, prev);
        } 
    }
    return dummy.next;
}

ListNode split(ListNode head, int step) {
    if (head == null) {
        return null;
    }
    for (int i = 1; head.next != null && i < step; ++i) {
        head = head.next;
    }
    ListNode right = head.next;
    head.next = null;
    return right;
}

ListNode merge(ListNode left, ListNode right, ListNode prev) {
    ListNode cur = prev;
    while (left != null && right != null) {
        if (left.val < right.val) {
            cur.next = left;
            left = left.next;
        }
        else {
            cur.next = right;
            right = right.next;
        }
        cur = cur.next;
    }

    cur.next = left == null ? right : left;
    while (cur.next != null) {
        cur = cur.next;
    }
    return cur;
}