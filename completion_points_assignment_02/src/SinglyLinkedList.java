// articles used:
// https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/

public class SinglyLinkedList {
    public Node first;
    public Node last;

    public SinglyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Node insertFirst(int _value) {
        Node inserted = new Node(first, _value);
        first = inserted;
        if (first.next == null) {
            last = first;
        }
        return inserted;
    }

    public int deleteFirst() throws Exception {
        if (first == null) {
            // something broke
            throw new Exception("the linked list was empty.");
        }
        int returnValue = first.value;
        first = first.next;
        if (first.next == null) {
            last = null;
        }
        return returnValue;
    }

    public Node insertAfter(Node _node, int _value) throws Exception {
        if (_node == null) {
            // something broke
            throw new Exception("a node cannot be appended to a null (last node indicator) node.");
        }
        Node inserted = new Node(_node.next, _value);
        _node.next = inserted;
        if (inserted.next == null) {
            last = inserted;
        }
        return inserted;
    }

    public Node insertLast(int _value) {
        Node inserted = new Node(null, _value);
        if (last != null) {
            last.next = inserted;
        }
        if (first == null) {
            first = inserted;
        }
        last = inserted;
        return inserted;
    }

}
