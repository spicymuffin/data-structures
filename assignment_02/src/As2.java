import java.io.*;

public class As2 {
    // _mode == true -> stored in stack
    // _mode == false -> not stored in stack
    public static int eval(char b, boolean _mode) {
        if (('a' <= b && b <= 'z') || ('A' <= b && b <= 'Z')) {
            return 0;
        } else if (b == ')') {
            return 5;
        } else if (b == '(' && !_mode) {
            return 4;
        } else if (b == '*' || b == '/') {
            return 3;
        } else if (b == '+' || b == '-') {
            return 2;
        } else if (b == '(' && _mode) {
            return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String input_string = "";
        try {
            BufferedReader rd = new BufferedReader(new FileReader("input.txt"));
            input_string = rd.readLine();
            rd.close();
        } catch (Exception e) {
            System.out.println("File IO error probably: " + e);
        }

        Stack operator_stack = new Stack(input_string.length());
        char[] answer = new char[input_string.length()];
        int answer_writer = 0;

        for (int i = 0; i < input_string.length(); i++) {
            char eval_char = input_string.charAt(i);
            int eval_result = eval(eval_char, false);

            // is an operand
            if (eval_result == 0) {
                answer[answer_writer++] = eval_char;
                continue;
            }

            // something broke really bad
            if (eval_result == -1) {
                System.out.println("critical error: eval returned -1");
                return;
            }

            // assume eval result > 0 (operator)

            // is a ')'
            if (eval_result == 5) {
                while (!operator_stack.isEmpty() && eval(operator_stack.peek(), true) != 1) {
                    answer[answer_writer++] = operator_stack.pop();
                }
                operator_stack.pop();
            } else {
                while (!operator_stack.isEmpty() && eval(operator_stack.peek(), true) >= eval_result) {
                    answer[answer_writer++] = operator_stack.pop();
                }
                operator_stack.push(eval_char);
            }
        }
        while (!operator_stack.isEmpty()) {
            answer[answer_writer++] = operator_stack.pop();
        }
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));
            wr.write(answer, 0, answer_writer);
            wr.close();
        } catch (Exception e) {
            System.out.println("File IO error probably: " + e);
        }
    }
}
