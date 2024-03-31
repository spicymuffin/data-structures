// articles used:
// https://www.educative.io/answers/what-is-integermaxvalue

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CPA2 {
    public static void main(String[] args) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));

            int a = Integer.parseInt(rd.readLine());
            int b = Integer.parseInt(rd.readLine());

            SinglyLinkedList aLL = new SinglyLinkedList();
            SinglyLinkedList bLL = new SinglyLinkedList();
            SinglyLinkedList resLL = new SinglyLinkedList();

            SinglyLinkedList.Node end = aLL.first;
            while (a > 0) {
                int digit = a % 10;
                end = aLL.insertAfter(end, digit);
                a = a / 10;
            }

            end = bLL.first;
            while (b > 0) {
                int digit = b % 10;
                end = bLL.insertAfter(end, digit);
                b = b / 10;
            }

            SinglyLinkedList.Iterator it = new SinglyLinkedList.Iterator(aLL);

            while (it.getCurrentNode().next != null) {
                System.out.println(it.getValue());
            }

            it = new SinglyLinkedList.Iterator(bLL);

            while (it.getCurrentNode().next != null) {
                System.out.println(it.getValue());
            }

            // get cwd (i wasnt sure what is cwd in java so i tested)
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));

            // for (int i = 0; i < n; i++) {
            // String s = rd.readLine();
            // String name = s.substring(s.indexOf(" ") + 1, s.length());
            // int price = Integer.parseInt(s.substring(0, s.indexOf(" ")));

            // if (price < min_price) {
            // min_price = price;
            // min_name = name;
            // }
            // }

            // wr.write();

            rd.close();
            wr.close();

        } catch (Exception e) {
            System.out.println("File IO error probably: " + e);
        }
    }
}