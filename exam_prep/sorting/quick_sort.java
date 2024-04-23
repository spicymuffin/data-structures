package sorting;
public class quick_sort {
    public static void partition(int[] a, int left, int right) {
        if (left >= right) {
            return; // if the left and right pointers meet (or exceed each other) then the section
                    // is already partitioned.
        }

        int tmp;
        int pivot = a[left]; // pivot is the always the first element
        int j = left; // the swap index is initialized to pivot.
                      // start partitioning starting from pivot element
        for (int i = left + 1; i <= right; i++) {
            if (a[i] <= pivot) {
                // if an element is smaller than the pivot we should move
                j++;
                tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
            }
        }
        tmp = a[j];
        a[j] = a[left];
        a[left] = tmp;
        partition(a, left, j - 1); // partition everything to the left of the pivot
        partition(a, j + 1, right); // partition everything to the right of the pivot
    }

    public static void sort(int[] a, int n) {
        partition(a, 0, n - 1);
    }
}
