package sorting;

// is stable. it's going to shift an element only if the elements is strictly bigger than the
// element we are trying to insert. that ensures that in situations where we have elements with
// same value, we dont swap their places. the one that came earlier will be inserted earlier, and
// the new ones with the same value won't try to shift them out of place since they arent strictly
// larger.

// runs in O(n^2). worst case is when we have to shift all elements of sorted array to accomodate
// the newcomer. the way we achieve that is having a reverse sorted list.

class insertion_sort {
    static void sort(int[] arr, int n) {
        int tmp;
        int j;
        for (int i = 1; i < n; i++) { // start at 1 since one element is sorted from the getgo
            tmp = arr[i]; // cache the element we are trying to insert
            for (j = i - 1; j >= 0; j--) { // set bound to j >= 0, bc we can't be checking negative indices
                if (tmp < arr[j]) { // you can put this condition in the for loop actually...
                                    //
                                    // make sure it's stable by not setting to not strict sign
                                    // setting a not strict sign will result in the items with
                                    // same value to be reversed - ? [needs checking, not sure]
                    arr[j + 1] = arr[j]; // shift elements until this one
                } else {
                    break; // if arr[j] does not satisfy, loop needs to end
                }
            }
            arr[j + 1] = tmp; // we need to store in j + 1, because if we exited the loop, it means
                              // that j is pointing to an element that does not satisfy our condition
                              // so we return to the previouis state by doing j + 1
        }
    }
}