public class Node {
    public int key;
    public boolean red;
    public String payload;

    public Node l, r, p;

    public Node(int _key, boolean _red, String _payload) {
        key = _key;
        red = _red;
        payload = _payload;
        l = r = p = null;
    }

    // null node (black colored)
    public Node() {
        key = -1;
        red = false;
        payload = "";
        l = r = p = null;
    }
}
