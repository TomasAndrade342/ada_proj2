import data.Request;
import data.Road;

public class PathFinder {
    private int numLocations;
    private int numRoads;

    public PathFinder(Road[] roads, int numLocations, int numRoads) {
        this.numLocations = numLocations;
        this.numRoads = numRoads;
    }

    public void addRoad(int start, int end, int duration) {

    }

    public int getSolution(Request req) {
        return 0;
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
