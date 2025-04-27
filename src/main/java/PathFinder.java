import data.Request;
import data.Road;
import UnionFind.UnionFindInArray;
import utilFuncs.RoadComparator;

import java.util.Arrays;


public class PathFinder {
    private int numLocations;
    private Road[] roads;

    public PathFinder(Road[] inputRoads, int numLocations) {
        this.numLocations = numLocations;
        this.roads = inputRoads;
        Arrays.sort(roads, new RoadComparator());
    }

    public int getSolution(Request req) {

        UnionFindInArray uf = new UnionFindInArray(numLocations);

        for (Road road : roads){
            int start = uf.find(road.getStart());
            int end = uf.find(road.getEnd());

            if(start != end){
                uf.union(start,end);
            }
            if (uf.find(req.getStart()) == uf.find(req.getEnd())){
                return road.getDuration();
            }
        }
        return -1;
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
