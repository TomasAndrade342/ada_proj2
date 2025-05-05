import data.Request;
import dataStructures.Edge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static utilFuncs.Utilities.*;

/*
 * Authors:
 *  Tomás Fonseca de Andrade, Nº 66196
 *  Paulo André Adriano Aires, Nº 71521
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = stringArrToIntArr(in.readLine().split(" "), 2);
        int numLocations = firstLine[0];
        int numRoads = firstLine[1];

        Edge[] roads = new Edge[numRoads];

        for (int i = 0; i < numRoads; i++) {
            int[] currRoadLine = getIntArrFromLine(in.readLine(), 3);
            int start = currRoadLine[0];
            int end = currRoadLine[1];
            int duration = currRoadLine[2];
            roads[i] = new Edge(start, end, duration);
        }

        PathFinder pf = new PathFinder(roads, numLocations);

        int numRequests = Integer.parseInt(in.readLine());
        Request[] requests = new Request[numRequests];

        for (int i = 0; i < numRequests; i++) {
            int[] currRoadLine = getIntArrFromLine(in.readLine(), 2);
            int start = currRoadLine[0];
            int end = currRoadLine[1];
            requests[i] = new Request(start, end);
        }

        int[] res = pf.getSolution(requests, numRequests);
        for (int i = 0; i < numRequests; i++) {
            System.out.println(res[i]);
        }
    }
}

