package sorting;

// unstable. if we are "unlucky" the ith element (the element we are trying to sort in current iteration)
// is going to "jump over" a value that has the same value as it
// example: {23(1), 12, 13, 14, 23(2), 1}
// during first iteration 23(1) is goint to get swapped with 1 at the end, hopping over 23(2).

// runs in O(n^2) worst case doesnt really matter, each iteration we have to do a full array wipe to find the
// current minimal element which is reaaaally bad

public class selection_sort {
    public static void sort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int swapindex = i; // declare the swap target (the mininal value in array) as the current element
                               // (we pretend its the smallest. if we dont find things smaller than it is the
                               // smallest)

            for (int j = i + 1; j < n; j++) { // we look from i + 1th element. (we could from i, but its redundant,
                                              // since)
                                              // arr[i] will never be smaller than arr[i]
                                              //     ^ = j                             ^ = swapindex

                if (arr[j] < arr[swapindex]) {
                    swapindex = j; // if found something smaller set new swap target
                }
            }
            int tmp = arr[i]; // actulally perform the swap
            arr[i] = arr[swapindex];
            arr[swapindex] = tmp;
        }
    }
}
