package humeniuk.opencv.model;

public class ExerciseState {

    private long time;
    private Position position;

    public ExerciseState(long time, Position position) {
        this.time = time;
        this.position = position;
    }

    public long getTime() {
        return time;
    }

    public Position getPosition() {
        return position;
    }
}
