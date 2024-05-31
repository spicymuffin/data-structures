package sorting;

public class merge_sort {
    public static void sort(int[] arr, int n) {
        int[] aux = new int[n];
        doMergeSort(arr, aux, 0, n - 1);
    }

    // stable. explained below

    // runs in O(nlogn) time. 2n-1 calls to doMergeSort are made,
    // but they have smaller and smaller execution time with each
    // iteration.
    // draw a picture to get a better understanding
    public static void doMergeSort(int[] a, int[] aux, int left, int right) {
        int mid;
        int i, j, k;

        if (left >= right) // if we have weird bounds or we have only one element, we dont have
                           // to do anything at all
            return;
        mid = (left + right) / 2; // standard midpoint calculation
        doMergeSort(a, aux, left, mid); // merge and sort left part
        doMergeSort(a, aux, mid + 1, right); // merge and sort right part

        // set left array reader (i) to left, right array reader (j) to right.
        // k is the auxillary array writer, set it to left, since that's the
        // beginning of the part of the array that we are going to merge
        for (i = left, j = mid + 1, k = left; (i <= mid) && (j <= right);) {
            // if left side is smaller or equal we need to write it first to aux
            // smaller OR EQUAL! if we dont do equal we are goiug to write the
            // right value, which breaks stability
            if (a[i] <= a[j]) {
                aux[k++] = a[i++]; // increment both pointers
            } else {
                aux[k++] = a[j++];
            }
        }
        // if we are not done reading from left array, then read and write until
        // reader reaches midpoint (bound of left array)
        while (i <= mid) {
            aux[k++] = a[i++];
        }
        // if we are not done reading from right array, then read and write until
        // reader reaches right (bound of right array)
        while (j <= right) {
            aux[k++] = a[j++];
        }

        // copy the merged array back to the main array
        // (we need the sorted data for later iterations)
        for (i = left; i <= right; i++) {
            a[i] = aux[i];
        }
    }

    // i caner died, try again later
    public static void divide_and_then_merge_cancer(int[] arr, int[] aux, int l, int r) {
        if (l >= r) { // l > r shouldnt really happen but hehe who knows...
            return;
        }
        int mid = (l + r) / 2;
        divide_and_then_merge_cancer(arr, aux, l, mid);
        divide_and_then_merge_cancer(arr, aux, mid + 1, r);

        int rightarrreader = l;
        int leftarrreader = mid + 1;
        int i = l;
        while (rightarrreader <= r || leftarrreader <= mid) {
            if ((leftarrreader <= mid && arr[leftarrreader] < arr[rightarrreader]) || rightarrreader == r + 1) {
                aux[i++] = arr[leftarrreader++];
            } else {
                aux[i++] = arr[rightarrreader++];
            }
        }

        for (i = l; i <= r; i++) {
            arr[i] = aux[i];
        }
    }
}
