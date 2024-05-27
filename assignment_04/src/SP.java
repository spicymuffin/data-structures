class SP {
	static int INF = 1051;

	public static int getSPLen(int n, int m, int[] x, int[] y, int[] c, int s, int t) {
		// construct LL representation of graph
		LLNode[] graph = new LLNode[n];
		for (int i = 0; i < n; i++) {
			graph[i] = null;
		}

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

		// create the priority queue
		VertexPriorityQueue pq = new VertexPriorityQueue(n, INF);

		// set source's d to 0
		pq.decrease_key(pq.queue_indx(s), 0);

		// pathfinding data storing arrays
		int[] prev = new int[n];

		while (!pq.is_empty()) {
			// implicitly x is part of S now
			HeapNode min_d_vert = pq.dequeue_min();
			LLNode iter = graph[min_d_vert.indx];

			// cycle through every path from x
			while (iter != null) {
				int dest_vert_indx = iter.v1_indx;
				if (min_d_vert.d + iter.weight < pq.get_vert_ref(dest_vert_indx).d) {
					pq.decrease_key(pq.queue_indx(dest_vert_indx), min_d_vert.d + iter.weight);
					prev[dest_vert_indx] = min_d_vert.indx;
				}
				iter = iter.next;
			}
		}

		if (pq.get_vert_ref(t).d == INF) {
			return -1;
		}

		return pq.get_vert_ref(t).d;
	}
}
