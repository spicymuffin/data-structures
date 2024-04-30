import java.util.LinkedList;
import java.util.Queue;

class As3 {

    public static void printTree(BST tree) {
        BSTNode root = tree.root;
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }

        int depth = maxDepth(root);
        Queue<NodeLevel> queue = new LinkedList<>();
        queue.add(new NodeLevel(root, 0, (int) Math.pow(2, depth - 1) - 1));

        int currentLevel = 0;
        int lastPosition = 0;

        while (!queue.isEmpty()) {
            NodeLevel nodeLevel = queue.poll();

            if (nodeLevel.level != currentLevel) {
                currentLevel = nodeLevel.level;
                lastPosition = 0;
                System.out.println();
            }

            int spaces = nodeLevel.position - lastPosition;
            printSpaces(spaces);
            System.out.print(nodeLevel.node.key);
            lastPosition = nodeLevel.position + 1;

            int offset = (int) Math.pow(2, (depth - nodeLevel.level - 2));

            if (nodeLevel.node.left != null) {
                queue.add(new NodeLevel(nodeLevel.node.left, nodeLevel.level + 1, nodeLevel.position - offset));
            }
            if (nodeLevel.node.right != null) {
                queue.add(new NodeLevel(nodeLevel.node.right, nodeLevel.level + 1, nodeLevel.position + offset));
            }
        }
        System.out.println();
    }

    public static void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    public static int maxDepth(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    public static class NodeLevel {
        BSTNode node;
        int level;
        int position;

        NodeLevel(BSTNode node, int level, int position) {
            this.node = node;
            this.level = level;
            this.position = position;
        }
    }

    public static void main(String[] args) {

        BST bst;

        // duplicate insert
        bst = new BST();
        bst.insert(5);
        bst.insert(5);
        printTree(bst);
        System.out.println("===============test 1 end===============");

        // delete root node (only root)
        bst = new BST();
        bst.insert(5);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        bst.delete(5);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 5");
        System.out.println("===============test 2 end===============");

        // delete root node (one child)
        bst = new BST();
        bst.insert(5);
        bst.insert(3);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        bst.delete(5);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 5");
        System.out.println("===============test 3 end===============");

        // delete root node (two children)
        bst = new BST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        bst.delete(5);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 5");
        System.out.println("===============test 4 end===============");

        // mixed insert/delete
        bst = new BST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        bst.insert(1);
        bst.insert(4);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        bst.delete(3);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 3");
        bst.delete(5);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 5");
        System.out.println("===============test 5 end===============");

        // right heavy tree
        bst = new BST();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        System.out.println("===============test 6 end===============");

        // two child node deletion
        bst = new BST();
        bst.insert(4);
        bst.insert(2);
        bst.insert(6);
        bst.insert(1);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        bst.delete(4);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 4");
        System.out.println("===============test 7 end===============");

        // left subtree max's parent is node to delete
        bst = new BST();
        bst.insert(4);
        bst.insert(2);
        bst.insert(6);
        bst.insert(1);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.delete(4);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        bst.delete(3);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> deleted 3");
        System.out.println("===============test 8 end===============");

        // left subtree max's parent is node to delete (another one)
        bst = new BST();
        bst.insert(4);
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(7);
        bst.insert(5);
        bst.insert(6);
        bst.insert(8);
        bst.insert(9);
        printTree(bst);
        System.out.println(">>>>>>>>>>>>>>> generated");
        System.out.println(bst.search(4));

        // my code related
        // BSTNode[] search_res = bst.search_reference(4);
        // String res0 = search_res[0] == null ? "null" : Integer.toString(search_res[0].key);
        // String res1 = search_res[1] == null ? "null" : Integer.toString(search_res[1].key);
        // System.out.println(res0 + " " + res1);

        bst.delete(4);
        System.out.println(bst.search(4));
        printTree(bst);
        System.out.println("===============test 8 end===============");
    }
}