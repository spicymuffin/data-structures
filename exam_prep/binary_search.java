public class binary_search {
    static int search(int[] arr, int n, int x) {
        int left = 0;
        int right = n - 1;
        int pivot = -1;

        while (left < right) {
            pivot = (right + left) / 2;
            if (x > arr[pivot]) {
                left = pivot + 1;
            } else {
                right = pivot;
            }
        }

        if (left == right && x == arr[left]) {
            return pivot;
        } else {
            return -1;
        }
    }

    static int search_recursive(int[] arr, int left, int right, int x) {
        if (left > right) {
            return -1; // left exceeded right, we still didnt find value so gg ff
        }

        int pivot = (left + right) / 2;
        if (arr[pivot] == x) {
            return pivot;
        } else if (arr[pivot] > x) {
            return search_recursive(arr, left, pivot - 1, x);
        } else {
            return search_recursive(arr, pivot + 1, right, x);
        }
    }
}
