/**
 * 单调队列: 用来求出在数组的某个区间范围内的最值
 */
void monotoneQueue(int[] arr, int k) {
    int n = arr.length;
    List<Integer> res = new ArrayLise<>();
    Deque<Integer> deque = new LinkedList<>();
    for (int i = 0; i < n; i++) {
        while (!deque.isEmpty() && arr[deque.peekLast()] <= nums[i]) {
            deque.pollLast();
        }
        deque.offerLast(i);
        // k：窗口大小
        if (i - k == deque.peekFirst()) {
            deque.pollFirst();
        }
        // 窗口内最大值
        if (i >= k - 1) {
            res.add(arr[deque.peekFirst()]);
        }
    }
}