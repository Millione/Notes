int[] shellSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    // 增量序列每次减半
    for (int gap = n; gap > 0; gap /= 2) {
        // 对每个序列做插入排序
        for (int end = gap; end < n; ++end) {
            int val = arr[end], i = end - gap;
            for (; i >= 0 && arr[i] > val; i -= gap) {
                arr[i + gap] = arr[i];
            }
            arr[i + gap] = val;
        }
    }
    return arr;
}