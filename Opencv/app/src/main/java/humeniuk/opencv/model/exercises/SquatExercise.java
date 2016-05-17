package humeniuk.opencv.model.exercises;

import humeniuk.opencv.model.Exercise;
import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.Position;
import humeniuk.opencv.utils.ExerciseStateStack;

public class SquatExercise extends Exercise {

    public static final String TAG = "Squat";

    @Override
    public boolean isDone(ExerciseStateStack stack) {
        ExerciseState last = stack.peek();
        ExerciseState preLast = stack.peekFromEnd(1);
        long timeDelta = last.getTime() - preLast.getTime();
        return Position.INITIAL.equals(last.getPosition())
                && Position.SQUAT_BOTTOM.equals(preLast.getPosition())
                && timeDelta >= MIN_TIME_DELTA;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public int statesCount() {
        return 2;
    }
}
