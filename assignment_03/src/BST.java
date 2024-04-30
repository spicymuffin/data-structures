import java.util.LinkedList;
import java.util.Queue;

public class BST {
    public BSTNode root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        // if root is null (tree is empty)
        // insert right away
        if (root == null) {
            root = new BSTNode(key);
            return;
        }

        BSTNode[] search_result = search_reference(key);

        BSTNode subtreeroot_parent = search_result[0];
        BSTNode subtreeroot = search_result[1];

        // if the search result isnt null, then the key
        // exists in tree so we return w/o doing anything
        if (subtreeroot != null) {
            return;
        }

        // if key is bigger than terminal node's key
        // insert as right child
        if (key > subtreeroot_parent.key) {
            subtreeroot_parent.right = new BSTNode(key);
        }
        // otherwise it is its left child. insert as it
        else {
            subtreeroot_parent.left = new BSTNode(key);
        }
    }

    public boolean search(int key) {
        // if null; it doenst exist -> false
        return !(search_reference(key)[1] == null);
    }

    public boolean delete(int key) {
        // if node doesnt exist return false and do nothing
        BSTNode[] search_result = search_reference(key);
        if (search_result[1] == null) {
            return false;
        }

        // it exists
        BSTNode node_to_delete_parent = search_result[0];
        BSTNode node_to_delete = search_result[1];
        int immediate_child_count = get_immediate_child_count(node_to_delete);

        // deleting root is a special case
        if (node_to_delete == root) {
            if (immediate_child_count == 0) {
                // root is deleted, no children. root beocomes null
                root = null;
            }
            // if it had only right child, it's the new root
            else if (immediate_child_count == 1) {
                root = root.right;
            }
            // identical for left
            else if (immediate_child_count == -1) {
                root = root.left;
            }
            // else it had both children
            // find max of left subtree (arbitrarily chosen)
            else {
                BSTNode left_subtree_max = node_to_delete.left;
                BSTNode left_subtree_max_parent = node_to_delete;
                while (left_subtree_max.right != null) {
                    left_subtree_max_parent = left_subtree_max;
                    left_subtree_max = left_subtree_max.right;
                }
                // if it has a left child (it can only have a left child)
                // stitch it to parent of left_subtree_max
                if (left_subtree_max.left != null) {
                    if (left_subtree_max_parent != node_to_delete) {
                        left_subtree_max_parent.right = left_subtree_max.left;
                    } else {
                        left_subtree_max_parent.left = left_subtree_max.left;
                    }
                } else {
                    // if left_subtree_max is directly the node_to_delete's
                    // left child, then we cant be deleting its parent's right subtree
                    if (left_subtree_max_parent != node_to_delete) {
                        left_subtree_max_parent.right = null;
                    } else {
                        left_subtree_max_parent.left = null;
                    }
                }
                // swap key of node to delete to the left_subtree_max's key
                node_to_delete.key = left_subtree_max.key;
            }

            return true;
        }

        // since node to delete had 0 immediate children just update
        // parent's references.
        if (immediate_child_count == 0) {
            // if child's key is bigger than parent's, child was right node
            if (node_to_delete.key > node_to_delete_parent.key) {
                node_to_delete_parent.right = null;
            }
            // else the key was smaller (since they can't be equal) -> child
            // was left node
            else {
                node_to_delete_parent.left = null;
            }
        }
        // if it had only right child, just "stitch" the right child of node to be
        // deleted to the parent
        else if (immediate_child_count == 1) {
            // if child's key is bigger than parent's, child was right node
            if (node_to_delete.key > node_to_delete_parent.key) {
                node_to_delete_parent.right = node_to_delete.right;
            }
            // else the key was smaller (since they can't be equal) -> child
            // was left node
            else {
                node_to_delete_parent.left = node_to_delete.right;
            }
        }
        // identical for left
        else if (immediate_child_count == -1) {
            // if child's key is bigger than parent's, child was right node
            if (node_to_delete.key > node_to_delete_parent.key) {
                node_to_delete_parent.right = node_to_delete.left;
            }
            // else the key was smaller (since they can't be equal) -> child
            // was left node
            else {
                node_to_delete_parent.left = node_to_delete.left;
            }
        }
        // else it had both children
        // find max of left subtree (arbitrarily chosen)
        else {

            BSTNode left_subtree_max = node_to_delete.left;
            BSTNode left_subtree_max_parent = node_to_delete;
            while (left_subtree_max.right != null) {
                left_subtree_max_parent = left_subtree_max;
                left_subtree_max = left_subtree_max.right;
            }

            // if it has a left child (it can only have a left child)
            // stitch it to parent of left_subtree_max
            if (left_subtree_max.left != null) {
                if (left_subtree_max_parent != node_to_delete) {
                    left_subtree_max_parent.right = left_subtree_max.left;
                } else {
                    left_subtree_max_parent.left = left_subtree_max.left;
                }
            } else {
                // if left_subtree_max is directly the node_to_delete's
                // left child, then we cant be deleting its parent's right subtree
                if (left_subtree_max_parent != node_to_delete) {
                    left_subtree_max_parent.right = null;
                } else {
                    left_subtree_max_parent.left = null;
                }
            }
            // swap key of node to delete to the left_subtree_max's key
            node_to_delete.key = left_subtree_max.key;
        }
        return true;
    }

    // custom below

    // seaches, but retuns reference if found
    public BSTNode[] search_reference(int key) {
        // if tree is empty obv value doesnt exist in tree
        if (root == null) {
            return new BSTNode[] { null, null };
        }

        // if root has the key
        if (root.key == key) {
            return new BSTNode[] { null, root };
        }

        BSTNode subtreeroot = root;
        BSTNode subtreeroot_parent = null;

        while (subtreeroot != null) {

            // if key is bigger than root, we go to right
            if (key > subtreeroot.key) {
                // cache subtreeroot, bc it will be the parent of the node
                // that gets picked below
                subtreeroot_parent = subtreeroot;
                subtreeroot = subtreeroot.right;
            }
            // if key is smaller than root we go to left
            else if (key < subtreeroot.key) {
                // cache subtreeroot, bc it will be the parent of the node
                // that gets picked below
                subtreeroot_parent = subtreeroot;
                subtreeroot = subtreeroot.left;
            }
            // if equals it means we found the key in the BST
            else {
                return new BSTNode[] { subtreeroot_parent, subtreeroot };
            }
        }

        // if we traversed and ended up at a null node
        // it means the node doenst exist in tree
        return new BSTNode[] { subtreeroot_parent, null };
    }

    // 0 for 0
    // 1 for right child
    // -1 for left child
    // 2 for both
    public int get_immediate_child_count(BSTNode root_node) {
        int immediate_child_count = 0;
        if (root_node.left != null && root_node.right != null) {
            return 2;
        } else if (root_node.left == null && root_node.right != null) {
            return 1;
        } else if (root_node.left != null && root_node.right == null) {
            return -1;
        } else {
            return 0;
        }
    }
}
