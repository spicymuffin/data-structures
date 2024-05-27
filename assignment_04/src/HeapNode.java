public class HeapNode {
    int indx; // vertex's index (name)
    int d; // vertex's d value

    public HeapNode(int _indx, int _d) {
        indx = _indx;
        d = _d;
    }

    public HeapNode() {
        indx = -1;
        d = -1;
    }
}
