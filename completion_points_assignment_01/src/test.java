public class test {

    public static int search(int n, int[] a, int x) {
        int left = 0;
        int right = n;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (a[mid] >= x)
                left = mid;
            else
                right = mid;
        }
        if (left + 1 == right && a[left] == x)
            return left;
        else
            return -1;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] arr = new int[] { 1, 2, 7, 8, 9, 10 };
        int x = 9;
        System.out.println(search(6, arr, 9));
    }

}
