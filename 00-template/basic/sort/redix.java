int[] redixSort(int[] arr, int len) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int exp = 10, R = 10;
    for (int i = 0; i < len; ++i) {
        List[] digits = new ArrayList[R * 2];
        for (int j = 0; j < n; ++j) {
            // 特定位上的值
            int bucket = (arr[j] / exp) % 10 + R;
            if (digits[bucket] == null) {
                digits[bucket] = new ArrayList();
            }
            digits[bucket].add(arr[j]);
        }
        int index = 0;
        // 完成一次排序后拷贝
        for (int k = 0; k < digits.length; ++k) {
            if (digits[k] == null) {
                continue;
            }
            for (int l = 0; l < digits[k].size(); ++l) {
                arr[index++] = (int)digits[k].get(l);
            }
        }
        exp *= 10;
    }
    return arr;
}