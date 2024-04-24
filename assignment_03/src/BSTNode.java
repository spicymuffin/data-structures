public class BSTNode {
    public int key;
    public BSTNode left, right;

    // custom below

    public BSTNode(int _key, BSTNode _left, BSTNode _right) {
        key = _key;
        left = _left;
        right = _right;
    }

    public BSTNode(int _key) {
        key = _key;
        left = null;
        right = null;
    }
}
