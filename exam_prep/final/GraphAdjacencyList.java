public class GraphAdjacencyList {

	public LLNode[] graph;

	public int nvert;
	public int nedge;

	// n - vertices count
	// m - edges count
	// x - vert0 (from)
	// y - vert1 (to)
	// c - weight

	public GraphAdjacencyList(int n, int m, int[] x, int[] y, int[] c, boolean digraph) {

		nvert = n;
		nedge = m;

		// construct LL representation of graph
		graph = new LLNode[n];
		for (int i = 0; i < n; i++) {
			graph[i] = null;
		}

		if (digraph) {
			for (int i = 0; i < m; i++) {
				int v0_indx = x[i];
				int v1_indx = y[i];

				LLNode next_0 = graph[v0_indx];

				// create new node and link to next
				LLNode inserting_0 = new LLNode(y[i], c[i], next_0);

				// the new header is the one we just created
				graph[v0_indx] = inserting_0;
			}
		} else {
			for (int i = 0; i < m; i++) {
				int v0_indx = x[i];
				int v1_indx = y[i];

				LLNode next_0 = graph[v0_indx];
				LLNode next_1 = graph[v1_indx];

				// create new node and link to next
				// (both ways, graph is undirected)
				LLNode inserting_0 = new LLNode(y[i], c[i], next_0);
				LLNode inserting_1 = new LLNode(x[i], c[i], next_1);

				// the new header is the one we just created
				graph[v0_indx] = inserting_0;
				graph[v1_indx] = inserting_1;
			}
		}
	}
}
