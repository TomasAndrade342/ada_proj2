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
        this.pq = new PriorityQueue<>();
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

        int hardness = 1;

        Queue<int[]> waiting = new LinkedList<>();
        found[start] = true;

        for (Integer node : graph.adjacentNodes(start)) {
                waiting.add(new int[]{start, node, Math.max(hardness, graph.getWeight(hardness, node))});
                found[node] = true;
        }

        while (!waiting.isEmpty()) {
            int[] curr = waiting.poll();
            if (curr[1] == end) {
                return Math.max(curr[2], graph.getWeight(curr[0], curr[1]));
            }

            for (Integer node : graph.adjacentNodes(curr[1])) {
                if (!found[node]) {
                    waiting.add(new int[]{curr[1], node, Math.max(curr[2], graph.getWeight(curr[1], node))});
                    found[node] = true;
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
