package humeniuk.opencv.model;

import humeniuk.opencv.utils.ExerciseStateStack;

public abstract class Exercise {

    protected static final long MIN_TIME_DELTA = 300;

    public abstract boolean isDone(ExerciseStateStack stack);
    public abstract String getTag();
    public abstract int statesCount();

    @Override
    public int hashCode() {
        return getTag().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return getTag().equals(((Exercise) o).getTag());
    }
}
