import UnionFind.UnionFind;
import data.Request;
import UnionFind.UnionFindInArray;
import dataStructures.*;

import java.util.*;

/*
 * Authors:
 *  Tomás Fonseca de Andrade, Nº 66196
 *  Paulo André Adriano Aires, Nº 71521
 */
public class PathFinder {
    private final int numLocations;
    private final UnionFind uf;

    private final PriorityQueue<Edge> pq;
    private final LinkedListGraph graph;


    public PathFinder(Edge[] inputRoads, int numLocations) {
        this.numLocations = numLocations;
        this.uf = new UnionFindInArray(numLocations); // Th(1)
        this.pq = new PriorityQueue<>(Arrays.asList(inputRoads)); // O(M)
        this.graph = new LinkedListGraph(numLocations); // O(N)
        obtainMst(); // O(M * log(N))
    }

    /**
     * Obtains the minimum spanning tree ands puts it into graph
     */
    private void obtainMst() {
        int mstFinalSize = numLocations-1;
        int mstSize = 0;
        while (mstSize < mstFinalSize) {
            Edge edge = pq.poll(); // O(log(M))
            int rep1 = uf.find(edge.firstNode()); // O(log(N))
            int rep2 = uf.find(edge.secondNode()); // O(log(N))
            if (rep1 != rep2) {
                graph.addEdge(edge);
                mstSize++;
                uf.union(rep1, rep2);
            }
        }
    }

    public int getSolution(Request req) {
        return getGoodJourneyHardness(req.start(), req.end());
    }

    /**
     * Returns the hardness of the good journeys from start to end
     * @param start - the starting location
     * @param end - the final location
     * @return the good journey hardness
     */
    private int getGoodJourneyHardness(int start, int end) { // O(N)
        boolean[] found = new boolean[numLocations];
        Queue<int[]> waiting = new LinkedList<>(); // [node, maxEdgeWeightSoFar]

        found[start] = true;
        waiting.add(new int[]{start, 0});

        while (!waiting.isEmpty()) {
            int[] curr = waiting.poll();
            int node = curr[0];
            int hardness = curr[1];

            if (node == end) return hardness;

            for (int neighbor : graph.adjacentNodes(node)) {
                if (!found[neighbor]) {
                    found[neighbor] = true;
                    int weight = graph.getWeight(node, neighbor);
                    waiting.add(new int[]{neighbor, Math.max(hardness, weight)});
                }
            }
        }
        return -1; // unreachable
    }

    /**
     * Returns an array with the solutions of the given requests
     * @param requests - the requests to answer
     * @param length - the number of requests
     * @return an array with the solutions of the given requests
     */
    public int[] getSolution(Request[] requests, int length) { //
        int[] res = new int[length];
        int curr = 0;
        for (Request req : requests) {
            res[curr] = getSolution(req);
            curr++;
        }
        return res;
    }
}
