import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CPA3 {

    public static void quicksort_edges(Edge[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int e = l;
        Edge tmp;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].weight < arr[l].weight) {
                e++;
                tmp = arr[i];
                arr[i] = arr[e];
                arr[e] = tmp;
            }
        }
        tmp = arr[l];
        arr[l] = arr[e];
        arr[e] = tmp;
        quicksort_edges(arr, l, e - 1);
        quicksort_edges(arr, e + 1, r);
    }

    public static void main(String[] args) {
        Edge[] edges = new Edge[0];
        String[] param = new String[2];
        int nvert = 0;
        int nedge = 0;
        try {
            BufferedReader rd = new BufferedReader(new FileReader("input.txt"));
            param = rd.readLine().split(" ");
            nvert = Integer.parseInt(param[0]);
            nedge = Integer.parseInt(param[1]);

            edges = new Edge[nedge];

            int weight;
            int vert0;
            int vert1;

            // read in edges: O(nedge)
            for (int i = 0; i < nedge; i++) {
                param = rd.readLine().split(" ");
                vert0 = Integer.parseInt(param[0]);
                vert1 = Integer.parseInt(param[1]);
                weight = Integer.parseInt(param[2]);
                edges[i] = new Edge(weight, vert0, vert1);
                System.out.println(weight);
            }

        } catch (Exception e) {
            System.out.println("oops the program got cancer and died: " + e);
        }

        // init unionfind: O(nedge)
        UnionFind uf = new UnionFind(nvert);
        int spanning_tree_union_name = 0;

        // sort the edges inside edges: O(nedge*log(nedge))
        quicksort_edges(edges, 0, nedge - 1);

        for (int i = 0; i < nedge; i++) {
            System.out.println(edges[i].weight);
        }

        int edges_in_spanning_tree = 0;

        int s1, s2;

        // will store answer
        String[] output_strings = new String[nedge];

        // run kruskal's
        for (int i = 0; i < nedge; i++) {
            if ((s1 = uf.find(edges[i].vert0)) != (s2 = uf.find(edges[i].vert1))) {
                spanning_tree_union_name = uf.union(s1, s2);
                edges_in_spanning_tree++;
                output_strings[i] = edges[i].vert0 + " " + edges[i].vert1 + "\n";
                System.out.println(output_strings[i]);
                System.out.println(nvert - 1 + " " + edges_in_spanning_tree + " " + i);
                if (edges_in_spanning_tree == nvert - 1) {
                    break;
                }
            }
        }

        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("output.txt"));

            if (false) {// if (edges_in_spanning_tree != (nvert - 1)) {
                wr.write("none");
            } else {
                for (int i = 0; i < nedge - 1; i++) {
                    System.out.println("GAY");
                }
            }

            wr.write(output_strings[i]);
            wr.close();
        } catch (Exception e) {
            System.out.println("File IO error probably: " + e);
        }
    }
}
