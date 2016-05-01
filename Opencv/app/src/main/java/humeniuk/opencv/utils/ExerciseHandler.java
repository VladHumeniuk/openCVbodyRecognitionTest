package humeniuk.opencv.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.Exercise;
import humeniuk.opencv.model.SquatExercise;

public class ExerciseHandler {

    private ExerciseStateStack stack;
    private Map<Exercise, Integer> exercises;

    public ExerciseHandler() {
        stack = new ExerciseStateStack();
        initExercises();
    }

    private void initExercises() {
        exercises = new HashMap<>();
        exercises.put(new SquatExercise(), 0);
    }

    public void addExerciseState(ExerciseState state) {
        if (stack.getSize() != 0) {
            ExerciseState last = stack.peek();
            if (last.getPosition().equals(state.getPosition())) {
                stack.pop();
            }
        }
        stack.pushState(state);
        Log.d("gotcha", state.getPosition().name() + " " + state.getTime());
        for (Exercise exercise : exercises.keySet()) {
            if (exercise.isDone(stack)) {
                stack.remove(exercise.statesCount());
                increaseDoneCount(exercise);
            }
        }
    }

    private void increaseDoneCount(Exercise exercise) {
        exercises.put(exercise, exercises.get(exercise) + 1);
        //TODO store somewhere or smth
    }
}
