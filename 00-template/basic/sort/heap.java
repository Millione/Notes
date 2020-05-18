
/****************大顶堆****************/

int[] heapSort(int[] arr) {
    // 每个结点的值都大于等于其左右孩子结点的值
    if (arr == null || arr.length <= 1) {
        return arr;
    }
    int n = arr.length;
    //上浮方式建堆
    for (int i = 0; i < arr.length; i++) {
        siftUp(arr, i);
    }
    int size = n - 1;
    swap(arr, 0, size);
    while (size > 0) {
        siftDown(arr, 0, size);
        swap(arr, 0, --size);
    }
    return arr;
}

// 上浮
void siftUp(int[] arr, int i) {
    // 当前结点为i，父亲结点为(i-1)/2
    while (arr[i] > arr[(i - 1) / 2]) {
        swap(arr, i, (i - 1) / 2);
        i = (i - 1) / 2;
    }
}

// 下沉
private void siftDown(int[] arr, int i, int heapSize) {
    // 父亲结点为i，左孩子结点为2*i+1，右孩子结点为2*i+2
    int l = 2 * i + 1;
    // 每次保证堆的性质
    while (l < heapSize) {
        int maxIndex = l + 1 < heapSize && arr[l + 1] > arr[l] ? l + 1 : l;
        maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        if (maxIndex == i) {
            break;
        }
        swap(arr, i, maxIndex);
        i = maxIndex;
        l = 2 * i + 1;
    }
}

/*************heapfiy优化**************/

int[] heapSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
        return arr;
    }
    int n = arr.length;
    int size = n - 1;
    for (int i = (size - 1) / 2; i >= 0; --i) {
        // 注意这儿是n，因为还没有swap
        siftDown(arr, i, n);
    }
    swap(arr, 0, size);
    while (size > 0) {
        siftDown(arr, 0, size);
        swap(arr, 0, --size);
    }
    return arr;
}

void siftDown(int[] arr, int i, int heapSize) {
    //从arr[i] 开始往下调整
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    int maxIdx = i;
    if (l < heapSize && arr[l] > arr[maxIdx]) {
        maxIdx = l;
    }
    if (r < heapSize && arr[r] > arr[maxIdx]) {
        maxIdx = r;
    }
    if (maxIdx != i) {
        swap(arr, i, maxIdx);
        siftDown(arr, maxIdx, heapSize);
    }
}