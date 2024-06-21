import java.util.Arrays;

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

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static boolean[] find_articulation_points(GraphAdjacencyList gal) {
        boolean[] visited = new boolean[gal.nvert];
        int[] dfn = new int[gal.nvert];
        int[] low = new int[gal.nvert];
        int[] parent = new int[gal.nvert];
        int[] children = new int[gal.nvert];
        boolean[] ap = new boolean[gal.nvert];
        int[] time = { 0 };

        Arrays.fill(dfn, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        for (int i = 0; i < gal.nvert; i++) {
            if (!visited[i]) {
                dfs_util(i, gal, visited, dfn, low, parent, children, time, ap);
            }
        }

        return ap;
    }

    public static void dfs_util(int v, GraphAdjacencyList gal, boolean[] visited, int[] dfn, int[] low, int[] parent,
            int[] children, int[] time, boolean[] ap) {

        // standard dfs shenanigans
        visited[v] = true;

        // mutable time thing
        dfn[v] = low[v] = time[0]++;
        LLNode iter = gal.graph[v];

        while (iter != null) {
            // set child
            int child = iter.v1_indx;

            // standard dfn shenanigans
            if (!visited[child]) {
                // set parent of child in the dfs tree
                parent[child] = v;
                // increase the count of children since we just found one
                children[v]++;

                // run dfs to expand tree further
                dfs_util(child, gal, visited, dfn, low, parent, children, time, ap);

                // low of unvisited is just the min between the dfs caller and low of the child
                low[v] = min(low[v], low[child]);

                // 1. if caller is root and root has more than two children then it is an ap
                // 2. if caller is non root and low of child is bigger or equal then caller's
                // low then caller is an ap
                if ((parent[v] != -1 && low[child] >= dfn[v]) || (parent[v] == -1 && children[v] > 1)) {
                    System.out.println("ap: " + v);
                    ap[v] = true;
                }
            }
            // if we already visted it - backedge found
            // additionally, dont consider the parent of child, bc naturally we are going to check the edge that we came from
            else if (child != parent[v]) {
                System.out.println("found backedge: " + v + " to " + child);
                low[v] = min(low[v], dfn[child]);
            }

            // we want to see next neighbor, so step the iterator
            iter = iter.next;
        }
    }
}