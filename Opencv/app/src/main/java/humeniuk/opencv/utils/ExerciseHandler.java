package humeniuk.opencv.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import humeniuk.opencv.model.BendExercise;
import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.Exercise;
import humeniuk.opencv.model.SquatExercise;
import humeniuk.opencv.model.Training;
import humeniuk.opencv.model.TrainingItem;
import io.realm.Realm;

public class ExerciseHandler {

    private ExerciseStateStack stack;
    private Map<Exercise, Integer> exercises;
    private Training mTraining;

    public ExerciseHandler() {
        stack = new ExerciseStateStack();
        initTraining();
        initExercises();
    }

    private void initExercises() {
        exercises = new HashMap<>();
        exercises.put(new SquatExercise(), 0);
        exercises.put(new BendExercise(), 0);
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
                storeExercise(exercise);
            }
        }
    }

    private void initTraining() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        mTraining = realm.createObject(Training.class);
        mTraining.setId(UUID.randomUUID().toString());
        mTraining.setId(UUID.randomUUID().toString());mTraining.setTime(System.currentTimeMillis());
        realm.commitTransaction();
    }

    private void increaseDoneCount(Exercise exercise) {
        exercises.put(exercise, exercises.get(exercise) + 1);
    }

    private void storeExercise(Exercise exercise) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TrainingItem item = realm.createObject(TrainingItem.class);
        item.setId(UUID.randomUUID().toString());
        item.setTime(System.currentTimeMillis());
        item.setName(exercise.getTag());
        item.setTraining(mTraining);
        realm.commitTransaction();
    }
}
