public class RBT {
    public static boolean RED = true;
    public static boolean BLACK = false;

    public Node root;

    public RBT() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean hasLeftChild(Node node) {
        return node.l != null;
    }

    public boolean hasRightChild(Node node) {
        return node.r != null;
    }

    public boolean insert(int k, String p) {
        Node nd = root;
        Node parent = null;

        while (nd != null) {
            parent = nd;
            if (k < nd.key) {
                nd = nd.l;
            } else if (k > nd.key) {
                nd = nd.r;
            } else {
                // "If k already exists in the tree, does nothing and returns false"
                return false;
            }
        }

        Node new_nd = new Node(k, RED, p);
        // is root
        if (parent == null) {
            root = new_nd;
        } else if (k < parent.key) {
            parent.l = new_nd;
        } else {
            parent.r = new_nd;
        }
        new_nd.p = parent;

        RBT_post_insert_cleanup(new_nd);

        // "Otherwise, k, along with the payload p, is inserted to the tree and true is
        // returned."
        return true;
    }

    public void RBT_post_insert_cleanup(Node x) {
        Node parent = x.p;

        // parent is null -> x is root
        if (parent == null) {
            x.red = BLACK;
            return;
        }

        // if parent is black -> ok
        if (parent.red == BLACK) {
            return;
        }

        Node grandparent = parent.p;
        if (grandparent == null) {
            parent.red = BLACK;
            return;
        }

        Node uncle;
        if (grandparent.l == parent) {
            uncle = grandparent.r;
            // case 1
            if (uncle != null && uncle.red == RED) {
                parent.red = BLACK;
                grandparent.red = RED;
                uncle.red = BLACK;

                // the while loop presented in class is great and all but
                // it doensnt check anything so im going to use recursion
                RBT_post_insert_cleanup(grandparent);
                return;
            } else {
                // case 2
                if (x == parent.r) {
                    rotate_left(parent);
                    // we dont need to "swap fully", bc we are going to exit
                    // soon anyways. just update parent reference
                    parent = x;
                }
                // case 3
                parent.red = BLACK;
                grandparent.red = RED;

                rotate_right(grandparent);

                // just in case
                root.red = BLACK;
            }

        } else if (grandparent.r == parent) {
            uncle = grandparent.l;
            // case 1
            if (uncle != null && uncle.red == RED) {
                parent.red = BLACK;
                grandparent.red = RED;
                uncle.red = BLACK;

                // the while loop presented in class is great and all but
                // it doensnt check anything so im going to use recursion
                RBT_post_insert_cleanup(grandparent);
                return;
            } else {
                // case 2
                if (x == parent.l) {
                    rotate_right(parent);
                    // we dont need to "swap fully", bc we are going to exit
                    // soon anyways. just update parent reference
                    parent = x;
                }
                // case 3
                parent.red = BLACK;
                grandparent.red = RED;

                rotate_left(grandparent);

                // just in case
                root.red = BLACK;
            }
        } else {
            System.out.println("RBT_post_insert_cleanup: critical exception, parent isnt a grandparent's child");
            System.exit(-1);
        }
    }

    public Node find_left_subtree_max(Node nd) {
        while (nd.r != null) {
            nd = nd.r;
        }
        return nd;
    }

    public Node find_sibling(Node nd) {
        Node parent = nd.p;
        if (nd == parent.l) {
            return parent.r;
        } else if (nd == parent.r) {
            return parent.l;
        } else {
            System.out.println("find_sibling: critical exception, parent isnt a child of its grandparent");
            System.exit(-1);
            return null;
        }
    }

    // needed if there is a possibility that the node is null
    public boolean is_black(Node nd) {
        return nd == null || nd.red == BLACK;
    }

