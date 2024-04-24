package structures;

public class stack {
    int[] data;
    int size;
    int top = -1;

    public stack(int _size) {
        data = new int[_size]
        size = _size;
    }

    int peek() {
        if (isEmpty()) {
            System.out.println("couldnt peek: empty");
            return -1;
        }
        return data[top];
    }

    int pop() {
        if (isEmpty()) {
            System.out.println("couldnt pop: empty");
            return -1;
        }
        return data[top--];
    }

    void push(int _x) {
        if (isFull()) {
            System.out.println("couldnt push: full");
            return;
        }
        data[++top] = _x;
    }

    boolean isFull() {
        return top == size - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }
}