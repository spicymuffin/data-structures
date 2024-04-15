class insertion_sort {
    static void sort(int[] arr, int n) {
        int tmp;
        int j;
        for (int i = 0; i < n; i++) {
            tmp = arr[i];
            for (j = i - 1; j >= 0; j--) {
                if (tmp < arr[j]) { // make sure it's stable yo
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;
        }
    }
}