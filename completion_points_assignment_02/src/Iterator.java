public class Iterator {
    public Node current;
    public SinglyLinkedList LL;

    public Iterator(SinglyLinkedList _LL) {
        LL = _LL;
        current = LL.first;
    }

    public Node mvToNext() {
        current = current.next;
        return current;
    }

    public Node mvToLast() {
        current = LL.last;
        return current;
    }

    public Node mvToFirst() {
        current = LL.first;
        return current;
    }

    public int getValue() {
        return current.value;
    }

    public Node getNextNode() {
        return current.next;
    }

    public Node getLastNode() {
        return LL.last;
    }

    public Node getFirstNode() {
        return LL.first;
    }

    public Node getCurrentNode() {
        return current;
    }

    public Node mvToNode(Node _node) {
        current = _node;
        return current;
    }
}
