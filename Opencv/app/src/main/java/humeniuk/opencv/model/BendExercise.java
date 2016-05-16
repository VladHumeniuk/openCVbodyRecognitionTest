package humeniuk.opencv.model;

import humeniuk.opencv.utils.ExerciseStateStack;

public class BendExercise extends Exercise {

    public static final String TAG = "Bend";

    @Override
    public boolean isDone(ExerciseStateStack stack) {
        return false;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public int statesCount() {
        return 0;
    }
}
