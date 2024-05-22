public class Edge {
    int weight;
    int vert0;
    int vert1;

    public Edge(int _weight, int _vert0, int _vert1) {
        weight = _weight;
        vert0 = _vert0;
        vert1 = _vert1;
    }

    public Edge() {
        weight = 0;
        vert0 = -1;
        vert1 = -1;
    }
}
