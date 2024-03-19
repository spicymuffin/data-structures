public class test {

    public static int search(int n, int[] a, int x) {
        int left = 0;
        int right = n - 1;

        int mid;

        left = 0;
        right = n - 1;
        while (left < right) {
            mid = (left + right) / 2 + 1;
            if (a[mid] >= x)
                left = mid;
            else
                right = mid - 1;
        }
        if ((left == right) && (a[left] == x)) {
            return left;
        } else
            return -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] arr = new int[] { 1, 0, -3};
        int x = 0;
        System.out.println(search(n, arr, x));
    }

}
