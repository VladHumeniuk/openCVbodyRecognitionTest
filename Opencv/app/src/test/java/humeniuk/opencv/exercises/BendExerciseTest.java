package humeniuk.opencv.exercises;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import humeniuk.opencv.model.Exercise;
import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.exercises.BendExercise;
import humeniuk.opencv.utils.ExerciseStateStack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BendExerciseTest {

    private Exercise exercise;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        exercise = new BendExercise();
    }

    @Test
    public void testBendExercisePositive() {
        ExerciseStateStack stack = new ExerciseStateStack();
        stack.pushState(new ExerciseState(0, Position.BEND_BOTTOM));
        stack.pushState(new ExerciseState(1000, Position.INITIAL));

        assertTrue(exercise.isDone(stack));
    }

    @Test
    public void testBendExerciseTimeSpanFail() {
        ExerciseStateStack stack = new ExerciseStateStack();
        stack.pushState(new ExerciseState(0, Position.BEND_BOTTOM));
        stack.pushState(new ExerciseState(10, Position.INITIAL));

        assertFalse(exercise.isDone(stack));
    }

    @Test
    public void testBendExerciseWrongPositions() {
        ExerciseStateStack stack = new ExerciseStateStack();
        stack.pushState(new ExerciseState(0, Position.SQUAT_BOTTOM));
        stack.pushState(new ExerciseState(1000, Position.BEND_BOTTOM));

        assertFalse(exercise.isDone(stack));
    }
}
