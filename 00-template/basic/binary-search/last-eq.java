int lastEqual(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] <= key) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    // 注意判断条件
    if (r >= 0 && arr[r] == key) {
        return r;
    }
    return -1;
}