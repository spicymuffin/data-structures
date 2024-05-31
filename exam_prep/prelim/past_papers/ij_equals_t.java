package past_papers;

import sorting.quick_sort;

public class ij_equals_t {

    public void quicksort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int pivot = arr[l];
        int edge = l;
        int tmp;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < arr[edge]) {
                edge++;
                tmp = arr[i];
                arr[i] = arr[edge];
                arr[edge] = tmp;
            }
        }
        tmp = arr[l];
        arr[l] = arr[edge];
        arr[edge] = tmp;
        quicksort(arr, l, edge - 1);
        quicksort(arr, edge + 1, r);
    }

    public static boolean ijExists(int t, int[] arr, int n) {
        quick_sort(a, 0, n - 1);
        int i = 0;
        int j = n - 1;

        while (i < j) {
            if (arr[i] * arr[j] > t) {
                j--;
            } else if (arr[i] * arr[j] < t) {
                i++;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] input = {1,2,3}
    }
}
