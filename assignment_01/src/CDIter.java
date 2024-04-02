public class CDIter {
    public DNode cur;
    public CDList list;

    public boolean atEnd() {
        return (cur == list.header);
    }

    public int getValue() {
        if (this.atEnd()) {
            return -1;
        }
        return cur.data;
    }

    public boolean setValue(int x) {
        if (this.atEnd()) {
            return false;
        }
        cur.data = x;
        return true;
    }

    public void prev() {
        cur = cur.prev;
    }

    public void next() {
        cur = cur.next;
    }

    public boolean delete() {
        if (this.atEnd()) {
            return false;
        }
        list.deleteAt(cur);
        next();
        return true;
    }

    public void insertBefore(int x) {
        list.insertAfter(cur.prev, new DNode(x));
    }

    public void insertAfter(int x) {
        list.insertAfter(cur, new DNode(x));
    }

    // custom
    public CDIter(DNode _cur, CDList _list) {
        cur = _cur;
        list = _list;
    }
}
