class insertion_sort {
    static void sort(int[] arr, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                } else {
                    break;
                }
            }
        }
    }
}