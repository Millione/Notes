int[] bucketSort(int[] arr, int bucketCount) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int[] res = new int[n];
    int min = arr[0], max = arr[0];
    // 最大最小值
    for (int i = 1; i < n; ++i) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
    }
    // 桶容量
    int gap = (int)Math.ceil((double)(max - min) / bucketCount);
    List[] buckets = new ArrayList[bucketCount];
    // 元素放入相应桶中
    for (int i = 0; i < n; ++i) {
        int idx = (arr[i] - min) / gap;
        if (buckets[idx] == null) {
            buckets[idx] = new ArrayList<>();
        }
        buckets[idx].add(arr[i]);
    }
    int k = 0;
    for (int i = 0; i < bucketCount; ++i) {
        if (buckets[i] == null) {
                continue;
            }
        // 集合排序
        Collections.sort(buckets[i]);
        for (int j = 0; j < buckets[i].size(); ++j) {
            res[k++] = (int)buckets[i].get(j);
        }
    }
    return res;
}