int firstEqual(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] >= key) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    // 注意判断条件
    if (l < arr.length && arr[l] == key) {
        return l;
    }
    return -1;
}