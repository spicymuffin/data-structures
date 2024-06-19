import java.math.BigInteger;
import java.io.*;

class test_3989 {

    public static void main(String[] args) {
        HTable ht;

        String str;
        int op_cnt, htable_len, p;
        int failed_insert_cnt = 0;

        BufferedReader rd = null;
        BufferedWriter wr = null;

        String query_result;
        String operation;
        String key;
        int pos;

        int load = 0;

        int hash;

        long starttime, elapsedtime;

        try {
            // read in the input
            rd = new BufferedReader(new FileReader("input.txt"));
            wr = new BufferedWriter(new FileWriter("output.txt"));

            htable_len = Integer.parseInt(rd.readLine());
            p = Integer.parseInt(rd.readLine());

            op_cnt = Integer.parseInt(rd.readLine());

            ht = new HTable(htable_len);

            // profiling start
            starttime = System.nanoTime();

            for (int i = 0; i < op_cnt; i++) {
                str = rd.readLine();
                pos = str.indexOf(" ");

                operation = str.substring(0, pos);
                key = str.substring(pos + 1);

                if (operation.charAt(0) == 'i') {
                    if (!ht.insert(new BigInteger(key), key)) {
                        failed_insert_cnt++;
                    } else {
                        load++;
                    }
                } else if (operation.charAt(0) == 'q') {
                    ht.query(new BigInteger(key));
                } else if (operation.charAt(0) == 't') {
                    hash = ht.hf.hash(new BigInteger(key));
                    wr.write(Integer.toString(hash) + '\n');
                }
            }

            // profiling end
            elapsedtime = System.nanoTime() - starttime;
            System.out.println("exec_time:  " + Double.toString((double) elapsedtime / 1000000000));
            // System.out.println("coll_count: " + ht.collision_cnt);
            // System.out.println("fld_insert: " + failed_insert_cnt);
            // System.out.println("load:       " + load);

            rd.close();
            rd = null;

            wr.close();
            wr = null;

        } catch (

        Exception e) {
            e.printStackTrace();
            System.out.println("error.");
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("error.");
                }
            }
            if (wr != null) {
                try {
                    wr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("error.");
                }
            }
        }
    }
}
