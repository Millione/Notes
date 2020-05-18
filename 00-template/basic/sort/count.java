
/*****************常用*****************/

int[] countSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int min = arr[0], max = arr[0];
    // 最大最小值
	for (int i = 1; i < n; ++i) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
    }
    // 计数数组
    int[] count = new int[max - min + 1];
    // 辅助数组
    int[] aux = new int[n];
    for (int num : arr) {
        count[num - min]++;
    }
    int index = 0;
    // 累加，count[i]存储小于等于i的元素个数
    for (int i = 1; i < count.length; ++i) {
        count[i] += count[i - 1];
    }
    // 关键步骤，自己该放置在哪个位置
    for (int i = arr.length - 1; i >= 0; --i) {
        aux[--count[arr[i] - min]] = arr[i];
    }
    return aux;
}


int[] countSort(int[] arr) {
    if (arr == null || arr.length == 0) {
        return arr;
    }
    int n = arr.length;
    int min = arr[0], max = arr[0];
    // 最大最小值
	for (int i = 1; i < n; ++i) {
        min = Math.min(min, arr[i]);
        max = Math.max(max, arr[i]);
    }
    // 计数数组
    int[] count = new int[max - min + 1];
    for (int num : arr) {
        count[num - min]++;
    }
    int index = 0;
    // 遍历输出
    for (int i = 0; i < count.length; ++i) {
        while (count[i] > 0) {
            arr[index++] = i + min;
            count[i]--;
        }
    }
    return arr;
}