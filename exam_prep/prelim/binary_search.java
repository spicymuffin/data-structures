public class binary_search {
    // searches for first x in arr. if not found returns -1;
    static int search(int[] arr, int n, int x) {
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int pivot = (right + left) / 2;
            if (x > arr[pivot]) {
                left = pivot + 1;
            } else {
                right = pivot;
            }
        }

        if (left == right && x == arr[left]) {
            return left;
        } else {
            return -1;
        }
    }

    static int search_last(int[] arr, int n, int x) {
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int pivot = (right + left + 1) / 2; // round up means that when n = 2 if we
                                                // use standard binsearch left right re-eval
                                                // we will end up in an infinite loop
                                                // so change sign to x <, as opposed to x >.
            if (x < arr[pivot]) {
                right = pivot - 1;
            } else {
                left = pivot;
            }
        }

        if (left == right && x == arr[left]) {
            return left;
        } else {
            return -1;
        }
    }

    static int search_insert_position(int[] a, int n, int x) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] < x)
                left = mid + 1;
            else
                right = mid;
        } // pretty standard binsearch up to this point

        boolean found;
        if (left == right) { // null input check
            if (a[left] == x) { // if what we want to insert was found
                                // then we insert it as the one before it, so in its place
                                // so return to insert to left.
                found = true;
                return left;
            } else { // if we didnt find the element
                     // it means that we are at an elem, that is either smaller or
                     // greater than x.
                     // in that case, just check if it's greater or smaller!
                     // if its greater, x should be after it, so in its place + 1 (left + 1)
                     // o/w its smaller, x should be be before it, so ins place (left)
                found = false;
                if (a[left] < x) {
                    return left + 1;
                } else {
                    return left;
                }
            }
        } else { // if this happens, that means that the array was null,
                 // left is initialized to 0 so return 0 (we have to insert as 0th elem)
            found = false;
            return left;
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

    static int find_insert_pos(int[] arr, int n, int x) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return arr[0];
    }
}
