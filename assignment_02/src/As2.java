import java.io.*;

public int eval(char b) {
    if ('a' < b < 'z') {
        return 3;
    } else if (b == "+" || b == "-") {
        return 2;
    } else if (b == "(") {
        return 1;
    }

}

public class As2 {
    public static void main(String[] args) {
        BufferedReader rd = new BufferedReader(new FileReader("input.txt"));
        String input_string = rd.readLine();
        rd.close();

        Stack input_stack = new Stack(input_string.length());

        for (int i = 0; i < input_string.length(); i++) {
            // convert
        }

        FileWriter wr = new BufferedWriter(new FileWriter("output.txt"));
        wr.close();
    }
}
