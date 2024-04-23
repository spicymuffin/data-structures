import sorting.bubble_sort;

public class sort_tester {

    public static void printarr(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int[] test_array = { 3, 6, 1, 2, 8, 9, 3 }; // n = 7

        bubble_sort.sort(test_array, 7);

        printarr(test_array, 7);

        System.out.println(binary_search.search_recursive(test_array, 0, 7 - 1, 9));
    }
}
