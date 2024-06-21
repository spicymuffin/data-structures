package sorting;

public class quick_sort {
    // isn't stable. it does "random" swaps with the pivot
    // for example: {1(1), 2, 1(2)}
    // pivot is 1(1). edge is 0
    // i checks 2, it's bigger than pivot so it leaves it alone.
    // then, i checks 1(2), it is eqal to the pivot, soit has to swap
    // it with the edge + 1 th element, so 2.
    // the array now looks like {1(1), 1(2), 2}
    // now, we need to pivot and edge, to put pivot in its correct spot.
    // 1(1) and 1(2) get swapped, breaking stability
    //
    // this last swap is required to place the pivot element in its
    // sorted position, but in the process of doing that we can ennd up
    // propagating a same-value element to the left end.

    // runs in O(nlogn) - worst case is when array is already sorted
    // it will cause the left partition to always be null, so we'll have
    // to repartition n-1 elements, instead of n/2 and n/2.
    // that will cause the effect of the running time of the algorithm to
    // be like iterative almost, instead of dividing the thing into two always
    //
    // this behaviour is directly tied to the fact that we are chosing the left
    // element, to be the pivot, which is always going to be previous pivot + 1
    // in an already sorted array.
    //
    // we can mitigate this by choosing a pivot at random.
    public static void partition(int[] a, int left, int right) {
        if (left >= right) {
            return; // if the left and right pointers meet (or exceed each other) then the section
                    // is already partitioned.
        }

        // to choose a random pivot, choose it first, then put into a[left], since it is
        // necessary
        // to have a pivot that does not interfere with the partitioning process

        int tmp;
        int edge = left; // the swap index is initialized to pivot.
                         // start partitioning starting from (pivot+1)th element
        for (int i = left + 1; i <= right; i++) {
            // if an element is smaller or equal than the pivot we should move
            if (a[i] < a[left]) {
                edge++;
                tmp = a[edge];
                a[edge] = a[i];
                a[i] = tmp;
            }
            // edge always is edging
            // edging between those that are larger and smaller than pivot.
            // edge is always on the last element, that is equal or smaller than pivot.
            // when we find an item, which is smaller than pivot, after the array
            // scanning pointer has already advanced further than the edge pointer,
            // we need to swap the new smaller value with a value that is bigger.
            // we can swap it with the value at edge + 1, bc that will contain the first
            // element which is (strictly) bigger than the pivot. that way, after we swap,
            // the newer smaller element will be again the latest smaller than pivot value
            // and the edge pointer will be set to the correct place.
        }
        // swap the edge with the pivot. since a[edge] is <= than pivot, if we swap
        // them, still, the order will be maintained.
        tmp = a[edge];
        a[edge] = a[left];
        a[left] = tmp;
        partition(a, left, edge - 1); // partition everything to the left of the pivot (former edge)
        partition(a, edge + 1, right); // partition everything to the right of the pivot (former edge)
    }

    public static void sort(int[] a, int n) {
        partition(a, 0, n - 1);
    }
}