    // help
    public boolean delete(int k) {
        Node nd = root;
        while (nd != null && nd.key != k) {
            if (k < nd.key) {
                nd = nd.l;
            } else {
                nd = nd.r;
            }
        }

        if (nd == null) {
            return false;
        }

        Node x, y = nd;
        boolean disappearing_node_color = y.red;

        if (nd.l == null) {
            x = nd.r;
            fix_parent_child_link(nd.p, nd, nd.r);
        } else if (nd.r == null) {
            x = nd.l;
            fix_parent_child_link(nd.p, nd, nd.l);
        } else {
            y = find_left_subtree_max(nd.l);
            disappearing_node_color = y.red;
            x = y.l;
            if (y.p != nd) {
                fix_parent_child_link(y.p, y, y.l);
                y.l = nd.l;
                y.l.p = y;
            }
            fix_parent_child_link(nd.p, nd, y);
            y.r = nd.r;
            y.r.p = y;
            y.red = nd.red;
        }

        if (disappearing_node_color == BLACK) {
            RBT_post_delete_cleanup(x);
        }

        return true;
    }

    // if only i did this before finals...
    public void RBT_post_delete_cleanup(Node x) {
        while (x != root && is_black(x)) {
            Node s;
            if (x == x.p.l) {
                s = x.p.r;
                if (s.red == RED) {
                    // case 1
                    s.red = BLACK;
                    x.p.red = RED;
                    rotate_left(x.p);
                    s = x.p.r;
                }

                if (is_black(s.l) && is_black(s.r)) {
                    // case 2
                    s.red = RED;
                    x = x.p;
                } else {
                    if (is_black(s.r)) {
                        // case 3
                        s.l.red = BLACK;
                        s.red = RED;
                        rotate_right(s);
                        s = x.p.r;
                    }

                    // case 4
                    s.red = x.p.red;
                    x.p.red = BLACK;
                    s.r.red = BLACK;
                    rotate_left(x.p);
                    x = root;
                }
            } else {
                s = x.p.l;
                if (s.red == RED) {
                    // case 1
                    s.red = BLACK;
                    x.p.red = RED;
                    rotate_right(x.p);
                    s = x.p.l;
                }

                if (is_black(s.r) && is_black(s.l)) {
                    // case 2
                    s.red = RED;
                    x = x.p;
                } else {
                    if (is_black(s.l)) {
                        // case 3
                        s.r.red = BLACK;
                        s.red = RED;
                        rotate_left(s);
                        s = x.p.l;
                    }

                    // case 4
                    s.red = x.p.red;
                    x.p.red = BLACK;
                    s.l.red = BLACK;
                    rotate_right(x.p);
                    x = root;
                }
            }
        }
        x.red = BLACK;
    }

    public String query(int k) {
        Node nd = root;
        while (nd != null) {
            if (k == nd.key) {
                // "If k exists in the tree, returns the associated payload."
                return nd.payload;
            } else if (k < nd.key) {
                nd = nd.l;
            } else {
                nd = nd.r;
            }
        }

        // "Otherwise, returns null."
        return null;
    }

    private void fix_parent_child_link(Node parent, Node old_child, Node new_child) {
        if (parent == null) {
            root = new_child;
        } else if (parent.l == old_child) {
            parent.l = new_child;
        } else if (parent.r == old_child) {
            parent.r = new_child;
        } else {
            System.out.println("fix_parent_child_link: critical exception, old_child isnt a child of parent");
            System.exit(-1);
        }

        if (new_child != null) {
            new_child.p = parent;
        }
    }

    public void rotate_right(Node nd) {
        Node parent = nd.p;
        Node left_child = nd.l;

        nd.l = left_child.r;
        if (left_child.r != null) {
            left_child.r.p = nd;
        }

        left_child.r = nd;
        nd.p = left_child;

        fix_parent_child_link(parent, nd, left_child);
    }

    public void rotate_left(Node nd) {
        Node parent = nd.p;
        Node right_child = nd.r;

        nd.r = right_child.l;
        if (right_child.l != null) {
            right_child.l.p = nd;
        }

        right_child.l = nd;
        nd.p = right_child;

        fix_parent_child_link(parent, nd, right_child);
    }
}
