// skeleton code -- do not modify!
//
// CCO 2103-01, Spring 2024
//
// PS4

import java.io.*;

class As4 {

    public static void  main(String[] args) {
		BufferedReader  rd = null;
		BufferedWriter  wr = null;
		String 			str;
		int[]           x, y, c;
		int				n, m, s, t, i, pos, pos2;
		int				ret;
		long			starttime, elapsedtime;

		try {
			// read in the input
			rd = new BufferedReader(new FileReader("input2.txt"));
			str = rd.readLine();
			pos = str.indexOf(" ");
			n = Integer.parseInt(str.substring(0, pos));
			m = Integer.parseInt(str.substring(pos + 1));

			str = rd.readLine();
			pos = str.indexOf(" ");
			s = Integer.parseInt(str.substring(0, pos));
			t = Integer.parseInt(str.substring(pos + 1));

			x = new int[m];
			y = new int[m];
			c = new int[m];

			for (i = 0; i < m; i++) {
				str = rd.readLine();
				pos = str.indexOf(" ");
				pos2 = str.indexOf(" ", pos + 1);
				x[i] = Integer.parseInt(str.substring(0, pos));
				y[i] = Integer.parseInt(str.substring(pos + 1, pos2));
				c[i] = Integer.parseInt(str.substring(pos2 + 1));
			}

			rd.close();
			rd = null;

			starttime = System.nanoTime();
			ret = SP.getSPLen(n, m, x, y, c, s, t);
			elapsedtime = System.nanoTime() - starttime;
			System.out.println(Double.toString((double)elapsedtime / 1000000000));

			// write the output
			wr = new BufferedWriter(new FileWriter("output.txt"));
			wr.write(Integer.toString(ret));
			wr.close();
			wr = null;
		}
		catch (Exception e) {
			// if the code throws any exception, stack trace will be output and the program will halt
			e.printStackTrace();
			System.out.println("Error.");
		}
		finally {
			if (rd != null) {
				try {
					rd.close();
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error.");
				}
			}
			if (wr != null) {
				try {
					wr.close();
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error.");
				}
			}
		}
	}
}
