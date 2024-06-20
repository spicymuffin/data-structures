import java.io.*;

class GraphTest {

	public static void main(String[] args) {
		BufferedReader rd = null;
		BufferedWriter wr = null;
		String str;
		int[] x, y, c;
		int n, m, i, pos, pos2;
		int ret;
		long starttime, elapsedtime;

		try {
			// read in the input
			rd = new BufferedReader(new FileReader("graphs/articulation_points_example.txt"));
			str = rd.readLine();
			pos = str.indexOf(" ");
			n = Integer.parseInt(str.substring(0, pos));
			m = Integer.parseInt(str.substring(pos + 1));

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
			GraphAdjacencyList gal = new GraphAdjacencyList(n, m, x, y, c, false);
			GraphAlgo.find_articulation_points(0, gal);
			elapsedtime = System.nanoTime() - starttime;
			System.out.println(Double.toString((double) elapsedtime / 1000000000));

			// write the output
			// wr = new BufferedWriter(new FileWriter("output.txt"));
			// wr.write(Integer.toString(ret));
			// wr.close();
			// wr = null;
		} catch (Exception e) {
			// if the code throws any exception, stack trace will be output and the program
			// will halt
			e.printStackTrace();
			System.out.println("Error.");
		} finally {
			if (rd != null) {
				try {
					rd.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error.");
				}
			}
			if (wr != null) {
				try {
					wr.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error.");
				}
			}
		}
	}
}
