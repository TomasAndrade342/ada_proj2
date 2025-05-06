package dataStructures;

import java.util.ArrayList;
// import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Authors:
 *  Tomás Fonseca de Andrade, Nº 66196
 *  Paulo André Adriano Aires, Nº 71521
 */
public class LinkedListGraph {
    List<LinkedList<Edge>> graph;
    int numNodes;
    int numEdges;

    public LinkedListGraph(int numNodes) {
        this.graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new LinkedList<>());
        }
        this.numNodes = numNodes;
        this.numEdges = 0;
    }

    /*
    public int numNodes() {return numNodes;}

    public int numEdges() {return numEdges;}
    */

    /**
     * Adds a new edge to the graph. Since it's an undirected graph, a new edge is added to both nodes.
     * @param edge - the edge to add
     */
    public void addEdge(Edge edge) {
        graph.get(edge.firstNode).add(edge);
        graph.get(edge.secondNode).add(new Edge(edge.secondNode, edge.firstNode, edge.weight));
    }

    /*
    public void addEdge(int node1, int node2, int weight) {
        addEdge(new Edge(node1, node2, weight));
    }

    public boolean edgeExists(int node1, int node2) {
        return graph.get(node1).contains(new Edge(node1, node2));
    }
    */

    /*
    // Pre: edgeExists(node1, node2)
    public int getWeight(int node1, int node2) {
        for (Edge edge : graph.get(node1)) {
            if (edge.secondNode == node2) {
                return edge.getWeight();
            }
        }
        return -1; // unreachable
    }
    */

    /*
    public int degree(int node) {
        return graph.get(node).size();
    }
    */

    /*
    public List<Integer> adjacentNodes(int node) {
        List<Integer> res = new ArrayList<>();
        for (Edge curr : graph.get(node)) {
            res.add(curr.secondNode);
        }
        return res;
    }
    */

    /**
     * Returns a list with all edges the given node is connected to.
     * @param node - the node being queried
     * @return the list of edges
     */
    public List<Edge> incidentEdges(int node) {
        return graph.get(node);
    }

}
