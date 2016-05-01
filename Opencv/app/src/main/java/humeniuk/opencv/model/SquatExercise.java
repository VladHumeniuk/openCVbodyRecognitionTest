package humeniuk.opencv.model;

import humeniuk.opencv.utils.ExerciseStateStack;

public class SquatExercise extends Exercise {

    @Override
    public boolean isDone(ExerciseStateStack stack) {
        ExerciseState last = stack.peek();
        ExerciseState preLast = stack.peekFromEnd(1);
        long timeDelta = last.getTime() - preLast.getTime();
        return Position.SQUAT_BOTTOM.equals(last.getPosition())
                && Position.INITIAL.equals(preLast.getPosition())
                && timeDelta >= MIN_TIME_DELTA;
    }

    @Override
    public String getTag() {
        return "Squat"; //TODO make const or smth
    }

    @Override
    public int statesCount() {
        return 2;
    }
}
