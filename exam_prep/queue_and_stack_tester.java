public class queue_and_stack_tester {
    public static void printarr(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int[] test_array = { 1, 2, 3, 4, 5, 6, 7 }; // n = 7

        queue a = new queue(5);

        printarr(a.data, 6);
        a.enqueue(1);
        printarr(a.data, 6);
        a.enqueue(2);
        printarr(a.data, 6);
        a.enqueue(3);
        printarr(a.data, 6);
        a.enqueue(4);
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.enqueue(5);
        printarr(a.data, 6);
        a.enqueue(6);
        printarr(a.data, 6);
        a.enqueue(7);
        printarr(a.data, 6);
        a.enqueue(8);
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
        a.dequeue();
        printarr(a.data, 6);
    }
}
