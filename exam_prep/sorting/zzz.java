package sorting;

public class zzz {

    void zzz_selection_sort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int min_index = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }

            int tmp = arr[i];
            arr[i] = arr[min_index];
            arr[min_index] = tmp;
        }
    }

    void zzz_bubble_sort(int[] arr, int n) {
        boolean sorted = true;
        do {
            sorted = true;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    void zzz_quick_sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int pivot = arr[l];
        int edge = l;
        int tmp;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] <= pivot) {
                edge++;
                tmp = arr[edge];
                arr[edge] = arr[i];
                arr[i] = tmp;
            }
        }

        tmp = arr[edge];
        arr[edge] = arr[l];
        arr[l] = tmp;

        zzz_quick_sort(arr, l, edge - 1);
        zzz_quick_sort(arr, edge + 1, r);
    }

    void zzz_insertion_sort(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int tmp = arr[i];
            int j;
            for (j = i - 1; tmp < arr[j] && j >= 0; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }

    void zzz_merge_sort(int[] arr, int n) {
        int[] ax = new int[n];
        zzz_doMergeSort(arr, 0, n - 1, ax);
    }

    void zzz_doMergeSort(int[] arr, int l, int r, int[] aux) {
        if (l >= r) {
            return;
        }

        int mid = (l + r) / 2;
        doMergeSort(arr, l, mid, aux);
        doMergeSort(arr, mid + 1, r, aux);

        int i, j, k;
        for (i = l, j = mid + 1, k = l; i <= mid && j <= r;) {
            if (arr[i] <= arr[j]) {
                aux[k++] = arr[i++];
            } else {
                aux[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            aux[k++] = arr[i++];
        }

        while (j <= r) {
            aux[k++] = arr[j++];
        }

        for (int i = l; i <= r; i++) {
            arr[i] = aux[i];
        }
    }
}
