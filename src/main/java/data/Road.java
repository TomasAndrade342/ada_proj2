package data;

public class Road {
    public final int start;
    public final int end;
    public final int duration;

    public Road(int start, int end, int duration) {
        this.start = start;
        this.end = end;
        this.duration = duration;
    }

    public int getStart() {return start;}

    public int getEnd() {return end;}

    public int getDuration() {return duration;}

}
