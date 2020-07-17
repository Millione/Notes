
/*****************数组*****************/

int[] qucikSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    quickSort(arr, 0, n - 1);
    return arr;
}

void quickSort(int[] arr, int l, int r) {
    if (l >= r) {
        return;
    }
    // 随机选择pivot，防止退化为O(n²)
    swap(arr, l, l + (int)(Math.random() * (r - l + 1)));
    int[] p = partition(arr, l, r);
    quickSort(arr, l, p[0]);
    quickSort(arr, p[1], r);
}

int[] partition(int[] arr, int l, int r) {
    // 用arr[l]作为划分点
    int val = arr[l];
    int start = l, end = r + 1;
    int cur = l + 1;
    // 交换导致的不稳定性
    while (cur < end) {
        if (arr[cur] < val) {
            swap(arr, ++start, cur++);
        } else if (arr[cur] > val) {
            swap(arr, --end, cur);
        } else {
            cur++;
        }
    }
    swap(arr, l, start);
    // 返回下次开始的位置，一左一右
    return new int[]{start - 1, end};
}

void shuffle(int arr[]) {
    final Random random = new Random();
    for (int idx = 1; idx < arr.length; ++idx) {
        final int r = random.nextInt(idx + 1);
        swap(arr, idx, r);
    }
}

/*****************链表*****************/

ListNode quickSort(ListNode head){
    if(head == null || head.next == null) {
		return head;
    }
    
    // 划分为三个子序列
    ListNode fakesmall = new ListNode(0), small = fakesmall;
    ListNode fakelarge = new ListNode(0), large = fakelarge;
    ListNode fakeequal = new ListNode(0), equal = fakeequal;
    // pivot
    ListNode cur = head;
    while(cur != null){
        if(cur.val < head.val){
            small.next = cur;
            small = small.next;
        }
        else if(cur.val == head.val){
            equal.next = cur;
            equal = equal.next;
        }
        else{
            large.next = cur;
            large = large.next;
        }
        cur = cur.next;
    }
    
    // put an end.
    small.next = equal.next = large.next = null;
    // merge them and return.
    return merge(merge(quickSort(fakesmall.next), quickSort(fakelarge.next)),fakeequal.next) ;
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