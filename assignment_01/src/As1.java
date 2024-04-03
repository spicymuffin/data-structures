// DO NOT SUBMIT
public class As1 {
    public static void print(String s) {
        System.out.println(s);
    }

    public static void print() {
        System.out.println();
    }

    public static void printLL(CDList l) {
        for (CDIter it = l.getIter(); !it.atEnd(); it.next()) {
            System.out.print(it.cur.prev.data + "<-" + it.cur.data + "->" + it.cur.next.data + "; ");
        }
        System.out.println();
    }

    public static void printNode(DNode n) {
        print(n.prev.data + "<-" + n.data + "->" + n.next.data);
    }

    public static void main(String[] args) {
        CDList l = new CDList();
        for (int i = 1; i < 11; i++) {
            l.insertAtEnd(i);
        }

        printLL(l);

        CDIter it = l.getIter();

        it.delete();
        print(it.getValue() + "");
        printLL(l);

        CDList l2 = new CDList();
        printLL(l2);
        CDIter it2 = l2.getIter();
        it2.insertAfter(1);
        printLL(l2);
        if (false == it2.delete()) {
            print("gay");
        }
        printLL(l2);
        it2.prev();
        printNode(it2.cur);
        it2.prev();
        printNode(it2.cur);
        it2.prev();
        printNode(it2.cur); 
        print("hi");
    }
}
