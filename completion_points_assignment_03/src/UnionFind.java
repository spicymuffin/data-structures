public class UnionFind {
    int[] parent;
    int[] cardinality;
    int size;

    // create a new UF DS
    public UnionFind(int _size) {
        parent = new int[_size];
        cardinality = new int[_size];
        size = _size;

        for (int i = 0; i < size; i++) {
            parent[i] = -1;
            cardinality[i] = 1;
        }
    }

    // find name of set
    public int find(int node) {
        while (parent[node] != -1) {
            // System.out.println("parent of " + Integer.toString(node) + " is " + Integer.toString(parent[node]));
            node = parent[node];
        }
        // System.out.println("returning " + node);
        return node;
    }

    // unite two sets by name
    public int union(int set0, int set1) {
        if (cardinality[set0] <= cardinality[set1]) {
            append(set0, set1);
            cardinality[set1] += cardinality[set0];
            return set1;
        } else {
            append(set1, set0);
            cardinality[set0] += cardinality[set1];
            return set0;
        }
    }

    // appends node0 to node1
    private void append(int node0, int node1) {
        parent[node0] = node1;

    }

    public String integer_padded(int inp, int fieldlen) {
        char[] buf = new char[fieldlen];
        String int_str = Integer.toString(inp);
        int int_len = int_str.length();
        for (int i = 0; i < fieldlen - int_len; i++) {
            buf[i] = ' ';
        }

        for (int i = fieldlen - int_len; i < fieldlen; i++) {
            buf[i] = int_str.charAt(i - (fieldlen - int_len));
        }

        return new String(buf);
    }

    public void debugprint() {
        for (int i = 0; i < size; i++) {
            System.out.print(integer_padded(i, 4));
        }
        System.out.print("\n");
        for (int i = 0; i < size; i++) {
            System.out.print(integer_padded(parent[i], 4));
        }
        System.out.print("\n");
        System.out.print("\n");
    }
}
