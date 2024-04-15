
public class Stack {
    private int size;
    private char[] data;
    private int top;

    public Stack(int _size) {
        top = -1;
        data = new char[_size];
    }

    public void push(char _item) {
        if (isFull()) {
            System.out.println("full");
            return;
        }
        data[++top] = _item;
    }

    public char pop() {
        if (isEmpty()) {
            System.out.println("empty");
            return 0;
        }
        return data[top--];
    }

    public char peek() {
        return data[top];
    }

    public boolean isEmpty() {
        return -1 == top;
    }

    public boolean isFull() {
        return top == size - 1;
    }

}
