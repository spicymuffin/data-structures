public class VertexPriorityQueue {
    public int[] vert_to_queue_indx_map;
    public HeapNode[] queue;

    public int size;
    public int active;

    // init priority queue
    public VertexPriorityQueue(int _size, int _initial_val) {
        size = _size;
        active = size;
        queue = new HeapNode[size];
        vert_to_queue_indx_map = new int[size];

        for (int i = 0; i < size; i++) {
            vert_to_queue_indx_map[i] = i;
            queue[i] = new HeapNode(i, _initial_val);
        }

        for (int i = active / 2 - 1; i >= 0; i--) {
            this.heapify(i);
        }
    }

    // takes queue indices
    // swap to elements in the queue, keep track of indices
    public void swap(int indx0, int indx1) {
        // System.out.println("BEFORE SWAP: " + "(" + indx0 + ", " + indx1 + ")");
        // printraw();
        HeapNode tmp_heapnode;
        tmp_heapnode = queue[indx0];
        queue[indx0] = queue[indx1];
        queue[indx1] = tmp_heapnode;

        int tmp_indx;
        tmp_indx = vert_to_queue_indx_map[queue[indx0].indx];
        vert_to_queue_indx_map[queue[indx0].indx] = vert_to_queue_indx_map[queue[indx1].indx];
        vert_to_queue_indx_map[queue[indx1].indx] = tmp_indx;

        // System.out.println("AFTER SWAP: ");
        // printraw();
    }

    // takes queue indices
    public int min_indx(int indx0, int indx1) {
        if (queue[indx0].d <= queue[indx1].d) {
            return indx0;
        }
        return indx1;
    }

    // takes queue index
    // start: heapify start index
    public void heapify(int start) {
        int left_child_indx, right_child_indx, min_child_indx;
        int tmp;

        for (;;) {
            left_child_indx = 2 * start + 1;
            right_child_indx = 2 * start + 2;

            // both children arent present
            if (left_child_indx > active - 1) { // adjust a for indices
                return;
            }
            // left child is present, right isnt
            else if (right_child_indx > active - 1) { // adjust a for indices
                // if left child is smaller, it should be higher - set swap target to left child
                min_child_indx = left_child_indx;
            }
            // both children are present
            else {
                // find minimal child's indx
                min_child_indx = min_indx(left_child_indx, right_child_indx);
            }

            // swap if the minimal is smaller than start
            if (queue[start].d > queue[min_child_indx].d) {
                swap(start, min_child_indx);

                // set up to traverse further down
                start = min_child_indx;
            }
            // if start was already minmal no changes need to be propagated, return
            else {
                return;
            }
        }
    }

    public void decrease_key(int indx, int val) {
        queue[indx].d = val;
        int parent_indx = (indx - 1) / 2;
        while (indx != 0 && queue[indx].d < queue[parent_indx].d) {
            swap(indx, parent_indx);
            indx = parent_indx;
            parent_indx = (parent_indx - 1) / 2;
        }
    }

    public HeapNode dequeue_min() {
        active--;
        swap(0, active); // became index corrected
        heapify(0);
        return queue[active];
    }

    public int queue_indx(int vert_indx) {
        return vert_to_queue_indx_map[vert_indx];
    }

    public int vert_indx(int queue_indx) {
        return queue[queue_indx].indx;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + get_vert_ref(i).d);
            if (i == active - 1) {
                System.out.println("<----inactive bound---->");
            }
        }
    }

    public void printraw() {
        System.out.println("i d mp");
        for (int i = 0; i < size; i++) {
            System.out
                    .println(i + " " + "(" + queue[i].d + ", " + queue[i].indx + ")" + " " + vert_to_queue_indx_map[i]);
        }
    }

    public boolean is_empty() {
        return active == 0;
    }

    public HeapNode get_vert_ref(int vert_indx) {
        return queue[queue_indx(vert_indx)];
    }
}
