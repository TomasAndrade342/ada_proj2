import UnionFind.UnionFind;
import data.Request;
import UnionFind.UnionFindInArray;
import dataStructures.*;

import java.util.*;


public class PathFinder {
    private final int numLocations;
    private final UnionFind uf;

    private final PriorityQueue<Edge> pq;
    private final LinkedListGraph graph;


    public PathFinder(Edge[] inputRoads, int numLocations) {
        this.numLocations = numLocations;
        this.uf = new UnionFindInArray(numLocations);
        this.pq = new PriorityQueue<>(Arrays.asList(inputRoads));
        this.graph = new LinkedListGraph(numLocations);
        pq.addAll(Arrays.asList(inputRoads));
        executeKruskal();
    }

    private void executeKruskal() {
        int mstFinalSize = numLocations-1;
        int mstSize = 0;
        while (mstSize < mstFinalSize) {
            Edge edge = pq.poll();
            int rep1 = uf.find(edge.firstNode());
            int rep2 = uf.find(edge.secondNode());
            if (rep1 != rep2) {
                graph.addEdge(edge);
                mstSize++;
                uf.union(rep1, rep2);
            }
        }
    }

    public int getSolution(Request req) {
        return dfs(req.getStart(), req.getEnd());
    }

    private int dfs(int start, int end) {
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

    public int[] getSolution(Request[] requests, int length) {
        int[] res = new int[length];
        int curr = 0;
        for (Request req : requests) {
            res[curr] = getSolution(req);
            curr++;
        }
        return res;
    }
}
