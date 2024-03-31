// articles used:
// https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/

public class SinglyLinkedList {
    class Node {
        public Node next;
        public int value;

        public Node(Node _next, int _value) {
            next = _next;
            value = _value;
        }
    }

    class Iterator {
        Node current;

        public Iterator(Node current) {

        }
    }

    public Node first;
    public Node last;

    public SinglyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Node insertFront(int _value) {
        Node inserted = new Node(first, _value);
        first = inserted;
        return inserted;
    }

    public int deleteFirst() throws Exception {
        if (first == null) {
            // something broke
            throw new Exception("the linked list was empty.");
        }
        int returnValue = first.value;
        first = first.next;
        return returnValue;
    }

    public Node insertAfter(Node _node, int _value) throws Exception {
        if (_node == null) {
            // something broke
            throw new Exception("a node cannot be appended to a null (last node indicator) node.");
        }
        Node inserted = new Node(_node.next, _value);
        _node.next = inserted;
        return inserted;
    }

    public Node insertLast(int _value) {
        Node inserted = new Node(null, _value);
        last.next = inserted;
        return inserted;
    }

}
