public class CDList {
    public final DNode header;

    public CDList() {
        DNode dnode = new DNode();

        dnode.next = dnode;
        dnode.prev = dnode;

        header = dnode;
    }

    public void insertAtFront(int x) {
        DNode inserted = new DNode();
        inserted.data = x;
        this.insertAfter(header, inserted);
    }

    public void insertAtEnd(int x) {
        DNode inserted = new DNode();
        inserted.data = x;
        this.insertAfter(header.prev, inserted);
    }

    public int deleteFromFront() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.deleteAt(header.next);
    }

    public int deleteFromEnd() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.deleteAt(header.prev);
    }

    public CDIter getIter() {
        if (this.isEmpty()) {
            return new CDIter(header, this);
        }
        return new CDIter(header.next, this);
    }

    // custom
    public boolean isEmpty() {
        return (header.next == header);
    }

    // works always
    public void insertAfter(DNode a, DNode i) {
        // assume a -> b
        // becomes a -> i -> b

        DNode b = a.next;

        a.next = i;
        b.prev = i;

        i.next = b;
        i.prev = a;
    }

    // unsafe, use only when sure that
    // the delition is valid
    public int deleteAt(DNode d) {
        // assume a -> d -> b
        // becomes a -> b

        DNode a = d.prev;
        DNode b = d.next;

        a.next = b;
        b.prev = a;

        return d.data;
    }
}