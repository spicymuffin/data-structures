package structures;

public class queue {

    public int[] data;
    public int size;
    public int front;
    public int rear;

    public queue(int s) {
        s++;
        data = new int[s]; // create an array of size + 1,
                           // because we need the "flag" cell
        size = s; // "virtual size" is size, bc when we do calculations we need to
                  // address by % of virtual size.
                  // so this doesnt reflect the # of data items we can store with this
                  // queue.
        front = 0;
        rear = s - 1; // rear is set to the last index of the array
                      // (index of "flag" cell) or alternatively could be set to -1
    }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println(front + " " + rear + " full");
            return;
        }
        rear = (rear + 1) % size; // increase by one then modulus
        data[rear] = x; // and only then store
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println(front + " " + rear + " empty");
            return -1;
        }
        int ret = data[front]; // first get data
        data[front] = 0; // clean up (optional)
        front = (front + 1) % size; // then advance by one read head
                                    // but dont forget to modulus
        return ret; // return data
    }

    public boolean isEmpty() {
        return front == ((rear + 1) % size); // if back is at flag cell (as initialized) then
                                             // the next "modulused" cell should be the front cell
    }

    public boolean isFull() {
        return front == ((rear + 2) % size); // if back is two cells away from front it means that we are
                                             // full bc that means rear is "behind" front
                                             // (but it actually isnt bc we are using modulo all the time)
    }
}
