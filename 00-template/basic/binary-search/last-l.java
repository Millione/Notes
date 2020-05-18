int lastSmall(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        // 注意
        if (arr[mid] < key) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return r;
}