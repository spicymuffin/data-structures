public class bubble_sort {
    static void sort(int[] arr, int n) {
        boolean sorted = false;
        int tmp;

        do { // do while, in order to enforce at least one array full check at least once
            sorted = true; // we always assume that the array is sorted
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) { // but if we do a single swap, it means that
                                           // actually, the array wasn't sorted

                    tmp = arr[i]; // perform swap
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    sorted = false; // we performed a swap so the array isnt sorted
                }
            }
        } while (!sorted); // sort until no swaps are done
    }
}
