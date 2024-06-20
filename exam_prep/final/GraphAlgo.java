class GraphAlgo {

    public GraphAlgo() {
    }

    public static void dfs(int v, GraphAdjacencyList gal) {
        boolean[] visited = new boolean[gal.nvert]; // array that keeps track which vertices we visted
        for (int i = 0; i < gal.nvert; i++)
            visited[i] = false; // init the array
        do_dfs(v, gal, visited); // dfs main code inside
    }

    private static void do_dfs(int v, GraphAdjacencyList gal, boolean[] visited) {
        visit(v, visited); // perform operation on node
        visited[v] = true; // mark the node as visited
        for (LLNode e = gal.graph[v]; e != null; e = e.next) { // go to next node in adjacency list
                                                               // until the next node doesnt turn out
                                                               // to be null (we reached end of this
                                                               // segment of the tree, we need to backtrack
                                                               // to find new areas of the graph)
            System.out.println("attempting to visit " + e.v1_indx + " from " + v);
            if (!visited[e.v1_indx]) {
                do_dfs(e.v1_indx, gal, visited); // continue dfs-ing down this node only if it was not visited before
            }
        }
    }

    private static void visit(int v, boolean[] visited) {
        System.out.println("visiting " + v);
    }

    // declare as static outside of func to not get cancer
    public static int last_dfn = -1;

    public static void find_articulation_points(int start, GraphAdjacencyList gal) {
        boolean[] visited = new boolean[gal.nvert];
        int[] dfn = new int[gal.nvert];
        int[] low_desc_dfn = new int[gal.nvert];

        // dont forget to reset last_dfn
        last_dfn = -1;

        a_do_dfs(start, gal, visited, dfn, low_desc_dfn, -1);

        // for (int i = 0; i < gal.nvert; i++) {
        // System.out.println(dfn[i]);
        // }
    }

    public static void a_do_dfs(
            int start,
            GraphAdjacencyList gal,
            boolean[] visited,
            int[] dfn,
            int[] low_desc_dfn,
            int prev) {

        LLNode iter = gal.graph[start];
        dfn[start] = ++last_dfn;
        visited[start] = true;
        // System.out.println("dfn: " + last_dfn);
        // System.out.println("visiting: " + start);
        while (iter != null) {
            System.out.println("attempting to visit: " + iter.v1_indx + " from " + start);
            if (!visited[iter.v1_indx]) {
                a_do_dfs(iter.v1_indx, gal, visited, dfn, low_desc_dfn, start);
            } else {
                if (iter.v1_indx != prev) {
                    System.out.println("backedge found: " + start + " to " + iter.v1_indx);
                }
            }
            iter = iter.next;
        }
    }
}