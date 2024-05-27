class SP {
	static int INF = 1051;

	// plain find minimum between two integers
	public static int min(int a, int b) {
		if (a <= b) {
			return a;
		}
		return b;
	}

	// find the index of the minimal in an array given the indices of the elements
	// to consider
	public static int min_indx(HeapNode[] arr, int indx0, int indx1) {
		if (arr[indx0].d <= arr[indx1].d) {
			return indx0;
		}
		return indx1;
	}

	// swap to elements in heap, keep track of indices
	public static void swap(HeapNode[] arr, int[] map, int indx0, int indx1) {
		HeapNode tmp_heapnode;
		tmp_heapnode = arr[indx0];
		arr[indx0] = arr[indx1];
		arr[indx1] = tmp_heapnode;

		int tmp_indx;
		tmp_indx = map[indx0];
		map[indx0] = map[indx1];
		map[indx1] = tmp_indx;
	}

	// priority_queue: priority queue
	// n: length of container (#)
	// a: active elements in the priority queue (#)
	// start: heapify start index
	public static void heapify(HeapNode[] priority_queue, int[] map, int n, int a, int start) {
		int left_child_indx, right_child_indx, min_child_indx;
		int tmp;

		for (;;) {
			left_child_indx = 2 * start + 1;
			right_child_indx = 2 * start + 2;

			// both children arent present
			if (left_child_indx > a - 1) { // adjust a for indices
				return;
			}
			// left child is present, right isnt
			else if (right_child_indx > a - 1) { // adjust a for indices
				// if left child is smaller, it should be higher - set swap target to left child
				min_child_indx = left_child_indx;
			}
			// both children are present
			else {
				// find minimal child's indx
				min_child_indx = min_indx(priority_queue, left_child_indx, right_child_indx);
			}

			// swap if the minimal is smaller than start
			if (priority_queue[start].d > priority_queue[min_child_indx].d) {
				swap(priority_queue, map, start, min_child_indx);

				// set up to traverse further down
				start = min_child_indx;
			}
			// if start was already minmal no changes need to be propagated, return
			else {
				return;
			}
		}
	}

	public static void decrease_key(HeapNode[] priority_queue, int[] map, int n, int a, int indx, int val) {
		priority_queue[indx].d = val;
		int parent_indx = (indx - 1) / 2;
		while (indx != 0 && priority_queue[indx].d < priority_queue[parent_indx].d) {
			swap(priority_queue, map, indx, parent_indx);
			indx = parent_indx;
			parent_indx = (parent_indx - 1) / 2;
		}
	}

	// decrement a after use!
	public static void delete_root(HeapNode[] priority_queue, int[] map, int n, int a) {
		swap(priority_queue, map, 0, a - 1);
		heapify(priority_queue, map, n, a - 1, 0);
	}

	public static void init_priority_queue(HeapNode[] priority_queue, int[] map, int n, int a) {
		for (int i = 0; i < n; i++) {
			map[i] = i;
			priority_queue[i] = new HeapNode(i, -1);
		}

		for (int i = a / 2 - 1; i >= 0; i--) {
			heapify(priority_queue, map, n, a, i);
		}
	}

	public static void print_priority_queue(HeapNode[] priority_queue, int n, int a) {
		for (int i = 0; i < n; i++) {
			System.out.println(i + " " + priority_queue[i].indx + ": " + priority_queue[i].d);
			if (i == a - 1) {
				System.out.println("<----inactive bound---->");
			}
		}
	}

	public static int getSPLen(int n, int m, int[] x, int[] y, int[] c, int s, int t) {
		// construct LL representation of graph
		LLNode[] graph = new LLNode[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new LLNode();
		}

		for (int i = 0; i < m; i++) {
			int v0_indx = x[i];
			LLNode next = graph[v0_indx].next;

			// create new node and link to next
			// CURRENTLY IT RECEIVES INPUT AS A DIRECTED GRAPH
			// TODO: change input receival process
			LLNode inserting = new LLNode(y[i], c[i], next);

			// the new header is the one we just created
			graph[v0_indx] = inserting;
		}

		// we will keep track of the set by setting a flag in a boolean array
		boolean[] shortest_path_tree_set = new boolean[n];

		// pathfinding data storing arrays
		int[] prev = new int[n];

		// priority queue that is going to store (V \ S)'s vertices
		int priority_queue_size = n;
		HeapNode[] priority_queue = new HeapNode[n]; // contains d[]
		int[] priority_queue_indx_map = new int[n]; // reflects the indices that map priority queue to d[]

		// initial dijkstra setup
		LLNode ref_node_s = graph[s];

		// initialize priority queue
		init_priority_queue(priority_queue, priority_queue_indx_map, n, priority_queue_size);

		// set d to inf fist
		for (int i = 0; i < n; i++) {
			priority_queue[priority_queue_indx_map[i]].d = INF;
		}

		// starting vert's d is zero
		decrease_key(priority_queue, priority_queue_indx_map, n, priority_queue_size, s, 0);
		shortest_path_tree_set[s] = true;

		// main loop
		while (priority_queue_size != 0) {
			// choose x from unvisited vertices that minimizes d[]
			int minimal_d_vert_indx = priority_queue[0].indx;
			delete_root(priority_queue, priority_queue_indx_map, n, priority_queue_size);
			priority_queue_size--;

			// add the vertex to the shortest path tree set
			shortest_path_tree_set[minimal_d_vert_indx] = true;

			LLNode iter;
			iter = graph[minimal_d_vert_indx];
			int current_node_indx = minimal_d_vert_indx;

			while (iter != null) {
				if (priority_queue[minimal_d_vert_indx].d
						+ iter.weight <= priority_queue[priority_queue_indx_map[iter.v1_indx]].d) {
					priority_queue[priority_queue_indx_map[iter.v1_indx]].d = priority_queue[minimal_d_vert_indx].d
							+ iter.weight;
					prev[iter.v1_indx] = minimal_d_vert_indx;
				}
				iter = iter.next;

			}
			print_priority_queue(priority_queue, n, priority_queue_size);
			System.out.println("");
		}

		return priority_queue[priority_queue_indx_map[t]].d;
	}
}
