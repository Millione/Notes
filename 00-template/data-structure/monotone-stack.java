/**
 * 寻找数组中的每一个元素　左边离它最近的比它大的数
 * 栈底到栈顶: 由大到小 (也可以自定义从小到大)
 */
void monotoneStack(int[] arr) {
    int n = arr.length;
    int[] L = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                L[top] = -1;
            } else {
                L[top] = stack.peek();
            }
        }
        stack.push(i);
    }
    while (!stack.isEmpty()) {
        int top = stack.pop();
        if (stack.isEmpty()) {
            L[top] = -1;
        } else {
            L[top] = stack.peek();
        }
    }
}
