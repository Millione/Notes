int firstLargeEqual(int[] arr, int key) {
    int l = 0, r = arr.length - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] >= key) {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    return l;
}