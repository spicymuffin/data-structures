import org.w3c.dom.Node;

class SP {
	int INF = 1051;

	// plain find minimum between two integers
	public static int min(int a, int b) {
		if (a <= b) {
			return a;
		}
		return b;
	}

	// find the index of the minimal in an array given the indices of the elements
	// to consider
	public static int min_indx(int[] arr, int indx0, int indx1) {
		if (arr[indx0] <= arr[indx1]) {
			return indx0;
		}
		return indx1;
	}

	public static void swap(int[] arr, int indx0, int indx1) {
		int tmp;
		tmp = arr[indx0];
		arr[indx0] = arr[indx1];
		arr[indx1] = tmp;
	}

	// priority_queue: priority queue
	// n: length of container (#)
	// a: active elements in the priority queue (#)
	// start: heapify start index
	public static void heapify(int[] priority_queue, int n, int a, int start) {
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
			if (priority_queue[start] > priority_queue[min_child_indx]) {
				swap(priority_queue, start, min_child_indx);

				// set up to traverse further down
				start = min_child_indx;
			}
			// if start was already minmal no changes need to be propagated, return
			else {
				return;
			}
		}
	}

	public static int init_priority_queue(int[] priority_queue, int n, int a) {
		for (int i = a / 2 - 1; i >= 0; i--) {
			heapify(priority_queue, n, a, i);
		}
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
			// CURRENTLY IT RECEIVES INPUT AS A DIRECTED GRAPH
			// TODO: change input receival process
			Node inserting = new Node(y[i], c[i], next);

			// the new header is the one we just created
			graph[v0_indx] = inserting;
		}

		// we will keep track of the set by setting a flag in a boolean array
		boolean[] shortest_path_found_set = new boolean[n];

		// pathfinding data storing arrays
		int[] dist = new int[n];
		int[] prev = new int[n];

		// priority queue that is going to store (V \ S)'s vertices
		int priority_queue_size = n;
		int[] priority_queue = new int[n]; // reflects d[]
		int[] priority_queue_indx_map = new int[n]; // reflects the indices that map priority queue to d[]

		// initialize priority queue

		// initial dijkstra setup
		Node ref_node_s = graph[s];

		// set all to inf fist
		for (int i = 0; i < n; i++) {
			dist[i] = INF;
		}

		// starting vert's dist is zero
		dist[s] = 0;

		Node iter;
		iter = ref_node_s;
		int current_node_indx = s;

		// main loop
		while (priority_queue_size != 0) {
			// choose x from unvisited vertices that minimizes dist[]
		}

		return -1;
	}
}
