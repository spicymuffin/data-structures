class SP {
	int INF = 1051;

	public static int min(int a, int b) {
		if (a <= b) {
			return a;
		}
		return b;
	}

	public static int getSPLen(int n, int m, int[] x, int[] y, int[] c, int s, int t) {
		// construct LL representation of graph
		Node[] graph = new Node[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new Node();
		}

		for (int i = 0; i < m; i++) {
			int v0_indx = x[i];
			Node next = graph[v0_indx].next;

			// create new node and link to next
			Node inserting = new Node(y[i], c[i], next);

			// the new header is the one we just created
			graph[v0_indx] = inserting;
		}

		// we will keep track of the set by setting a flag in this array
		boolean[] shortest_path_found_set = new boolean[n];

		int[] d = new int[n];
		int[] prev = new int[n];

		// priority queue that is going to store (V \ S)'s vertices
		int[] priority_queue = new int[n];

		// initial dijkstra setup
		// loop through the elements that we can visit from s
		Node ref_node_s = graph[s];
		Node iter;

		// set all to inf fist
		for (int i = 0; i < n; i++) {
			d[i] = INF;
		}

		// starting vert's d is zero
		d[s] = 0;

		// iterate through edges outgoing from vert indx=s
		iter = ref_node_s;
		while (iter != null) {
			d[iter.v1_indx] = iter.weight;
			iter = iter.next;
		}

		return -1;
	}
}
