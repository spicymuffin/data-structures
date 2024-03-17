class AsB {
    public static void main(String[] args) {
        Oracle o = new Oracle();
        int n, tofind, left, right, mid, elem, pos;
        boolean found = false;

        n = o.getN();
        tofind = o.getWhatToFind();

        left = 0;
        right = n - 1;

        while (left < right) {
            mid = (left + right) / 2;
            elem = o.getElementAt(mid);
            if (elem < tofind) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if ((left == right) && (o.getElementAt(left) == tofind)) {
            found = true;
            pos = left;
        } else {
            found = false;
            pos = -1;
        }

        o.reportAnswer(found, pos);
    }
}