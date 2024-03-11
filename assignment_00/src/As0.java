import java.io.*;

public class As0 {
    public static void main(String[] args) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));

            int n = Integer.parseInt(rd.readLine());

            int min_price = Integer.MAX_VALUE;
            String min_name = "";

            // get cwd (i wasnt sure what is cwd in java so i tested)
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));

            for (int i = 0; i < n; i++) {
                String s = rd.readLine();
                String name = s.substring(s.indexOf(" ") + 1, s.length());
                int price = Integer.parseInt(s.substring(0, s.indexOf(" ")));

                if (price < min_price) {
                    min_price = price;
                    min_name = name;
                }
            }

            wr.write(min_name);

            rd.close();
            wr.close();

        } catch (Exception e) {
            System.out.println("File IO error probably: " + e);
        }
    }
}
