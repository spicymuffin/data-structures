package structures;

public class queue {

    public int[] data;
    public int size;
    public int front;
    public int rear;

    public queue(int s) {
        s++;
        data = new int[s];
        size = s;
        front = 0;
        rear = s - 1;
    }

    public void enqueue(int x) {
        if (isFull()) {
            System.out.println(front + " " + rear + " full");
            return;
        }
        rear = (rear + 1) % size;
        data[rear] = x;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println(front + " " + rear + " empty");
            return -1;
        }
        int ret = data[front];
        data[front] = 0;
        front = (front + 1) % size;
        return ret;
    }

    public boolean isEmpty() {
        return front == ((rear + 1) % size);
    }

    public boolean isFull() {
        return front == ((rear + 2) % size);
    }
}

