class As3 {
    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(2);
        bst.insert(3);
        bst.insert(-1);
        System.out.println(bst.search(1));
    }
}