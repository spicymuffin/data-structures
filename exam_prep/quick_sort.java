public class quick_sort {
    public static void partition(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int tmp;
        int pivot = a[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (a[i] <= pivot) {
                j++;
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
            }
        }
        tmp = a[j];
        a[j] = a[left];
        a[left] = tmp;
        partition(a, left, j - 1);
        partition(a, j + 1, right);
    }

    public static void sort(int[] a, int n) {
        partition(a, 0, n - 1);
    }
}
