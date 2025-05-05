package dataStructures;

public class Edge implements Comparable<Edge> {
    int weight;
    int firstNode;
    int secondNode;

    public Edge(int firstNode, int secondNode, int weight) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = weight;
    }

    public Edge(int firstNode, int secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = -1;
    }

    public int getWeight() {return weight;}

    public int firstNode() {return firstNode;}

    public int secondNode() {return secondNode;}

    // Pre: node == firstNode || node == secondNode
    public int oppositeNode(int node) {
        return node == firstNode ? firstNode : secondNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return (firstNode == edge.firstNode && secondNode == edge.secondNode) ||
                (firstNode == edge.secondNode && secondNode == edge.firstNode);
    }

    @Override
    public int compareTo(Edge edge) {
        return Integer.compare(this.weight, edge.weight); // ascending order for Kruskal
    }
}
