package utilFuncs;

import data.Road;

import java.util.Comparator;

public class RoadComparator implements Comparator<Road> {

    public int compare(Road r1, Road r2){
        return Integer.compare(r1.getDuration(), r2.getDuration());
    }

}
