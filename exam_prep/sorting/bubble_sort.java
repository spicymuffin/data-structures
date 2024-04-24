package sorting;

// stable. we never swap elements, that have the same value. and we dont have any other "random" swappings.

// runs in O(n^2). worst case - minimal at end, we have to cylce the entire list n times, bc each iteration
// moves it by one place to the left (ascending order)

public class bubble_sort {
    public static void sort(int[] arr, int n) {
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
