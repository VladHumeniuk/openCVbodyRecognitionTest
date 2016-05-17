package humeniuk.opencv;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.Position;
import humeniuk.opencv.utils.ExerciseStateStack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StateStackTest {

    private ExerciseStateStack stack;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        stack = new ExerciseStateStack();
    }

    @Test
    public void testPush() {

        stack.pushState(new ExerciseState(0, Position.SQUAT_BOTTOM));
        assertEquals(1, stack.getSize());
    }

    @Test
    public void testSize() {
        stack.pushState(new ExerciseState(0, Position.SQUAT_BOTTOM));
        stack.pushState(new ExerciseState(0, Position.SQUAT_BOTTOM));
        stack.pushState(new ExerciseState(0, Position.SQUAT_BOTTOM));

        assertEquals(3, stack.getSize());
    }

    @Test
    public void testPeek() {
        ExerciseState state = new ExerciseState(0, Position.SQUAT_BOTTOM);
        stack.pushState(state);
        ExerciseState stackState = stack.peek();

        assertEquals(1, stack.getSize());
        assertEquals(state.getPosition(), stackState.getPosition());
        assertEquals(state.getTime(), stackState.getTime());
    }

    @Test
    public void testPeekFromEnd() {
        ExerciseState state1 = new ExerciseState(0, Position.SQUAT_BOTTOM);
        ExerciseState state2 = new ExerciseState(10, Position.INITIAL);
        stack.pushState(state1);
        stack.pushState(state2);

        ExerciseState stackState1 = stack.peekFromEnd(1);
        ExerciseState stackState2 = stack.peekFromEnd(0);

        assertEquals(2, stack.getSize());
        assertEquals(state1.getPosition(), stackState1.getPosition());
        assertEquals(state1.getTime(), stackState1.getTime());
        assertEquals(state2.getPosition(), stackState2.getPosition());
        assertEquals(state2.getTime(), stackState2.getTime());
    }

    @Test
    public void testPop() {
        ExerciseState state = new ExerciseState(0, Position.SQUAT_BOTTOM);
        stack.pushState(state);
        ExerciseState stackState = stack.pop();

        assertEquals(0, stack.getSize());
        assertEquals(state.getPosition(), stackState.getPosition());
        assertEquals(state.getTime(), stackState.getTime());
    }
}
