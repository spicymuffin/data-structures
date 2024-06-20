public class LLNode {
    int v1_indx; // connected vertex's index
    int weight; // connection to the other vertex's weight
    LLNode next; // pointer to next node in LL (has nothing to do with the graph)

    public LLNode(int _v1_indx, int _weight, LLNode _next) {
        v1_indx = _v1_indx;
        weight = _weight;
        next = _next;
    }

    public LLNode() {
        v1_indx = -1;
        weight = -1;
        next = null;
    }
}
