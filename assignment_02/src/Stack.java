
public class Stack {
    private int size;
    private char[] data;
    private int top;

    public Stack(int _size) {
        top = -1;
        size = _size;
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
            return '\0';
        }
        return data[top--];
    }

    public char peek() {
        if (isEmpty()) {
            System.out.println("empty");
            return '\0';
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

}
