// articles used:
// https://www.educative.io/answers/what-is-integermaxvalue
// https://stackoverflow.com/questions/47541459/no-enclosing-instance-is-accessible-must-qualify-the-allocation-with-an-enclosi

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CPA2 {

    // fast exponentiation (legacy code)
    static int pow(int x, int power) {
        int result = 1;
        while (power > 0) {
            if (power % 2 == 1) {
                result *= x;
            }
            x *= x;
            power /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));

            // parse out integers
            // int a = Integer.parseInt(rd.readLine());
            // int b = Integer.parseInt(rd.readLine());

            // NEVERMIND n and m can be 10k lol
            // do it manually

            String a = rd.readLine();
            String b = rd.readLine();

            // linked lists
            SinglyLinkedList aLL = new SinglyLinkedList();
            SinglyLinkedList bLL = new SinglyLinkedList();
            SinglyLinkedList rLL = new SinglyLinkedList();

            // push them into LLs
            for (int i = a.length() - 1; i >= 0; i--) {
                int digit = a.charAt(i) - '0';
                aLL.insertLast(digit);
            }

            for (int i = b.length() - 1; i >= 0; i--) {
                int digit = b.charAt(i) - '0';
                bLL.insertLast(digit);
            }

            // iterators
            Iterator aIterator = new Iterator(aLL);
            Iterator bIterator = new Iterator(bLL);

            Iterator rIterator = new Iterator(rLL);
            Node exponentShiftMarker = null;

            int nextStartExponent = 1;
            int currentExponent = 0;
            // one nested loop, O(n*m) (i think)
            while (aIterator.getCurrentNode() != null) {
                while (bIterator.getCurrentNode() != null) {
                    // do digit-wise multiplication
                    int multiplicationResult = bIterator.getValue() * aIterator.getValue();

                    if (rIterator.getCurrentNode() == null) {
                        rLL.insertLast(multiplicationResult);
                        rIterator.mvToLast();
                    } else {
                        rIterator.getCurrentNode().value += multiplicationResult;
                    }

                    if (currentExponent == nextStartExponent) {
                        exponentShiftMarker = rIterator.getCurrentNode();
                    }
                    // shift to left
                    currentExponent++;
                    bIterator.mvToNext();
                    rIterator.mvToNext();
                }
                bIterator.mvToFirst();

                // move to the shift exponentMarker
                rIterator.mvToNode(exponentShiftMarker);
                aIterator.mvToNext();

                // shifts the multiplication values to the left by this in next iteration
                currentExponent = nextStartExponent;

                // set the iteration after the next's exponent shift
                nextStartExponent++;
            }

            // jmp to beginning of the LL to start calculating digits
            rIterator.mvToFirst();

            // calcualte digits
            while (rIterator.getCurrentNode() != null) {
                int carryOver = rIterator.getValue() / 10;
                rIterator.getCurrentNode().value = rIterator.getValue() % 10;
                if (rIterator.getNextNode() == null && carryOver > 0) {
                    rLL.insertLast(carryOver);
                } else if (carryOver > 0) {
                    rIterator.getNextNode().value += carryOver;
                }
                rIterator.mvToNext();
            }
            String answer = "";

            // jmp to beginning of the LL to build answer
            rIterator.mvToFirst();

            // build answer
            while (rIterator.getCurrentNode() != null) {
                answer = Integer.toString(rIterator.getCurrentNode().value) + answer;
                rIterator.mvToNext();
            }

            wr.write(answer);

            rd.close();
            wr.close();

        } catch (Exception e) {
            System.out.println("oops the program got cancer and died: " + e);
        }
    }
}